package Model;

import android.os.Handler;
import android.os.Message;
import android.widget.SeekBar;
import android.widget.TextView;

public class SeekBarAction {
	private Handler syncHandler;
	private SeekBar seekBar;
	private TextView tvHour;
	private Boolean stayHere;
	
	public SeekBarAction(SeekBar seekBar, TextView tvHour, Handler syncHandler){
		this.seekBar = seekBar;
		this.tvHour = tvHour;
		this.syncHandler = syncHandler;
		this.stayHere = true;
	}
	
	public Runnable ouhouSeekBar = new Runnable(){
		public void run(){
			while (stayHere) {
				tvHour.setText(String.valueOf(seekBar.getProgress()));
			}
			Message msg = syncHandler.obtainMessage();
			syncHandler.sendMessage(msg);
		}
	};
}
