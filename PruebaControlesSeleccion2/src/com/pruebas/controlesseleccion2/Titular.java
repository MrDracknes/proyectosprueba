package com.pruebas.controlesseleccion2;

public class Titular {
	String titulo;
	String subtitulo;

    public Titular(String tit, String sub){
        titulo = tit;
        subtitulo = sub;
    }
 
    public String getTitulo(){
        return titulo;
    }
 
    public String getSubtitulo(){
        return subtitulo;
    }
}
