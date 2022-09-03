package com.netclip.netclips.service;

import com.netclip.netclips.domain.Video;
import com.netclip.netclips.domain.VideoUser;
import java.util.List;
import java.util.Optional;

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
     * Get the "id" videoUser.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<VideoUser> findOne(Long id);

    VideoUser deleteVideoFromSet(VideoUser videoUser, Video video);

    Optional<VideoUser> findByUserLogin(String login);

    /**
     * Delete the "id" videoUser.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
