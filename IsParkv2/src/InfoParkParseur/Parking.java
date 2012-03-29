package InfoParkParseur;

public class Parking {
	private String id;
	private String nom;
	private String telephone;
	private String logo;
	private String adresse;
	
	
	public Parking() {
		super();
	}
	
	
	public Parking(String id, String nom, String telephone, String logo,
			String adresse) {
		super();
		this.id = id;
		this.nom = nom;
		this.telephone = telephone;
		this.logo = logo;
		this.adresse = adresse;
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}


	@Override
	public String toString() {
		return "Parking [id=" + id + ", nom=" + nom + ", telephone="
				+ telephone + ", logo=" + logo + ", adresse=" + adresse + "]";
	}	
}
