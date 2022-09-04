package fr.m2i.todoihm.model;

public class Todo {
	
	
	private String nom;
	private String description;
	
	public Todo() {
		
	}
	public Todo(String nom, String description) {
		this.setNom(nom);
		this.setDescription(description);
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
