package Model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.List;
import java.util.Random;

import com.meyer.IsParkv2.R;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class HistoAdapter extends BaseAdapter {
	List<Reservation> listReservation;
	LayoutInflater inflater;
	Context context;
	
	public HistoAdapter(Context context, List<Reservation> listReservation){
		inflater = LayoutInflater.from(context);
		this.listReservation = listReservation;
		this.context = context;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listReservation.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listReservation.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	private class ViewHolder{
		ImageView logo;
		TextView parknom;
		TextView heureArrivee;
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if(convertView == null) {
			holder = new ViewHolder();
			
			convertView = inflater.inflate(R.layout.reservationitem, null);
			holder.parknom = (TextView)convertView.findViewById(R.id.parkNom);
			holder.heureArrivee = (TextView)convertView.findViewById(R.id.heureArrivee);
			holder.logo = (ImageView)convertView.findViewById(R.id.logo);
			convertView.setTag(holder);
	
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		try{
			System.out.println("Start read bitmap");
			int i = (int)(Math.random() * (4-1)) + 1;
			//System.out.println("HistoA = random int i = " + i);
			FileInputStream in = null;
			if (i==1) {
				in = new FileInputStream(Environment.getExternalStorageDirectory().toString() + "/IsPark/" + "velizy.png");
			}
			if (i==2) {
				in = new FileInputStream(Environment.getExternalStorageDirectory().toString() + "/IsPark/" + "test.png");
			}
			if (i==3) {
				in = new FileInputStream(Environment.getExternalStorageDirectory().toString() + "/IsPark/" + "passy.png");
			}
			BufferedInputStream buf = new BufferedInputStream(in);
			holder.logo.setImageBitmap(BitmapFactory.decodeStream(buf));
			
		} catch (Exception e) {
		    e.printStackTrace();
		}
		holder.parknom.setText(listReservation.get(position).getNomParking());
		holder.heureArrivee.setText("Payé le " + listReservation.get(position).getDateDebut());

		return convertView;
	}

}
