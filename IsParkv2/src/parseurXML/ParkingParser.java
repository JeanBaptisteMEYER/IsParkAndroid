package parseurXML;


import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParkingParser extends DefaultHandler {
	
	private static final String REPONSE = "Reponse";
	private final String PARKING;
	private final String ID;
	private final String NOM;
	private final String TELEPHONE;
	private final String LOGO;
	private final String ADRESSE;
	
	private List<Parking> listeParking;
	private boolean inItem;
	private Parking parking;
	private StringBuffer buffer;
	
	
	public ParkingParser(){
		super();
		PARKING = "Parking";
		ID = "idparking";
		NOM = "nomparking";
		TELEPHONE = "telephone";
		LOGO = "logo";
		ADRESSE = "adresse";
		inItem = false;
		listeParking = new ArrayList<Parking>();
		buffer = new StringBuffer();
		parking = new Parking();
	}
	
	
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("Start docuemnt");
		listeParking = new ArrayList<Parking>();

	}
	
	@Override
	public void startElement(String uri, String localName, String name,	Attributes attributes) throws SAXException {
		buffer = new StringBuffer();	
//		System.out.println("Start Element :" + name);

		// Nous avons rencontré un tag PARKING, il faut donc instancier un nouveau objet parking
		if (name.equalsIgnoreCase(PARKING)){
			System.out.println("NEW PARKING");
			this.parking = new Parking();
			inItem = true;
		}
	}
	
	
	@Override
	public void endElement(String uri, String localName, String name) throws SAXException {		
		System.out.println("End Element name :" + name);

		if (name.equalsIgnoreCase(ID)){
			if(inItem){
				// Les caractères sont dans l'objet buffer
				System.out.println ("Got an ID!");
				this.parking.setId(buffer.toString());
				buffer = null;
			}
		}
		if (name.equalsIgnoreCase(NOM)){
			if(inItem){
				this.parking.setNom(buffer.toString());
				buffer = null;
			}
		}
		if (name.equalsIgnoreCase(TELEPHONE)){
			if(inItem){
				this.parking.setTelephone(buffer.toString());
				buffer = null;
			}
		}
		if (name.equalsIgnoreCase(LOGO)){
			if(inItem){
				this.parking.setLogo(buffer.toString());
				buffer = null;
			}
		}
		if(name.equalsIgnoreCase(ADRESSE)){
			if(inItem){
				this.parking.setAdresse(buffer.toString());
				buffer = null;
			}
		}
		if (name.equalsIgnoreCase(PARKING)){
			System.out.println("Adding new parking");
			listeParking.add(parking);
			inItem = false;
		}
	}
	
	
	public void characters(char[] ch,int start, int length)	throws SAXException
	{
		buffer = new StringBuffer (new String (ch,start,length));
		System.out.println ("END CHARACTERS, BUFFER IS (" + buffer + ")");
	}
	
	
	public List<Parking> getParkingList() {
		// TODO Auto-generated method stub
		return this.listeParking;
	}

}
