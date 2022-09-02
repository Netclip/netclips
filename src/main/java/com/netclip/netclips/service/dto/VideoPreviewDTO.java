package com.netclip.netclips.service.dto;

import com.netclip.netclips.domain.Video;
import java.time.LocalDate;

public class VideoPreviewDTO {

    private Long id;
    private String thumbnailRef;
    private String title;
    private String description;
    private LocalDate uploadDate;
    private String uploaderLogin;
    private Long uploaderId;

    public VideoPreviewDTO(Video video) {
        this.id = video.getId();
        this.title = video.getTitle();
        this.description = video.getDescription();
        this.uploadDate = video.getUploadDate();
        this.uploaderLogin = video.getUploader().getInternalUser().getLogin();
        this.uploaderId = video.getUploader().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getThumbnailRef() {
        return thumbnailRef;
    }

    public void setThumbnailRef(String thumbnailRef) {
        this.thumbnailRef = thumbnailRef;
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

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
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
}
