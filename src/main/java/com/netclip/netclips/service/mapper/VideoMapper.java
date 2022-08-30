package com.netclip.netclips.service.mapper;

import com.netclip.netclips.domain.Video;
import com.netclip.netclips.domain.VideoUser;
import com.netclip.netclips.repository.VideoUserRepository;
import com.netclip.netclips.service.dto.UploadDTO;
import com.netclip.netclips.service.dto.VideoDTO;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoMapper {

    @Autowired
    private final VideoUserRepository userRepo;

    public VideoMapper(VideoUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public Video videoUploadDTOToVideo(UploadDTO uploadDTO) {
        Video res = new Video();
        res.setTitle(uploadDTO.getTitle());
        res.setDescription(uploadDTO.getDescription());
        res.setUploadDate(LocalDate.now());
        res.setContentRef(uploadDTO.getFileUrl());
        Optional<VideoUser> user = userRepo.findByInternalUser_Login(uploadDTO.getUploaderLogin());
        user.ifPresent(res::setUploader);
        return res;
    }

    public VideoDTO videoToVideoDTO(Video video) {
        return new VideoDTO(video);
    }
}
