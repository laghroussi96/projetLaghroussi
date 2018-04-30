/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author fatima
 */
@Entity
public class Recruteur implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String prenom;
    private String nom;
    private double tel;
    @ManyToOne
    private Pays pays;
    @ManyToOne
    private Admin admin;
    @OneToOne
   private Compte compte;
    @OneToOne
    private User user;
    @OneToMany(mappedBy = "recruteur")
    private List<NotificationCategorie> notificationCategories;
    @OneToMany(mappedBy = "recruteur")
    private List<Mission> missions;
    @OneToMany(mappedBy = "recruteur")
    private List<Paiement> paiements;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateInscription;

    public Compte getCompte() {
        return compte;
    }

    public void setCompte(Compte compte) {
        this.compte = compte;
    }

    public Date getDateInscription() {
        return dateInscription;
    }

    public void setDateInscription(Date dateInscription) {
        this.dateInscription = dateInscription;
    }

    public List<Paiement> getPaiements() {
        return paiements;
    }

    public void setPaiements(List<Paiement> paiements) {
        this.paiements = paiements;
    }

    public Recruteur() {
    }

    public Recruteur(Long id, String prenom, String nom, double tel, Pays pays, Admin admin, User user, Date dateInscription) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.tel = tel;
        this.pays = pays;
        this.admin = admin;
        this.user = user;
        this.dateInscription = dateInscription;
    }

    public Recruteur(Long id) {
        this.id = id;
    }

    public Recruteur(Long id, String prenom, String nom, double tel) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.tel = tel;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getTel() {
        return tel;
    }

    public void setTel(double tel) {
        this.tel = tel;
    }

    public Pays getPays() {
        return pays;
    }

    public void setPays(Pays pays) {
        this.pays = pays;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<NotificationCategorie> getNotificationCategories() {
        return notificationCategories;
    }

    public void setNotificationCategories(List<NotificationCategorie> notificationCategories) {
        this.notificationCategories = notificationCategories;
    }

    public List<Mission> getMissions() {
        return missions;
    }

    public void setMissions(List<Mission> missions) {
        this.missions = missions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recruteur)) {
            return false;
        }
        Recruteur other = (Recruteur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Recruteur{" + "id=" + id + ", prenom=" + prenom + ", nom=" + nom + ", tel=" + tel + '}';
    }

}
