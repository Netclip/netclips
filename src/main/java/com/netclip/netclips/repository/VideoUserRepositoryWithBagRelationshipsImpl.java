package com.netclip.netclips.repository;

import com.netclip.netclips.domain.VideoUser;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class VideoUserRepositoryWithBagRelationshipsImpl implements VideoUserRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<VideoUser> fetchBagRelationships(Optional<VideoUser> videoUser) {
        return videoUser.map(this::fetchLikedVideos).map(this::fetchVideosDislikeds);
    }

    @Override
    public Page<VideoUser> fetchBagRelationships(Page<VideoUser> videoUsers) {
        return new PageImpl<>(fetchBagRelationships(videoUsers.getContent()), videoUsers.getPageable(), videoUsers.getTotalElements());
    }

    @Override
    public List<VideoUser> fetchBagRelationships(List<VideoUser> videoUsers) {
        return Optional.of(videoUsers).map(this::fetchLikedVideos).map(this::fetchVideosDislikeds).orElse(Collections.emptyList());
    }

    VideoUser fetchLikedVideos(VideoUser result) {
        return entityManager
            .createQuery(
                "select videoUser from VideoUser videoUser left join fetch videoUser.likedVideos where videoUser is :videoUser",
                VideoUser.class
            )
            .setParameter("videoUser", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<VideoUser> fetchLikedVideos(List<VideoUser> videoUsers) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, videoUsers.size()).forEach(index -> order.put(videoUsers.get(index).getId(), index));
        List<VideoUser> result = entityManager
            .createQuery(
                "select distinct videoUser from VideoUser videoUser left join fetch videoUser.likedVideos where videoUser in :videoUsers",
                VideoUser.class
            )
            .setParameter("videoUsers", videoUsers)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }

    VideoUser fetchVideosDislikeds(VideoUser result) {
        return entityManager
            .createQuery(
                "select videoUser from VideoUser videoUser left join fetch videoUser.videosDisliked where videoUser is :videoUser",
                VideoUser.class
            )
            .setParameter("videoUser", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<VideoUser> fetchVideosDislikeds(List<VideoUser> videoUsers) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, videoUsers.size()).forEach(index -> order.put(videoUsers.get(index).getId(), index));
        List<VideoUser> result = entityManager
            .createQuery(
                "select distinct videoUser from VideoUser videoUser left join fetch videoUser.videosDisliked where videoUser in :videoUsers",
                VideoUser.class
            )
            .setParameter("videoUsers", videoUsers)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
