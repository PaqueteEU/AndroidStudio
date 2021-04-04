package eu.paquete.sonidos;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class StartActivity extends AppCompatActivity {

    protected TextView label1;
    protected Button boton1;
    protected Button boton2;
    protected Button boton3;
    protected Button boton4;
    protected Button boton5;
    protected Button boton6;
    protected Button boton7;
    protected Button boton8;



    private MediaPlayer mp;
    private int longitud = 0;
    private String externalRoute = "";
    private File directory = null;
    private File[] ficheros = null;
    private int i = 0;
    private String soundRoute = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label1 = (TextView) findViewById(R.id.label1_start);
        boton1 = (Button) findViewById(R.id.boton1_start);
        boton2 = (Button) findViewById(R.id.boton2_start);
        boton3 = (Button) findViewById(R.id.boton3_start);
        boton4 = (Button) findViewById(R.id.boton4_start);
        boton5 = (Button) findViewById(R.id.boton5_start);
        boton6 = (Button) findViewById(R.id.boton6_start);
        boton7 = (Button) findViewById(R.id.boton7_start);
        boton8 = (Button) findViewById(R.id.boton8_start);


        externalRoute = Environment.getExternalStorageDirectory().getPath();
        Toast.makeText(this, "La ruta es: " + externalRoute, Toast.LENGTH_SHORT).show();

        directory = new File(externalRoute + "/AudioPrueba");
        if (!directory.exists()) {
            Toast.makeText(this, "El fichero NO existe", Toast.LENGTH_SHORT).show();
            directory.mkdir();
        } else {
            Toast.makeText(this, "El fichero YA existe", Toast.LENGTH_SHORT).show();
        }

        if (directory.listFiles() != null) {
            ficheros = directory.listFiles();
            for (i = 0; i < ficheros.length; i++) {
                soundRoute = externalRoute + "/AudioPrueba/" + ficheros[i].getName();
                Toast.makeText(this, soundRoute, Toast.LENGTH_SHORT).show();
            }

        } else {
            Toast.makeText(this, "No existe ningun fichero de sonido", Toast.LENGTH_SHORT).show();
        }

        //https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mp = MediaPlayer.create(StartActivity.this, R.raw.deepware_brainwaves_lsd);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                    }
                });
                mp.start();


            }
        });
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(StartActivity.this, R.raw.deepware_brainwaves_lullaby);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                    }
                });
                mp.start();

            }
        });
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp = MediaPlayer.create(StartActivity.this, R.raw.deepware_brainwaves_sea);
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.stop();
                    }
                });
                mp.start();

            }
        });
        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (longitud > 0) {
                    //Ir al punto pausado
                    mp.seekTo(longitud);
                } else {
                    mp = MediaPlayer.create(StartActivity.this, R.raw.tiburon);
                    if (mp.isPlaying()) {
                        Toast.makeText(StartActivity.this, "--" + mp.getCurrentPosition(), Toast.LENGTH_SHORT).show();
                    }
                    mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.stop();
                            longitud = 0;
                        }
                    });
                }
                mp.start();

            }
        });
        boton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.pause();
                longitud = mp.getCurrentPosition();
            }
        });
        boton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mp.stop();
                longitud = 0;
                mp.prepareAsync();//me deja preparada la cancion para la proxima vez que le de al play

            }
        });
        boton7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    mp = new MediaPlayer();
                    Uri url = Uri.parse("https://www.learningcontainer.com/wp-content/uploads/2020/02/Kalimba.mp3");
                    mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mp.setDataSource(StartActivity.this, url);
                    mp.prepare();
                    mp.start();
                } catch (IOException e) {
                    Toast.makeText(StartActivity.this, "Imposible reproducir el archivo mp3", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
        boton8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    soundRoute = externalRoute + "/AudioPrueba/coche.mp3";
                    mp = new MediaPlayer();
                    mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                    mp.setDataSource(soundRoute);
                    mp.prepare();
                    mp.start();
                } catch (IOException e) {
                    Toast.makeText(StartActivity.this, "Imposible reproducir el archivo mp3", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });



    }
}