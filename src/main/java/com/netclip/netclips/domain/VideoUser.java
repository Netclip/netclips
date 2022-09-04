package com.netclip.netclips.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A VideoUser.
 */
@Entity
@Table(name = "video_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class VideoUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(unique = true)
    private User internalUser;

    @OneToMany(mappedBy = "videoUser")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "videoUser", "video" }, allowSetters = true)
    private Set<Comment> comments = new HashSet<>();

    @OneToMany(mappedBy = "uploader")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "comments", "uploader" }, allowSetters = true)
    private Set<Video> videos = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "rel_video_user__liked_videos",
        joinColumns = @JoinColumn(name = "video_user_id"),
        inverseJoinColumns = @JoinColumn(name = "liked_videos_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "comments", "uploader" }, allowSetters = true)
    private Set<Video> likedVideos = new HashSet<>();

    @ManyToMany
    @JoinTable(
        name = "rel_video_user__videos_disliked",
        joinColumns = @JoinColumn(name = "video_user_id"),
        inverseJoinColumns = @JoinColumn(name = "videos_disliked_id")
    )
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnoreProperties(value = { "comments", "uploader" }, allowSetters = true)
    private Set<Video> videosDislikeds = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public VideoUser(User user) {
        this.id = user.getId();
        this.internalUser = user;
    }

    public VideoUser() {}

    public Long getId() {
        return this.id;
    }

    public VideoUser id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getInternalUser() {
        return this.internalUser;
    }

    public void setInternalUser(User user) {
        this.internalUser = user;
    }

    public VideoUser internalUser(User user) {
        this.setInternalUser(user);
        return this;
    }

    public Set<Comment> getComments() {
        return this.comments;
    }

    public void setComments(Set<Comment> comments) {
        if (this.comments != null) {
            this.comments.forEach(i -> i.setVideoUser(null));
        }
        if (comments != null) {
            comments.forEach(i -> i.setVideoUser(this));
        }
        this.comments = comments;
    }

    public VideoUser comments(Set<Comment> comments) {
        this.setComments(comments);
        return this;
    }

    public VideoUser addComment(Comment comment) {
        this.comments.add(comment);
        comment.setVideoUser(this);
        return this;
    }

    public VideoUser removeComment(Comment comment) {
        this.comments.remove(comment);
        comment.setVideoUser(null);
        return this;
    }

    public Set<Video> getVideos() {
        return this.videos;
    }

    public void setVideos(Set<Video> videos) {
        if (this.videos != null) {
            this.videos.forEach(i -> i.setUploader(null));
        }
        if (videos != null) {
            videos.forEach(i -> i.setUploader(this));
        }
        this.videos = videos;
    }

    public VideoUser videos(Set<Video> videos) {
        this.setVideos(videos);
        return this;
    }

    public VideoUser addVideos(Video video) {
        this.videos.add(video);
        video.setUploader(this);
        return this;
    }

    public VideoUser removeVideos(Video video) {
        this.videos.remove(video);
        video.setUploader(null);
        return this;
    }

    public Set<Video> getLikedVideos() {
        return this.likedVideos;
    }

    public void setLikedVideos(Set<Video> videos) {
        this.likedVideos = videos;
    }

    public VideoUser likedVideos(Set<Video> videos) {
        this.setLikedVideos(videos);
        return this;
    }

    public VideoUser addLikedVideos(Video video) {
        this.likedVideos.add(video);
        return this;
    }

    public VideoUser removeLikedVideos(Video video) {
        this.likedVideos.remove(video);
        return this;
    }

    public Set<Video> getVideosDislikeds() {
        return this.videosDislikeds;
    }

    public void setVideosDislikeds(Set<Video> videos) {
        this.videosDislikeds = videos;
    }

    public VideoUser videosDislikeds(Set<Video> videos) {
        this.setVideosDislikeds(videos);
        return this;
    }

    public VideoUser addVideosDisliked(Video video) {
        this.videosDislikeds.add(video);
        return this;
    }

    public VideoUser removeVideosDisliked(Video video) {
        this.videosDislikeds.remove(video);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VideoUser)) {
            return false;
        }
        return id != null && id.equals(((VideoUser) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VideoUser{" +
            "id=" + getId() +
            "}";
    }
}
