package Model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

public class LogoDownload {
	private String logoPassy;
	private String logoTest;
	private String logoVelizy;
	private Bitmap bitmap;
	
	public LogoDownload(){
		System.out.println("Constructor LogoDownload call");
		this.logoPassy = "passy.png";
		this.logoTest = "test.png";
		this.logoVelizy = "velizy.png";
	}
	
	public Runnable downloadLogo = new Runnable(){
		public void run(){
			try {
				System.out.println("Start download");
				
				URL urlImage = new URL("http://natanelpartouche.com/API_ISPARK/API_ISPARK_OLD/images/" + logoPassy);
				System.out.println("DL = Url de l'image = " + urlImage);
				HttpURLConnection connection = (HttpURLConnection) urlImage.openConnection();
				System.out.println("DL = InputStream create");
				InputStream inputStream = connection.getInputStream();
				System.out.println("DL = add to listBitmap");
				bitmap = BitmapFactory.decodeStream(inputStream);
				System.out.println("DL = End First Down");
				
				System.out.println("DL = Start 1st write logo (passy)");
				String path = Environment.getExternalStorageDirectory().toString();
				File file = new File(path + "/IsPark/", "passy.png");
				FileOutputStream out = new FileOutputStream(file);
			    bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
				
				urlImage = new URL("http://natanelpartouche.com/API_ISPARK/API_ISPARK_OLD/images/" + logoTest);
				connection = (HttpURLConnection) urlImage.openConnection();
				inputStream = connection.getInputStream();
				bitmap = BitmapFactory.decodeStream(inputStream);
				System.out.println("DL = End 2nd Down");
				
				System.out.println("DL = Start 2nd write logo (test)");
				path = Environment.getExternalStorageDirectory().toString();
				file = new File(path + "/IsPark/", "test.png");
				out = new FileOutputStream(file);
			    bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
				
				
				urlImage = new URL("http://natanelpartouche.com/API_ISPARK/API_ISPARK_OLD/images/" + logoVelizy);
				connection = (HttpURLConnection) urlImage.openConnection();
				inputStream = connection.getInputStream();
				bitmap = BitmapFactory.decodeStream(inputStream);
				System.out.println("DL = End 3th Down");
				
				System.out.println("DL = Start 1st write logo (velisy)");
				path = Environment.getExternalStorageDirectory().toString();
				file = new File(path + "/IsPark/", "velizy.png");
				out = new FileOutputStream(file);
			    bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
			    out.close();
			    
			    System.out.println("DL = end save all Logo");
				System.out.println("DL = End of logo downloading");
		
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	};
}
