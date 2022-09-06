package com.netclip.netclips.repository;

import com.netclip.netclips.domain.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Comment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findAllByVideo_Id(Long id, Pageable pageable);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Comment c SET c.likes = c.likes + 1 WHERE c.id = :id")
    void incrementLikes(@Param("id") Long id);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Comment c SET c.likes = c.likes - 1 WHERE c.id = :id")
    void decrementLikes(@Param("id") Long id);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Comment c SET c.dislikes = c.dislikes + 1 WHERE c.id = :id")
    void incrementDislikes(@Param("id") Long id);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Comment c SET c.dislikes = c.dislikes - 1 WHERE c.id = :id")
    void decrementDislikes(@Param("id") Long id);
}
