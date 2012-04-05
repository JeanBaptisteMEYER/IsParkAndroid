package com.meyer.IsParkv2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.meyer.IsParkv2.R;
import com.meyer.IsParkv2.R.anim;
import com.meyer.IsParkv2.R.id;
import com.meyer.IsParkv2.R.layout;

import Model.QRCodeDownload;
import Model.Reservation;
import ReservationParseur.ReservationControleur;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class FinReservationActivity extends Activity {
	private ImageView qRCode;
	private TextView datePaye;
	private TextView heureArrivee;
	private TextView prix;
	private TextView code;
	private Button bValider;
	
	private QRCodeDownload qrCodeDown;
	private String idParking;
	private String nomParking;
	private String idUser;
	private String temps;
	private String datedebut;
	private ReservationControleur reservationControl;
	private Reservation reservation;
	
	private Handler reservationHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			reservation = reservationControl.getReservation();
			System.out.println("FinResA = Fin parsing reservation : " + reservation);
			
			qrCodeDown = new QRCodeDownload(reservation.getqRCode(), qrCodeHandler);
			Thread qrCodeTh = new Thread(qrCodeDown.downloadQRCode, "QRCodeDownBackground");
			qrCodeTh.start();
			
			datePaye.setText(datePaye.getText() + reservation.getDateDebut());
			heureArrivee.setText(heureArrivee.getText() + reservation.getTemps());
			prix.setText(prix.getText() + reservation.getPrix() + "€");
			code.setText(code.getText() + reservation.getCode());
		}
	};
	private Handler qrCodeHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			System.out.println("FinResA = Fin recup QRcode");
			reservation.setNomParking(nomParking);
			try {
				System.out.println("FinResA = Start save reservation");
				
				String path = Environment.getExternalStorageDirectory().toString();
				System.out.println();
				File file = new File(path + "/IsPark/", reservation.getCode() + ".res");
				
				FileOutputStream fos = new FileOutputStream(file);
				ObjectOutputStream os = new ObjectOutputStream(fos);
				os.writeObject(reservation);
				os.close();
				System.out.println("fos.close(). It's the END");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				String fileNameBmp = reservation.getqRCode();
				String path = Environment.getExternalStorageDirectory().toString();
				File file = new File(path + "/IsPark/", fileNameBmp);
				
				FileOutputStream out = new FileOutputStream(file);
			    qrCodeDown.getQRCode().compress(Bitmap.CompressFormat.PNG, 90, out);
			    out.close();
			    System.out.println("FinResA = end save bitmap");
			} catch (Exception e) {
			    e.printStackTrace();
			}
			
			try{
				System.out.println("Start read bitmap");
				FileInputStream in = new FileInputStream(Environment.getExternalStorageDirectory().toString() + "/IsPark/" + reservation.getqRCode());
				BufferedInputStream buf = new BufferedInputStream(in);
				qRCode.setImageBitmap(BitmapFactory.decodeStream(buf));
				
			} catch (Exception e) {
			    e.printStackTrace();
			}
			
			
			try{
				System.out.println("Start read reservation");
				
				String path = Environment.getExternalStorageDirectory().toString();
				File file = new File(path + "/IsPark/", reservation.getCode() + ".res");
				
				System.out.println(file.getAbsolutePath());
				
				FileInputStream in = new FileInputStream(file);
				ObjectInputStream oi = new ObjectInputStream(in);
				Reservation restemp = (Reservation) oi.readObject();
				oi.close();
				
				System.out.println(restemp.getIdReservation());
				
			} catch (Exception e) {
			    e.printStackTrace();
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		System.out.println("Oncreate finreservation");
		
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.fadein, R.anim.fadeout);
		setContentView(R.layout.finreservationlayout);
		
		qRCode = (ImageView) findViewById(R.id.qRCode);
		datePaye = (TextView) findViewById(R.id.datePaye);
		heureArrivee = (TextView) findViewById(R.id.heureArrivee);
		prix = (TextView) findViewById(R.id.prix);
		code = (TextView) findViewById(R.id.code);
		bValider = (Button) findViewById(R.id.bValider);
		
		//On récupère l’Intent que l’on a reçu
		Intent thisIntent = getIntent();
		//On récupère l'extra grâce à son id
		idParking = thisIntent.getExtras().getString("idParking");
		nomParking = thisIntent.getExtras().getString("nomParking");
		temps = thisIntent.getExtras().getString("temps");
		datedebut = thisIntent.getExtras().getString("datedebut");
		idUser = thisIntent.getExtras().getString("idUser");
		System.out.println("FinResA = " + idParking+"/"+temps+"/"+datedebut+"/"+idUser);
		
		//parsing
        reservationControl = new ReservationControleur (reservationHandler, idParking, nomParking, temps, datedebut, idUser);
	    Thread thReservation = new Thread(reservationControl.parseReservation,"ParseBackground");
	    thReservation.start();
	    
	    bValider.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(FinReservationActivity.this,IsParkv2Activity.class);
				startActivity(intent);
			}
		});
	}
}
