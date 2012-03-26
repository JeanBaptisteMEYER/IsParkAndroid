package com.meyer.IsParkv2;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import parseurXML.Controleur;
import parseurXML.Parking;;


public class ListParkActivity extends Activity{
	
	private ListView mList;
	private List<Parking> listParking;
	private Controleur ct;
	Handler syncHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			listParking=ct.getData();
			for (Parking p : listParking) {
				System.out.println(p);
			}
			//setListView();
		}
	};
		
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listviewpark);
        System.out.println("hello");
        
        //parsing
        ct = new Controleur(syncHandler);
        Thread thread = new Thread(ct.parseData,"ParseBackground");
        thread.start();
        
        //R�cup�ration de la listview cr��e dans le fichier main.xml
        mList = (ListView) findViewById(R.id.listView1);
 
        //Cr�ation de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
        
        //On d�clare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;
        
        //Cr�ation d'une HashMap pour ins�rer les informations du premier item de notre listView
        map = new HashMap<String, String>();
        map.put("img", String.valueOf(R.drawable.ic_launcher));
        //on ins�re un �l�ment titre que l'on r�cup�rera dans le textView titre cr�� dans le fichier affichageitem.xml
        map.put("parknom", "Parly2");
        //on ins�re un �l�ment description que l'on r�cup�rera dans le textView description cr�� dans le fichier affichageitem.xml
        map.put("nbplace", "100");
        //enfin on ajoute cette hashMap dans la arrayList
        listItem.add(map);
        
        //Deuxieme item
        map = new HashMap<String, String>();
        map.put("img", String.valueOf(R.drawable.ic_launcher));
        map.put("parknom", "CdG");
        map.put("nbplace", "300");
        listItem.add(map);
        
        //Deuxieme item
        map = new HashMap<String, String>();
        map.put("img", String.valueOf(R.drawable.ic_launcher));
        map.put("parknom", "Parking ECE");
        map.put("nbplace", "200");
        listItem.add(map);
        
        //Cr�ation d'un SimpleAdapter qui se chargera de mettre les items pr�sent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.parkitem,
               new String[] {"img", "parknom", "nbplace"}, new int[] {R.id.logo, R.id.parknom, R.id.parkadresse});
        
        //On attribut � notre listView l'adapter que l'on vient de cr�er
        mList.setAdapter(mSchedule);
        
        //Enfin on met un �couteur d'�v�nement sur notre listView
        mList.setOnItemClickListener(new OnItemClickListener() {
			@Override
         	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				//on r�cup�re la HashMap contenant les infos de notre item (titre, description, img)
        		HashMap<String, String> map = (HashMap<String, String>) mList.getItemAtPosition(position);
        		//on cr�er une boite de dialogue
        		AlertDialog.Builder adb = new AlertDialog.Builder(ListParkActivity.this);
        		//on attribut un titre � notre boite de dialogue
        		adb.setTitle("S�lection Item");
        		//on ins�re un message � notre boite de dialogue, et ici on affiche le titre de l'item cliqu�
        		adb.setMessage("Votre choix : "+map.get("parknom"));
        		//on indique que l'on veut le bouton ok � notre boite de dialogue
        		adb.setPositiveButton("Ok", null);
        		//on affiche la boite de dialogue
        		adb.show();
        	}
         });
    }
	
	//set des textview
	/*
	 * public void setListView(){
        //R�cup�ration de la listview cr��e dans le fichier main.xml
        mList = (ListView) findViewById(R.id.listView1);
 
        //Cr�ation de la ArrayList qui nous permettra de remplire la listView
        ArrayList<HashMap<String, String>> listItem = new ArrayList<HashMap<String, String>>();
        
        //On d�clare la HashMap qui contiendra les informations pour un item
        HashMap<String, String> map;
        
        //Cr�ation d'une HashMap pour ins�rer les informations du premier item de notre listView
        map = new HashMap<String, String>();
        for (Parking p : listParking) {
        	map.put("img", p.getLogo());
        	map.put("parknom", p.getNom());
        	map.put("parkadresse", p.getAdresse());
        	map.put("parktelephone", p.getTelephone());
        	listItem.add(map);
		}
        
        map.put("img", String.valueOf(R.drawable.ic_launcher));
        //on ins�re un �l�ment titre que l'on r�cup�rera dans le textView titre cr�� dans le fichier affichageitem.xml
        map.put("parknom", "Parly2");
        //on ins�re un �l�ment description que l'on r�cup�rera dans le textView description cr�� dans le fichier affichageitem.xml
        map.put("nbplace", "100");
        //enfin on ajoute cette hashMap dans la arrayList
        listItem.add(map);
        
        //Deuxieme item
        map = new HashMap<String, String>();
        map.put("img", String.valueOf(R.drawable.ic_launcher));
        map.put("parknom", "CdG");
        map.put("nbplace", "300");
        listItem.add(map);
        
        //Deuxieme item
        map = new HashMap<String, String>();
        map.put("img", String.valueOf(R.drawable.ic_launcher));
        map.put("parknom", "Parking ECE");
        map.put("nbplace", "200");
        listItem.add(map);
        
        //Cr�ation d'un SimpleAdapter qui se chargera de mettre les items pr�sent dans notre list (listItem) dans la vue affichageitem
        SimpleAdapter mSchedule = new SimpleAdapter (this.getBaseContext(), listItem, R.layout.parkitem,
               new String[] {"img", "parknom", "parkadresse", "parktelephone"}, new int[] {R.id.logo, R.id.parknom, R.id.parkadresse, R.id.parktelephone});
        
        //On attribut � notre listView l'adapter que l'on vient de cr�er
        mList.setAdapter(mSchedule);
        
        //Enfin on met un �couteur d'�v�nement sur notre listView
        mList.setOnItemClickListener(new OnItemClickListener() {
			@Override
         	public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				//on r�cup�re la HashMap contenant les infos de notre item (titre, description, img)
        		HashMap<String, String> map = (HashMap<String, String>) mList.getItemAtPosition(position);
        		//on cr�er une boite de dialogue
        		AlertDialog.Builder adb = new AlertDialog.Builder(ListParkActivity.this);
        		//on attribut un titre � notre boite de dialogue
        		adb.setTitle("S�lection Item");
        		//on ins�re un message � notre boite de dialogue, et ici on affiche le titre de l'item cliqu�
        		adb.setMessage("Votre choix : "+map.get("parknom"));
        		//on indique que l'on veut le bouton ok � notre boite de dialogue
        		adb.setPositiveButton("Ok", null);
        		//on affiche la boite de dialogue
        		adb.show();
        	}
         });
	}*/
}
