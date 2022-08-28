package com.netclip.netclips.service.dto;

import org.springframework.web.multipart.MultipartFile;

public class UploadDTO {

    private Long id;

    private MultipartFile file;

    private String title;

    private String description;

    private String uploaderLogin;

    private Long uploaderId;

    public UploadDTO() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUploaderLogin() {
        return uploaderLogin;
    }

    public void setUploaderLogin(String uploaderLogin) {
        this.uploaderLogin = uploaderLogin;
    }

    public Long getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(Long uploaderId) {
        this.uploaderId = uploaderId;
    }

    public interface UploadId {
        UserId id(Long id);
    }

    public interface UserId {
        UserLogin userId(Long id);
    }

    public interface UserLogin {
        FileToUpload userLogin(String login);
    }

    public interface FileToUpload {
        UploadDTOBuild fileToUpload(MultipartFile file);
    }

    public interface UploadDTOBuild {
        UploadDTO build();
    }

    public static class UploadDTOBuilder implements UploadId, UserId, UserLogin, FileToUpload, UploadDTOBuild {

        private Long id;

        private MultipartFile file;

        private String title;

        private String description;

        private String uploaderLogin;

        private Long uploaderId;

        public UploadDTOBuilder() {}

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
}
