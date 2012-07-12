package com.pruebas.pruebamenus1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView lblMensaje;    

    private static final String MNU_OPC1 = "MnuOpc1";
    private static final String MNU_OPC2 = "MnuOpc2";
    private static final String MNU_OPC3 = "MnuOpc3";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lblMensaje = (TextView)findViewById(R.id.tv1);
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
}
