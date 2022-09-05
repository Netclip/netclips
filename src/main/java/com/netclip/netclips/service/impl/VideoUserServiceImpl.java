package com.netclip.netclips.service.impl;

import com.netclip.netclips.domain.Video;
import com.netclip.netclips.domain.VideoUser;
import com.netclip.netclips.repository.VideoUserRepository;
import com.netclip.netclips.service.VideoUserService;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link VideoUser}.
 */
@Service
@Transactional
public class VideoUserServiceImpl implements VideoUserService {

    private final Logger log = LoggerFactory.getLogger(VideoUserServiceImpl.class);

    private final VideoUserRepository videoUserRepository;

    public VideoUserServiceImpl(VideoUserRepository videoUserRepository) {
        this.videoUserRepository = videoUserRepository;
    }

    @Override
    public VideoUser save(VideoUser videoUser) {
        log.debug("Request to save VideoUser : {}", videoUser);
        return videoUserRepository.save(videoUser);
    }

    @Override
    public VideoUser update(VideoUser videoUser) {
        log.debug("Request to save VideoUser : {}", videoUser);
        return videoUserRepository.save(videoUser);
    }

    @Override
    public Optional<VideoUser> partialUpdate(VideoUser videoUser) {
        log.debug("Request to partially update VideoUser : {}", videoUser);

        return videoUserRepository
            .findById(videoUser.getId())
            .map(existingVideoUser -> {
                return existingVideoUser;
            })
            .map(videoUserRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public List<VideoUser> findAll() {
        log.debug("Request to get all VideoUsers");
        return videoUserRepository.findAll();
    }

    public Page<VideoUser> findAllWithEagerRelationships(Pageable pageable) {
        return videoUserRepository.findAllWithEagerRelationships(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VideoUser> findOne(Long id) {
        log.debug("Request to get VideoUser : {}", id);
        return videoUserRepository.findOneWithEagerRelationships(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete VideoUser : {}", id);
        videoUserRepository.deleteById(id);
    }

    @Override
    public VideoUser deleteVideoFromSet(VideoUser videoUser, Video video) {
        Set<Video> ownedVideos = videoUser.getVideos();
        ownedVideos.remove(video);
        return videoUser;
    }

    @Override
    public VideoUser addLikedVideo(VideoUser videoUser, Video video) {
        Set<Video> likedVideos = videoUser.getLikedVideos();
        likedVideos.add(video);
        videoUserRepository.save(videoUser);
        return videoUser;
    }

    @Override
    public VideoUser addDislikedVideo(VideoUser videoUser, Video video) {
        Set<Video> dislikedVideos = videoUser.getVideosDisliked();
        dislikedVideos.add(video);
        videoUserRepository.save(videoUser);
        return videoUser;
    }

    @Override
    public boolean isLikedVideo(VideoUser videoUser, Video video) {
        return videoUser.getLikedVideos().contains(video);
    }

    @Override
    public boolean isDislikedVideo(VideoUser videoUser, Video video) {
        return videoUser.getVideosDisliked().contains(video);
    }

    @Override
    public VideoUser removeLikedVideo(VideoUser videoUser, Video video) {
        Set<Video> likedVideos = videoUser.getLikedVideos();
        likedVideos.remove(video);
        videoUserRepository.save(videoUser);
        return videoUser;
    }

    @Override
    public VideoUser removeDislikedVideo(VideoUser videoUser, Video video) {
        Set<Video> dislikedVideos = videoUser.getVideosDisliked();
        dislikedVideos.remove(video);
        videoUserRepository.save(videoUser);
        return videoUser;
    }

    @Override
    public Optional<VideoUser> findByUserLogin(String login) {
        return videoUserRepository.findByInternalUser_Login(login);
    }
}
