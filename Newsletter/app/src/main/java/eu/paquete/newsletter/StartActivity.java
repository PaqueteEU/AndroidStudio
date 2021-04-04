package eu.paquete.newsletter;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    protected TextView label1;
    protected CheckBox check1;
    protected Button boton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label1 = (TextView) findViewById(R.id.label1_start);
        check1 = (CheckBox) findViewById(R.id.check_start);
        boton1 = (Button) findViewById(R.id.boton1_start);

        //creamos el evento del checkbox
        check1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check1.isChecked()){
                    Toast.makeText(StartActivity.this, "Está activo", Toast.LENGTH_SHORT).show();
                    label1.setText("Si desea recibir la newsletter");
                } else {
                    Toast.makeText(StartActivity.this, "Está inactivo", Toast.LENGTH_SHORT).show();
                    label1.setText("No desea recibir la newsletter");
                }
            }
        });

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check1.isChecked()){
                    check1.setChecked(false);
                    boton1.setText("Activar CheckBox");
                    boton1.setTextColor(Color.BLACK);
                    boton1.setBackgroundColor(Color.GREEN);
                } else{
                    check1.setChecked(true);
                    boton1.setText("Desactivar CheckBox");
                    boton1.setTextColor(Color.WHITE);
                    boton1.setBackgroundColor(Color.RED);
                }
            }
        });
    }
}