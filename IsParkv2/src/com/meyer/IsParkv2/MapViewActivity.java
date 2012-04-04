package com.meyer.IsParkv2;


import java.util.List;

import Model.ListItimizedOverlay;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.meyer.IsParkv2.R;
import com.meyer.IsParkv2.R.layout;

public class MapViewActivity extends MapActivity{
	private MapView mapView;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maplayout);
        
        mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
        Drawable drawable = this.getResources().getDrawable(R.drawable.marqueur);
        ListItimizedOverlay itemizedoverlay = new ListItimizedOverlay(drawable, this);
        
        GeoPoint geoPoint = new GeoPoint(48857727 , 2279985);
        OverlayItem overlayitem = new OverlayItem(geoPoint, "Parking ", "Passy Plaza");
        itemizedoverlay.addOverlayItem(overlayitem);
        
        geoPoint = new GeoPoint(48781052, 2220268);
        overlayitem = new OverlayItem(geoPoint, "Parking ", "Velizy");
        itemizedoverlay.addOverlayItem(overlayitem);
        
        geoPoint = new GeoPoint(48827789,2117743);
        overlayitem = new OverlayItem(geoPoint, "Parking ", "Parly 2");
        itemizedoverlay.addOverlayItem(overlayitem);
        
        geoPoint = new GeoPoint(48842435,2322664);
        overlayitem = new OverlayItem(geoPoint, "Parking ", "Montparnasse");
        itemizedoverlay.addOverlayItem(overlayitem);
        
        geoPoint = new GeoPoint(48898468, 2088518);
        overlayitem = new OverlayItem(geoPoint, "Parking ", "Saint Germain en Laye");
        itemizedoverlay.addOverlayItem(overlayitem);
        
        
        List<Overlay> mapOverlays = mapView.getOverlays();
        mapOverlays.add(itemizedoverlay);
    }
	
	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
}
