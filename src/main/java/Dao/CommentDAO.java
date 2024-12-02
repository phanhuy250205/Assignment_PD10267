package Dao;

import entity.Comment;

import entity.Usersentity;
import entity.VideoEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import until.XJPA;

import java.util.ArrayList;
import java.util.List;

public class CommentDAO implements GenericDao<Comment> {


    private EntityManager em = XJPA.getEntityManager();

    @Override
    public List<Comment> findAll() {
        return em.createQuery("SELECT c FROM Comment c", Comment.class).getResultList();
    }

    @Override
    public Comment findById(String id) {
        return em.find(Comment.class, id);
    }



    @Override
    public void create(Comment item) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.persist(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error adding comment", e);
        }
    }

    @Override
    public void update(Comment item) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(item);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Error updating comment", e);
        }
    }

    @Override
    public boolean deleteById(int id) {  // Thay đổi tham số thành int
        EntityTransaction transaction = em.getTransaction();
        boolean isDeleted = false;

        try {
            transaction.begin();
            Comment comment = findById(String.valueOf(id));  // Truyền tham số kiểu int

            if (comment != null) {
                em.remove(comment);
                isDeleted = true;
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return isDeleted;
    }

    // Tìm tất cả bình luận của một video
    public List<Comment> findCommentsByVideo(VideoEntity video) {
        if (video == null) {
            throw new IllegalArgumentException("Video must not be null");
        }

        String jpql = "SELECT c FROM Comment c WHERE c.video = :video";
        TypedQuery<Comment> query = em.createQuery(jpql, Comment.class);
        query.setParameter("video", video);
        return query.getResultList();
    }

    // Tìm tất cả bình luận của một người dùng
    public List<Comment> findCommentsByUser(Usersentity user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null");
        }

        String jpql = "SELECT c FROM Comment c WHERE c.user = :user";
        TypedQuery<Comment> query = em.createQuery(jpql, Comment.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    public List<Comment> getCommentsForVideo(Long videoId) {
        try {
            String jpql = "SELECT c FROM Comment c JOIN FETCH c.user WHERE c.video.id = :videoId ORDER BY c.commentDate DESC";
            TypedQuery<Comment> query = em.createQuery(jpql, Comment.class);
            query.setParameter("videoId", videoId);

            List<Comment> result = query.getResultList();
            System.out.println("Query executed successfully");
            System.out.println("Number of comments found: " + result.size());
            for (Comment c : result) {
                System.out.println("Comment ID: " + c.getId());
                System.out.println("Content: " + c.getCommentText());
                System.out.println("User: " + c.getUser().getFullname());
                System.out.println("Date: " + c.getCommentDate());
                System.out.println("---------------");
            }
            return result;
        } catch (Exception e) {
            System.err.println("Error getting comments: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        // Tạo một đối tượng VideoEntity với videoId bạn muốn kiểm tra
        Long videoId = 1L; // Thay videoId với ID video thực tế mà bạn muốn kiểm tra

        // Tạo đối tượng CommentDAO để gọi phương thức
        CommentDAO commentDAO = new CommentDAO();

        // Lấy danh sách bình luận cho video cụ thể
        List<Comment> comments = commentDAO.getCommentsForVideo(videoId);

        // In ra danh sách bình luận
        if (comments != null && !comments.isEmpty()) {
            for (Comment comment : comments) {
                System.out.println("User: " + comment.getUser().getFullname());  // Hiển thị tên người dùng
                System.out.println("Comment: " + comment.getCommentText());  // Hiển thị nội dung bình luận
                System.out.println("Created At: " + comment.getCommentDate());  // Hiển thị thời gian bình luận
                System.out.println("-------------------------");
            }
        } else {
            System.out.println("No comments found for video ID: " + videoId);
        }
    }
}
