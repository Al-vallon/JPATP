package demoJPA;

import demoJPA.bo.Livre;
import demoJPA.bo.Emprunt;
import demoJPA.bo.Client;
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

                // test many to many et autres

                Emprunt emprunt em.find(Emprunt.class, 1);
                if (emprunt != null) {
                    System.out.println("Emprunt trouvé: " + emprunt);
                }

                Long clientId = 1L;

                // Requête JPQL pour récupérer les emprunts du client
                TypedQuery<Emprunt> query = entityManager.createQuery(
                        "SELECT e FROM Emprunt e WHERE e.client.id = :clientId", Emprunt.class);
                query.setParameter("clientId", clientId);

                List<Emprunt> emprunts = query.getResultList();

                // Affichage des emprunts
                if (emprunts.isEmpty()) {
                    System.out.println(" Aucun emprunt trouvé pour ce client.");
                } else {
                    System.out.println("" + emprunts.size() + " emprunt(s) :");
                    for (Emprunt emprunt : emprunts) {
                        System.out.println("Emprunt ID: " + emprunt.getId() + " - Date: " + emprunt.getDateEmprunt());

                        // Affichage des livres associés à chaque emprunt
                        for (Livre livre : emprunt.getLivres()) {
                            System.out.println("  " + livre.getTitre() + " de " + livre.getAuteur());
                        }
                    }
                }
            } finally {




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
