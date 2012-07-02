package com.pruebas.controlesseleccion2;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PruebaControlesSeleccion2Activity extends Activity {
    /** Called when the activity is first created. */
	
	private Titular[] datos =
		    new Titular[]{
		        new Titular("T’tulo 1", "Subt’tulo largo 1"),
		        new Titular("T’tulo 2", "Subt’tulo largo 2"),
		        new Titular("T’tulo 3", "Subt’tulo largo 3"),
		        new Titular("T’tulo 4", "Subt’tulo largo 4"),
		        new Titular("T’tulo 5", "Subt’tulo largo 5")};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        
        	 
        AdaptadorTitulares adaptador = new AdaptadorTitulares(this);
         
        ListView lstOpciones = (ListView)findViewById(R.id.LstOpciones);
         
        lstOpciones.setAdapter(adaptador);
    	
    	lstOpciones.setOnItemClickListener(new OnItemClickListener() {
    	    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
    	        //Acciones necesarias al hacer click
    	    }
    	});
    	
    }


	
	class AdaptadorTitulares extends ArrayAdapter {
		 
	    Activity context;
	 
	        AdaptadorTitulares(Activity context) {
	            super(context, R.layout.listitem_titular, datos);
	            this.context = context;
	        }
	 
	        public View getView(int position, View convertView, ViewGroup parent) {
	        LayoutInflater inflater = context.getLayoutInflater();
	        View item = inflater.inflate(R.layout.listitem_titular, null);
	 
	        TextView lblTitulo = (TextView)item.findViewById(R.id.LblTitulo);
	        lblTitulo.setText(datos[position].getTitulo());
	 
	        TextView lblSubtitulo = (TextView)item.findViewById(R.id.LblSubTitulo);
	        lblSubtitulo.setText(datos[position].getSubtitulo());
	 
	        return(item);
	    }
	}
}