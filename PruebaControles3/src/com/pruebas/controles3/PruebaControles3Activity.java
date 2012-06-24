package com.pruebas.controles3;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class PruebaControles3Activity extends Activity {
    /** Called when the activity is first created. */
	
	RadioButton rb1;
	RadioButton rb2;
	RadioGroup rg;
	TextView lbl;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        rg = (RadioGroup)findViewById(R.id.rg1);
        rb1 =(RadioButton)findViewById(R.id.rb1);
        rb2 =(RadioButton)findViewById(R.id.rb2);
        lbl =(TextView)findViewById(R.id.LblMensaje);
        
        
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				lbl.setText("ID opcion seleccionada: " + checkedId);
				
			}
		});
    }
}