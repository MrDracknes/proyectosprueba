package com.pruebas.controlesselecion4;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class PruebaControlesSeleccion4Activity extends Activity {
    /** Called when the activity is first created. */
	
	private String[] datos = new String[25];
	//...
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        for(int i=1; i<=25; i++)
	        datos[i-1] = "Dato " + i;
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,datos);
        final GridView grdOpciones = (GridView)findViewById(R.id.GridOpciones);
        final TextView lblMensaje = (TextView)findViewById(R.id.lbl1);
        grdOpciones.setAdapter(adaptador);
        
        grdOpciones.setOnItemSelectedListener(
        	    new AdapterView.OnItemSelectedListener() {
        	            public void onItemSelected(AdapterView<?> parent,
        	                android.view.View v, int position, long id) {
        	                    lblMensaje.setText("Seleccionado: " + datos[position]);
        	            }
        	 
        	            public void onNothingSelected(AdapterView<?> parent) {
        	                lblMensaje.setText("");
        	            }
        	});
    }
}