package entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")  // Chỉ định tên bảng trong cơ sở dữ liệu (nếu cần)
public class Usersentity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fullname", nullable = false, length = 50)
    private String fullname;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "admin", nullable = false)
    private boolean admin;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Favoritesentity> favoritesentities;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments;

    private String image;

    // Constructor mặc định
    public Usersentity() {
        // Constructor không tham số
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Set<Favoritesentity> getFavoritesentities() {
        return favoritesentities;
    }

    public void setFavoritesentities(Set<Favoritesentity> favoritesentities) {
        this.favoritesentities = favoritesentities;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    // Constructor với tham số
    public Usersentity(String fullname, String email, String password, boolean admin) {
        this.fullname = fullname;
        this.email = email;
        this.password = password;
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Users entity{" +
                "id=" + id +
                ", fullname='" + fullname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                ", favorites entities=" + favoritesentities +
                ", comments=" + comments +
                ", image='" + image + '\'' +
                '}';
    }
}
