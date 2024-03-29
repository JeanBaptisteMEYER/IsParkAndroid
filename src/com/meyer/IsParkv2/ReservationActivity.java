package com.meyer.IsParkv2;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.meyer.IsParkv2.R;
import com.meyer.IsParkv2.R.anim;
import com.meyer.IsParkv2.R.id;
import com.meyer.IsParkv2.R.layout;

import InfoParkParseur.InfoPlaceControleur;
import Model.Parking;
import Model.SeekBarAction;
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
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.TimePicker;

public class ReservationActivity extends Activity implements OnSeekBarChangeListener{
	private InfoPlaceControleur infoPlaceControl;
	private NbPlaceControleur nbPlaceControl;
	private SeekBarAction seekBarAction;
	private SeekBar hourSeekPicker;
	private Parking parking;
	private int nbPlaceDispo;
	private String nomParking;
	private String idPark;
	private String idUser = "1";
	private String url;
	private TimePicker tp;
	private String whichGroup;
	private TextView hour;
	
	/*private Handler nbPlaceHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			nbPlaceDispo=nbPlaceControl.getData();
			//setNbPlace();
			System.out.println("ReservA = Parsing nb place done");
			TextView tvNbPlace = (TextView) findViewById(R.id.parknbplace);
			tvNbPlace.setText("Nombre de places disponibles : " + String.valueOf(nbPlaceDispo));
		}
	};*/
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.zoom_enter, R.anim.zoom_exit);
		setContentView(R.layout.reservationlayout);
		
		System.out.println("ReservA = onCreate resAct");
		
		//On r�cup�re l�Intent que l�on a re�u
		Intent thisIntent = getIntent();
		//On r�cup�re l'extra gr�ce � son id
		idPark = thisIntent.getExtras().getString("idPark");
		
		System.out.println("ReservA = GetExtra Parking :" + parking);
		
		TextView tvNom = (TextView) findViewById(R.id.parknom);
		TextView tvTelephone = (TextView) findViewById(R.id.parktelephone);
		TextView tvAdresse = (TextView) findViewById(R.id.parkadresse);
		//tp = (TimePicker) findViewById(R.id.timePicker);
		
		nomParking = thisIntent.getExtras().getString("nom");
		System.out.println("ReservA = GetExtra Nom du Parking :" + nomParking);
		
		tvNom.setText(nomParking);
		tvTelephone.setText(thisIntent.getExtras().getString("adresse"));
		tvAdresse.setText(thisIntent.getExtras().getString("telephone"));
		
		whichGroup = thisIntent.getExtras().getString("whichGroup");
		
		//capture de l'heure
		hourSeekPicker = (SeekBar) findViewById(R.id.hourBarPiker);
		hourSeekPicker.setProgress(1);
		hourSeekPicker.setOnSeekBarChangeListener(this);
		hour = (TextView) findViewById(R.id.hour);
		
		/***********************************************/
        /*
        //parsing du nombre de place dispo
  		//parsing
        nbPlaceControl = new NbPlaceControleur(nbPlaceHandler, idPark);
	    Thread nbPlaceTh = new Thread(nbPlaceControl.parseData,"ParseBackground");
	    nbPlaceTh.start();*/
	    /***********************************************/
        
	    //On cr�e le Listener sur le Bouton
	    OnClickListener bReservationListener = new OnClickListener()
	    {
		    @Override
			public void onClick(View v) {
		    	System.out.println("ReservA = Button reserver pressed");
		    	faireReservation();
			}
	    };
	     
	    //On r�cupere le bouton souhait� et on lui affecte le Listener
	    Button bReservation = (Button) findViewById(R.id.bReserver);
	    bReservation.setOnClickListener(bReservationListener);
	}
	
	public void faireReservation() {
		//get hour
		System.out.println("start faireReservation");
		String heure = new String();
		//heure = String.valueOf(tp.getCurrentHour());
		heure = String.valueOf(hourSeekPicker.getProgress());
		System.out.println("ReservA = Heure : " + heure);
		//make date
		/*String date = new String();
		Date dateActuelle = new Date();
		date = String.valueOf(String.valueOf(dateActuelle.getYear() + 1900) + "-" + String.valueOf(dateActuelle.getMonth()) + "-" + String.valueOf(dateActuelle.getDay()));
		*/
		Calendar c = Calendar.getInstance();
		String date = String.valueOf(c.get(Calendar.YEAR)) + "-" + String.valueOf(c.get(Calendar.MONTH)+1) + "-" + String.valueOf(c.get(Calendar.DATE));
		System.out.println("ReservA = Date : " + date);
		
		// On met en place le passage entre les deux activit�s sur ce Listener
	    Intent intent = new Intent(ReservationActivity.this, FinReservationActivity.class);
	    
	    //idParking=1&temps=1&datedebut=1&idUtilisateur=1
	    intent.putExtra("idParking", this.idPark);
	    intent.putExtra("temps", heure);
	    intent.putExtra("datedebut", date);
	    intent.putExtra("idUser", this.idUser);
	    intent.putExtra("nomParking", this.nomParking);
	    System.out.println("Nom du parking pass� a finReservation : " + this.nomParking);
	    
	    if (whichGroup=="inReservationGroup") {
	    	ReservationGroup parentActivity = (ReservationGroup)getParent();
		    parentActivity.goNextActivity(intent);
		}
	    
	    if (whichGroup=="inMapGroup") {
	    	MapGroup parentActivity = (MapGroup) getParent();
		    parentActivity.goNextActivity(intent);
		}
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		// TODO Auto-generated method stub
		hour.setText("Heure d'arriv�e prevue dans " + progress + "h");
		System.out.println("Seekbar change" + progress);
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar arg0) {
		// TODO Auto-generated method stub
		
	}
}
