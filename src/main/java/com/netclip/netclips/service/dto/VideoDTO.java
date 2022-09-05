package com.netclip.netclips.service.dto;

import com.netclip.netclips.domain.Comment;
import com.netclip.netclips.domain.Video;
import java.time.LocalDate;
import java.util.Set;

public class VideoDTO extends VideoPreviewDTO {

    private String contentKey;

    private String preSignedUrl;

    private String thumbnailPresignedUrl;

    private Long likes;

    private Long dislikes;

    private Set<Comment> comments;

    public VideoDTO(Video video) {
        super(video);
        this.contentKey = video.getContentRef();
        this.likes = video.getLikes();
        this.dislikes = video.getDislikes();
        this.comments = video.getComments();
    }

    public String getContentKey() {
        return contentKey;
    }

    public void setContentKey(String contentKey) {
        this.contentKey = contentKey;
    }

    public String getPreSignedUrl() {
        return preSignedUrl;
    }

    public void setPreSignedUrl(String preSignedUrl) {
        this.preSignedUrl = preSignedUrl;
    }

    public String getThumbnailPresignedUrl() {
        return thumbnailPresignedUrl;
    }

    public void setThumbnailPresignedUrl(String thumbnailPresignedUrl) {
        this.thumbnailPresignedUrl = thumbnailPresignedUrl;
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

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
