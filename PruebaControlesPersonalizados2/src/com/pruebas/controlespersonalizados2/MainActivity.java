package com.pruebas.controlespersonalizados2;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {
	
	ControlLogin ctlLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ctlLogin = (ControlLogin)findViewById(R.id.CtlLogin);
        
        ctlLogin.setOnLoginListener(new OnLoginListener()
        {       
			public void OnLogin(String user, String pass) {
				//Validamos el usuario y la contraseña
	            if (user.equals("demo") && pass.equals("demo"))
	                ctlLogin.setMensaje("Login correcto!");
	            else
	                ctlLogin.setMensaje("Vuelva a intentarlo.");
				
			}
        });
    }
}
