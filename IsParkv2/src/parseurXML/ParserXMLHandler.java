package parseurXML;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class ParserXMLHandler extends DefaultHandler {
	
	private final String REPONSE;
	private final String PARKING;
	private final String ID;
	private final String NOM;
	private final String TELEPHONE;
	private final String LOGO;
	private final String ADRESSE;
	
	private ArrayList listeParking;
	private boolean inItem;
	private Parking parking;
	private StringBuffer buffer;
	
	
	@Override
	public void processingInstruction(String target, String data) throws SAXException {
		super.processingInstruction(target, data);
	}
	
	
	public ParserXMLHandler(){
		super();
		REPONSE = "Reponse";
		PARKING = "Parking";
		ID = "idparking";
		NOM = "nomparking";
		TELEPHONE = "telephone";
		LOGO = "logo";
		ADRESSE = "adresse";
	}
	
	
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		listeParking = new ArrayList();

	}
	
	
	@Override
	public void startElement(String uri, String localName, String name,	Attributes attributes) throws SAXException {
		buffer = new StringBuffer();		

		// Nous avons rencontré un tag ITEM, il faut donc instancier un nouveau feed
		if (localName.equalsIgnoreCase(PARKING)){
			this.parking = new Parking();
			inItem = true;
		}
	}
	
	
	@Override
	public void endElement(String uri, String localName, String name) throws SAXException {		

		if (localName.equalsIgnoreCase(ID)){
			if(inItem){
				// Les caractères sont dans l'objet buffer
				this.parking.setId(buffer.toString());
				buffer = null;
			}
		}
		if (localName.equalsIgnoreCase(NOM)){
			if(inItem){
				this.parking.setNom(buffer.toString());
				buffer = null;
			}
		}
		if (localName.equalsIgnoreCase(TELEPHONE)){
			if(inItem){
				this.parking.setTelephone(buffer.toString());
				buffer = null;
			}
		}
		if (localName.equalsIgnoreCase(LOGO)){
			if(inItem){
				this.parking.setLogo(buffer.toString());
				buffer = null;
			}
		}
		if(localName.equalsIgnoreCase(ADRESSE)){
			if(inItem){
				this.parking.setAdresse(buffer.toString());
				buffer = null;
			}
		}
		if (localName.equalsIgnoreCase(PARKING)){
			listeParking.add(parking);
			inItem = false;
		}
	}
	
	
	public void characters(char[] ch,int start, int length)	throws SAXException{
		String lecture = new String(ch,start,length);
		if(buffer != null) buffer.append(lecture);
	}
	
	
	public ArrayList getData() {
		// TODO Auto-generated method stub
		return null;
	}

}
