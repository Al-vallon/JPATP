package demoJPA;

import demoJPA.bo.Livre;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class Main {
    public static void main(String[] args) {
        // Création de l'EntityManagerFactory
        try (EntityManagerFactory emf = Persistence.createEntityManagerFactory("demoJPA")) {
            // Création de l'EntityManager
            EntityManager em = emf.createEntityManager();

            try {
                em.getTransaction().begin();  // Début de la transaction

                // 🔹 Création d'un livre
                Livre l = new Livre("clean code", "Bob Martin");
                em.persist(l);

                // 🔹 Récupération d'un livre
                Livre livreTrouve = em.find(Livre.class, 4);
                if (livreTrouve != null) {
                    System.out.println("Livre trouvé: " + livreTrouve);
                }

                // 🔹 Mise à jour d'un livre
                Livre livreAModifier = em.find(Livre.class, 1);
                if (livreAModifier != null) {
                    livreAModifier.setTitre("De la terre à la lune");
                    System.out.println("Livre mis à jour: " + livreAModifier);
                }

                // 🔹 Suppression d'un livre (décommenter si besoin)
                // Livre livreASupprimer = em.find(Livre.class, 1);
                // if (livreASupprimer != null) {
                //     em.remove(livreASupprimer);
                // }

                em.getTransaction().commit();  // Fin de la transaction

            } catch (Exception e) {
                em.getTransaction().rollback();  // Annule la transaction en cas d'erreur
                e.printStackTrace();
            } finally {
                em.close();  // Ferme l'EntityManager
            }
        }
    }
}
