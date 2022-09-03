package com.netclip.netclips.service;

import com.netclip.netclips.domain.Comment;
import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link Comment}.
 */
public interface CommentService {
    /**
     * Save a comment.
     *
     * @param comment the entity to save.
     * @return the persisted entity.
     */
    Comment save(Comment comment);

    List<Comment> getAllByVideo(Long videoId, int pageNo, int pageSize, String sortBy);

    /**
     * Updates a comment.
     *
     * @param comment the entity to update.
     * @return the persisted entity.
     */
    Comment update(Comment comment);

    /**
     * Partially updates a comment.
     *
     * @param comment the entity to update partially.
     * @return the persisted entity.
     */
    Optional<Comment> partialUpdate(Comment comment);

    /**
     * Get all the comments.
     *
     * @return the list of entities.
     */
    List<Comment> findAll();

    /**
     * Get the "id" comment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Comment> findOne(Long id);

    /**
     * Delete the "id" comment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
