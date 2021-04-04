package eu.paquete.notas;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {

    protected ImageView ima1;
    protected TextView label1;

    private Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ima1 = (ImageView) findViewById(R.id.ima1_main);
        label1 = (TextView) findViewById(R.id.label1_start);

        //Creamos la rutina para pasar de pantalla a los 3 segundos
        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                pasarPantalla = new Intent(StartActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        };
        Timer t = new Timer();
        t.schedule(tt, 2000);

    }
}