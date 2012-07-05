package com.pruebas.controlespersonalizados3;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	private NuevoControl ctlColor;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        ctlColor = (NuevoControl)findViewById(R.id.scColor);
        
        ctlColor.setOnColorSelectedListener(new OnColorSelectedListener()
        {
            public void OnColorSelected(int color)
            {
                //Aquí se trataría el color seleccionado (parámetro 'color'
                //...
            }
        });
    }    
}
