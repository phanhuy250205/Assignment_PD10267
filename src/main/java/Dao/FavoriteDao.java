package Dao;

import entity.Favoritesentity;
import entity.Usersentity;
import entity.VideoEntity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import until.XJPA;

import java.util.List;

public class FavoriteDao  implements  GenericDao<Favoritesentity>{
    private EntityManager em = XJPA.getEntityManager();
    @Override
    public List<Favoritesentity> findAll() {
        return em.createQuery("SELECT f FROM Favoritesentity f", Favoritesentity.class).getResultList();
    }

    @Override
    public Favoritesentity findById(String id) {
        return em.find(Favoritesentity.class, id);
    }

    @Override
    public void create(Favoritesentity item) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            if (isFavoriteExists(item.getUser(), item.getVideo())) {
                throw new RuntimeException("User đã thích video này rồi!");
            }

            em.persist(item);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Error adding favorite", e);
        }
    }

    @Override
    public void update(Favoritesentity item) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(item);
        transaction.commit();
    }

    @Override
    public boolean deleteById(int id) {
        EntityTransaction transaction = em.getTransaction();
        boolean isDeleted = false;

        try {
            transaction.begin(); // Bắt đầu giao dịch

            // Tìm đối tượng yêu thích theo id
            Favoritesentity favorite = findById(String.valueOf(id));

            if (favorite != null) {
                // Xóa đối tượng yêu thích
                em.remove(favorite);
                isDeleted = true; // Đánh dấu là đã xóa thành công
            }

            transaction.commit(); // Commit giao dịch
        } catch (Exception e) {
            // Nếu có lỗi, rollback giao dịch
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace(); // Ghi lỗi vào log
        }

        return isDeleted; // Trả về true nếu xóa thành công, false nếu không tìm thấy hoặc có lỗi
    }


    public List<Favoritesentity> findFavoritesByUser(Usersentity user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null");
        }

        String jpql = "SELECT f " +
                "FROM Favoritesentity f " +
                "JOIN f.video v " +
                "WHERE f.user = :user";

        TypedQuery<Favoritesentity> query = em.createQuery(jpql, Favoritesentity.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    public boolean isFavoriteExists(Usersentity user, VideoEntity video) {
        if (user == null || video == null) {
            return false;  // Trả về false nếu user hoặc video là null
        }

        try {
            // JPQL query kiểm tra xem video có tồn tại trong danh sách yêu thích của người dùng
            String jpql = "SELECT COUNT(f) FROM Favoritesentity f WHERE f.user = :user AND f.video = :video";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("user", user);
            query.setParameter("video", video);

            // Lấy số lượng bản ghi
            Long count = query.getSingleResult();

            // Nếu có ít nhất một bản ghi (nghĩa là video đã được yêu thích)
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();  // Ghi log nếu có lỗi trong quá trình truy vấn
            return false;  // Trả về false nếu có lỗi trong truy vấn
        }
    }

    public boolean isVideoFavoritedByUser(Long userId, Long videoId) {
        if (userId == null || videoId == null) {
            return false;  // Trả về false nếu userId hoặc videoId là null
        }

        try {
            // JPQL query kiểm tra xem video có tồn tại trong danh sách yêu thích của người dùng
            String jpql = "SELECT COUNT(f) FROM Favoritesentity f WHERE f.user.id = :userId AND f.video.id = :videoId";
            TypedQuery<Long> query = em.createQuery(jpql, Long.class);
            query.setParameter("userId", userId);
            query.setParameter("videoId", videoId);

            // Lấy số lượng bản ghi
            Long count = query.getSingleResult();

            // Nếu có ít nhất một bản ghi (nghĩa là video đã được yêu thích)
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();  // Ghi log nếu có lỗi trong quá trình truy vấn
            return false;  // Trả về false nếu có lỗi trong truy vấn
        }
    }

}
