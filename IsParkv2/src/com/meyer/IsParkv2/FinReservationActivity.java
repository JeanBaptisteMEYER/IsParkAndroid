package com.meyer.IsParkv2;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import NbPlaceParseur.NbPlaceControleur;
import ReservationParseur.Reservation;
import ReservationParseur.ReservationControleur;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

public class FinReservationActivity extends Activity {
	
	private ImageView qRCode;
	private String idParking;
	private String idUser;
	private String temps;
	private String datedebut;
	private ReservationControleur reservationControl;
	private Reservation reservation;
	private Handler reservationHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			reservation = reservationControl.getReservation();
			downloadImage();
			System.out.println(reservation);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.finreservationlayout);
		
		qRCode = (ImageView) findViewById(R.id.imageView);
		
		//On récupère l’Intent que l’on a reçu
		Intent thisIntent = getIntent();
		//On récupère l'extra grâce à son id
		idParking = thisIntent.getExtras().getString("idParking");
		temps = thisIntent.getExtras().getString("temps");
		datedebut = thisIntent.getExtras().getString("datedebut");
		idUser = thisIntent.getExtras().getString("idUser");
		System.out.println(idParking+"/"+temps+"/"+datedebut+"/"+idUser);
		
		//parsing
        /*reservationControl = new ReservationControleur (reservationHandler, idParking, temps, datedebut, idUser);
	    Thread nbPlaceTh = new Thread(reservationControl.parseReservation,"ParseBackground");
	    nbPlaceTh.start();*/
	}
	
	private void downloadImage() {
		Bitmap bitmap = null;

		try {
			URL urlImage = new URL("http://natanelpartouche.com/API_ISPARK/API_ISPARK_OLD/Reservations/WX13062349.png");
			HttpURLConnection connection = (HttpURLConnection) urlImage.openConnection();
			
			InputStream inputStream = connection.getInputStream();
			bitmap = BitmapFactory.decodeStream(inputStream);
			qRCode.setImageBitmap(bitmap);

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
