package entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Video")  // Table name in the database
public class VideoEntity {

    @Id
    @Column(name = "Id")
    private int id;

    @Column(name = "Title")
    private String title;

    @Column(name = "Poster")
    private String poster;

    @Column(name = "Views")
    private int views;

    @Column(name = "ShareCount", nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer shareCount = 0; // Default value is 0

    @Column(name = "Description", columnDefinition = "NVARCHAR(MAX)")
    private String description;

    @Column(name = "Active")
    private boolean active;
    @OneToMany(mappedBy = "videosentity")
    private Set<Sharesentity> shares; // Danh sách các chia sẻ liên kết với video

    @OneToMany(mappedBy = "video")
    private Set<Favoritesentity> favoritesSet;  // Optional rename for clarity

    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false) // Ánh xạ tới cột UserId trong bảng Video
    private Usersentity user;

    // Quan hệ một-nhiều với CommentEntity
    @OneToMany(mappedBy = "video")
    private Set<Comment> comments;



    // Một video có thể có nhiều bình luận


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<Sharesentity> getShares() {
        return shares;
    }

    public void setShares(Set<Sharesentity> shares) {
        this.shares = shares;
    }

    public Set<Favoritesentity> getFavoritesSet() {
        return favoritesSet;
    }

    public void setFavoritesSet(Set<Favoritesentity> favoritesSet) {
        this.favoritesSet = favoritesSet;
    }

    public Usersentity getUser() {
        return user;
    }

    public void setUser(Usersentity user) {
        this.user = user;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public String toString() {
        return "VideoEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", poster='" + poster + '\'' +
                ", views=" + views +
                ", shareCount=" + shareCount +
                ", description='" + description + '\'' +
                ", active=" + active +
                ", shares=" + shares +
                ", favoritesSet=" + favoritesSet +
                ", user=" + user +
                ", comments=" + comments +
                '}';
    }
}
