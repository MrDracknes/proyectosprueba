package com.pruebas.controlespersonalizados2;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	ControlLogin ctlLogin;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ctlLogin = (ControlLogin) findViewById(R.id.CtlLogin);

		ctlLogin.setOnLoginListener(new OnLoginListener() {
			@Override
			public void onLogin(String usuario, String password) {
				// Validamos el usuario y la contrase–a
				if (usuario.equals("demo") && password.equals("demo"))
					ctlLogin.setMensaje("Login correcto!");
				else
					ctlLogin.setMensaje("Vuelva a intentarlo.");
			}
		});
	}
}
