package com.netclip.netclips.repository;

import com.netclip.netclips.domain.Video;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Video entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findOneByContentRef(String contentRef);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Video v SET v.likes = v.likes + 1 WHERE v.id = :id")
    void incrementLikes(@Param("id") Long id);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Video v SET v.likes = v.likes - 1 WHERE v.id = :id")
    void decrementLikes(@Param("id") Long id);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Video v SET v.dislikes = v.dislikes + 1 WHERE v.id = :id")
    void incrementDislikes(@Param("id") Long id);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Video v SET v.dislikes = v.dislikes - 1 WHERE v.id = :id")
    void decrementDislikes(@Param("id") Long id);

    @Modifying(flushAutomatically = true)
    @Query("UPDATE Video v SET v.viewCount = v.viewCount + 1 WHERE v.id = :id")
    void incrementViewCount(@Param("id") Long id);
}
