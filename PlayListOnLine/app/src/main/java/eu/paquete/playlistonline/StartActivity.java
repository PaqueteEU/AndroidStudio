package eu.paquete.playlistonline;

/**
 * @author Marco Rodriguez
 */

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    protected TextView label1;
    protected TextView label2;
    protected ImageButton img1;
    protected ImageButton img2;
    protected ListView list1;
    protected DataBaseSQL db;

    private int numRegistros=0;
    private ArrayList<String> filas = new ArrayList<String>();
    private ArrayAdapter<String> adaptador;
    private Intent pasarPantalla;
    private String contenidoRegistro="";
    private String[] campos;
    private int identificador=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        label1 = (TextView) findViewById(R.id.label1_start);
        label2 = (TextView) findViewById(R.id.label2_start);
        img1 = (ImageButton) findViewById(R.id.img1_start);
        img2 = (ImageButton) findViewById(R.id.img2_start);
        list1 = (ListView) findViewById(R.id.list1_start);

        db = new DataBaseSQL(this);
        numRegistros = db.numeroDeFavoritos();

        //Insertamos dos registros de pruebas
        //db.insertarFavorito("Conejito en su casa","https://test-videos.co.uk/vids/bigbuckbunny/mp4/h264/1080/Big_Buck_Bunny_1080_10s_10MB.mp4",2);
        //db.insertarFavorito("Medusa nadando","https://test-videos.co.uk/vids/jellyfish/mp4/h264/1080/Jellyfish_1080_10s_10MB.mp4",3);

        //Creamos un evento en el boton Añadir (+) que nos lleve a la actividad CrearActivity
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pasarPantalla = new Intent(StartActivity.this, CrearActivity.class);
                finish();
                startActivity(pasarPantalla);
            }
        });

        //Creamos un evento en el boton Salir (x) que nos cierre la aplicación
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(StartActivity.this);
                builder.setTitle(getString(R.string.builder1_start_text));
                builder.setMessage(getString(R.string.builder2_start_text));

                builder.setPositiveButton(getString(R.string.builder3_start_text), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(StartActivity .this, getString(R.string.toast1_start_text),Toast.LENGTH_SHORT).show();
                        System.exit(0);
                    }
                });
                builder.setNegativeButton(getString(R.string.builder4_start_text),null);
                AlertDialog dialog = builder.create();
                dialog.show();
                }

        });


        //Creamos una etiqueta para añadir una nota cuando la BBDD esta vacia
        if (numRegistros == 0) {
            label2.setVisibility(View.VISIBLE);
            label2.setText(getString(R.string.label2_start_text1));

        }
        //Si la bbdd contiene registros, nos las lista
        else {
            numRegistros = db.numeroDeFavoritos();
            label2.setVisibility(View.VISIBLE);
            label2.setText(getString(R.string.label2_start_text2)+numRegistros+getString(R.string.label2_start_text3));
            filas = db.listarTodosLosFavoritos();
            adaptador = new ArrayAdapter<String>(StartActivity.this, android.R.layout.simple_list_item_1, filas);
            list1.setAdapter(adaptador);
        }

        //Creamos el evento de click corto en el item de la lista para pasar a la actividad ReproducirActivity
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                contenidoRegistro = parent.getItemAtPosition(position).toString();
                campos = contenidoRegistro.split(". ");
                if (campos.length > 1) {

                    Registro n = db.listarUnRegistro(Integer.parseInt(campos[0]));
                    if (n != null) {
                        identificador = Integer.parseInt(campos[0]);

                        pasarPantalla = new Intent(StartActivity.this, ReproductorActivity.class);
                        pasarPantalla.putExtra("id", Integer.toString(n.getId()));
                        pasarPantalla.putExtra("titulo", n.getTitulo());
                        pasarPantalla.putExtra("url", n.getUrl());
                        finish();
                        startActivity(pasarPantalla);

                    }
                }


            }
        });

        //Creamos el evento de click largo en el item de la lista para borrar este registro
        list1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(StartActivity.this);
                builder.setTitle(getString(R.string.builder5_start_text));
                builder.setMessage(getString(R.string.builder6_start_text));

                builder.setPositiveButton(getString(R.string.builder7_start_text), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        contenidoRegistro = parent.getItemAtPosition(position).toString();
                        campos = contenidoRegistro.split(". ");
                        if (campos.length>1){
                            Toast.makeText(StartActivity.this, getString(R.string.builder8_start_text)+campos[0], Toast.LENGTH_SHORT).show();
                            identificador= Integer.parseInt(campos[0]);
                            db.borrarFavorito(identificador);
                            pasarPantalla = new Intent(StartActivity.this, StartActivity.class);
                            pasarPantalla.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(pasarPantalla);
                        }
                    }
                });

                builder.setNegativeButton(getString(R.string.builder9_start_text),null);
                AlertDialog dialog = builder.create();
                dialog.show();



                return true;
            }
        });




    }
}