package eu.paquete.notas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListadoActivity extends AppCompatActivity {

    protected TextView label1;
    protected TextView label2;
    protected ListView list1;
    protected ImageButton img1;
    protected ImageButton img2;
    protected BaseDatosSQLite db;

    private ArrayList<String> filas = new ArrayList<String>();
    private ArrayAdapter<String> adaptador;
    private Intent pasarPantalla;
    private String contenidoNota = "";
    private String[] partes;
    private int identificador = 0;
    private int numNotas = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        label1 = (TextView) findViewById(R.id.label1_list);
        label2 = (TextView) findViewById(R.id.label2_list);
        list1 = (ListView) findViewById(R.id.list1_list);
        img1 = (ImageButton) findViewById(R.id.img1_list);
        img2 = (ImageButton) findViewById(R.id.img2_list);


        db = new BaseDatosSQLite(this);
        numNotas = db.numeroDeNotas();

        //Creamos un evento en el boton Añadir nota que nos lleve a la actividad CrearNotaActivity
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarPantalla = new Intent(ListadoActivity.this, CrearNotaActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });
        //Creamos un evento en el boton Opciones que nos lleve a la actividad BorrarNotasActivity
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarPantalla = new Intent(ListadoActivity.this, BorrarNotasActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });

        //Creamos una etiqueta para añadir una nota cuando la BBDD esta vacia
        if (numNotas == 0) {
            label2.setVisibility(View.VISIBLE);
            label2.setText(getString(R.string.label2_list_text));
            label2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(ListadoActivity.this, getString(R.string.toast1_list_text), Toast.LENGTH_SHORT).show();
                    pasarPantalla = new Intent(ListadoActivity.this, CrearNotaActivity.class);
                    finish();
                    startActivity(pasarPantalla);
                }
            });
        }
        //Si la bbdd contiene notas, nos las lista
        else {
            label2.setVisibility(View.GONE);
            filas = db.listarTodasNotas();
            adaptador = new ArrayAdapter<String>(ListadoActivity.this, android.R.layout.simple_list_item_1, filas);
            list1.setAdapter(adaptador);
        }

        //Creamos el evento de click corto en el item de la lista para ver la actividad Ver Nota
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                contenidoNota = parent.getItemAtPosition(position).toString();
                partes = contenidoNota.split(" .-");
                if (partes.length > 1) {

                    Nota n = db.listarUnaNota(Integer.parseInt(partes[0]));
                    if (n != null) {
                        identificador = Integer.parseInt(partes[0]);

                        pasarPantalla = new Intent(ListadoActivity.this, VerNotaActivity.class);
                        pasarPantalla.putExtra("id", Integer.toString(n.getId()));
                        pasarPantalla.putExtra("title", n.getTitle());
                        pasarPantalla.putExtra("telefono", Integer.toString(n.getTelefono()));
                        pasarPantalla.putExtra("lugar", n.getLugar());
                        pasarPantalla.putExtra("cliente", n.getCliente());
                        pasarPantalla.putExtra("url", n.getUrl());
                        finish();
                        startActivity(pasarPantalla);
                    }
                }


            }
        });

        //Creamos el evento de click largo en el item de la lista para ver la actividad Actualizar Nota
        list1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                contenidoNota = parent.getItemAtPosition(position).toString();
                partes = contenidoNota.split(" .-");
                if (partes.length > 1) {

                    Nota n = db.listarUnaNota(Integer.parseInt(partes[0]));
                    if (n != null) {
                        identificador = Integer.parseInt(partes[0]);

                        pasarPantalla = new Intent(ListadoActivity.this, ActualizarNotaActivity.class);
                        pasarPantalla.putExtra("id", Integer.toString(n.getId()));
                        pasarPantalla.putExtra("title", n.getTitle());
                        pasarPantalla.putExtra("telefono", Integer.toString(n.getTelefono()));
                        pasarPantalla.putExtra("lugar", n.getLugar());
                        pasarPantalla.putExtra("cliente", n.getCliente());
                        pasarPantalla.putExtra("url", n.getUrl());
                        finish();
                        startActivity(pasarPantalla);
                    }
                }

                return true;
            }
        });

    }
}