package com.meyer.IsParkv2;


import android.content.Intent;
import android.os.Bundle;
import com.google.android.maps.MapActivity;
import com.meyer.IsParkv2.R;
import com.meyer.IsParkv2.R.layout;

public class MapViewActivity extends MapActivity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maplayout);
    }
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
