package com.example.gestionbibliotheque.Model;

import java.io.Serializable;

public class Commande implements Serializable {
    private String ID, Title, Date_commande, Date_livraison, nbExemplaire;

    public Commande(String ID, String title , String nbExemplaire, String date_commande, String date_livraison) {
        this.ID = ID;
        this.Title = title;
        this.nbExemplaire = nbExemplaire;
        this.Date_commande = date_commande;
        this.Date_livraison = date_livraison;
    }

    public String getID() {
        return ID;
    }

    public String getTitle() {
        return Title;
    }

    public String getDate_commande() {
        return Date_commande;
    }

    public String getDate_livraison() {
        return Date_livraison;
    }

    public String getNbExemplaire() {
        return nbExemplaire;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDate_commande(String date_commande) {
        Date_commande = date_commande;
    }

    public void setDate_livraison(String date_livraison) {
        Date_livraison = date_livraison;
    }

    public void setNbExemplaire(String nbExemplaire) {
        this.nbExemplaire = nbExemplaire;
    }
}
