package com.animefrontline.hnf;

/*
 * Modificación del siguiente tutorial
 * http://www.anddev.org/scrolling_a_picture_horizontally_and_vertically-t3245.html
 */

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.AudioManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

public class imagen extends Activity {
	private int screen_w = 320;
	private int screen_h = 480;
	private int scrollX = 0;
	private int scrollY = 0;

	private int imageWidth = 0;
	private int imageHeight = 0;
	private boolean ampliado = true;
	private boolean primera_carga = true;

	MoveImageView main;
	Bitmap sourceBmp;
	Bitmap showBmp;
	Bitmap RawBmp;
	Resources res;
	Paint paint;
	GestureDetector gestureScanner;
	ViewGroup.LayoutParams layout;
	Bundle bundle;
	String StrUrl;
	String StrUrlImage;
	String StrUrlImage2;

	InputStream is;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		res = getResources();
		bundle = getIntent().getExtras();
		StrUrl = bundle.getString("cap") + "page/1";
		StrUrlImage2 = "";
		DisplayMetrics dm = res.getDisplayMetrics();
		screen_w = dm.widthPixels;
		screen_h = dm.heightPixels - 20;
		gestureScanner = new GestureDetector(this, new GestureListener());
		paint = new Paint();
		main = new MoveImageView(this);
		setContentView(main, new ViewGroup.LayoutParams(screen_w, screen_h));
		new CargaImgs().execute();
		AudioManager audioManager = (AudioManager) getApplicationContext()
				.getSystemService(Context.AUDIO_SERVICE);
		audioManager.setStreamMute(AudioManager.STREAM_SYSTEM, false);
		audioManager.setStreamMute(AudioManager.STREAM_MUSIC, false);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(Menu.NONE, 1, Menu.NONE, "Capítulo Anterior").setIcon(
				R.drawable.ic_menu_left);
		menu.add(Menu.NONE, 2, Menu.NONE, "Capítulo Siguiente").setIcon(
				R.drawable.ic_menu_right);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == 1) {
			anteriorCapitulo(StrUrl);
			return true;
		} else {
			siguienteCapitulo(StrUrl);
			return true;
		}
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		int z, l;
		switch (keyCode) {
		case KeyEvent.KEYCODE_VOLUME_UP:
			z = StrUrl.lastIndexOf("/");
			l = Integer.parseInt(StrUrl.substring(z + 1)) + 1;
			StrUrl = StrUrl.substring(0, z + 1) + l;
			new CargaImgs().execute();
			ampliado = true;
			Toast.makeText(this, "Pagina: " + l, Toast.LENGTH_LONG).show();
			return true;
		case KeyEvent.KEYCODE_VOLUME_DOWN:
			z = StrUrl.lastIndexOf("/");
			l = Integer.parseInt(StrUrl.substring(z + 1)) - 1;
			if (l != 0) {
				StrUrl = StrUrl.substring(0, z + 1) + l;
				new CargaImgs().execute();
				ampliado = true;
				Toast.makeText(this, "Pagina: " + l, Toast.LENGTH_LONG).show();
				return true;
			} else {
				Toast.makeText(this, "Capitulo Anterior", Toast.LENGTH_LONG)
						.show();
				anteriorCapitulo(StrUrl);
				return true;
			}

		}
		return super.onKeyDown(keyCode, event);
	}

	public Bitmap urlImageToBitmap(String urlImage) {
		Bitmap mIcon1 = null;
		URL url_value;
		try {
			url_value = new URL(urlImage);
			if (url_value != null) {
				URLConnection connection = (url_value.openConnection());
				connection.setConnectTimeout(15000);
				mIcon1 = BitmapFactory
						.decodeStream(connection.getInputStream());
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return mIcon1;
	}

	public Bitmap redimensionarImagenMaximo(Bitmap mBitmap, float newWidth,
			float newHeigth) {
		// Redimensionamos
		int width = mBitmap.getWidth();
		int height = mBitmap.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeigth) / height;
		// create a matrix for the manipulation
		Matrix matrix = new Matrix();
		// resize the bit map
		matrix.postScale(scaleWidth, scaleHeight);
		// recreate the new Bitmap
		return Bitmap.createBitmap(mBitmap, 0, 0, width, height, matrix, false);
	}

	private String imageUrl(String url) {
		Document doc = null;
		try {
			doc = Jsoup.connect(url).get();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Elements div_exterior = doc.getElementsByClass("inner");
		for (Element subelementos : div_exterior) {
			Elements imgs = subelementos.getElementsByTag("img");
			StrUrlImage = imgs.get(1).attr("src");
			if (StrUrlImage == StrUrlImage2) {

			} else {
				return imgs.get(1).attr("src");
			}
		}
		return null;
	}

	private void siguienteCapitulo(String url) {
		int y = url.lastIndexOf("/");
		int w = url.substring(0, y).lastIndexOf("/");
		char[] palabraArray = url.toCharArray();
		char trasbordo[] = null;
		boolean uno_u_otro = false;
		if (palabraArray[w - 2] != '/') {
			if (palabraArray[w - 1] == '9') {
				palabraArray[w - 2] += 1;
				palabraArray[w - 1] = '0';
			} else {
				palabraArray[w - 1] += 1;
			}
		} else {

			if (palabraArray[w - 1] == '9') {

				trasbordo = new char[url.length() + 1];
				int p = 0;
				for (int i = 0; i < palabraArray.length; i++) {
					if ((i + 1) == (w - 1)) {
						trasbordo[i] = '1';
						p++;
					} else {
						trasbordo[i] = palabraArray[p];
						p++;
					}
				}
				trasbordo[w - 1] = '0';
				uno_u_otro = true;
			} else {
				palabraArray[w - 1] += 1;
			}
		}
		if (!uno_u_otro) {
			StringBuffer cadena = new StringBuffer();
			for (char c : palabraArray) {
				cadena.append(c);
			}
			StrUrl = cadena.toString();
		} else {
			StringBuffer cadena = new StringBuffer();
			for (char c : trasbordo) {
				cadena.append(c);
			}
			StrUrl = cadena.toString();
		}
		new CargaImgs().execute();
	}

	private void anteriorCapitulo(String url) {
		int pos0 = url.lastIndexOf("/");
		int pos1 = url.substring(0, pos0).lastIndexOf("/");
		pos0 = url.substring(0, pos1).lastIndexOf("/");
		char[] palabraArray = url.toCharArray();
		if (palabraArray[pos0 + 2] == '/') {
			if (palabraArray[pos0 + 1] <= '1') {
				finish();
			} else {
				palabraArray[pos0 + 1] -= 1;
				StringBuffer cadena = new StringBuffer();
				for (char c : palabraArray) {
					cadena.append(c);
				}
				StrUrl = cadena.toString();
				new CargaImgs().execute();
				ampliado = true;
			}
		} else {
			if (palabraArray[pos0 + 2] == '0') {
				palabraArray[pos0 + 2] = '9';
				palabraArray[pos0 + 1] -= 1;
				if (palabraArray[pos0 + 1] == '0') {
					palabraArray[pos0 + 1] = '/';
				}
				StringBuffer cadena = new StringBuffer();
				for (char c : palabraArray) {
					cadena.append(c);
				}
				StrUrl = cadena.toString();
				new CargaImgs().execute();
				ampliado = true;
			} else {
				palabraArray[pos0 + 2] -= 1;
				StringBuffer cadena = new StringBuffer();
				for (char c : palabraArray) {
					cadena.append(c);
				}
				StrUrl = cadena.toString();
				new CargaImgs().execute();
				ampliado = true;
			}
		}
		new CargaImgs().execute();

	}

	public boolean onTouchEvent(MotionEvent me) {
		return gestureScanner.onTouchEvent(me);
	}

	public class GestureListener extends
			GestureDetector.SimpleOnGestureListener {

		public boolean onScroll(MotionEvent e1, MotionEvent e2,
				float distanceX, float distanceY) {
			main.handleScroll(distanceX, distanceY);
			return true;
		}

		public boolean onDown(MotionEvent e) {
			return true;
		}

		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			int z, l;
			if (ampliado) {
				Log.i("Seguimiento", "Entra, e2.x= " + e2.getX() + " e1.x= "
						+ e1.getX());
				if (e2.getX() - e1.getX() >= 50) {
					z = StrUrl.lastIndexOf("/");
					l = Integer.parseInt(StrUrl.substring(z + 1)) + 1;
					StrUrl = StrUrl.substring(0, z + 1) + l;
					new CargaImgs().execute();
					ampliado = true;
				} else if (e2.getX() - e1.getX() <= -50) {
					z = StrUrl.lastIndexOf("/");
					l = Integer.parseInt(StrUrl.substring(z + 1)) - 1;
					if (l != 0) {
						StrUrl = StrUrl.substring(0, z + 1) + l;
						new CargaImgs().execute();
						ampliado = true;
						return true;
					} else {
						anteriorCapitulo(StrUrl);
						return true;
					}
				}
			}

			return false;
		}

		public boolean onDoubleTap(MotionEvent e) {

			if (ampliado) {
				showBmp = Bitmap.createBitmap(RawBmp);
				sourceBmp = RawBmp;
				imageWidth = showBmp.getWidth();
				imageHeight = showBmp.getHeight();
				scrollX = 0;
				scrollY = 0;
				main.setImageBitmap(showBmp);
				ampliado = false;
			} else {
				sourceBmp = redimensionarImagenMaximo(sourceBmp, screen_w,
						screen_h);
				imageWidth = sourceBmp.getWidth();
				imageHeight = sourceBmp.getHeight();
				showBmp = Bitmap.createBitmap(sourceBmp);
				scrollX = 0;
				scrollY = 0;
				main.setImageBitmap(showBmp);
				ampliado = true;
			}
			return false;
		}

		public void onLongPress(MotionEvent e) {
		}

		public void onShowPress(MotionEvent e) {
		}

		public boolean onSingleTapUp(MotionEvent e) {
			return true;
		}
	}

	class MoveImageView extends ImageView {
		public MoveImageView(Context context) {
			super(context);
		}

		protected void onDraw(Canvas canvas) {
			if (!primera_carga) {
				canvas.drawBitmap(showBmp, 0, 0, paint);
			}
		}

		public void handleScroll(float distX, float distY) {
			if ((scrollX <= imageWidth - screen_w) && (scrollX >= 0)) {
				scrollX += distX;
			}

			if ((scrollY <= imageHeight - screen_h) && (scrollY >= 0)) {
				scrollY += distY;
			}

			if (scrollX < 0)
				scrollX = 0;
			if (scrollY < 0)
				scrollY = 0;
			if (scrollX > imageWidth - screen_w)
				scrollX = imageWidth - screen_w;
			if (scrollY > imageHeight - screen_h)
				scrollY = imageHeight - screen_h;
			if ((scrollX <= imageWidth - screen_w)
					&& (scrollY <= imageHeight - screen_h)) {
				showBmp = Bitmap.createBitmap(sourceBmp, scrollX, scrollY,
						screen_w, screen_h);
				invalidate();
			}
		}
	}

	class CargaImgs extends AsyncTask<Void, Void, Bitmap> {

		final ProgressDialog progressDialog = new ProgressDialog(imagen.this);

		protected void onPreExecute() {
			progressDialog.setTitle("");
			progressDialog.setMessage("Cargando Imagen...");
			progressDialog.show();
		}

		protected Bitmap doInBackground(Void... params) {
			return urlImageToBitmap(imageUrl(StrUrl));
		}

		protected void onPostExecute(Bitmap imagen) {
			RawBmp = imagen;
			sourceBmp = RawBmp;
			sourceBmp = redimensionarImagenMaximo(sourceBmp, screen_w,
					screen_h);
			imageWidth = sourceBmp.getWidth();
			imageHeight = sourceBmp.getHeight();
			showBmp = Bitmap.createBitmap(sourceBmp);
			main.setImageBitmap(showBmp);
			primera_carga = false;
			progressDialog.dismiss();

		}
	}
}
