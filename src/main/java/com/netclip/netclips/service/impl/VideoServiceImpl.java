package com.netclip.netclips.service.impl;

import com.netclip.netclips.domain.Comment;
import com.netclip.netclips.domain.User;
import com.netclip.netclips.domain.Video;
import com.netclip.netclips.domain.VideoUser;
import com.netclip.netclips.repository.VideoRepository;
import com.netclip.netclips.service.S3Service;
import com.netclip.netclips.service.VideoService;
import com.netclip.netclips.service.VideoUserService;
import com.netclip.netclips.service.dto.VideoDTO;
import com.netclip.netclips.service.dto.VideoPreviewDTO;
import com.netclip.netclips.service.mapper.VideoMapper;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
 * Service Implementation for managing {@link Video}.
 */
@Service
@Transactional
public class VideoServiceImpl implements VideoService {

    private final String defaultThumbnailKey = "netclips/img/1662349353115-thumbnail-placeholder.jpg";
    private final Logger log = LoggerFactory.getLogger(VideoServiceImpl.class);

    @Autowired
    private final VideoRepository videoRepository;

    private final VideoMapper videoMapper;

    private final S3Service s3Service;

    private final VideoUserService videoUserService;

    public VideoServiceImpl(
        VideoRepository videoRepository,
        VideoMapper videoMapper,
        S3Service s3Service,
        VideoUserService videoUserService
    ) {
        this.videoRepository = videoRepository;
        this.videoMapper = videoMapper;
        this.s3Service = s3Service;
        this.videoUserService = videoUserService;
    }

    public Video updateVideoComment(Comment comment, Video video) {
        Set<Comment> comments = video.getComments();
        comments.add(comment);
        this.update(video);
        return video;
    }

    @Override
    @Transactional
    public Video uploadThumbnail(Video video, MultipartFile file) {
        String thumbnailKey = s3Service.uploadFile(file, s3Service.generateThumbnailName(file));
        video.setThumbnailRef(thumbnailKey);

        videoRepository.save(video);
        return video;
    }

    @Override
    public Video save(Video video) {
        log.debug("Request to save Video : {}", video);
        return videoRepository.save(video);
    }

    @Override
    public Video update(Video video) {
        log.debug("Request to save Video : {}", video);
        return videoRepository.save(video);
    }

    @Override
    public Optional<Video> partialUpdate(Video video) {
        log.debug("Request to partially update Video : {}", video);

        return videoRepository
            .findById(video.getId())
            .map(existingVideo -> {
                if (video.getContentRef() != null) {
                    existingVideo.setContentRef(video.getContentRef());
                }
                if (video.getTitle() != null) {
                    existingVideo.setTitle(video.getTitle());
                }
                if (video.getDescription() != null) {
                    existingVideo.setDescription(video.getDescription());
                }
                if (video.getLikes() != null) {
                    existingVideo.setLikes(video.getLikes());
                }
                if (video.getDislikes() != null) {
                    existingVideo.setDislikes(video.getDislikes());
                }
                if (video.getUploadDate() != null) {
                    existingVideo.setUploadDate(video.getUploadDate());
                }
                if (video.getViewCount() != null) {
                    existingVideo.setViewCount(video.getViewCount());
                }
                if (video.getThumbnailRef() != null) {
                    existingVideo.setThumbnailRef(video.getThumbnailRef());
                }

                return existingVideo;
            })
            .map(videoRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Video> findAll(Pageable pageable) {
        log.debug("Request to get all Videos");
        return videoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Video> findOne(Long id) {
        log.debug("Request to get Video : {}", id);
        return videoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Video> findVideoByContentKey(String contentRef) {
        log.debug("Request to get Video : {}", contentRef);
        return videoRepository.findOneByContentRef(contentRef);
    }

    @Override
    public Video getVideoById(Long videoId) {
        return videoRepository.findById(videoId).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Video : {}", id);
        videoRepository.deleteById(id);
    }

    @Override
    public VideoPreviewDTO videoToPreviewDTOWithPresignedThumbnail(Video video) {
        VideoPreviewDTO res = videoMapper.videoToPreviewDTO(video);
        if (video.getThumbnailRef().equals("") || video.getThumbnailRef() == null) {
            res.setThumbnailRef(s3Service.generatePresignedUrl(defaultThumbnailKey));
        } else {
            res.setThumbnailRef(s3Service.generatePresignedUrl(video.getThumbnailRef()));
        }

        return res;
    }

    @Override
    public VideoDTO likeVideo(Video video, VideoUser user) {
        Long vidId = video.getId();
        if (videoUserService.isLikedVideo(user, video)) {
            videoRepository.decrementLikes(vidId);
            video.setLikes(video.getLikes() - 1);
            videoUserService.removeLikedVideo(user, video);
        } else if (videoUserService.isDislikedVideo(user, video)) {
            videoRepository.decrementDislikes(vidId);
            video.setDislikes(video.getDislikes() - 1);
            videoUserService.removeDislikedVideo(user, video);

            videoRepository.incrementLikes(vidId);
            video.setLikes(video.getLikes() + 1);
            videoUserService.addLikedVideo(user, video);
        } else {
            videoRepository.incrementLikes(vidId);
            video.setLikes(video.getLikes() + 1);
            videoUserService.addLikedVideo(user, video);
        }
        Optional<Video> updatedVid = findOne(vidId);
        if (updatedVid.isPresent()) {
            return new VideoDTO(updatedVid.get());
        }
        return null;
        //return new VideoDTO(video);
    }

    @Override
    public VideoDTO dislikeVideo(Video video, VideoUser user) {
        Long vidId = video.getId();
        if (videoUserService.isDislikedVideo(user, video)) {
            videoRepository.decrementDislikes(vidId);
            video.setDislikes(video.getDislikes() - 1);
            videoUserService.removeDislikedVideo(user, video);
        } else if (videoUserService.isLikedVideo(user, video)) {
            videoRepository.decrementLikes(vidId);
            video.setLikes(video.getLikes() - 1);
            videoUserService.removeLikedVideo(user, video);

            videoRepository.incrementDislikes(vidId);
            video.setDislikes(video.getDislikes() + 1);
            videoUserService.addDislikedVideo(user, video);
        } else {
            videoRepository.incrementDislikes(vidId);
            video.setDislikes(video.getDislikes() + 1);
            videoUserService.addDislikedVideo(user, video);
        }
        return new VideoDTO(video);
    }

    @Override
    public void incrementViewCount(Long id) {
        videoRepository.incrementViewCount(id);
    }

    @Override
    public Page<VideoPreviewDTO> getVideoPreviews(int pageNo, int pageSize, String sortBy) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        Page<VideoPreviewDTO> pagedRes = videoRepository.findAll(pageable).map(this::videoToPreviewDTOWithPresignedThumbnail);

        if (pagedRes.hasContent()) {
            return pagedRes;
        }
        return new PageImpl<>(new ArrayList<>());
    }

    @Override
    public Page<VideoPreviewDTO> getVideoPreviewsByUploader(int pageNo, int pageSize, String sortBy, Long uploaderId) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy).descending());

        Page<VideoPreviewDTO> pagedRes = videoRepository
            .findAllByUploader_Id(uploaderId, pageable)
            .map(this::videoToPreviewDTOWithPresignedThumbnail);

        if (pagedRes.hasContent()) {
            return pagedRes;
        }
        return new PageImpl<>(new ArrayList<>());
    }
}
