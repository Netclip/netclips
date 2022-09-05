package com.netclip.netclips.repository;

import com.netclip.netclips.domain.VideoUser;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the VideoUser entity.
 *
 * When extending this class, extend VideoUserRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface VideoUserRepository extends VideoUserRepositoryWithBagRelationships, JpaRepository<VideoUser, Long> {
    default Optional<VideoUser> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findById(id));
    }

    default List<VideoUser> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAll());
    }

    default Page<VideoUser> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAll(pageable));
    }

    Optional<VideoUser> findByInternalUser_Login(String login);
    Optional<VideoUser> findByInternalUser_Id(Long id);
}
