package com.meyer.IsParkv2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Model.QRCodeDownload;
import Model.Reservation;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HistoReservationActivity extends Activity {
	private TextView tvIdReservation;
	private TextView tvPrix;
	private TextView tvCode;
	private Button bRetour;
	private ImageView ivQRCode;
	
	private String idParking;
	private String idReservation;
	private String heureArrivee;
	private String prix;
	private String code;
	private String qRCode;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.historeservationlayout);
		
		ivQRCode = (ImageView) findViewById(R.id.qRCode);
		tvIdReservation = (TextView) findViewById(R.id.idReservation);
		tvPrix = (TextView) findViewById(R.id.prix);
		tvCode = (TextView) findViewById(R.id.code);
		bRetour = (Button) findViewById(R.id.bRetour);
		
		//On récupère l’Intent que l’on a reçu
		Intent thisIntent = getIntent();
		//On récupère l'extra grâce à son id
		idParking = thisIntent.getExtras().getString("idParking");
		idReservation = thisIntent.getExtras().getString("idReservation");
		heureArrivee = thisIntent.getExtras().getString("HeureArrivee");
		prix = thisIntent.getExtras().getString("Prix");
		code = thisIntent.getExtras().getString("Code");
		qRCode = thisIntent.getExtras().getString("QRCode");
		
		tvIdReservation.setText("Id de la réservation : " + idReservation);
		tvPrix.setText("Prix : " + prix  + "€");
		tvCode.setText("Code de la reservation : " + code);
		
		try{
			System.out.println("Start read bitmap");
			FileInputStream in = new FileInputStream(Environment.getExternalStorageDirectory().toString() + "/IsPark/" + qRCode);
			BufferedInputStream buf = new BufferedInputStream(in);
			ivQRCode.setImageBitmap(BitmapFactory.decodeStream(buf));
			
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		bRetour.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(HistoReservationActivity.this,HistoActivity.class);
				
				HistoriqueGroup parentActivity = (HistoriqueGroup)getParent();
			    parentActivity.goBackActivity(intent);
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		System.out.println("BackPressed in" + this.getTitle());
	}
}
