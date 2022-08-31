package com.netclip.netclips.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Video.
 */
@Entity
@Table(name = "video")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content_ref")
    private String contentRef;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "likes")
    private Long likes;

    @Column(name = "dislikes")
    private Long dislikes;

    @Column(name = "upload_date")
    private LocalDate uploadDate;

    @OneToMany(mappedBy = "video")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "videoUser", "video" }, allowSetters = true)
    private Set<Comment> comments = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties(value = { "internalUser", "comments", "videos" }, allowSetters = true)
    private VideoUser uploader;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Video() {}

    public Long getId() {
        return this.id;
    }

    public Video id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContentRef() {
        return this.contentRef;
    }

    public Video contentRef(String contentRef) {
        this.setContentRef(contentRef);
        return this;
    }

    public void setContentRef(String contentRef) {
        this.contentRef = contentRef;
    }

    public String getTitle() {
        return this.title;
    }

    public Video title(String title) {
        this.setTitle(title);
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public Video description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getLikes() {
        return this.likes;
    }

    public Video likes(Long likes) {
        this.setLikes(likes);
        return this;
    }

    public void setLikes(Long likes) {
        this.likes = likes;
    }

    public Long getDislikes() {
        return this.dislikes;
    }

    public Video dislikes(Long dislikes) {
        this.setDislikes(dislikes);
        return this;
    }

    public void setDislikes(Long dislikes) {
        this.dislikes = dislikes;
    }

    public LocalDate getUploadDate() {
        return this.uploadDate;
    }

    public Video uploadDate(LocalDate uploadDate) {
        this.setUploadDate(uploadDate);
        return this;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public Set<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Set<Comment> comments) {
        if (this.comments != null) {
            this.comments.forEach(i -> i.setVideo(null));
        }
        if (comments != null) {
            comments.forEach(i -> i.setVideo(this));
        }
        this.comments = comments;
    }

    public Video comments(Set<Comment> comments) {
        this.setComments(comments);
        return this;
    }

    public Video addComment(Comment comment) {
        this.comments.add(comment);
        comment.setVideo(this);
        return this;
    }

    public Video removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setVideo(null);
        return this;
    }

    public VideoUser getUploader() {
        return this.uploader;
    }

    public void setUploader(VideoUser videoUser) {
        this.uploader = videoUser;
    }

    public Video uploader(VideoUser videoUser) {
        this.setUploader(videoUser);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Video)) {
            return false;
        }
        return id != null && id.equals(((Video) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Video{" +
            "id=" + getId() +
            ", contentRef='" + getContentRef() + "'" +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", likes=" + getLikes() +
            ", dislikes=" + getDislikes() +
            ", uploadDate='" + getUploadDate() + "'" +
            "}";
    }
}
