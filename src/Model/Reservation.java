package Model;

import java.io.Serializable;

public class Reservation implements Serializable{
	private String idReservation;
	private String idParking;
	private String nomParking;
	private String dateDebut;
	private String code;
	private String Prix;
	private String temps;
	private String qRCode;
	
	public Reservation(){};
	
	public Reservation(String idReservation, String idParking,
			String dateDebut, String code, String prix, String temps,
			String qRCode) {
		super();
		this.idReservation = idReservation;
		this.idParking = idParking;
		this.dateDebut = dateDebut;
		this.code = code;
		Prix = prix;
		this.temps = temps;
		this.qRCode = qRCode;
		this.nomParking = null;
	}
	
	public String getIdParking() {
		return idParking;
	}

	public void setIdParking(String idParking) {
		this.idParking = idParking;
	}
	
	public String getNomParking() {
		return this.nomParking;
	}

	public void setNomParking(String nomParking) {
		this.nomParking = nomParking;
	}

	public String getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(String idReservation) {
		this.idReservation = idReservation;
	}

	public String getDateDebut() {
		return dateDebut;
	}

	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getPrix() {
		return Prix;
	}

	public void setPrix(String prix) {
		Prix = prix;
	}

	public String getTemps() {
		return temps;
	}

	public void setTemps(String temps) {
		this.temps = temps;
	}

	public String getqRCode() {
		return qRCode;
	}

	public void setqRCode(String qRCode) {
		this.qRCode = qRCode;
	}

	@Override
	public String toString() {
		return "Reservation [idReservation=" + idReservation + ", idParking="
				+ idParking + ", nomParking=" + nomParking + ", dateDebut="
				+ dateDebut + ", code=" + code + ", Prix=" + Prix + ", temps="
				+ temps + ", qRCode=" + qRCode + "]";
	}
	
}