package com.preubas.pruebamenus2;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView lblMensaje;  
	ListView lstLista;

    private static final String MNU_OPC1 = "MnuOpc1";
    private static final String MNU_OPC2 = "MnuOpc2";
    private static final String MNU_OPC3 = "MnuOpc3";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Obtenemos las referencias a los controles
        lblMensaje = (TextView)findViewById(R.id.tv1);
        lstLista = (ListView)findViewById(R.id.lv1);
        
        String[] datos = new String[]{"Elem1","Elem2","Elem3","Elem4","Elem5"};
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, datos);
        
        lstLista.setAdapter(adaptador);
        //Asociamos los menús contextuales a los controles
        registerForContextMenu(lblMensaje);
        registerForContextMenu(lstLista);	
    }

  
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       /* //Alternativa 1
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;  */	        
        //Alternativa 2
        menu.add(Menu.NONE, 1, Menu.NONE, "Opcion1").setIcon(R.drawable.tag);
        menu.add(Menu.NONE, 2, Menu.NONE, "Opcion2").setIcon(R.drawable.filter);
        //menu.add(Menu.NONE, 3, Menu.NONE, "Opcion3").setIcon(R.drawable.chart);
        SubMenu smnu = menu.addSubMenu(Menu.NONE, 3, Menu.NONE, "Opcion3").setIcon(R.drawable.chart);
			smnu.add(Menu.NONE, 301, Menu.NONE, "Opcion 3.1");
			smnu.add(Menu.NONE, 302, Menu.NONE, "Opcion 3.2");
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       if (item.getItemId() == R.id.MnuOpc1 || item.getItemId() == 1) {
    	   lblMensaje.setText("Opcion 1 pulsada!");
           return true;
       }else if(item.getItemId() == R.id.MnuOpc2 || item.getItemId() == 2) {
    	   lblMensaje.setText("Opcion 2 pulsada!");
           return true;
       }else{
    	   lblMensaje.setText("Opcion 3 pulsada!");
           return true;
       }        
    }
    
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
     
        MenuInflater inflater = getMenuInflater();
        if(v.getId() == R.id.tv1)
            inflater.inflate(R.menu.menu_ctx_etiqueta, menu);
        else if(v.getId() == R.id.lv1)
        {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;     
            menu.setHeaderTitle(lstLista.getAdapter().getItem(info.position).toString());     
            inflater.inflate(R.menu.menu_ctx_lista, menu);
        }
    }
    
    @Override
    public boolean onContextItemSelected(MenuItem item) {
     
    	AdapterContextMenuInfo info =
    	        (AdapterContextMenuInfo) item.getMenuInfo();
    	 
    	    switch (item.getItemId()) {
    	        case R.id.CtxLblOpc1:
    	            lblMensaje.setText("Etiqueta: Opcion 1 pulsada!");
    	            return true;
    	        case R.id.CtxLblOpc2:
    	            lblMensaje.setText("Etiqueta: Opcion 2 pulsada!");
    	            return true;
    	        case R.id.CtxLstOpc1:
    	            lblMensaje.setText("Lista[" + info.position + "]: Opcion 1 pulsada!");
    	            return true;
    	        case R.id.CtxLstOpc2:
    	            lblMensaje.setText("Lista[" + info.position + "]: Opcion 2 pulsada!");
    	            return true;
    	        default:
    	            return super.onContextItemSelected(item);
    	    }
    }
}
