package eu.paquete.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class CrearNotaActivity extends AppCompatActivity {

    protected TextView label2;
    protected TextView label3;
    protected TextView label4;
    protected TextView label5;
    protected TextView label6;
    protected EditText caja1;
    protected EditText caja2;
    protected EditText caja3;
    protected EditText caja4;
    protected EditText caja5;
    protected Button boton1;
    protected Button boton2;
    protected BaseDatosSQLite db;

    private String contenidoCaja1 = "";
    private String contenidoCaja2 = "";
    private String contenidoCaja3 = "";
    private String contenidoCaja4 = "";
    private String contenidoCaja5 = "";

    private Intent pasarPantalla;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_nota);

        label2 = (TextView) findViewById(R.id.label2_crear);
        label3 = (TextView) findViewById(R.id.label3_crear);
        label4 = (TextView) findViewById(R.id.label4_crear);
        label5 = (TextView) findViewById(R.id.label5_crear);
        label6 = (TextView) findViewById(R.id.label6_crear);
        caja1 = (EditText) findViewById(R.id.caja1_crear);
        caja2 = (EditText) findViewById(R.id.caja2_crear);
        caja3 = (EditText) findViewById(R.id.caja3_crear);
        caja4 = (EditText) findViewById(R.id.caja4_crear);
        caja5 = (EditText) findViewById(R.id.caja5_crear);
        boton1 = (Button) findViewById(R.id.boton1_crear);
        boton2 = (Button) findViewById(R.id.boton2_crear);

        db = new BaseDatosSQLite(this);

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contenidoCaja1 = caja1.getText().toString();
                contenidoCaja2 = caja2.getText().toString();
                contenidoCaja3 = caja3.getText().toString();
                contenidoCaja4 = caja4.getText().toString();
                contenidoCaja5 = caja5.getText().toString();


                if (contenidoCaja1.equals("")) {
                    Toast.makeText(CrearNotaActivity.this, getString(R.string.toast1_crear_text), Toast.LENGTH_SHORT).show();
                } else {
                    if (contenidoCaja2.equals("")) {


                        db.insertarNota(contenidoCaja1, 0, contenidoCaja3, contenidoCaja4, contenidoCaja5);
                        Toast.makeText(CrearNotaActivity.this, getString(R.string.toast2_crear_text), Toast.LENGTH_SHORT).show();

                        //Creamos la rutina para pasar de pantalla a los 3 segundos
                        TimerTask tt = new TimerTask() {
                            @Override
                            public void run() {
                                pasarPantalla = new Intent(CrearNotaActivity.this, ListadoActivity.class);
                                finish();
                                startActivity(pasarPantalla);
                            }
                        };
                        Timer t = new Timer();
                        t.schedule(tt, 1000);
                    } else {
                        db.insertarNota(contenidoCaja1, Integer.parseInt(contenidoCaja2), contenidoCaja3, contenidoCaja4, contenidoCaja5);
                        Toast.makeText(CrearNotaActivity.this, getString(R.string.toast3_crear_text), Toast.LENGTH_SHORT).show();

                        //Creamos la rutina para pasar de pantalla a los 3 segundos
                        TimerTask tt = new TimerTask() {
                            @Override
                            public void run() {
                                pasarPantalla = new Intent(CrearNotaActivity.this, ListadoActivity.class);
                                finish();
                                startActivity(pasarPantalla);
                            }
                        };
                        Timer t = new Timer();
                        t.schedule(tt, 1000);
                    }
                }

            }
        });
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos la rutina para pasar de pantalla a los 3 segundos
                TimerTask tt = new TimerTask() {
                    @Override
                    public void run() {
                        pasarPantalla = new Intent(CrearNotaActivity.this, ListadoActivity.class);
                        finish();
                        startActivity(pasarPantalla);
                    }
                };
                Timer t = new Timer();
                t.schedule(tt, 1000);
            }
        });
    }
}