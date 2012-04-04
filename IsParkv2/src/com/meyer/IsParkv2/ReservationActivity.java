package com.meyer.IsParkv2;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.meyer.IsParkv2.R;
import com.meyer.IsParkv2.R.anim;
import com.meyer.IsParkv2.R.id;
import com.meyer.IsParkv2.R.layout;

import InfoParkParseur.InfoPlaceControleur;
import Model.Parking;
import NbPlaceParseur.NbPlaceControleur;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
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
	private TimePicker tp;;
	
	private Handler nbPlaceHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			nbPlaceDispo=nbPlaceControl.getData();
			//setNbPlace();
			System.out.println("ReservA = Parsing nb place done");
			TextView tvNbPlace = (TextView) findViewById(R.id.parknbplace);
			tvNbPlace.setText("Nombre de places disponibles : " + String.valueOf(nbPlaceDispo));
		}
	};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
		setContentView(R.layout.reservationlayout);
		
		System.out.println("ReservA = onCreate resAct");
		
		//On récupère l’Intent que l’on a reçu
		Intent thisIntent = getIntent();
		//On récupère l'extra grâce à son id
		idPark = thisIntent.getExtras().getString("idPark");
		
		System.out.println("ReservA = GetExtra Parking :" + parking);
		
		TextView tvNom = (TextView) findViewById(R.id.parknom);
		TextView tvTelephone = (TextView) findViewById(R.id.parktelephone);
		TextView tvAdresse = (TextView) findViewById(R.id.parkadresse);
		tp = (TimePicker) findViewById(R.id.timePicker);
		
		tvNom.setText(thisIntent.getExtras().getString("nom"));
		tvTelephone.setText(thisIntent.getExtras().getString("adresse"));
		tvAdresse.setText(thisIntent.getExtras().getString("telephone"));
		
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
		    	System.out.println("ReservA = Button reserver pressed");
		    	faireReservation();
			}
	    };
	     
	    //On récupere le bouton souhaité et on lui affecte le Listener
	    Button bReservation = (Button) findViewById(R.id.bReserver);
	    bReservation.setOnClickListener(bReservationListener);
	}
	
	public void faireReservation() {
		//get hour
		System.out.println("start faireReservation");
		String heure = new String();
		heure = String.valueOf(tp.getCurrentHour());
		System.out.println("ReservA = Heure : " + heure);
		//make date
		/*String date = new String();
		Date dateActuelle = new Date();
		date = String.valueOf(String.valueOf(dateActuelle.getYear() + 1900) + "-" + String.valueOf(dateActuelle.getMonth()) + "-" + String.valueOf(dateActuelle.getDay()));
		*/
		Calendar c = Calendar.getInstance();
		String date = String.valueOf(c.get(Calendar.YEAR)) + "-" + String.valueOf(c.get(Calendar.MONTH)+1) + "-" + String.valueOf(c.get(Calendar.DATE));
		System.out.println("ReservA = Date : " + date);
		
		// On met en place le passage entre les deux activités sur ce Listener
	    Intent intent = new Intent(ReservationActivity.this, FinReservationActivity.class);
	    
	    //idParking=1&temps=1&datedebut=1&idUtilisateur=1
	    intent.putExtra("idParking", this.idPark);
	    intent.putExtra("temps", heure);
	    intent.putExtra("datedebut", date);
	    intent.putExtra("idUser", this.idUser);
	    
	    ReservationGroup parentActivity = (ReservationGroup)getParent();
	    parentActivity.goNextActivity(intent);
	    
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		System.out.println("ReservA = BackPressed in" + this.getTitle());
		super.onBackPressed();
	}
}
