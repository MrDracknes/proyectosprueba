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
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class listaMangas extends Activity {
	
	ListView lv_mangas;
	ArrayAdapter<String> adaptador;
	ArrayList<String> urls;
	String[] datos;

	
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_mangas);
		new CargaMangas().execute();
		lv_mangas = (ListView) findViewById(R.id.lv_mangas);
		lv_mangas.setAdapter(adaptador);
		lv_mangas.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> a, View v, int position, long id) {
				Intent intent = new Intent(listaMangas.this, listaCapitulos.class);
				intent.putExtra("url", urls.get(position).toString());
				startActivity(intent);
			}
		});
	}

	class CargaMangas extends AsyncTask<Void, Void, Void> {

		final ProgressDialog progressDialog = new ProgressDialog(listaMangas.this);

		protected void onPreExecute() {
			progressDialog.setTitle("");
			progressDialog.setMessage("Cargando Lista de Mangas...");
			progressDialog.show();
		}

		protected Void doInBackground(Void... params) {
			try{
				String[] datos2 = totalSeries();
				adaptador = new ArrayAdapter<String>(listaMangas.this,
						android.R.layout.simple_list_item_1, datos2);				
			}catch(Exception e){
				new CargaMangas().execute();
				Toast.makeText(listaMangas.this, "Si tarda es que el servidor está un poco saturado", Toast.LENGTH_LONG).show();
			}
			return null;
		}

		protected void onPostExecute(Void result) {
			lv_mangas.setAdapter(adaptador);
			progressDialog.dismiss();
		}

		private String[] totalSeries() {
			Document doc = null;			
			ArrayList<String> lista = new ArrayList<String>();
			urls = new ArrayList<String>();
			try {
				doc = Jsoup.connect("http://manga.animefrontline.com/reader/read/12_prince/es-mx/0/1/page/1").get();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			Elements content = doc.getElementsByClass("dropdown");
			Elements lis = content.get(0).getElementsByTag("li");
			for (Element li : lis) {
				Elements links = li.getElementsByTag("a");
				for (Element a : links) {
					lista.add(a.text());
					urls.add(a.attr("href"));
				}
			}
			String[] strArray = new String[lista.size()];
			datos = lista.toArray(strArray);
			return datos;
		}
	}	
	
}
