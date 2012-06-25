package com.pruebas.controlesseleccion1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class PruebaControlesSeleccion1Activity extends Activity {
    /** Called when the activity is first created. */
	
	
	Spinner lista;
	
	TextView lblMensaje;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

    	final String[] datos =
    	        new String[]{"Elem1","Elem2","Elem3","Elem4","Elem5"};
    	
    	ArrayAdapter<String> adaptador =
    	        new ArrayAdapter<String>(this,
    	            android.R.layout.simple_spinner_item, datos);
        lista = (Spinner)findViewById(R.id.CmbOpciones);
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        lista.setAdapter(adaptador);
        lblMensaje = (TextView)findViewById(R.id.tv1);
        lista.setOnItemSelectedListener(
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