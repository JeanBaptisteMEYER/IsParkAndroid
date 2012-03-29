package NbPlaceParseur;


import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class NbPlaceParser extends DefaultHandler {
	
	private static final String REPONSE = "Reponse";
	private final String NBPLACE = "nombreplacedisponible";
	
	private String nbPlace;
	private boolean inItem;
	private StringBuffer buffer;
	
	
	public NbPlaceParser(){
		super();
		inItem = false;
		buffer = new StringBuffer();
	}
	
	
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
		System.out.println("Start docuemnt");
		nbPlace = "0";

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
	}
	
	
	@Override
	public void endElement(String uri, String localName, String name) throws SAXException {		
		System.out.println("End Element name :" + name);

		if (name.equalsIgnoreCase(NBPLACE)){
			if(inItem){
				// Les caractères sont dans l'objet buffer
				System.out.println ("Got an ID!");
				this.nbPlace = buffer.toString();
				buffer = null;
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
	
	
	public String getNbPlace() {
		// TODO Auto-generated method stub
		return this.nbPlace;
	}

}
