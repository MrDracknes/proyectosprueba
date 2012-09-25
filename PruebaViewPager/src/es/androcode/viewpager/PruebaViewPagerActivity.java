package es.androcode.viewpager;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.viewpagerindicator.R;
import com.viewpagerindicator.TitlePageIndicator;


public class PruebaViewPagerActivity extends Activity {

	private ViewPager columnas;
	private ColumnasAdapter miAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		miAdapter = new ColumnasAdapter(this);
		columnas = (ViewPager) findViewById(R.id.columnas);
		columnas.setAdapter(miAdapter);

		TitlePageIndicator titleIndicator = (TitlePageIndicator) findViewById(R.id.titulos);
		titleIndicator.setViewPager(columnas);
	}

	public boolean isOnline() {
		ConnectivityManager cm = (ConnectivityManager) this
				.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo netInfo = cm.getActiveNetworkInfo();

		if (netInfo != null && netInfo.isConnectedOrConnecting()) {
			return true;
		}

		return false;
	}

}