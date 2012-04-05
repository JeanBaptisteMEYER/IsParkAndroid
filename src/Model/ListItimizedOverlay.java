package Model;

import java.util.ArrayList;
import java.util.List;

import InfoParkParseur.InfoPlaceControleur;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
 
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;
import com.meyer.IsParkv2.MapGroup;
import com.meyer.IsParkv2.ReservationActivity;
import com.meyer.IsParkv2.ReservationGroup;

public class ListItimizedOverlay extends ItemizedOverlay<OverlayItem> {
	private ArrayList<OverlayItem>  arrayListOverlayItem = new ArrayList<OverlayItem>();
	private Context context;
	
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
		}
	};
	
	public ListItimizedOverlay(Drawable defaultMarker, Context pContext) {
		super(boundCenterBottom(defaultMarker));
		this.context = pContext;
		
		//parsing
        ct = new InfoPlaceControleur(syncHandler,"http://natanelpartouche.com/API_ISPARK/API_ISPARK_OLD/Action/ActionPark.php?Action=all");
        Thread thread = new Thread(ct.parseData,"ParseBackground");
        thread.start();
	}

	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return arrayListOverlayItem.get(i);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return arrayListOverlayItem.size();
	}
	
	public void addOverlayItem(OverlayItem overlay)
	{
		arrayListOverlayItem.add(overlay);
		populate();
	}
	
	@Override
	protected boolean onTap(int index)
	{
		System.out.println("Start onTap " + arrayListOverlayItem.get(index).getSnippet());
		final OverlayItem item = arrayListOverlayItem.get(index);
		AlertDialog.Builder dialog = new AlertDialog.Builder(context);
		dialog.setTitle(item.getTitle());
		dialog.setMessage(item.getSnippet());
		System.out.println("Add button in alertDialog");
		dialog.setNeutralButton("Reserver une place", new OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(context,ReservationActivity.class);
				System.out.println("Snippet : " + item.getSnippet());
				for (Parking parking : listParking) {
					System.out.println("Boucle For of parking : " + parking.getNom());
					if(item.getSnippet().equalsIgnoreCase(parking.getNom())){
						System.out.println("add extra to intent " + parking.getNom());
						intent.putExtra("idPark", parking.getId());
						intent.putExtra("nom", parking.getNom());
						intent.putExtra("adresse", parking.getAdresse());
						intent.putExtra("telephone", parking.getTelephone());
						intent.putExtra("whichGroup", "inMapGroup");
					}
				}
				
				MapGroup parentActivity = (MapGroup) context;
			    parentActivity.goNextActivity(intent);
			}
		});
				
		dialog.show();
		return true;
	}

}
