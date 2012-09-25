package com.animefrontline.hnf;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class splash extends Activity {

	private final int SPLASH_DISPLAY_LENGTH = 3000;
	Runnable generadorSplash = new Runnable() {
		public void run() {
			SharedPreferences prefs = getSharedPreferences("MisPreferencias",
					Context.MODE_PRIVATE);
			if (!prefs.getBoolean("primera_ejecucion", false)) {
				Intent intent = new Intent(splash.this, instrucciones.class);
				startActivity(intent);
				SharedPreferences.Editor editor = prefs.edit();
				editor.putBoolean("primera_ejecucion", true);
				editor.commit();
			} else {
				Intent intent = new Intent(splash.this, listaMangas.class);
				startActivity(intent);
			}
			finish();
		}
	};

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		new Handler().postDelayed(generadorSplash, SPLASH_DISPLAY_LENGTH);
	}
}
