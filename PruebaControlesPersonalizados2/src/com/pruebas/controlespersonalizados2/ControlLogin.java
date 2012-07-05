package com.pruebas.controlespersonalizados2;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ControlLogin extends LinearLayout {
	
	EditText txtUsuario;
	EditText txtPassword;
	TextView lblMensaje;
	Button btnLogin;
	OnLoginListener listener;
	
	public ControlLogin(Context context) {		
		super(context);
		inicializar();
	}
	
	public ControlLogin(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    inicializar(); 
		// Procesamos los atributos XML personalizados
	    TypedArray a =
	        getContext().obtainStyledAttributes(attrs,
	            R.styleable.ControlLogin);
	 
	    String textoBoton = a.getString(
	            R.styleable.ControlLogin_login_text);
	 
	    btnLogin.setText(textoBoton);
	 
	    a.recycle();
	}
	
	
	public void setMensaje(String msg)
	{
	    lblMensaje.setText(msg);
	}
	
	private void inicializar(){
		//Utilizamos el layout 'control_login' como interfaz del control
		LayoutInflater li = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		li.inflate(R.layout.control_login, this, true);		
		//Obtenemoslas referencias a los distintos control
		txtUsuario = (EditText)findViewById(R.id.TxtUsuario);
		txtPassword = (EditText)findViewById(R.id.TxtPassword);
		btnLogin = (Button)findViewById(R.id.BtnAceptar);
		lblMensaje = (TextView)findViewById(R.id.LblMensaje);
		//Asociamos los eventos necesarios
		asignarEventos();	    
	}
	
	public void setOnLoginListener(OnLoginListener l)
    {
		listener = l;
    }
	
	private void asignarEventos()
	{
	    btnLogin.setOnClickListener(new OnClickListener(){
	        public void onClick(View v) {
	            listener.OnLogin(txtUsuario.getText().toString(),
	                         txtPassword.getText().toString());
	        }
	    });
	}
}
