package com.animefrontline.hnf;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class instrucciones extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instrucciones);
        Button boton = (Button)findViewById(R.id.btn_disfruta);
        boton.setOnClickListener(new OnClickListener(){
			public void onClick(View arg0) {	
				Intent intent = new Intent(instrucciones.this, listaMangas.class);
				startActivity(intent);
			}        	
        });
    }    
}
