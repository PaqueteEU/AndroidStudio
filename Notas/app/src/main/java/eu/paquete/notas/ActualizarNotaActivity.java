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

public class ActualizarNotaActivity extends AppCompatActivity {

    protected TextView label2;
    protected TextView label3;
    protected TextView label4;
    protected TextView label5;
    protected TextView label6;
    protected EditText caja2;
    protected EditText caja3;
    protected EditText caja4;
    protected EditText caja5;
    protected EditText caja6;
    protected Button boton1;
    protected Button boton2;
    protected BaseDatosSQLite db;

    private String contenidoCaja1 = "";
    private String contenidoCaja2 = "";
    private String contenidoCaja3 = "";
    private String contenidoCaja4 = "";
    private String contenidoCaja5 = "";
    private String contenidoCaja6 = "";

    private Intent pasarPantalla;

    private Bundle extras;
    private String paquete1 = "";
    private String paquete2 = "";
    private String paquete3 = "";
    private String paquete4 = "";
    private String paquete5 = "";
    private String paquete6 = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_nota);

        label2 = (TextView) findViewById(R.id.label2_act);
        label3 = (TextView) findViewById(R.id.label3_act);
        label4 = (TextView) findViewById(R.id.label4_act);
        label5 = (TextView) findViewById(R.id.label5_act);
        label6 = (TextView) findViewById(R.id.label6_act);
        caja2 = (EditText) findViewById(R.id.caja2_act);
        caja3 = (EditText) findViewById(R.id.caja3_act);
        caja4 = (EditText) findViewById(R.id.caja4_act);
        caja5 = (EditText) findViewById(R.id.caja5_act);
        caja6 = (EditText) findViewById(R.id.caja6_act);
        boton1 = (Button) findViewById(R.id.boton1_act);
        boton2 = (Button) findViewById(R.id.boton2_act);

        extras = getIntent().getExtras();
        if (extras != null) {
            db = new BaseDatosSQLite(this);

            paquete1 = extras.getString("id");
            paquete2 = extras.getString("title");
            paquete3 = extras.getString("telefono");
            paquete4 = extras.getString("lugar");
            paquete5 = extras.getString("cliente");
            paquete6 = extras.getString("url");

            caja2.setText(paquete2);
            caja3.setText(paquete3);
            caja4.setText(paquete4);
            caja5.setText(paquete5);
            caja6.setText(paquete6);
        } else {
            Toast.makeText(this, getString(R.string.toast1_act_text), Toast.LENGTH_SHORT).show();
        }
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarPantalla = new Intent(ActualizarNotaActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(paquete1);


                contenidoCaja2 = caja2.getText().toString();
                contenidoCaja3 = caja3.getText().toString();
                contenidoCaja4 = caja4.getText().toString();
                contenidoCaja5 = caja5.getText().toString();
                contenidoCaja6 = caja6.getText().toString();

                int telf = Integer.parseInt(contenidoCaja3);


                if (contenidoCaja2.equals("")) {
                    Toast.makeText(ActualizarNotaActivity.this, getString(R.string.toast2_act_text), Toast.LENGTH_SHORT).show();
                } else {
                    if (contenidoCaja3.equals("")) {


                        caja2.setText("Guardando...");
                        caja3.setText("Guardando...");
                        caja4.setText("Guardando...");
                        caja5.setText("Guardando...");
                        caja6.setText("Guardando...");
                        db.actualizarNota(id, contenidoCaja2, 0, contenidoCaja4, contenidoCaja5, contenidoCaja6);
                        Toast.makeText(ActualizarNotaActivity.this, getString(R.string.toast3_act_text), Toast.LENGTH_SHORT).show();
                        //Creamos la rutina para pasar de pantalla a los 3 segundos
                        TimerTask tt = new TimerTask() {
                            @Override
                            public void run() {
                                pasarPantalla = new Intent(ActualizarNotaActivity.this, ListadoActivity.class);
                                finish();
                                startActivity(pasarPantalla);
                            }
                        };
                        Timer t = new Timer();
                        t.schedule(tt, 2000);
                    } else {
                        caja2.setText(getString(R.string.caja2_act_text));
                        caja3.setText(getString(R.string.caja2_act_text));
                        caja4.setText(getString(R.string.caja2_act_text));
                        caja5.setText(getString(R.string.caja2_act_text));
                        caja6.setText(getString(R.string.caja2_act_text));
                        db.actualizarNota(id, contenidoCaja2, telf, contenidoCaja4, contenidoCaja5, contenidoCaja6);
                        Toast.makeText(ActualizarNotaActivity.this, getString(R.string.toast3_act_text), Toast.LENGTH_SHORT).show();
                        //Creamos la rutina para pasar de pantalla a los 3 segundos
                        TimerTask tt = new TimerTask() {
                            @Override
                            public void run() {
                                pasarPantalla = new Intent(ActualizarNotaActivity.this, ListadoActivity.class);
                                finish();
                                startActivity(pasarPantalla);
                            }
                        };
                        Timer t = new Timer();
                        t.schedule(tt, 2000);
                    }
                }
            }
        });

    }
}