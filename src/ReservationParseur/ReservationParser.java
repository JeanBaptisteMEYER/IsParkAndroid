package ReservationParseur;


import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import Model.Reservation;

public class ReservationParser extends DefaultHandler {
	
	private static final String REPONSE = "Reponse";
	private final String IDRESERVATION;
	private final String DATEDEBUT;
	private final String CODE;
	private final String PRIX;
	//bug !!!!
	private final String QRCODE;
	//Private final String QRCODE = ""
		
	Reservation reservation;
	private boolean inItem;
	private boolean inTemps;
	private StringBuffer buffer;
	
	
	public ReservationParser(){
		super();
		inItem = false;
		inTemps = false;
		buffer = new StringBuffer();
		this.reservation = new Reservation();
		IDRESERVATION = "idreservation";
		DATEDEBUT = "datedebut";
		CODE = "code";
		PRIX = "prix";
		QRCODE = "temps";
	}
	
	
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("Start docuemnt");

	}
	
	@Override
	public void startElement(String uri, String localName, String name,	Attributes attributes) throws SAXException {
		buffer = new StringBuffer();	
//		System.out.println("Start Element :" + name);

		// Nous avons rencontré un tag PARKING, il faut donc instancier un nouveau objet parking
		if (name.equalsIgnoreCase(REPONSE)){
			System.out.println("Go pour place");
			inItem = true;
		}
		if (name.equalsIgnoreCase(QRCODE)){
			System.out.println("Go pour qrcode");
			inTemps = true;
		}
	}
	
	
	@Override
	public void endElement(String uri, String localName, String name) throws SAXException {		
		System.out.println("End Element name :" + name);

		if (name.equalsIgnoreCase(IDRESERVATION)){
			if(inItem){
				// Les caractères sont dans l'objet buffer
				System.out.println ("Got an ID!");
				this.reservation.setIdReservation(buffer.toString());
				buffer = null;
			}
		}
		
		if (name.equalsIgnoreCase(DATEDEBUT)){
			if(inItem){
				// Les caractères sont dans l'objet buffer
				System.out.println ("Got an Datedebut!");
				this.reservation.setDateDebut(buffer.toString());
				buffer = null;
			}
		}
		
		if (name.equalsIgnoreCase(CODE)){
			if(inItem){
				// Les caractères sont dans l'objet buffer
				System.out.println ("Got an Caode!");
				this.reservation.setCode(buffer.toString());
				buffer = null;
			}
		}
		
		if (name.equalsIgnoreCase(PRIX)){
			if(inItem){
				// Les caractères sont dans l'objet buffer
				System.out.println ("Got an prix!");
				this.reservation.setPrix(buffer.toString());
				buffer = null;
			}
		}
		if (name.equalsIgnoreCase(QRCODE)){
			if(inItem){
				if(inTemps){
					// Les caractères sont dans l'objet buffer
					System.out.println ("Got an qrCode!");
					this.reservation.setqRCode(buffer.toString());
					buffer = null;
				}
			}
		}
		
		if (name.equalsIgnoreCase(REPONSE)){
			inItem = false;
		}
	}
	
	
	public void characters(char[] ch,int start, int length)	throws SAXException
	{
		buffer = new StringBuffer (new String (ch,start,length));
		System.out.println ("END CHARACTERS, BUFFER IS (" + buffer + ")");
	}
	
	
	public Reservation getReservation() {
		// TODO Auto-generated method stub
		return this.reservation;
	}

}
