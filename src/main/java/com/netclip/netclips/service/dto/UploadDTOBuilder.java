package com.netclip.netclips.service.dto;

import com.netclip.netclips.service.dto.UploadDTO.*;
import org.springframework.web.multipart.MultipartFile;

public class UploadDTOBuilder implements UploadId, UserId, UserLogin, FileToUpload, UploadDTOBuild {

    private Long id;

    private MultipartFile file;

    private String title;

    private String description;

    private String uploaderLogin;

    private Long uploaderId;

    @Override
    public UserId id(Long id) {
        this.id = id;
        return this;
    }

    @Override
    public UserLogin userId(Long id) {
        this.uploaderId = id;
        return this;
    }

    @Override
    public FileToUpload userLogin(String login) {
        this.uploaderLogin = login;
        return this;
    }

    @Override
    public UploadDTOBuild fileToUpload(MultipartFile file) {
        this.file = file;
        return this;
    }

    public UploadDTOBuilder title(String title) {
        this.title = title;
        return this;
    }

    public UploadDTOBuilder description(String description) {
        this.description = description;
        return this;
    }

    @Override
    public UploadDTO build() {
        return new UploadDTO();
    }
}
