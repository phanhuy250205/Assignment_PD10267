package Dao;

import entity.VideoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import until.XJPA;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VideosDao  implements  GenericDao<VideoEntity>{
    private EntityManager em = XJPA.getEntityManager();
    @Override
    public List<VideoEntity> findAll() {
        return em.createQuery("select v from VideoEntity v ", VideoEntity.class).getResultList();
    }

    @Override
    public VideoEntity findById(String id) {
       return  em.find(VideoEntity.class, id);
    }

    @Override
    public void create(VideoEntity item) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(item);
        transaction.commit();
    }

    @Override
    public void update(VideoEntity item) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(item);
        transaction.commit();
    }

    @Override
    public boolean deleteById(int id) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            // Tìm video theo ID
            VideoEntity video = em.find(VideoEntity.class, id); // Sử dụng find thay vì gọi findById
            if (video != null) {
                // Xóa video
                em.remove(video);
                transaction.commit();
                System.out.println("Video with ID " + id + " deleted successfully."); // Log thành công
                return true; // Trả về true nếu xóa thành công
            } else {
                transaction.rollback();
                System.out.println("Video with ID " + id + " not found."); // Log khi không tìm thấy video
                return false; // Không tìm thấy video
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback(); // Rollback nếu xảy ra lỗi
            }
            return false; // Trả về false nếu có lỗi
        }
    }
    public List<VideoEntity> searchByTitle(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return Collections.emptyList();  // Nếu từ khóa tìm kiếm rỗng, trả về danh sách trống.
        }

        String jpql = "SELECT v FROM VideoEntity v WHERE v.title LIKE :keyword";  // Tìm kiếm video có title chứa từ khóa
        TypedQuery<VideoEntity> query = em.createQuery(jpql, VideoEntity.class);
        query.setParameter("keyword", "%" + keyword + "%");  // Thêm dấu % để tìm kiếm chứa từ khóa
        return query.getResultList();
    }
    public List<VideoEntity> findVideosByUser(Long userId) {
        if (userId == null) {
            return Collections.emptyList(); // Trả về danh sách trống nếu userId null
        }

        // Truy vấn tìm video theo userId
        String jpql = "SELECT v FROM VideoEntity v WHERE v.user.id = :userId";
        TypedQuery<VideoEntity> query = em.createQuery(jpql, VideoEntity.class);
        query.setParameter("userId", userId); // Truyền tham số userId là Long

        return query.getResultList();
    }
    public void incrementShareCount(int videoId) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            // Tăng giá trị ShareCount của video
            String jpql = "UPDATE VideoEntity v SET v.shareCount = v.shareCount + 1 WHERE v.id = :videoId";
            int updatedRows = em.createQuery(jpql)
                    .setParameter("videoId", videoId)
                    .executeUpdate();
            transaction.commit();
            if (updatedRows > 0) {
                System.out.println("Share count for video ID " + videoId + " incremented successfully.");
            } else {
                System.out.println("Video ID " + videoId + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }


    public static void main(String[] args) {
        // Tạo instance của DAO (giả sử bạn đã có một đối tượng VideoDao đã được khởi tạo)
        VideosDao videoDao = new VideosDao();

        // ID của video cần xóa
        String keyword = "hay";
        List<VideoEntity> results = videoDao.searchByTitle(keyword);
        if (results.isEmpty()) {
            System.out.println("No videos found with the keyword: " + keyword);
        } else {
            System.out.println("Videos found with the keyword '" + keyword + "':");
            for (VideoEntity video : results) {
                System.out.println("Video Title: " + video.getTitle());
            }
        }
    }
    public List<VideoEntity> findActiveVideos() {
        // Sử dụng JPQL để truy vấn các video có active = true
        String jpql = "SELECT v FROM VideoEntity v WHERE v.active = true";
        TypedQuery<VideoEntity> query = em.createQuery(jpql, VideoEntity.class);
        return query.getResultList();
    }
}








