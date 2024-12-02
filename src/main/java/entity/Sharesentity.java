package entity;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Share", uniqueConstraints = {@UniqueConstraint(columnNames = {"UserId", "VideoId"})})
public class Sharesentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Sử dụng Integer để khớp với kiểu INT trong bảng SQL

    // Many-to-One relationship with Usersentity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", nullable = false)
    private Usersentity usersentity;

    // Many-to-One relationship with VideoEntity
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "VideoId", nullable = false)
    private VideoEntity videosentity;

    // Emails to be shared, with NVARCHAR(MAX) type
    @Column(name = "Emails", columnDefinition = "NVARCHAR(MAX)")
    private String emails;

    // Date when the video was shared
    @Temporal(TemporalType.DATE)
    @Column(name = "ShareDate", nullable = false, columnDefinition = "DATE DEFAULT GETDATE()")
    private Date shareDate;





    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usersentity getUsersentity() {
        return usersentity;
    }

    public void setUsersentity(Usersentity usersentity) {
        this.usersentity = usersentity;
    }

    public VideoEntity getVideosentity() {
        return videosentity;
    }

    public void setVideosentity(VideoEntity videosentity) {
        this.videosentity = videosentity;
    }

    public String getEmails() {
        return emails;
    }

    public void setEmails(String emails) {
        this.emails = emails;
    }

    public Date getShareDate() {
        return shareDate;
    }

    public void setShareDate(Date shareDate) {
        this.shareDate = shareDate;
    }


    @Override
    public String toString() {
        return "Sharesentity{" +
                "id=" + id +
                ", usersentity=" + usersentity +
                ", videosentity=" + videosentity +
                ", emails='" + emails + '\'' +
                ", shareDate=" + shareDate +
                '}';
    }
}
