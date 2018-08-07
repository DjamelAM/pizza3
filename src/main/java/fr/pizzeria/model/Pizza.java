package fr.pizzeria.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pizzas")
public class Pizza {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "CODE")
	private String code;
	@Column(name = "NOM")
	private String nom;
	@Column(name = "PRIX")
	private double prix;

	@Enumerated(EnumType.STRING)
	private CategoriePizza categorie;

	public Pizza() {

	}

	public Pizza(String nvCode, String nvLibelle, double nvPrix, CategoriePizza categorie) {
		code = nvCode.toUpperCase();
		nom = nvLibelle;
		prix = nvPrix;
		this.categorie = categorie;
	}

	public Pizza(int nvId, String nvCode, String nvLibelle, double nvPrix, CategoriePizza categorie) {
		id = nvId;
		code = nvCode.toUpperCase();
		nom = nvLibelle;
		prix = nvPrix;
		this.categorie = categorie;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLibelle() {
		return nom;
	}

	public void setLibelle(String libelle) {
		this.nom = libelle;
	}

	public double getPrix() {
		return prix;
	}

	public void setPrix(double prix) {
		this.prix = prix;
	}

	public CategoriePizza getCategorie() {
		return categorie;
	}

	public void setCategorie(CategoriePizza categorie) {
		this.categorie = categorie;
	}

	public String toString() {
		return code + " -> " + nom + "(" + prix + " \u20ac)" + "cat�gorie : " + categorie;
	}

}
