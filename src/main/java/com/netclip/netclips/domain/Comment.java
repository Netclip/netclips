package com.netclip.netclips.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Comment.
 */
@Entity
@Table(name = "comment")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Comment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "time_stamp")
    private Instant timeStamp;

    @Column(name = "content")
    private String content;

    @Column(name = "likes")
    private Long likes;

    @Column(name = "dislikes")
    private Long dislikes;

    @ManyToOne
    @JsonIgnoreProperties(value = { "internalUser", "comments", "videos" }, allowSetters = true)
    private VideoUser videoUser;

    @ManyToOne
    @JsonIgnoreProperties(value = { "comments", "uploader" }, allowSetters = true)
    private Video video;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Comment id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getTimeStamp() {
        return this.timeStamp;
    }

    public Comment timeStamp(Instant timeStamp) {
        this.setTimeStamp(timeStamp);
        return this;
    }

    public void setTimeStamp(Instant timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getContent() {
        return this.content;
    }

    public Comment content(String content) {
        this.setContent(content);
        return this;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getLikes() {
        return this.likes;
    }

    public Comment likes(Long likes) {
        this.setLikes(likes);
        return this;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getDislikes() {
        return this.dislikes;
    }

    public Comment dislikes(Long dislikes) {
        this.setDislikes(dislikes);
        return this;
    }

    public void setDislikes(Long dislikes) {
        this.dislikes = dislikes;
    }

    public VideoUser getVideoUser() {
        return this.videoUser;
    }

    public void setVideoUser(VideoUser videoUser) {
        this.videoUser = videoUser;
    }

    public Comment videoUser(VideoUser videoUser) {
        this.setVideoUser(videoUser);
        return this;
    }

    public Video getVideo() {
        return this.video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    public Comment video(Video video) {
        this.setVideo(video);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comment)) {
            return false;
        }
        return id != null && id.equals(((Comment) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Comment{" +
            "id=" + getId() +
            ", timeStamp='" + getTimeStamp() + "'" +
            ", content='" + getContent() + "'" +
            ", likes=" + getLikes() +
            ", dislikes=" + getDislikes() +
            "}";
    }
}
