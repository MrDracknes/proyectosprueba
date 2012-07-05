package com.pruebas.controlespersonalizados3;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class NuevoControl extends View 
{
	private int colorSeleccionado = Color.BLACK;
	
	private OnColorSelectedListener listener;
	
	public NuevoControl(Context context) {
		super(context);
	}
	
	public NuevoControl(Context context, AttributeSet ats, int defaultStyle) {
		super(context, ats, defaultStyle );
	}
	
	public NuevoControl(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) 
	{
		int ancho = calcularAncho(widthMeasureSpec);
		int alto = calcularAlto(heightMeasureSpec);

		setMeasuredDimension(ancho, alto);
	}
	
	private int calcularAlto(int limitesSpec) 
	{
		int res = 100; //Alto por defecto
		
		int modo = MeasureSpec.getMode(limitesSpec);
		int limite = MeasureSpec.getSize(limitesSpec);
		
		if (modo == MeasureSpec.AT_MOST) {
			res = limite;
		} 
		else if (modo == MeasureSpec.EXACTLY) {
			res = limite;
		}
		
		return res;
	}
	
	private int calcularAncho(int limitesSpec) 
	{
		int res = 200; //Ancho por defecto
		
		int modo = MeasureSpec.getMode(limitesSpec);
		int limite = MeasureSpec.getSize(limitesSpec);
		
		if (modo == MeasureSpec.AT_MOST) {
			res = limite;
		} 
		else if (modo == MeasureSpec.EXACTLY) {
			res = limite;
		}
		
		return res;
	}
	
	@Override
	protected void onDraw(Canvas canvas) 
	{
		//Obtenemos las dimensiones del control
		int alto = getMeasuredHeight();
		int ancho = getMeasuredWidth();
		
		//Colores Disponibles
		Paint pRelleno = new Paint();
		pRelleno.setStyle(Style.FILL);
		
		pRelleno.setColor(Color.RED);
		canvas.drawRect(0, 0, ancho/4, alto/2, pRelleno);
		
		pRelleno.setColor(Color.GREEN);
		canvas.drawRect(ancho/4, 0, 2*(ancho/4), alto/2, pRelleno);
		
		pRelleno.setColor(Color.BLUE);
		canvas.drawRect(2*(ancho/4), 0, 3*(ancho/4), alto/2, pRelleno);
		
		pRelleno.setColor(Color.YELLOW);
		canvas.drawRect(3*(ancho/4), 0, 4*(ancho/4), alto/2, pRelleno);
		
		//Color Seleccionado
		pRelleno.setColor(colorSeleccionado);
		canvas.drawRect(0, alto/2, ancho, alto, pRelleno);
		
		//Marco del control
		Paint pBorde = new Paint();
		pBorde.setStyle(Style.STROKE);
		pBorde.setColor(Color.WHITE);
		canvas.drawRect(0, 0, ancho-1, alto/2, pBorde);
		canvas.drawRect(0, 0, ancho-1, alto-1, pBorde);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		//Si se ha pulsado en la zona superior
		if (event.getY() > 0 && event.getY() < (getMeasuredHeight()/2))
		{
			//Si se ha pulsado dentro de los l�mites del control
			if (event.getX() > 0 && event.getX() < getMeasuredWidth())
			{
				//Determinamos el color seleccionado seg�n el punto pulsado
				if(event.getX() > (getMeasuredWidth()/4)*3)
					colorSeleccionado = Color.YELLOW;
				else if(event.getX() > (getMeasuredWidth()/4)*2)
					colorSeleccionado = Color.BLUE;
				else if(event.getX() > (getMeasuredWidth()/4)*1)
					colorSeleccionado = Color.GREEN;
				else
					colorSeleccionado = Color.RED;
				
				//Refrescamos el control
				this.invalidate();
			}
		}
		//Si se ha pulsado en la zona inferior
		else if (event.getY() > (getMeasuredHeight()/2) && event.getY() < getMeasuredHeight())
		{
			//Lanzamos el evento externo de selecci�n de color
			listener.OnColorSelected(colorSeleccionado);
		}
		
		return super.onTouchEvent(event);
	}
	
	public void setOnColorSelectedListener(OnColorSelectedListener l) 
	{
		listener = l;
	}
}
