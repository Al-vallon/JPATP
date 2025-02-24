package demoJPA.bo;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "Emprunt")
public class Emprunt implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "ID")
    private int id;
    @Column(name = "DATE_DEBUT")
    private String dateDebut;
    @Column(name = "DATE_FIN")
    private String dateFin;
    @Column(name = "DELAI")
    private int delai;
    @ManyToOne
    @JoinColumn(name = "ID_CLIENT")
    private Client client;

    public Emprunt() {
    }

    public Emprunt(String dateDebut, String dateFin, int delai) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.delai = delai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public int getDelai() {
        return delai;
    }

    public void setDelai(int delai) {
        this.delai = delai;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Emprunt{" +
                "id=" + id +
                ", dateDebut='" + dateDebut + '\'' +
                ", dateFin='" + dateFin + '\'' +
                ", delai=" + delai +
                ", client=" + client +
                '}';
    }


    //map relation many to many
    @ManyToMany
    @JoinTable(name = "COMPO",
            joinColumns = @JoinColumn(name = "ID_EMP", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "ID_LIV", referencedColumnName = "ID"))
    private List<Livre> livres;

    //map relation many to one

    @ManyToOne
    @JoinColumn(name = "ID_CLIENT")
    private Client client;


    public List<Emprunt> getEmprunts() {
        return emprunts;
    }

    public void setEmprunts(List<Emprunt> emprunts) {
        this.emprunts = emprunts;
    }

    public List<Livre> getLivres() {
        return livres;
    }





}