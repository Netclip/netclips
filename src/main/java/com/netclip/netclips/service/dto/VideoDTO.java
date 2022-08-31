package com.netclip.netclips.service.dto;

import com.netclip.netclips.domain.Comment;
import com.netclip.netclips.domain.Video;
import java.time.LocalDate;
import java.util.Set;

public class VideoDTO {

    private Long id;

    private String contentKey;

    private String title;

    private String description;

    private Long likes;

    private Long dislikes;

    private LocalDate uploadDate;

    private Set<Comment> comments;

    private String uploaderLogin;

    private String uploaderId;

    public VideoDTO(Video video) {
        this.id = video.getId();
        this.contentKey = video.getContentRef();
        this.title = video.getTitle();
        this.description = video.getDescription();
        this.likes = video.getLikes();
        this.dislikes = video.getDislikes();
        this.uploadDate = video.getUploadDate();
        this.comments = video.getComments();
        this.uploaderLogin = video.getUploader().getInternalUser().getLogin();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContentKey() {
        return contentKey;
    }

    public void setContentKey(String contentKey) {
        this.contentKey = contentKey;
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

    public Long getLikes() {
        return likes;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getDislikes() {
        return dislikes;
    }

    public void setDislikes(Long dislikes) {
        this.dislikes = dislikes;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public String getUploaderLogin() {
        return uploaderLogin;
    }

    public void setUploaderLogin(String uploaderLogin) {
        this.uploaderLogin = uploaderLogin;
    }

    public String getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(String uploaderId) {
        this.uploaderId = uploaderId;
    }
}
