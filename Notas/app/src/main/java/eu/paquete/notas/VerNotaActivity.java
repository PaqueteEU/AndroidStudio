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

public class VerNotaActivity extends AppCompatActivity {

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

    private String contenidoCaja2 = "";
    private String contenidoCaja3 = "";
    private String contenidoCaja4 = "";
    private String contenidoCaja5 = "";
    private String contenidoCaja6 = "";

    private Intent pasarPantalla;

    private Bundle extras;
    private String paquete1="";
    private String paquete2="";
    private String paquete3="";
    private String paquete4="";
    private String paquete5="";
    private String paquete6="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_nota);

        label2 = (TextView) findViewById(R.id.label2_ver);
        label3 = (TextView) findViewById(R.id.label3_ver);
        label4 = (TextView) findViewById(R.id.label4_ver);
        label5 = (TextView) findViewById(R.id.label5_ver);
        label6 = (TextView) findViewById(R.id.label6_ver);
        caja2 = (EditText) findViewById(R.id.caja2_ver);
        caja3 = (EditText) findViewById(R.id.caja3_ver);
        caja4 = (EditText) findViewById(R.id.caja4_ver);
        caja5 = (EditText) findViewById(R.id.caja5_ver);
        caja6 = (EditText) findViewById(R.id.caja6_ver);
        boton1 = (Button) findViewById(R.id.boton1_ver);
        boton2 = (Button) findViewById(R.id.boton2_ver);

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
            Toast.makeText(this, getString(R.string.toast1_ver_text), Toast.LENGTH_SHORT).show();
        }

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarPantalla = new Intent(VerNotaActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });

        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = Integer.parseInt(paquete1);

                caja2.setText(getString(R.string.caja2_ver_text));
                caja3.setText(getString(R.string.caja2_ver_text));
                caja4.setText(getString(R.string.caja2_ver_text));
                caja5.setText(getString(R.string.caja2_ver_text));
                caja6.setText(getString(R.string.caja2_ver_text));
                db.borrarNota(id);
                Toast.makeText(VerNotaActivity.this, getString(R.string.toast2_ver_text), Toast.LENGTH_SHORT).show();
                //Creamos la rutina para pasar de pantalla a los 3 segundos
                TimerTask tt = new TimerTask() {
                    @Override
                    public void run() {
                        pasarPantalla = new Intent(VerNotaActivity.this, ListadoActivity.class);
                        finish();
                        startActivity(pasarPantalla);
                    }
                };
                Timer t = new Timer();
                t.schedule(tt, 2000);
            }
        });
    }

}