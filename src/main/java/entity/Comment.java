package entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "Comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "UserId", nullable = false)
    private Usersentity user;  // Đối tượng User liên kết

    @ManyToOne
    @JoinColumn(name = "VideoId", nullable = false)
    private VideoEntity video;  // Đối tượng Video liên kết

    @Column(name = "CommentText", columnDefinition = "NVARCHAR(MAX)")
    private String commentText;

    @Column(name = "CommentDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentDate;

    // Getter và Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usersentity getUser() {
        return user;
    }

    public void setUser(Usersentity user) {
        this.user = user;
    }

    public VideoEntity getVideo() {
        return video;
    }

    public void setVideo(VideoEntity video) {
        this.video = video;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", user=" + user +
                ", video=" + video +
                ", commentText='" + commentText + '\'' +
                ", commentDate=" + commentDate +
                '}';
    }
}
