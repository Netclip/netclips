package com.netclip.netclips.web.rest;

import com.amazonaws.Response;
import com.netclip.netclips.domain.Comment;
import com.netclip.netclips.domain.Video;
import com.netclip.netclips.domain.VideoUser;
import com.netclip.netclips.repository.CommentRepository;
import com.netclip.netclips.repository.VideoRepository;
import com.netclip.netclips.service.CommentService;
import com.netclip.netclips.service.VideoService;
import com.netclip.netclips.service.VideoUserService;
import com.netclip.netclips.service.dto.CommentDTO;
import com.netclip.netclips.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.netclip.netclips.domain.Comment}.
 */
@RestController
@RequestMapping("/api")
public class CommentResource {

    private final Logger log = LoggerFactory.getLogger(CommentResource.class);

    private static final String ENTITY_NAME = "comment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CommentService commentService;

    private final CommentRepository commentRepository;

    private final VideoRepository videoRepository;

    private final VideoService videoService;

    private final VideoUserService videoUserService;

    public CommentResource(
        CommentService commentService,
        CommentRepository commentRepository,
        VideoRepository videoRepository,
        VideoService videoService,
        VideoUserService videoUserService
    ) {
        this.commentService = commentService;
        this.commentRepository = commentRepository;
        this.videoRepository = videoRepository;
        this.videoService = videoService;
        this.videoUserService = videoUserService;
    }

    /**
     * {@code POST  /comments} : Create a new comment.
     *
     * @param comment the comment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new comment, or with status {@code 400 (Bad Request)} if the comment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/comments")
    @Transactional(readOnly = true)
    public ResponseEntity<Comment> createComment(@RequestBody Comment comment, @RequestParam(name = "video_id") Long videoId)
        throws URISyntaxException {
        log.debug("REST request to save Comment : {}", comment);
        if (comment.getId() != null) {
            throw new BadRequestAlertException("A new comment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Optional<Video> videoRes = videoRepository.findById(videoId);
        if (videoRes.isEmpty()) {
            throw new BadRequestAlertException("Video cannot be found", ENTITY_NAME, "vidnotfound");
        }

        log.debug("Saving comment to video id {}", videoRes.get().getId());
        videoService.updateVideoComment(comment, videoRes.get());

        Comment result = commentService.save(comment);
        return ResponseEntity
            .created(new URI("/api/comments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PostMapping("/comments/post")
    @Transactional(readOnly = true)
    public ResponseEntity<Comment> postComment(
        @RequestParam(name = "video_id") Long videoId,
        @RequestParam(name = "content") String content,
        Authentication auth
    ) throws URISyntaxException {
        log.debug("REST request to post new Comment on video: {}", videoId);
        Optional<Video> videoRes = videoRepository.findById(videoId);
        Optional<VideoUser> videoUserRes = videoUserService.findByUserLogin(auth.getName());
        if (videoRes.isEmpty()) {
            throw new BadRequestAlertException("Video cannot be found", ENTITY_NAME, "vidnotfound");
        }
        if (videoUserRes.isEmpty()) {
            throw new BadRequestAlertException("User cannot be found", ENTITY_NAME, "usernotfound");
        }
        Comment comment = new Comment().content(content).timeStamp(Instant.now()).videoUser(videoUserRes.get()).video(videoRes.get());
        log.debug("Saving comment to video id {}", videoRes.get().getId());
        videoService.updateVideoComment(comment, videoRes.get());
        Comment result = commentService.save(comment);

        return ResponseEntity
            .created(new URI("/api/comments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @GetMapping("/comments/video")
    public ResponseEntity<Page<CommentDTO>> getCommentsById(
        @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
        @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize,
        @RequestParam(value = "sortBy", defaultValue = "id") String sortBy,
        @RequestParam(name = "video_id") Long id
    ) {
        Page<CommentDTO> res = commentService.getAllByVideo(id, pageNo, pageSize, sortBy).map(commentService::CommentToCommentDTO);

        return ResponseEntity.ok(res);
    }

    /**
     * {@code PUT  /comments/:id} : Updates an existing comment.
     *
     * @param commentId the id of the comment to save.
     * @param comment the comment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated comment,
     * or with status {@code 400 (Bad Request)} if the comment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the comment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     **/
    @PutMapping("/comments/{id}")
    public ResponseEntity<Comment> updateComment(
        @PathVariable(value = "id", required = false) final Long commentId,
        @RequestBody Comment comment,
        @RequestParam(name = "video_id") Long videoId
    ) throws URISyntaxException {
        log.debug("REST request to update Comment : {}, {}", commentId, comment);
        Optional<Video> videoRes = videoRepository.findById(videoId);
        if (comment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(commentId, comment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!commentRepository.existsById(commentId)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }
        if (videoRes.isEmpty()) {
            throw new BadRequestAlertException("Video cannot be found", ENTITY_NAME, "vidnotfound");
        }

        videoService.updateVideoComment(comment, videoRes.get());

        Comment result = commentService.update(comment);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, comment.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /comments/:id} : Partial updates given fields of an existing comment, field will ignore if it is null
     *
     * @param id the id of the comment to save.
     * @param comment the comment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated comment,
     * or with status {@code 400 (Bad Request)} if the comment is not valid,
     * or with status {@code 404 (Not Found)} if the comment is not found,
     * or with status {@code 500 (Internal Server Error)} if the comment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/comments/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Comment> partialUpdateComment(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Comment comment
    ) throws URISyntaxException {
        log.debug("REST request to partial update Comment partially : {}, {}", id, comment);
        if (comment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, comment.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!commentRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Comment> result = commentService.partialUpdate(comment);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, comment.getId().toString())
        );
    }

    /**
     * {@code GET  /comments} : get all the comments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of comments in body.
     */
    @GetMapping("/comments")
    public List<Comment> getAllComments() {
        log.debug("REST request to get all Comments");
        return commentService.findAll();
    }

    /**
     * {@code GET  /comments/:id} : get the "id" comment.
     *
     * @param id the id of the comment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the comment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getComment(@PathVariable Long id) {
        log.debug("REST request to get Comment : {}", id);
        Optional<Comment> comment = commentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(comment);
    }

    /**
     * {@code DELETE  /comments/:id} : delete the "id" comment.
     *
     * @param id the id of the comment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id) {
        log.debug("REST request to delete Comment : {}", id);
        commentService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString()))
            .build();
    }
}
