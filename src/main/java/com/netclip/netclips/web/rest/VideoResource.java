package com.netclip.netclips.web.rest;

import com.amazonaws.Response;
import com.netclip.netclips.domain.User;
import com.netclip.netclips.domain.Video;
import com.netclip.netclips.domain.VideoUser;
import com.netclip.netclips.repository.VideoRepository;
import com.netclip.netclips.repository.VideoUserRepository;
import com.netclip.netclips.security.AuthoritiesConstants;
import com.netclip.netclips.service.S3Service;
import com.netclip.netclips.service.VideoService;
import com.netclip.netclips.service.dto.UploadDTO;
import com.netclip.netclips.service.dto.VideoDTO;
import com.netclip.netclips.service.impl.VideoUserServiceImpl;
import com.netclip.netclips.service.mapper.VideoMapper;
import com.netclip.netclips.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.netclip.netclips.domain.Video}.
 */
@RestController
@RequestMapping("/api")
public class VideoResource {

    private final Logger log = LoggerFactory.getLogger(VideoResource.class);

    private static final String ENTITY_NAME = "video";

    @Autowired
    private final S3Service s3Service;

    @Autowired
    private final VideoUserRepository videoUserRepository;

    @Autowired
    private final VideoUserServiceImpl videoUserService;

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VideoService videoService;

    private final VideoRepository videoRepository;

    private final VideoMapper videoMapper;

    public VideoResource(
        VideoService videoService,
        VideoRepository videoRepository,
        VideoUserRepository videoUserRepository,
        S3Service s3Service,
        VideoUserServiceImpl videoUserService,
        VideoMapper videoMapper
    ) {
        this.videoService = videoService;
        this.videoRepository = videoRepository;
        this.videoUserRepository = videoUserRepository;
        this.s3Service = s3Service;
        this.videoUserService = videoUserService;
        this.videoMapper = videoMapper;
    }

    //    @GetMapping("/testUser")
    //    public ResponseEntity<String> testPrincipal(Authentication authentication) {
    //        return new ResponseEntity<>(authentication.toString(), HttpStatus.OK);
    //    }

