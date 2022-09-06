package com.netclip.netclips.service.dto;

import com.netclip.netclips.domain.Comment;
import com.netclip.netclips.domain.VideoUser;
import java.time.Instant;

public class CommentDTO {

    private Long id;
    private String content;
    private Instant timeStamp;
    private Long likes;
    private Long dislikes;
    private Long uploaderId;
    private String uploader;
    private Long videoId;

    public CommentDTO(
        Long id,
        String content,
        Instant timeStamp,
        Long likes,
        Long dislikes,
        Long uploaderId,
        String uploader,
        Long videoId
    ) {
        this.id = id;
        this.content = content;
        this.timeStamp = timeStamp;
        this.likes = likes;
        this.dislikes = dislikes;
        this.uploaderId = uploaderId;
        this.uploader = uploader;
        this.videoId = videoId;
    }

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.timeStamp = comment.getTimeStamp();
        this.likes = comment.getLikes();
        this.dislikes = comment.getDislikes();
        this.uploaderId = comment.getVideoUser().getInternalUser().getId();
        this.uploader = comment.getVideoUser().getInternalUser().getLogin();
        this.videoId = comment.getVideo().getId();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Instant getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
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

    public Long getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(Long uploaderId) {
        this.uploaderId = uploaderId;
    }

    public String getUploader() {
        return uploader;
    }

    public void setUploader(String uploader) {
        this.uploader = uploader;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }
}
