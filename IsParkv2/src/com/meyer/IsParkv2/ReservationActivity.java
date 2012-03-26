package com.meyer.IsParkv2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ReservationActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reservationlayout);
		
		//On récupère l’Intent que l’on a reçu
		Intent thisIntent = getIntent();
		//On récupère les deux extra grâce à leurs id
		int idPark = thisIntent.getExtras().getInt("id");
		System.out.println(idPark);
		TextView tv = (TextView)findViewById(R.id.tvResa);
		
		tv.setText(String.valueOf(idPark));
	}
}