    @PostMapping("/videos/upload")
    @PreAuthorize("hasAuthority(\"" + AuthoritiesConstants.USER + "\")")
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ResponseEntity<UploadDTO> uploadVideo(
        @RequestPart(value = "file") MultipartFile file,
        Authentication auth,
        @RequestParam(name = "title", required = false) String title,
        @RequestParam(name = "description", required = false) String description
    ) {
        try {
            Optional<VideoUser> vidUser = videoUserRepository.findByInternalUser_Login(auth.getName());
            if (vidUser.isEmpty() || !vidUser.get().getInternalUser().isActivated()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            String fileRef = s3Service.uploadFile(file);
            UploadDTO upDTO = new UploadDTO.UploadDTOBuilder()
                .userId(vidUser.get().getId())
                .userLogin(vidUser.get().getInternalUser().getLogin())
                .uploadedFilePath(fileRef)
                .title(title)
                .description(description)
                .build();

            Video videoEntity = s3Service.convertUploadDTOtoVideo(upDTO);
            videoRepository.save(videoEntity);

            vidUser.get().addVideos(videoEntity);
            videoUserRepository.save(vidUser.get());

            return new ResponseEntity<>(upDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/videos/delete")
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ResponseEntity<String> deleteVideo(@RequestParam Long id, Authentication auth) {
        Optional<Video> videoRes = videoRepository.findById(id);
        Optional<VideoUser> videoUser = videoUserRepository.findByInternalUser_Login(auth.getName());
        if (videoUser.isEmpty() || videoRes.isEmpty()) {
            return new ResponseEntity<>("Invalid video response", HttpStatus.BAD_REQUEST);
        }
        if (!videoRes.get().getUploader().getInternalUser().getLogin().equals(auth.getName())) {
            return new ResponseEntity<>("You must be the video uploader or have admin privileges", HttpStatus.UNAUTHORIZED);
        }
        Video video = videoRes.get();
        VideoUser user = videoUser.get();

        String[] pathSplit = video.getContentRef().split("/");
        String fileName = pathSplit[pathSplit.length - 1];

        s3Service.deleteFile(fileName);
        videoService.delete(video.getId());
        videoUserService.deleteVideoFromSet(user, video);
        videoUserRepository.save(user);

        return new ResponseEntity<>(video.toString(), HttpStatus.OK);
    }

    /**
     * {@code POST  /videos} : Create a new video.
     *
     * @param video the video to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new video, or with status {@code 400 (Bad Request)} if the video has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/videos")
    public ResponseEntity<Video> createVideo(@RequestBody Video video) throws URISyntaxException {
        log.debug("REST request to save Video : {}", video);
        if (video.getId() != null) {
            throw new BadRequestAlertException("A new video cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Video result = videoService.save(video);
        return ResponseEntity
            .created(new URI("/api/videos/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @GetMapping("/videos/fetch-play-video/{id}")
    public ResponseEntity<VideoDTO> fetchPlayableVideo(@PathVariable("id") Long id) {
        log.debug("REST request to fetch a playable Video DTO: {}", id);
        Optional<Video> videoRes = videoRepository.findById(id);
        if (videoRes.isEmpty()) {
            throw new BadRequestAlertException("Video by ID not found", ENTITY_NAME, "videonotfound");
        }
        VideoDTO videoDTO = videoMapper.videoToVideoDTO(videoRes.get());
        videoDTO.setPreSignedUrl(s3Service.generatePresignedUrl(videoDTO.getContentKey()));
        return ResponseEntity.ok(videoDTO);
    }

    /**
     * {@code PUT  /videos/:id} : Updates an existing video.
     *
     * @param id the id of the video to save.
     * @param video the video to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated video,
     * or with status {@code 400 (Bad Request)} if the video is not valid,
     * or with status {@code 500 (Internal Server Error)} if the video couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/videos/{id}")
    public ResponseEntity<Video> updateVideo(@PathVariable(value = "id", required = false) final Long id, @RequestBody Video video)
        throws URISyntaxException {
        log.debug("REST request to update Video : {}, {}", id, video);
        if (video.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, video.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!videoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Video result = videoService.update(video);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, video.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /videos/:id} : Partial updates given fields of an existing video, field will ignore if it is null
     *
     * @param id the id of the video to save.
     * @param video the video to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated video,
     * or with status {@code 400 (Bad Request)} if the video is not valid,
     * or with status {@code 404 (Not Found)} if the video is not found,
     * or with status {@code 500 (Internal Server Error)} if the video couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/videos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Video> partialUpdateVideo(@PathVariable(value = "id", required = false) final Long id, @RequestBody Video video)
        throws URISyntaxException {
        log.debug("REST request to partial update Video partially : {}, {}", id, video);
        if (video.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, video.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!videoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Video> result = videoService.partialUpdate(video);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, video.getId().toString())
        );
    }

    /**
     * {@code GET  /videos} : get all the videos.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of videos in body.
     */
    @GetMapping("/videos")
    public ResponseEntity<List<Video>> getAllVideos(@org.springdoc.api.annotations.ParameterObject Pageable pageable) {
        log.debug("REST request to get a page of Videos");
        Page<Video> page = videoService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /videos/:id} : get the "id" video.
     *
     * @param id the id of the video to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the video, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/videos/{id}")
    public ResponseEntity<Video> getVideo(@PathVariable Long id) {
        log.debug("REST request to get Video : {}", id);
        Optional<Video> video = videoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(video);
    }
    /**
     * {@code DELETE  /videos/:id} : delete the "id" video.
     *
     * @param id the id of the video to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    //    @DeleteMapping("/videos/{id}")
    //    public ResponseEntity<Void> deleteVideo(@PathVariable Long id) {
    //        log.debug("REST request to delete Video : {}", id);
    //        videoService.delete(id);
    //        return ResponseEntity
    //            .noContent()
    //            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
    //            .build();
    //    }
}
