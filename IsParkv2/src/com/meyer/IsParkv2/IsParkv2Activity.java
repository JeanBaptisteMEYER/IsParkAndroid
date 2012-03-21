package com.meyer.IsParkv2;

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
	    
	    Resources res = getResources();
	    TabHost tabHost = getTabHost();
	    tabHost.setup();
	    TabHost.TabSpec spec;  // Resusable TabSpec for each tab
	    Intent intent;  // Reusable Intent for each tab

	    //tab d'acceil
	    //afficher ici la liste des parking
	    intent = new Intent().setClass(this, ListParkActivity.class);
	    spec = tabHost.newTabSpec("parkingList").setIndicator("Liste de parking").setContent(intent);
	    tabHost.addTab(spec);
	    
	    //Tab Liste de reservation
	    intent = new Intent().setClass(this, HistoActivity.class);
	    spec = tabHost.newTabSpec("historique").setIndicator("Historique").setContent(intent);
	    tabHost.addTab(spec);
	    
	    //tab map
	    // Create an Intent to launch an Activity for the tab (to be reused)
	    intent = new Intent().setClass(this, MapViewActivity.class);
	    // Initialize a TabSpec for each tab and add it to the TabHost
	    spec = tabHost.newTabSpec("map").setIndicator("Map").setContent(intent);
	    tabHost.addTab(spec);
	    
	    tabHost.setCurrentTab(0);
	}
}