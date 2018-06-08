package com.iesvirgendelcarmen.dam.recuperacion11;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private Grafico grafico=new Grafico(this);
    private int x=25,y=25;
    private static final int RADIO_CIRCULO=25;
    private double ancho,alto;
    private Paint pincel=new Paint();
    SensorManager sensorManager;
    Sensor accelerometro=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        setContentView(grafico);
        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        accelerometro=sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        sensorManager.registerListener(this,accelerometro,SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
       grafico.onSensorEvent(event);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    //VISTA
    private class Grafico extends View{

        public Grafico(Context context) {
            super(context);
            pincel.setColor(Color.BLUE);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawCircle(x,y,RADIO_CIRCULO,pincel);
            invalidate();
        }

        public void onSensorEvent(SensorEvent event){
            x=x-(int)event.values[0];
            y=y+(int)event.values[1];
            
            if(x<=0 + RADIO_CIRCULO){
                x=0+RADIO_CIRCULO;
            }
        }
    }

    private void onSizeChanged(int w,int h,int w_antes,int h_antes){
        ancho=w;
        alto=h;
    }
}
