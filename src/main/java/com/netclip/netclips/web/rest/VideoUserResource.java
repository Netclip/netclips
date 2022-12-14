package com.netclip.netclips.web.rest;

import com.netclip.netclips.domain.Comment;
import com.netclip.netclips.domain.Video;
import com.netclip.netclips.domain.VideoUser;
import com.netclip.netclips.repository.VideoUserRepository;
import com.netclip.netclips.service.VideoService;
import com.netclip.netclips.service.VideoUserService;
import com.netclip.netclips.service.dto.VideoPreviewDTO;
import com.netclip.netclips.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.PaginationUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.netclip.netclips.domain.VideoUser}.
 */
@RestController
@RequestMapping("/api")
public class VideoUserResource {

    private final Logger log = LoggerFactory.getLogger(VideoUserResource.class);

    private static final String ENTITY_NAME = "videoUser";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VideoService videoService;

    private final VideoUserService videoUserService;

    private final VideoUserRepository videoUserRepository;

    public VideoUserResource(VideoUserService videoUserService, VideoUserRepository videoUserRepository, VideoService videoService) {
        this.videoUserService = videoUserService;
        this.videoUserRepository = videoUserRepository;
        this.videoService = videoService;
    }

    /**
     * {@code POST  /video-users} : Create a new videoUser.
     *
     * @param videoUser the videoUser to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new videoUser, or with status {@code 400 (Bad Request)} if the videoUser has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/video-users")
    public ResponseEntity<VideoUser> createVideoUser(@RequestBody VideoUser videoUser) throws URISyntaxException {
        log.debug("REST request to save VideoUser : {}", videoUser);
        if (videoUser.getId() != null) {
            throw new BadRequestAlertException("A new videoUser cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VideoUser result = videoUserService.save(videoUser);
        return ResponseEntity
            .created(new URI("/api/video-users/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @GetMapping("/video-user/{id}/videos")
    @Transactional
    public ResponseEntity<Set<Video>> getUserVideos(@PathVariable Long id) {
        log.debug("REST request to get user videos : {}", id);
        Optional<VideoUser> userRes = videoUserRepository.findByInternalUser_Id(id);
        if (userRes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userRes.get().getVideos(), HttpStatus.OK);
    }

    @GetMapping("/video-user/video-previews")
    public ResponseEntity<Page<VideoPreviewDTO>> getUserVideoPreviews(
        @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
        @RequestParam(value = "user_id") Long userId
    ) {
        log.debug("REST request to get a page of Videos");
        Page<VideoPreviewDTO> page = videoService.getVideoPreviewsByUploader(pageNo, pageSize, sortBy, userId);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page);
    }

    @GetMapping("/video-user/{id}/comments")
    @Transactional
    public ResponseEntity<Set<Comment>> getUserComment(@PathVariable Long id) {
        log.debug("REST request to get user videos : {}", id);
        Optional<VideoUser> userRes = videoUserRepository.findByInternalUser_Id(id);
        if (userRes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(userRes.get().getComments(), HttpStatus.OK);
    }

    /**
     * {@code PUT  /video-users/:id} : Updates an existing videoUser.
     *
     * @param id the id of the videoUser to save.
     * @param videoUser the videoUser to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated videoUser,
     * or with status {@code 400 (Bad Request)} if the videoUser is not valid,
     * or with status {@code 500 (Internal Server Error)} if the videoUser couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/video-users/{id}")
    public ResponseEntity<VideoUser> updateVideoUser(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VideoUser videoUser
    ) throws URISyntaxException {
        log.debug("REST request to update VideoUser : {}, {}", id, videoUser);
        if (videoUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, videoUser.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!videoUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        VideoUser result = videoUserService.update(videoUser);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, videoUser.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /video-users/:id} : Partial updates given fields of an existing videoUser, field will ignore if it is null
     *
     * @param id the id of the videoUser to save.
     * @param videoUser the videoUser to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated videoUser,
     * or with status {@code 400 (Bad Request)} if the videoUser is not valid,
     * or with status {@code 404 (Not Found)} if the videoUser is not found,
     * or with status {@code 500 (Internal Server Error)} if the videoUser couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/video-users/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<VideoUser> partialUpdateVideoUser(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody VideoUser videoUser
    ) throws URISyntaxException {
        log.debug("REST request to partial update VideoUser partially : {}, {}", id, videoUser);
        if (videoUser.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, videoUser.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!videoUserRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<VideoUser> result = videoUserService.partialUpdate(videoUser);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, videoUser.getId().toString())
        );
    }

    /**
     * {@code GET  /video-users} : get all the videoUsers.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of videoUsers in body.
     */
    @GetMapping("/video-users")
    public List<VideoUser> getAllVideoUsers(@RequestParam(required = false, defaultValue = "false") boolean eagerload) {
        log.debug("REST request to get all VideoUsers");
        return videoUserService.findAll();
    }

    /**
     * {@code GET  /video-users/:id} : get the "id" videoUser.
     *
     * @param id the id of the videoUser to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the videoUser, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/video-users/{id}")
    public ResponseEntity<VideoUser> getVideoUser(@PathVariable Long id) {
        log.debug("REST request to get VideoUser : {}", id);
        Optional<VideoUser> videoUser = videoUserService.findOne(id);
        return ResponseUtil.wrapOrNotFound(videoUser);
    }

    /**
     * {@code DELETE  /video-users/:id} : delete the "id" videoUser.
     *
     * @param id the id of the videoUser to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/video-users/{id}")
    public ResponseEntity<Void> deleteVideoUser(@PathVariable Long id) {
        log.debug("REST request to delete VideoUser : {}", id);
        videoUserService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
