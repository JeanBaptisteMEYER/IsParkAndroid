package com.meyer.IsParkv2;

import java.util.Date;
import java.util.List;

import InfoParkParseur.InfoPlaceControleur;
import Model.Parking;
import NbPlaceParseur.NbPlaceControleur;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class ReservationActivity extends Activity {
	private InfoPlaceControleur infoPlaceControl;
	private NbPlaceControleur nbPlaceControl;
	private Parking parking;
	private int nbPlaceDispo;
	private String idPark;
	private String idUser = "1";
	private String url;
	private Handler nbPlaceHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			nbPlaceDispo=nbPlaceControl.getData();
			//setNbPlace();
			System.out.println("Parsing nb place done");
			TextView tvNbPlace = (TextView) findViewById(R.id.parknbplace);
			tvNbPlace.setText("Nombre de places disponibles : " + String.valueOf(nbPlaceDispo));
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reservationlayout);
		System.out.println("onCreate resAct");
		
		//On récupère l’Intent que l’on a reçu
		Intent thisIntent = getIntent();
		//On récupère l'extra grâce à son id
		idPark = thisIntent.getExtras().getString("idPark");
		
		System.out.println("GetExtra Parking :" + parking);
		
		TextView tvNom = (TextView) findViewById(R.id.parknom);
		TextView tvTelephone = (TextView) findViewById(R.id.parktelephone);
		TextView tvAdresse = (TextView) findViewById(R.id.parkadresse);
		
		tvNom.setText(thisIntent.getExtras().getString("nom"));
		tvTelephone.setText(thisIntent.getExtras().getString("adresse"));
		tvAdresse.setText(thisIntent.getExtras().getString("telephone"));
		
		/***********************************************/
		//Parsing des info
		//construction de l'url
		/*url = new String("http://natanelpartouche.com/API_ISPARK/API_ISPARK_OLD/Action/ActionParking.php?Action=infopark&id=");
		url = url + String.valueOf(idPark);
		
		System.out.println(url);
		//parsing
        infoPlaceControl = new InfoPlaceControleur(infoParkHandler,url);
        Thread thread = new Thread(infoPlaceControl.parseData,"ParseBackground");
        thread.start();*/
        
        /***********************************************/
        /***********************************************/
        
        //parsing du nombre de place dispo
  		//parsing
        nbPlaceControl = new NbPlaceControleur(nbPlaceHandler, idPark);
	    Thread nbPlaceTh = new Thread(nbPlaceControl.parseData,"ParseBackground");
	    nbPlaceTh.start();
	    /***********************************************/
        
	    //On crée le Listener sur le Bouton
	    OnClickListener bReservationListener = new OnClickListener()
	    {
		    @Override
			public void onClick(View v) {
		    	faireReservation();
			}
	    };
	     
	    //On récupere le bouton souhaité et on lui affecte le Listener
	    Button bReservation = (Button) findViewById(R.id.bReserver);
	    bReservation.setOnClickListener(bReservationListener);
	}
	
	public void faireReservation() {
		//get hour
		TimePicker tp = (TimePicker) findViewById(R.id.timePicker);
		String heure = new String();
		heure = String.valueOf(tp.getCurrentHour());
		System.out.println("Heure : " + heure);
		//make date
		String date = new String();
		Date dateActuelle = new Date();
		date = String.valueOf(dateActuelle.getDay()) + "-" + String.valueOf(dateActuelle.getMonth()) + "-" + String.valueOf(dateActuelle.getYear()) + "-" + String.valueOf(dateActuelle.getHours()) + ":" + String.valueOf(dateActuelle.getMinutes());
		System.out.println("Date : " + date);
		
		idPark="8";
		
		// On met en place le passage entre les deux activités sur ce Listener
	    Intent intent = new Intent(ReservationActivity.this, FinReservationActivity.class);
	    //idParking=1&temps=1&datedebut=1&idUtilisateur=1
	    intent.putExtra("idParking", this.idPark);
	    intent.putExtra("temps", heure);
	    intent.putExtra("datedebut", date);
	    intent.putExtra("idUser", this.idUser);
	    startActivity(intent);
	    
	}
}
