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

    @Override
    @Transactional(readOnly = true)
    public Optional<VideoUser> findOne(Long id) {
        log.debug("Request to get VideoUser : {}", id);
        return videoUserRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete VideoUser : {}", id);
        videoUserRepository.deleteById(id);
    }

    public VideoUser deleteVideoFromSet(VideoUser videoUser, Video video) {
        Set<Video> ownedVideos = videoUser.getVideos();
        ownedVideos.remove(video);
        return videoUser;
    }
}
