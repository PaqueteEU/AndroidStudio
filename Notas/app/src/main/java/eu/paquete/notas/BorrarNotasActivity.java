package eu.paquete.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class BorrarNotasActivity extends AppCompatActivity {

    protected TextView label1;
    protected Button boton1;
    protected Button boton2;
    protected BaseDatosSQLite db;

    private Intent pasarPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_notas);

        label1 = (TextView) findViewById(R.id.label1_borrar);
        boton1 = (Button) findViewById(R.id.boton1_borrar);
        boton2 = (Button) findViewById(R.id.boton2_borrar);

        db = new BaseDatosSQLite(this);

        //Creamos el evento del boton 1 para borrar todas las notas
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.borrarTodasNotas();
                boton1.setText(getString(R.string.boton1_2_borrar_text));
                Toast.makeText(BorrarNotasActivity.this, getString(R.string.toast1_borrar_text), Toast.LENGTH_SHORT).show();
                //Creamos la rutina para pasar de pantalla a los 3 segundos
                TimerTask tt = new TimerTask() {
                    @Override
                    public void run() {
                        pasarPantalla = new Intent(BorrarNotasActivity.this, ListadoActivity.class);
                        finish();
                        startActivity(pasarPantalla);
                    }
                };
                Timer t = new Timer();
                t.schedule(tt, 2000);
            }
        });


        //Creamos un evento para volver a la pantalla del listado
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarPantalla = new Intent(BorrarNotasActivity.this, ListadoActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });
    }
}