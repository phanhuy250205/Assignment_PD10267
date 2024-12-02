package until;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class XJPA {

    // Static EntityManagerFactory, initialized only once
    private static EntityManagerFactory emf;

    static {
        try {
            emf = Persistence.createEntityManagerFactory("QLVideo"); // Initialize once
        } catch (Exception e) {
            throw new IllegalStateException("Failed to create EntityManagerFactory", e);
        }
    }

    // Method to get EntityManager
    public static EntityManager getEntityManager() {
        if (emf == null) {
            throw new IllegalStateException("EntityManagerFactory is not initialized");
        }
        return emf.createEntityManager();
    }

    // Method to close EntityManagerFactory
    public static void close() {
        if (emf != null && emf.isOpen()) { // Check if it’s open before closing
            emf.close();
        }
    }
}
