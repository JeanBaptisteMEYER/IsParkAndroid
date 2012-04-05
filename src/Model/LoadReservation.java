package Model;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.graphics.BitmapFactory;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;

public class LoadReservation {
	private Handler syncHandler = new Handler();
	private List<Reservation> listReservation;
	
	public LoadReservation(Handler syncHandler) {
		super();
		this.syncHandler = syncHandler;
		this.listReservation=new ArrayList<Reservation>();
	}
	
	public Runnable MakeListReservation = new Runnable() {
		public void run() {
			try{
				System.out.println("Start read bitmap");
				
				System.out.println("ouhou");
				String path = Environment.getExternalStorageDirectory().toString();
				System.out.println(path);
				path = path + "/IsPark/";
				System.out.println(path);
				
				File dir = new File(path);
				File[] filelist = dir.listFiles(new FilenameFilter() {
					
					@Override
					public boolean accept(File dir, String name) {
						// TODO Auto-generated method stub
						return name.endsWith(".res");
					}
				});
				
				for (File file : filelist) {
					System.out.println("Start to get the file : " + file.getName());
					try{
						path = Environment.getExternalStorageDirectory().toString();
						path = path + "/IsPark/";
						System.out.println(path);
						
						File finalFile = new File(path, file.getName());
						
						System.out.println(finalFile.getPath());
						
						FileInputStream in = new FileInputStream(finalFile);
						ObjectInputStream oi = new ObjectInputStream(in);
						listReservation.add((Reservation) oi.readObject());
						oi.close();
						
						System.out.println("new REservation add : " + file.getName());
					} catch (Exception e) {
					    e.printStackTrace();
					}
				}
			} catch (Exception e) {
			    e.printStackTrace();
			}
			
			Message msg = syncHandler.obtainMessage();
			syncHandler.sendMessage(msg);
		}
	};
	
	public List<Reservation> getListReservation(){
		return listReservation;
		
	}
}
