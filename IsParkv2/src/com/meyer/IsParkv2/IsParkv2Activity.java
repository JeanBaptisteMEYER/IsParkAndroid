package com.meyer.IsParkv2;

import Model.LogoDownload;
import android.app.*;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class IsParkv2Activity extends TabActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.tablayout);
	    
	    LogoDownload logoDown = new LogoDownload();
	    Thread thLogoDown = new Thread(logoDown.downloadLogo);
	    thLogoDown.start();
	    
	    Resources res = getResources();
	    TabHost tabHost = getTabHost();
	    tabHost.setup();
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab
	    
	    //tab map
	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, MapGroup.class);
	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("map").setIndicator("Trouver un Parking").setContent(intent);
	    tabHost.addTab(spec);
	    // new comment
	    //tab d'acceil
	    //afficher ici la liste des parking
	    System.out.println("Start IsPark");
	    intent = new Intent(this, ReservationGroup.class);
	    spec = tabHost.newTabSpec("reservation").setIndicator("Reservez une place").setContent(intent);
	    tabHost.addTab(spec);
	    
	    //Tab Liste de reservation
	    intent = new Intent().setClass(this, HistoriqueGroup.class);
	    spec = tabHost.newTabSpec("historique").setIndicator("Historique de vos réservations").setContent(intent);
	    tabHost.addTab(spec);
	    
	    
	    
	    tabHost.setCurrentTab(0);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		System.out.println("BackPressed in" + this.getTitle());
		super.onBackPressed();
	}
}