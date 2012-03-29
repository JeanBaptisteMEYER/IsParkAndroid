package Model;

import java.util.List;

import com.meyer.IsParkv2.R;
import com.meyer.IsParkv2.R.id;
import com.meyer.IsParkv2.R.layout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ParkAdapter extends BaseAdapter {
	List<Parking> listPark;
	LayoutInflater inflater;
	
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
			holder.parknom = (TextView)convertView.findViewById(R.id.parknom);
			holder.parkadresse = (TextView)convertView.findViewById(R.id.parkadresse);
			holder.parktelephone = (TextView)convertView.findViewById(R.id.parktelephone);
			convertView.setTag(holder);
	
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.parknom.setText(listPark.get(position).getNom());
		holder.parkadresse.setText(listPark.get(position).getAdresse());
		holder.parktelephone.setText(listPark.get(position).getTelephone());

		return convertView;
	}
}
