package com.example.notifytest;

import java.io.File;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;

public class MainActivity extends Activity {
	private Button bt_tz;
	NotificationManager manager;
	Handler h;
	RemoteViews contentView;
	Notification notify;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bt_tz = (Button) findViewById(R.id.bt_tz);
		bt_tz.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				getNotify();
			}
		});
	}

	public void getNotify() {
		manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notify = new Notification(R.drawable.ic_launcher, "通知",
				System.currentTimeMillis());
		/*
		 * Uri soundUri = Uri.fromFile(new
		 * File("/system/media/audio/ringtones/Basic_tone.ogg")); notify.sound =
		 * soundUri; long[] vibrates = {0, 1000, 1000, 1000}; notify.vibrate =
		 * vibrates;
		 */
		contentView = new RemoteViews(getPackageName(), R.layout.notify);
		/*
		 * contentView.setImageViewResource(R.id.image,
		 * R.drawable.notification_image);
		 * contentView.setTextViewText(R.id.title, "Custom notification");
		 * contentView.setTextViewText(R.id.text, "This is a custom layout");
		 */
		int i = 0;
		/*
		 * while (true) { i++; contentView.setProgressBar(R.id.myProgressBar,
		 * 100, i, false); }
		 */
		h = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				Log.d("startThread", msg.what + "");
				contentView.setProgressBar(R.id.myProgressBar, 100, msg.what,
						false);
				contentView.setTextViewText(R.id.bt_tz, msg.what + "%");
				notify.contentView = contentView;

				/*
				 * Intent intent = new Intent(this, TestActivity.class);
				 * PendingIntent pi = PendingIntent.getActivity(this, 0, intent,
				 * PendingIntent.FLAG_CANCEL_CURRENT);
				 */

				// notify.setLatestEventInfo(MainActivity.this, "你好", "吃饭了吗？",
				// pi);
				manager.notify(1, notify);
			}
		};
		Intent intent = new Intent(this, TestActivity.class);
		  PendingIntent pi = PendingIntent.getActivity(this, 0, intent,
		  PendingIntent.FLAG_CANCEL_CURRENT);
		contentView.setOnClickPendingIntent(R.id.bt_tz, pi);
		startThread();
	}

	public void updatePro(RemoteViews contentView) {
		int i = 0;
		while (true) {
			try {
				Thread.sleep(1000);
				i++;
				if (i > 100)
					break;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

	public void startThread() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int i = 0;
				while (true) {
					try {
						Log.d("startThread", i + "");
						Thread.sleep(1000);
						i++;
						if (i > 100){
							manager.cancel(1);
							break;
						}
						// contentView.setProgressBar(R.id.myProgressBar,
						// 10000000, i, false);
						// contentView.setTextViewText(R.id.bt_tz, i+"%");
						h.sendEmptyMessage(i);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		}).start();
	}
}
