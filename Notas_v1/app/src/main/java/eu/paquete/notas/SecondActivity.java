package eu.paquete.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    protected TextView label1;
    protected EditText caja1;
    protected Button boton1;
    protected Button boton2;
    protected Spinner sp1;

    private String contenidoCaja1 = "";

    protected BaseDatosSQLite db;

    private Intent pasarPantalla;

    private Bundle extras;
    private String paquete1="";
    private String paquete2="";
    private String paquete3="";

    private ArrayList<String> prioridades = new ArrayList<String>();
    private ArrayAdapter<String> adaptadorSP;
    private int prioridadSeleccionada= -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        label1 = (TextView) findViewById(R.id.label1_second);
        caja1 = (EditText) findViewById(R.id.caja1_second);
        boton1 = (Button) findViewById(R.id.boton1_second);
        boton2 = (Button) findViewById(R.id.boton2_second);
        sp1 = (Spinner) findViewById(R.id.sp1_second);


        extras = getIntent().getExtras();
        if (extras != null) {

            db = new BaseDatosSQLite(this);

            paquete1 = extras.getString("id");
            paquete2 = extras.getString("title");
            paquete3 = extras.getString("priority");

            caja1.setText(paquete2);

            prioridades.add("Muy alta");
            prioridades.add("Alta");
            prioridades.add("Media");
            prioridades.add("Baja");
            prioridades.add("Muy baja");
            adaptadorSP = new ArrayAdapter<String>(SecondActivity.this, android.R.layout.simple_list_item_1, prioridades);
            sp1.setAdapter(adaptadorSP);
            sp1.setSelection(Integer.parseInt(paquete3));
            prioridadSeleccionada = Integer.parseInt(paquete3);


            sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    prioridadSeleccionada=position;

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });


            boton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    contenidoCaja1 = caja1.getText().toString();
                    if (contenidoCaja1.equals("")){
                        Toast.makeText(SecondActivity.this, "Debe introducir una nota", Toast.LENGTH_SHORT).show();
                    } else {
                        db.actualizarNota(Integer.parseInt(paquete1),contenidoCaja1,prioridadSeleccionada);
                        Toast.makeText(SecondActivity.this, "Nota actualizada correctamente", Toast.LENGTH_SHORT).show();
                        pasarPantalla = new Intent(SecondActivity.this, StartActivity.class);
                        pasarPantalla.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(pasarPantalla);
                    }

                }
            });
        } else {
            Toast.makeText(this, "No ha llegado ninguna información", Toast.LENGTH_SHORT).show();
        }
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarPantalla = new Intent(SecondActivity.this,StartActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });
    }
}