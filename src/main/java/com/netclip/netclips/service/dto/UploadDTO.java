package com.netclip.netclips.service.dto;

import org.springframework.web.multipart.MultipartFile;

public class UploadDTO {

    private String fileUrl;

    private String title;

    private String description;

    private String uploaderLogin;

    private Long uploaderId;

    public UploadDTO() {}

    public UploadDTO(String fileUrl, String title, String description, String uploaderLogin, Long uploaderId) {
        this.fileUrl = fileUrl;
        this.title = title;
        this.description = description;
        this.uploaderLogin = uploaderLogin;
        this.uploaderId = uploaderId;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
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

    public interface UserId {
        UserLogin userId(Long id);
    }

    public interface UserLogin {
        FileURL userLogin(String login);
    }

    public interface FileURL {
        UploadDTOBuild uploadedFilePath(String fileUrl);
    }

    public interface UploadDTOBuild {
        UploadDTOBuild title(String title);
        UploadDTOBuild description(String description);
        UploadDTO build();
    }

    public static class UploadDTOBuilder implements UserId, UserLogin, FileURL, UploadDTOBuild {

        private String fileUrl;

        private String title;

        private String description;

        private String uploaderLogin;

        private Long uploaderId;

        public UploadDTOBuilder() {}

        @Override
        public UserLogin userId(Long id) {
            this.uploaderId = id;
            return this;
        }

        @Override
        public FileURL userLogin(String login) {
            this.uploaderLogin = login;
            return this;
        }

        @Override
        public UploadDTOBuild uploadedFilePath(String fileUrl) {
            this.fileUrl = fileUrl;
            return this;
        }

        public UploadDTOBuild title(String title) {
            this.title = title;
            return this;
        }

        public UploadDTOBuild description(String description) {
            this.description = description;
            return this;
        }

        @Override
        public UploadDTO build() {
            return new UploadDTO(fileUrl, title, description, uploaderLogin, uploaderId);
        }
    }
}
