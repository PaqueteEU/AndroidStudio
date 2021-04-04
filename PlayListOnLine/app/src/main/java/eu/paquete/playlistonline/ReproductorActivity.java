package eu.paquete.playlistonline;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class ReproductorActivity extends AppCompatActivity {

    protected VideoView video1;
    protected DataBaseSQL db;

    private String ruta = "";
    private MediaController mc;

    private Intent pasarPantalla;
    private Bundle extras;

    //Controlamos la pulsación del botón atras, para que vuelva a StartActivity
    @Override
    public void onBackPressed()
    {

        pasarPantalla = new Intent(ReproductorActivity.this, StartActivity.class);
        finish();
        startActivity(pasarPantalla);
        super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);

        video1 = (VideoView) findViewById(R.id.video1_rep);

        db = new DataBaseSQL(this);


        extras = getIntent().getExtras();
        if (extras != null) {
            db = new DataBaseSQL(this);


            ruta = extras.getString("url");

            System.out.println("Ruta-->" + ruta);
            mc = new MediaController(ReproductorActivity.this);
            mc.setAnchorView(video1);
            video1.setMediaController(mc);
            video1.setVideoURI(Uri.parse(ruta));
            video1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    video1.start();
                }
            });
        } else {
            Toast.makeText(this, getString(R.string.toast1_rep_text), Toast.LENGTH_SHORT).show();
        }



    }
}