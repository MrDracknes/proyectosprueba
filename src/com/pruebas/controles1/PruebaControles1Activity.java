package com.pruebas.controles1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class PruebaControles1Activity extends Activity {
    /** Called when the activity is first created. */
	
	Button boton;
	ToggleButton toggle;
	ImageButton image;
	TextView label;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        boton = (Button)findViewById(R.id.boton1);
        toggle = (ToggleButton)findViewById(R.id.toggleButton1);
        image = (ImageButton)findViewById(R.id.imageButton1);
        label = (TextView)findViewById(R.id.lblMensaje);
        boton.setOnClickListener(new View.OnClickListener() {			
		
			public void onClick(View v) {
				// TODO Auto-generated method stub
				label.setText("Botón pulsado");
			}
		});
        
        toggle.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (toggle.isChecked()){
					label.setText("On Pulsado");
				}else{
					label.setText("Off Pulsado");
				}
			}
		});
        
    }
}