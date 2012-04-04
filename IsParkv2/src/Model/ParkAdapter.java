package Model;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import com.meyer.IsParkv2.R;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ParkAdapter extends BaseAdapter {
	List<Parking> listPark;
	LayoutInflater inflater;
	LogoDownload logoDown;
	
	public ParkAdapter(Context context, List<Parking> listPark){
		inflater = LayoutInflater.from(context);
		this.listPark = listPark;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listPark.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return listPark.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	
	private class ViewHolder{
		ImageView logo;
		TextView parknom;
		TextView parkadresse;
		TextView parktelephone;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if(convertView == null) {
			holder = new ViewHolder();
			
			convertView = inflater.inflate(R.layout.parkitem, null);
			holder.logo = (ImageView)convertView.findViewById(R.id.logo);
			holder.parknom = (TextView)convertView.findViewById(R.id.parknom);
			holder.parkadresse = (TextView)convertView.findViewById(R.id.parkadresse);
			holder.parktelephone = (TextView)convertView.findViewById(R.id.parktelephone);
			convertView.setTag(holder);
	
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		/*
		logoDown = new LogoDownload(listPark.get(position).getLogo(), logoDownHandler);
		Thread thLogoDown = new Thread(logoDown.downloadLogo);
		thLogoDown.start();*/

		holder.parknom.setText(listPark.get(position).getNom());
		holder.parkadresse.setText(listPark.get(position).getAdresse());
		holder.parktelephone.setText(listPark.get(position).getTelephone());

		return convertView;
	}
}
