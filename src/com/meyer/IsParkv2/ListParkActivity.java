package com.meyer.IsParkv2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.meyer.IsParkv2.R;
import com.meyer.IsParkv2.R.id;
import com.meyer.IsParkv2.R.layout;

import InfoParkParseur.InfoPlaceControleur;
import Model.ParkAdapter;
import Model.Parking;
import android.app.Activity;
import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;


public class ListParkActivity extends Activity{
	
	private ListView mList;
	private List<Parking> listParking;
	private Parking parking;
	private InfoPlaceControleur ct;
	Handler syncHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			listParking=ct.getData();
			for (Parking p : listParking) {
				System.out.println(p);
			}
			setListView();
		}
	};
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lvpark);
        
        //parsing
        ct = new InfoPlaceControleur(syncHandler,"http://natanelpartouche.com/API_ISPARK/API_ISPARK_OLD/Action/ActionPark.php?Action=all");
        Thread thread = new Thread(ct.parseData,"ParseBackground");
        thread.start();
    }
	
	//set des textview
	public void setListView(){
		mList = (ListView) findViewById(R.id.listView1);
        
        ParkAdapter adapter = new ParkAdapter(this, listParking);
        mList.setAdapter(adapter);
        
        //Enfin on met un écouteur d'évènement sur notre listView
        mList.setOnItemClickListener(new OnItemClickListener() {
			@Override
         	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Intent intent = new Intent(v.getContext(),ReservationActivity.class);
				
				intent.putExtra("idPark", String.valueOf(position+1));
				intent.putExtra("nom", listParking.get(position).getNom());
				intent.putExtra("adresse", listParking.get(position).getAdresse());
				intent.putExtra("telephone", listParking.get(position).getTelephone());
				intent.putExtra("whichGroup", "inReservationGroup");
				
				//startActivity(intent);
				ReservationGroup parentActivity = (ReservationGroup)getParent();
			    parentActivity.goNextActivity(intent);
			}
         });
	}
}
