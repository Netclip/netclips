package com.netclip.netclips.service;

import com.netclip.netclips.domain.Video;
import com.netclip.netclips.domain.VideoUser;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link VideoUser}.
 */
public interface VideoUserService {
    /**
     * Save a videoUser.
     *
     * @param videoUser the entity to save.
     * @return the persisted entity.
     */
    VideoUser save(VideoUser videoUser);

    /**
     * Updates a videoUser.
     *
     * @param videoUser the entity to update.
     * @return the persisted entity.
     */
    VideoUser update(VideoUser videoUser);

    /**
     * Partially updates a videoUser.
     *
     * @param videoUser the entity to update partially.
     * @return the persisted entity.
     */
    Optional<VideoUser> partialUpdate(VideoUser videoUser);

    /**
     * Get all the videoUsers.
     *
     * @return the list of entities.
     */
    List<VideoUser> findAll();

    /**
     * Get all the videoUsers with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<VideoUser> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" videoUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VideoUser> findOne(Long id);

    VideoUser deleteVideoFromSet(VideoUser videoUser, Video video);

    VideoUser addLikedVideo(VideoUser videoUser, Video video);

    VideoUser addDislikedVideo(VideoUser videoUser, Video video);

    boolean isLikedVideo(VideoUser videoUser, Video video);

    boolean isDislikedVideo(VideoUser videoUser, Video video);

    VideoUser removeLikedVideo(VideoUser videoUser, Video video);

    VideoUser removeDislikedVideo(VideoUser videoUser, Video video);

    Optional<VideoUser> findByUserLogin(String login);

    /**
     * Delete the "id" videoUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
