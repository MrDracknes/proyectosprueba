package es.androcode.viewpager;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import es.androcode.viewpager.prueba.R;

class ColumnasAdapter extends PagerAdapter {

	public ColumnasAdapter(Context cxt) {		
		this.cxt = cxt;
	}

	private static final int NUM_COLUMNAS = 2;
	private Context cxt;

	@Override
	public int getCount() {
		return NUM_COLUMNAS;
	}

	@Override
	public Object instantiateItem(View collection, int position) {
		LinearLayout v = (LinearLayout) LayoutInflater.from(cxt).inflate(
				R.layout.columna1, null);

		if (position == 0) {
			v = (LinearLayout) LayoutInflater.from(cxt).inflate(
					R.layout.columna1, null);
		} else {
			v = (LinearLayout) LayoutInflater.from(cxt).inflate(
					R.layout.columna2, null);		
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

	@Override
	public CharSequence getPageTitle(int position) {
		return "Columna " + (position + 1);
	}

}
