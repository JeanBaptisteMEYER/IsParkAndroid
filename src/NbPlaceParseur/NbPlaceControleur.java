package NbPlaceParseur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import android.os.Handler;
import android.os.Message;


public class NbPlaceControleur {
	private int nbPlace;
	private Handler syncHandler = new Handler();
	private String urlString;
	
	public NbPlaceControleur(Handler syncHandler, String idPark){
		this.syncHandler=syncHandler;
		this.urlString = "http://natanelpartouche.com/API_ISPARK/API_ISPARK_OLD/Action/ActionParking.php?Action=infoplace&id=" + idPark;
	}

	public Runnable parseData = new Runnable(){
		public void run(){
			// On passe par une classe factory pour obtenir une instance de sax
			SAXParserFactory fabrique = SAXParserFactory.newInstance();
			SAXParser parseur = null;
			try {
				// On "fabrique" une instance de SAXParser
				parseur = fabrique.newSAXParser();
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			// On d�fini l'url du fichier XML
			URL url = null;
			try {
				url = new URL(urlString);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
			/*
			 * Le handler sera gestionnaire du fichier XML c'est � dire que c'est lui qui sera charg�
			 * des op�rations de parsing. On vera cette classe en d�tails ci apr�s.
			*/
			NbPlaceParser handler = new NbPlaceParser();
			try {
				// On parse le fichier XML
				System.out.println(urlString);
				
				InputStream in = url.openStream();
				System.out.println(in);
				parseur.parse(in, handler);
				// On r�cup�re directement la liste des feeds
				nbPlace = Integer.parseInt(handler.getNbPlace());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Message msg = syncHandler.obtainMessage();
			syncHandler.sendMessage(msg);
		}
		
	};
	
	public int getData(){
		// On la retourne l'array list
		return this.nbPlace;
	}
}
