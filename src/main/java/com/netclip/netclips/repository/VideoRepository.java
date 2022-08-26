package com.netclip.netclips.repository;

import com.netclip.netclips.domain.Video;
import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Video entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VideoRepository extends JpaRepository<Video, Long> {
    Optional<Video> findOneByContentRef(String contentRef);
}
