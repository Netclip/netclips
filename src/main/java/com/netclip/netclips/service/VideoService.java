package com.netclip.netclips.service;

import com.netclip.netclips.domain.Comment;
import com.netclip.netclips.domain.User;
import com.netclip.netclips.domain.Video;
import com.netclip.netclips.domain.VideoUser;
import com.netclip.netclips.service.dto.VideoDTO;
import com.netclip.netclips.service.dto.VideoPreviewDTO;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Interface for managing {@link Video}.
 */
public interface VideoService {
    /**
     * Save a video.
     *
     * @param video the entity to save.
     * @return the persisted entity.
     */
    Video save(Video video);

    /**
     * Updates a video.
     *
     * @param video the entity to update.
     * @return the persisted entity.
     */
    Video update(Video video);

    /**
     * Partially updates a video.
     *
     * @param video the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Video> partialUpdate(Video video);

    /**
     * Get all the videos.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Video> findAll(Pageable pageable);

    /**
     * Get the "id" video.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Video> findOne(Long id);

    /**
     *
     * @param contentRef the key of the S3 file
     * @return the entity
     */
    Optional<Video> findVideoByContentKey(String contentRef);

    Video getVideoById(Long videoId);

    Video updateVideoComment(Comment comment, Video video);

    Video uploadThumbnail(Video video, MultipartFile file);

    Page<VideoPreviewDTO> getVideoPreviews(int pageNo, int pageSize, String sortBy);

    Page<VideoPreviewDTO> getVideoPreviewsByUploader(int pageNo, int pageSize, String sortBy, Long uploaderId);

    VideoPreviewDTO videoToPreviewDTOWithPresignedThumbnail(Video video);

    VideoDTO likeVideo(Video video, VideoUser user);

    VideoDTO dislikeVideo(Video video, VideoUser user);

    void incrementViewCount(Long id);

    /**
     * Delete the "id" video.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
