package Dao;

import entity.Usersentity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import until.XJPA;

import java.util.List;

public class UserDao implements  GenericDao<Usersentity> {
    private EntityManager em = XJPA.getEntityManager();
    @Override
    public List<Usersentity> findAll() {
        return em.createQuery("select  u from Usersentity u", Usersentity.class).getResultList();
    }

    @Override
    public Usersentity findById(String id) {
        return em.find(Usersentity.class, id);
    }

    @Override
    public void create(Usersentity item) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(item);
        transaction.commit();
    }

    @Override
    public void update(Usersentity item) {
        if (item == null) {
            throw new IllegalArgumentException("Cannot update a null entity");
        }

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            em.merge(item); // Cập nhật đối tượng vào database
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Rollback nếu có lỗi
            }
            e.printStackTrace();
            throw new RuntimeException("Error updating user entity.", e);
        }
    }




    @Override
    public boolean deleteById(int id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Usersentity user = findById(String.valueOf(id));
        if (user != null) {
            em.remove(user);
        }
        transaction.commit();
        return false;
    }
    public Usersentity findByUsernameAndPassword(String username, String password) {
        // Tạo truy vấn JPQL để tìm kiếm user dựa trên username (email) và password
        String jpql = "SELECT u FROM Usersentity u WHERE u.email = :username AND u.password = :password";

        // Tạo TypedQuery và truyền tham số vào
        TypedQuery<Usersentity> query = em.createQuery(jpql, Usersentity.class);
        query.setParameter("username", username);
        query.setParameter("password", password);

        try {
            // Thực hiện truy vấn và trả về kết quả
            return query.getSingleResult();  // Nếu không tìm thấy, sẽ ném ra NoResultException
        } catch (NoResultException e) {
            // Nếu không tìm thấy người dùng, trả về null
            return null;
        } catch (Exception e) {
            // Xử lý các ngoại lệ khác nếu cần thiết
            e.printStackTrace();  // Có thể ghi log hoặc xử lý ngoại lệ cụ thể ở đây
            return null;
        }
    }
    public boolean changePassword(String email, String oldPassword, String newPassword) {
        try {
            // Tìm người dùng theo email và mật khẩu cũ
            String jpql = "SELECT u FROM Usersentity u WHERE u.email = :email AND u.password = :oldPassword";
            TypedQuery<Usersentity> query = em.createQuery(jpql, Usersentity.class);
            query.setParameter("email", email);
            query.setParameter("oldPassword", oldPassword);

            // Lấy người dùng ra nếu tồn tại
            Usersentity user = query.getSingleResult();

            if (user != null) {
                // Cập nhật mật khẩu mới
                user.setPassword(newPassword);  // Cập nhật mật khẩu mới mà không mã hóa
                em.getTransaction().begin();
                em.merge(user); // Lưu thay đổi vào cơ sở dữ liệu
                em.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false; // Nếu mật khẩu cũ không chính xác hoặc có lỗi
    }

    public boolean updateUserInfo(Long userId, String fullname, String newEmail, String newImagePath) {
        try {
            // Tạo truy vấn JPQL để tìm người dùng dựa trên ID
            String jpql = "SELECT u FROM Usersentity u WHERE u.id = :userId";
            TypedQuery<Usersentity> query = em.createQuery(jpql, Usersentity.class);
            query.setParameter("userId", userId);

            // Tìm người dùng theo ID
            List<Usersentity> results = query.getResultList();
            Usersentity user = results.isEmpty() ? null : results.get(0);

            if (user != null) {
                // In ra để debug
                System.out.println("Người dùng trước khi cập nhật: " + user);

                // Cập nhật thông tin người dùng
                user.setFullname(fullname);  // Cập nhật tên đầy đủ
                user.setEmail(newEmail);     // Cập nhật email mới

                // Kiểm tra nếu có ảnh mới, thì cập nhật
                if (newImagePath != null && !newImagePath.trim().isEmpty()) {
                    user.setImage(newImagePath);  // Cập nhật đường dẫn ảnh mới
                }

                // Nếu giao dịch chưa được bắt đầu, bắt đầu một giao dịch mới
                if (!em.getTransaction().isActive()) {
                    em.getTransaction().begin();
                }

                // Cập nhật đối tượng người dùng vào cơ sở dữ liệu
                em.merge(user);
                em.getTransaction().commit();

                // In ra để kiểm tra
                System.out.println("Người dùng sau khi cập nhật: " + user);

                return true;  // Cập nhật thành công
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Nếu có lỗi, rollback giao dịch
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        }
        return false;  // Nếu không tìm thấy người dùng hoặc có lỗi
    }



}
