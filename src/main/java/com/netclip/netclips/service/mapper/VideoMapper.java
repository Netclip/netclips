package com.netclip.netclips.service.mapper;

import com.netclip.netclips.domain.User;
import com.netclip.netclips.domain.Video;
import com.netclip.netclips.domain.VideoUser;
import com.netclip.netclips.repository.UserRepository;
import com.netclip.netclips.repository.VideoUserRepository;
import com.netclip.netclips.service.dto.VideoUploadDTO;
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

    public Video videoUploadDTOToVideo(VideoUploadDTO videoDTO) {
        Video res = new Video();
        res.setId(videoDTO.getId());
        res.setTitle(videoDTO.getTitle());
        res.setDescription(videoDTO.getDescription());
        Optional<VideoUser> user = userRepo.findOneByInternalUser_Login(videoDTO.getUploader().getLogin());
        user.ifPresent(res::setUploader);
        return res;
    }
}
