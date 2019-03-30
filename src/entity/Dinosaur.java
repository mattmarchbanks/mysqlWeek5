package entity;

public class Dinosaur {

	private int id;
	private String speciesName;
	private String diet;
	private String stance;
	
	public Dinosaur(int id, String speciesName, String diet, String stance) {
		this.setId(id);
		this.setSpeciesName(speciesName);
		this.setDiet(diet);
		this.setStance(stance);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSpeciesName() {
		return speciesName;
	}

	public void setSpeciesName(String speciesName) {
		this.speciesName = speciesName;
	}

	public String getDiet() {
		return diet;
	}

	public void setDiet(String diet) {
		this.diet = diet;
	}

	public String getStance() {
		return stance;
	}

	public void setStance(String stance) {
		this.stance = stance;
	}
	
	
	
}
