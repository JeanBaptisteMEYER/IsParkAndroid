package com.meyer.IsParkv2;

import android.app.ActivityGroup;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class HistoriqueGroup extends ActivityGroup {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    View view = getLocalActivityManager().startActivity("activityA", new Intent(this, HistoActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	    this.setContentView(view);
	}
	
	public void goNextActivity(Intent intent) {
		View view = getLocalActivityManager().startActivity("Next Activity", intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	    this.setContentView(view);
	}
	
	public void goBackActivity(Intent intent) {
		View view = getLocalActivityManager().startActivity("Back Activity", intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)).getDecorView();
	    this.setContentView(view);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}
}
