package com.example.pruebatabs2;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

	private ViewPager columnas;
	private static int NUM_COLUMNAS = 2;
	private Context cxt;
	private ColumnasAdapter miAdapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		cxt = this;

		miAdapter = new ColumnasAdapter();
		columnas = (ViewPager) findViewById(R.id.columnas);
		columnas.setAdapter(miAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private class ColumnasAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return NUM_COLUMNAS;
		}

		@Override
		public Object instantiateItem(View collection, int position) {
			LinearLayout v = (LinearLayout) LayoutInflater.from(cxt).inflate(R.layout.activity_main, null);

			if (position == 0) {
				v = (LinearLayout) LayoutInflater.from(cxt).inflate(
						R.layout.columna1, null);
			} else if (position == 1) {
				v = (LinearLayout) LayoutInflater.from(cxt).inflate(
						R.layout.columna2, null);
			} else {
				v = (LinearLayout) LayoutInflater.from(cxt).inflate(
						R.layout.columna3, null);
			}

			((ViewPager) collection).addView(v, 0);

			return v;
		}

		@Override
		public void destroyItem(View collection, int position, Object view) {
			((ViewPager) collection).removeView((LinearLayout) view);
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return view == ((LinearLayout) object);
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}
}
