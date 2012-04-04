package com.meyer.IsParkv2;


import java.util.List;

import org.w3c.dom.ls.LSInput;

import com.meyer.IsParkv2.R;
import com.meyer.IsParkv2.R.layout;

import Model.HistoAdapter;
import Model.LoadReservation;
import Model.ParkAdapter;
import Model.Parking;
import Model.QRCodeDownload;
import Model.Reservation;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class HistoActivity extends Activity{
	private ListView mList;
	private List<Reservation> listReservation;
	private LoadReservation loadResevation;
	
	Handler loadReservationHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			System.out.println("End of loading reservations");
			listReservation = loadResevation.getListReservation();
			
			mList = (ListView) findViewById(R.id.lvHistorique);
	        HistoAdapter adapter = new HistoAdapter(HistoActivity.this, listReservation);
	        mList.setAdapter(adapter);
	        
	        //Enfin on met un écouteur d'évènement sur notre listView
	        mList.setOnItemClickListener(new OnItemClickListener() {
				@Override
	         	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
					Intent intent = new Intent(v.getContext(),HistoReservationActivity.class);
					
					intent.putExtra("idReservation", listReservation.get(position).getIdReservation());
					intent.putExtra("idParking", listReservation.get(position).getIdParking());
					intent.putExtra("HeureArrivee", listReservation.get(position).getTemps());
					intent.putExtra("Prix", listReservation.get(position).getPrix());
					intent.putExtra("Code", listReservation.get(position).getCode());
					intent.putExtra("QRCode", listReservation.get(position).getqRCode());
					
					//startActivity(intent);
					
					HistoriqueGroup parentActivity = (HistoriqueGroup)getParent();
				    parentActivity.goNextActivity(intent);
				}
	         });
		}
	};
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lvhistorique);
        
        System.out.println("Start activity historique");
        
        loadResevation = new LoadReservation(loadReservationHandler);
        Thread qrCodeTh = new Thread(loadResevation.MakeListReservation, "MakeListReservationBackground");
		qrCodeTh.start();
        
        /*mList = (ListView) findViewById(R.id.lvHistorique);
        HistoAdapter adapter = new HistoAdapter(this, listReservation);
        mList.setAdapter(adapter);*/
    }
}
 