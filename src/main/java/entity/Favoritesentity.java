package entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Favorite", uniqueConstraints = {@UniqueConstraint(columnNames = {"UserId", "VideoId"})})

public class Favoritesentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Changed to Long to maintain consistency with other entities

    // Many-to-One relationship with VideoEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VideoId", nullable = false)  // Foreign key to Video table
    private VideoEntity video;

    // Many-to-One relationship with UsersEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", nullable = false)  // Foreign key to Users table
    private Usersentity user;

    // Like date (only date part)
    @Temporal(TemporalType.DATE)
    @Column(name = "LikeDate", nullable = false)
    private Date likeDate;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VideoEntity getVideo() {
        return video;
    }

    public void setVideo(VideoEntity video) {
        this.video = video;
    }

    public Usersentity getUser() {
        return user;
    }

    public void setUser(Usersentity user) {
        this.user = user;
    }

    public Date getLikeDate() {
        return likeDate;
    }

    public void setLikeDate(Date likeDate) {
        this.likeDate = likeDate;
    }

    @Override
    public String toString() {
        return "Favoritesentity{" +
                "id=" + id +
                ", video=" + (video != null ? video.getId() : null) +  // To avoid null pointer
                ", user=" + (user != null ? user.getId() : null) +  // To avoid null pointer
                ", likeDate=" + likeDate +
                '}';
    }
}
