package eu.paquete.video;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;


public class StartActivity extends AppCompatActivity {

    protected VideoView video1;
    protected Button boton1;
    protected Button boton2;
    protected Button boton3;
    protected TextView label1;

    private String route="";
    private MediaController mc;

    private String externalRoute = "";
    private File directory = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        video1 = (VideoView) findViewById(R.id.video1_start);
        boton1 = (Button) findViewById(R.id.boton1_start);
        boton2 = (Button) findViewById(R.id.boton2_start);
        boton3 = (Button) findViewById(R.id.boton3_start);
        label1 = (TextView) findViewById(R.id.label1_start);

        externalRoute = Environment.getExternalStorageDirectory().getPath();
        Toast.makeText(this, "La ruta es: " + externalRoute, Toast.LENGTH_SHORT).show();
        System.out.println("-->"+externalRoute);

        //https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/1080/Big_Buck_Bunny_1080_10s_10MB.mp4

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                route="android.resource://"+getPackageName()+"/"+R.raw.bunny;
                mc = new MediaController(StartActivity.this);
                mc.setAnchorView(video1);
                video1.setMediaController(mc);
                video1.setVideoURI(Uri.parse(route));
                video1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        video1.start();
                    }
                });
            }
        });
        //https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/1080/Big_Buck_Bunny_1080_10s_10MB.mp4
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                route="https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/1080/Big_Buck_Bunny_1080_10s_10MB.mp4";
                mc = new MediaController(StartActivity.this);
                mc.setAnchorView(video1);
                video1.setMediaController(mc);
                video1.setVideoURI(Uri.parse(route));
                video1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        video1.start();
                    }
                });
            }
        });

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                route= Environment.getExternalStorageDirectory()+"/MisVideos/bunny.mp4";
                mc = new MediaController(StartActivity.this);
                mc.setAnchorView(video1);
                video1.setMediaController(mc);
                video1.setVideoPath(route);
                video1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        video1.start();
                    }
                });
            }
        });
    }
}