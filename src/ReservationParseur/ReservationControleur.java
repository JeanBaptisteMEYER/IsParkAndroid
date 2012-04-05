package ReservationParseur;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
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

import Model.QRCodeDownload;
import Model.Reservation;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;


public class ReservationControleur {
	private Handler syncHandler = new Handler();
	private String urlString;
	private Reservation reservation;
	private QRCodeDownload qrCodeDown;
	private String nomParking;
	
	public ReservationControleur(Handler syncHandler, String idParking, String nomParking, String temps, String datedebut, String idUser){
		this.syncHandler=syncHandler;
		this.reservation = new Reservation();
		this.urlString = "http://natanelpartouche.com/API_ISPARK/API_ISPARK_OLD/Action/ActionReservation.php?Action=faire&idParking="+ idParking +"&temps=" + temps + "&datedebut=" + datedebut + "&idUtilisateur=" + idUser;
		this.nomParking = nomParking;
	}

	public Runnable parseReservation = new Runnable(){
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
			// On défini l'url du fichier XML
			URL url = null;
			try {
				url = new URL(urlString);
			} catch (MalformedURLException e1) {
				e1.printStackTrace();
			}
			/*
			 * Le handler sera gestionnaire du fichier XML c'est à dire que c'est lui qui sera chargé
			 * des opérations de parsing. On vera cette classe en détails ci après.
			*/
			ReservationParser handler = new ReservationParser();
			try {
				// On parse le fichier XML
				System.out.println(urlString);
				
				InputStream in = url.openStream();
				System.out.println(in);
				parseur.parse(in, handler);
				// On récupère directement la liste des feeds
				reservation = handler.getReservation();
				reservation.setNomParking(nomParking);
				System.out.println("Control get reservation");
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				
			}
			
			Message msg = syncHandler.obtainMessage();
			syncHandler.sendMessage(msg);
		}
	};
	
	public Reservation getReservation(){
		// On la retourne l'array list
		return this.reservation;
	}
}
