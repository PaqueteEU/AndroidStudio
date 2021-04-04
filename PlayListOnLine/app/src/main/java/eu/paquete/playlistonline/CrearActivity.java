package eu.paquete.playlistonline;

/**
 * @author Marco Rodriguez
 */
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CrearActivity extends AppCompatActivity {

    protected TextView label1;
    protected EditText caja1;
    protected EditText caja2;
    protected Spinner spin1;
    protected Button boton1;
    protected DataBaseSQL db;

    private int numRegistros=0;
    private String contenidoTitulo="";
    private String contenidoUrl="";
    private String contenidoPrioridad="";
    private Intent pasarPantalla;

    private ArrayList<String> prioridades = new ArrayList<String>();
    private ArrayAdapter<String> adaptadorSP;
    private int prioridadSeleccionada= -1;

    //Controlamos la pulsación del botón atras, para que vuelva a StartActivity
    @Override
    public void onBackPressed()
    {

        pasarPantalla = new Intent(CrearActivity.this, StartActivity.class);
        finish();
        startActivity(pasarPantalla);
        super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);

        label1= (TextView) findViewById(R.id.label1_crear);
        caja1= (EditText) findViewById(R.id.caja1_crear);
        caja2= (EditText) findViewById(R.id.caja2_crear);
        spin1= (Spinner) findViewById(R.id.spin1_crear);
        boton1= (Button) findViewById(R.id.boton1_crear);

        db = new DataBaseSQL(this);
        numRegistros = db.numeroDeFavoritos();


        prioridades.add(getString(R.string.prioridad1_crear_text));
        prioridades.add(getString(R.string.prioridad2_crear_text));
        prioridades.add(getString(R.string.prioridad3_crear_text));
        prioridades.add(getString(R.string.prioridad4_crear_text));
        prioridades.add(getString(R.string.prioridad5_crear_text));
        adaptadorSP = new ArrayAdapter<String>(CrearActivity.this, android.R.layout.simple_list_item_1, prioridades);
        spin1.setAdapter(adaptadorSP);

        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                prioridadSeleccionada=position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        //Programamos el boton para guardar
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caja1.setError(null);
                caja2.setError(null);
                contenidoTitulo = caja1.getText().toString();
                contenidoUrl = caja2.getText().toString();
                if (contenidoTitulo.equals("")){
                    caja1.setError(getString(R.string.seterror1_crear_text));
                    caja1.requestFocus();
                }
                if (contenidoUrl.equals("")){
                    caja2.setError(getString(R.string.seterror2_crear_text));
                    caja2.requestFocus();
                }
                else {

                    if (prioridadSeleccionada == 0) {
                        prioridadSeleccionada = 1;
                    } else if (prioridadSeleccionada == 1) {
                        prioridadSeleccionada = 2;
                    } else if (prioridadSeleccionada == 2) {
                        prioridadSeleccionada = 3;
                    } else if (prioridadSeleccionada == 3) {
                        prioridadSeleccionada = 4;
                    } else {
                        prioridadSeleccionada = 5;
                    }
                    db.insertarFavorito(contenidoTitulo, contenidoUrl, prioridadSeleccionada);
                    prioridadSeleccionada = -1;
                    Toast.makeText(CrearActivity.this, getString(R.string.toast1_crear_text), Toast.LENGTH_SHORT).show();
                    pasarPantalla = new Intent(CrearActivity.this, StartActivity.class);
                    pasarPantalla.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(pasarPantalla);
                }
            }
        });


    }
}