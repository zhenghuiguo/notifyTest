package com.example.notifytest;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Bundle;

public class TestActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.test);
		NotificationManager m = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
		m.cancel(1);
	}
}
