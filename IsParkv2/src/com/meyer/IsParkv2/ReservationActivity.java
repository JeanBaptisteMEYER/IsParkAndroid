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
		
		//On r�cup�re l�Intent que l�on a re�u
		Intent thisIntent = getIntent();
		//On r�cup�re les deux extra gr�ce � leurs id
		int idPark = thisIntent.getExtras().getInt("id");
		System.out.println(idPark);
		TextView tv = (TextView)findViewById(R.id.tvResa);
		
		tv.setText(String.valueOf(idPark));
	}
}
