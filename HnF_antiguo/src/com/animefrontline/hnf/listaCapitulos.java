package com.animefrontline.hnf;

import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class listaCapitulos extends Activity {
	private ListView lv_capitulos;
	String[] datos;
	ArrayAdapter<String> adaptador;
	Bundle bundle;
	ArrayList<String> urls;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_capitulos);
		bundle = getIntent().getExtras();
		new CargaCaps().execute();
		lv_capitulos = (ListView) findViewById(R.id.lv_capitulos);
		lv_capitulos.setAdapter(adaptador);
		lv_capitulos.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				Intent intent = new Intent(listaCapitulos.this, imagen.class);
				intent.putExtra("cap", urls.get(arg2).toString());
				startActivity(intent);
			}
		});
	}

	class CargaCaps extends AsyncTask<Void, Void, Void> {
		
		final ProgressDialog progressDialog = new ProgressDialog(listaCapitulos.this);


		protected void onPreExecute() {
			progressDialog.setTitle("");
			progressDialog.setMessage("Cargando Lista de Capitulos...");
			progressDialog.show();
		}

		protected Void doInBackground(Void... params) {
			String[] datos2 = totalCapitulos();
			adaptador = new ArrayAdapter<String>(listaCapitulos.this,
					android.R.layout.simple_list_item_1, datos2);
			return null;
		}

		protected void onPostExecute(Void result) {
			lv_capitulos.setAdapter(adaptador);
			progressDialog.dismiss();
		}

		private String[] totalCapitulos() {
			Document doc = null;
			ArrayList<String> lista = new ArrayList<String>();
			urls = new ArrayList<String>();
			try {
				doc = Jsoup.connect(bundle.getString("url")).get();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

			Elements content = doc.getElementsByClass("list");
			for (Element cont : content) {
				Elements subs = cont.getElementsByTag("a");
				for (Element sub : subs) {
					if (!sub.attr("title").equals("Hoshi no Fansub")) {
						lista.add(sub.attr("title"));
						Log.i("TAG", sub.text());
						Log.i("TAG", sub.attr("href"));
						urls.add(sub.attr("href"));
					}
				}
			}
			String[] strArray = new String[lista.size()];
			return lista.toArray(strArray);
		}
	}

}
