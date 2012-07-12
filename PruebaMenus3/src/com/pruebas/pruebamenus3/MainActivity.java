package com.pruebas.pruebamenus3;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.CheckBox;

public class MainActivity extends Activity {

	private static final int MNU_OPC1 = 1;
	private static final int MNU_OPC2 = 2;
	private static final int MNU_OPC3 = 3;
	private static final int MNU_OPC4 = 4;
	private static final int SMNU_OPC1 = 31;
	private static final int SMNU_OPC2 = 32;
	
	 
	private static final int GRUPO_MENU_1 = 101;
	 
	private int opcionSeleccionada = 0;
	
	CheckBox cb1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cb1 = (CheckBox)findViewById(R.id.cb1);
        cb1.setText("CheckBox1");
        cb1.setChecked(false);
    }

/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {    	
    	MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
    	
    	construirMenu(menu);
    	
		return true;		
    }*/
    
    public boolean onPrepareOptionsMenu(Menu menu){
    	menu.clear();
    	if (cb1.isChecked())
    		construirMenu(menu, true);
    	else
    		construirMenu(menu, false);
    	return super.onPrepareOptionsMenu(menu);
    }

    private void construirMenu(Menu menu, boolean extendido)
    {
         menu.add(Menu.NONE, MNU_OPC1, Menu.NONE, "Opcion1").setIcon(R.drawable.tag);
         menu.add(Menu.NONE, MNU_OPC2, Menu.NONE, "Opcion2").setIcon(R.drawable.filter);
     
         SubMenu smnu = menu.addSubMenu(Menu.NONE, MNU_OPC3, Menu.NONE, "Opcion3").setIcon(R.drawable.chart);
     
         smnu.add(GRUPO_MENU_1, SMNU_OPC1, Menu.NONE, "Opcion 3.1");
         smnu.add(GRUPO_MENU_1, SMNU_OPC2, Menu.NONE, "Opcion 3.2");
     
         //Establecemos la selección exclusiva para el grupo de opciones
         smnu.setGroupCheckable(GRUPO_MENU_1, true, true);
     
         if(extendido)
             menu.add(Menu.NONE, MNU_OPC4, Menu.NONE, "Opcion4").setIcon(R.drawable.tag);
         
         //Marcamos la opción seleccionada actualmente
         if(opcionSeleccionada == 1)
              smnu.getItem(0).setChecked(true);
         else if(opcionSeleccionada == 2)
              smnu.getItem(1).setChecked(true);
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
     
            //...
            //Omito el resto de opciones por simplicidad
     
            case SMNU_OPC1:
                opcionSeleccionada = 1;
                item.setChecked(true);
                return true;
            case SMNU_OPC2:
                opcionSeleccionada = 2;
                item.setChecked(true);
                return true;
     
            //...
        }
		return false;
    }
}
