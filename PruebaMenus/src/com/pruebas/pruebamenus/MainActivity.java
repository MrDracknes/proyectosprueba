package com.pruebas.pruebamenus;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);        
        Resources res = getResources();
        
        //Nos creamos el objeto Tabs (del TabHost) lo vinculamos con el XML y lanzamos el metodo SETUP.
        TabHost tabs=(TabHost)findViewById(android.R.id.tabhost);
        tabs.setup();
         
        //Creamos un objeto TabSpect para ir a�adiendole las pesta�as.
        TabHost.TabSpec spec=tabs.newTabSpec("mitab1");
        spec.setContent(R.id.tab1);
        spec.setIndicator("TAB1", res.getDrawable(android.R.drawable.ic_btn_speak_now));
        tabs.addTab(spec);
         
        spec=tabs.newTabSpec("mitab2");
        spec.setContent(R.id.tab2);
        spec.setIndicator("TAB2", res.getDrawable(android.R.drawable.ic_dialog_map));
        tabs.addTab(spec);
         
        tabs.setCurrentTab(0);
        
        tabs.setOnTabChangedListener(new OnTabChangeListener() {
            public void onTabChanged(String tabId) {
                Log.i("AndroidTabsDemo", "Pulsada pesta�a: " + tabId);
            }
        });
    }    
}
