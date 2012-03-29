package Model;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;

public class QRCodeDownload {
	private String idQRCode;
	private Bitmap bitmap;
	private Handler syncHandler = new Handler();
	
	public QRCodeDownload(){}
	
	public QRCodeDownload(String idQRCode, Handler syncHandler){
		this.idQRCode = idQRCode;
		this.bitmap = null;
		this.syncHandler = syncHandler;
	}
	
	public Runnable downloadQRCode = new Runnable(){
		public void run(){
			try {
				URL urlImage = new URL("http://natanelpartouche.com/API_ISPARK/API_ISPARK_OLD/Reservations/" + idQRCode);
				HttpURLConnection connection = (HttpURLConnection) urlImage.openConnection();
				
				InputStream inputStream = connection.getInputStream();
				bitmap = BitmapFactory.decodeStream(inputStream);
		
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			Message msg = syncHandler.obtainMessage();
			syncHandler.sendMessage(msg);
		}
	};
	
	public Bitmap getQRCode(){
		return this.bitmap;
	}
}
