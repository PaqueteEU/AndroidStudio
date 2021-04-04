package eu.paquete.aplicacionconscroll;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class StartActivity extends AppCompatActivity {

    protected LinearLayout linear2;
    protected Button boton3;
    protected Button boton4;
    protected ImageView img1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linear2 = (LinearLayout) findViewById(R.id.linear2_start);
        boton3 = (Button) findViewById(R.id.boton3_start);
        boton4 = (Button) findViewById(R.id.boton4_start);
        img1 = (ImageView) findViewById(R.id.img1_start);

        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linear2.getVisibility() == View.GONE){
                    linear2.setVisibility(View.VISIBLE);
                    boton3.setText("No Visualizar");
                } else{
                    linear2.setVisibility(View.GONE);
                    boton3.setText("Visualizar");
                }

            }
        });

        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    img1.setImageResource(R.drawable.logoios);
                

            }
        });

    }
}