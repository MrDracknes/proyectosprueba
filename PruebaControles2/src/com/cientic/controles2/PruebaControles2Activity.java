package com.cientic.controles2;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.style.StyleSpan;
import android.widget.EditText;
import android.widget.TextView;

public class PruebaControles2Activity extends Activity {
    /** Called when the activity is first created. */
	
	Editable str = Editable.Factory.getInstance().newEditable("Esto es un simulacro");
	EditText texto;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        str.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 11,20,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        texto = (EditText)findViewById(R.id.etTexto);
        texto.setText(str);
    }
}