package com.meyer.IsParkv2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import Model.QRCodeDownload;
import Model.Reservation;
import NbPlaceParseur.NbPlaceControleur;
import ReservationParseur.ReservationControleur;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;
import android.widget.TextView;

public class FinReservationActivity extends Activity {
	private QRCodeDownload qrCodeDown;
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
			System.out.println("Fin parsing reservation : " + reservation);
			
			qrCodeDown = new QRCodeDownload(reservation.getqRCode(), qrCodeHandler);
			Thread qrCodeTh = new Thread(qrCodeDown.downloadQRCode, "QRCodeDownBackground");
			qrCodeTh.start();
		}
	};
	private Handler qrCodeHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			System.out.println("Fin recup QRcode");
			qRCode.setImageBitmap(qrCodeDown.getQRCode());
			FileOutputStream fos = openFileOutput(reservation.getCode(), Context.MODE_PRIVATE);
			fos.write(string.getBytes());
			fos.close();
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
        reservationControl = new ReservationControleur (reservationHandler, idParking, temps, datedebut, idUser);
	    Thread nbPlaceTh = new Thread(reservationControl.parseReservation,"ParseBackground");
	    nbPlaceTh.start();
	}
}
