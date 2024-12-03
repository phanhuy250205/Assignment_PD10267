package Dao;

import entity.Sharesentity;
import entity.VideoEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;
import until.XJPA;

import java.util.List;

public class ShareDao implements GenericDao<Sharesentity> {
    private EntityManager em = XJPA.getEntityManager();

    @Override
    public List<Sharesentity> findAll() {
        return em.createQuery("SELECT s FROM Sharesentity s", Sharesentity.class).getResultList();
    }

    @Override
    public Sharesentity findById(String id) {
        return em.find(Sharesentity.class, id);
    }

    @Override
    public void create(Sharesentity item) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(item);
        transaction.commit();
    }

    @Override
    public void update(Sharesentity item) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.merge(item);
        transaction.commit();
    }

    @Override
    public boolean deleteById(int id) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        Sharesentity share = findById(String.valueOf(id));
        if (share != null) {
            em.remove(share);
        }
        transaction.commit();
        return share != null;
    }

    public List<Sharesentity> findAllSharesWithVideo() {
        String jpql = "SELECT s FROM Sharesentity s JOIN s.videosentity v";
        TypedQuery<Sharesentity> query = em.createQuery(jpql, Sharesentity.class);
        return query.getResultList();
    }
    public void updateViews(int videoId) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();

        // Lấy video từ cơ sở dữ liệu theo ID
        VideoEntity video = em.find(VideoEntity.class, videoId);

        if (video != null) {
            // Tăng lượt xem lên 1
            video.setViews(video.getViews() + 1);

            // Cập nhật video vào cơ sở dữ liệu
            em.merge(video);
        }

        transaction.commit();
    }

}
