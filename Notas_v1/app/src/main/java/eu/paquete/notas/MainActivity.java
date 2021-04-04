package eu.paquete.notas;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    protected ImageView ima1;

    private Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ima1 = (ImageView) findViewById(R.id.ima1_main);

        TimerTask tt = new TimerTask() {
            @Override
            public void run() {
                pasarPantalla = new Intent(MainActivity.this, StartActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        };
        Timer t = new Timer();
        t.schedule(tt, 3000);

    }
}