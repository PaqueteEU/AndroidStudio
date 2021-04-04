package eu.paquete.notas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
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

public class StartActivity extends AppCompatActivity {

    protected TextView label1;
    protected EditText caja1;
    protected ListView list1;
    protected Button boton1;
    protected Spinner sp1;

    private String contenidoCaja1 = "";

    protected BaseDatosSQLite db;

    private ArrayList<String> filas = new ArrayList<String>();
    private ArrayAdapter<String> adaptador;
    private Intent pasarPantalla;
    private String contenidoNota="";
    private String[] partes;
    private int identificador=0;
    private ArrayList<String> prioridades = new ArrayList<String>();
    private ArrayAdapter<String> adaptadorSP;
    private int prioridadSeleccionada= -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        label1 = (TextView) findViewById(R.id.label1_start);
        caja1 = (EditText) findViewById(R.id.caja1_start);
        list1 = (ListView) findViewById(R.id.list1_start);
        boton1 = (Button) findViewById(R.id.boton1_start);
        sp1 = (Spinner) findViewById(R.id.sp1_start);

        db = new BaseDatosSQLite(this);

        prioridades.add("Muy alta");
        prioridades.add("Alta");
        prioridades.add("Media");
        prioridades.add("Baja");
        prioridades.add("Muy baja");
        adaptadorSP = new ArrayAdapter<String>(StartActivity.this, android.R.layout.simple_list_item_1, prioridades);
        sp1.setAdapter(adaptadorSP);

        sp1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                prioridadSeleccionada=position;

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        filas = db.listarTodasNotas();
        adaptador = new ArrayAdapter<String>(StartActivity.this, android.R.layout.simple_list_item_1, filas);
        list1.setAdapter(adaptador);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contenidoCaja1 = caja1.getText().toString();
                if (contenidoCaja1.equals("")){
                    Toast.makeText(StartActivity.this, "Debe introducir una nota", Toast.LENGTH_SHORT).show();
                } else {
                    //Lo insertamos en la base da datos
                    Toast.makeText(StartActivity.this, "La prioridad seleccionada es: "+prioridadSeleccionada, Toast.LENGTH_SHORT).show();
                    if (prioridadSeleccionada == -1){
                            prioridadSeleccionada=0;
                    }

                    db.insertarNota(contenidoCaja1,prioridadSeleccionada);
                    prioridadSeleccionada=-1;
                    Toast.makeText(StartActivity.this, "Nota insertada correctamente", Toast.LENGTH_SHORT).show();
                    pasarPantalla = new Intent(StartActivity.this, StartActivity.class);
                    pasarPantalla.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(pasarPantalla);
                }
            }
        });
        //Creamos un evento de long click para borrar una nota
        list1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(StartActivity.this);
                builder.setTitle("Eliminar nota");
                builder.setMessage("¿Estás seguro que quieres eliminar la nota?");

                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        contenidoNota = parent.getItemAtPosition(position).toString();
                        partes = contenidoNota.split(".-");
                        if (partes.length>1){
                            Toast.makeText(StartActivity.this, "He borrado el id: "+partes[0], Toast.LENGTH_SHORT).show();
                            identificador= Integer.parseInt(partes[0]);
                            db.borrarNota(identificador);
                            pasarPantalla = new Intent(StartActivity.this, StartActivity.class);
                            pasarPantalla.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                            startActivity(pasarPantalla);
                        }
                    }
                });

                builder.setNegativeButton("No",null);
                AlertDialog dialog = builder.create();
                dialog.show();



                return true;
            }
        });

        //Creamos un evento de click en la nota
        list1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                contenidoNota = parent.getItemAtPosition(position).toString();
                partes = contenidoNota.split(".-");
                if (partes.length>1){

                    Nota n = db.listarUnaNota(Integer.parseInt(partes[0]));
                    if (n != null) {
                        identificador = Integer.parseInt(partes[0]);
                        AlertDialog.Builder builder = new AlertDialog.Builder(StartActivity.this);
                        builder.setTitle("Mostrar nota");
                        builder.setMessage("Identificador: " + n.getId() + "\n Título: " + n.getTitle()+ "\n Prioridad: " + n.getPriority());

                        builder.setPositiveButton("Actualizar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                pasarPantalla = new Intent(StartActivity.this,SecondActivity.class);
                                pasarPantalla.putExtra("id",Integer.toString(n.getId()));
                                pasarPantalla.putExtra("title",n.getTitle());
                                pasarPantalla.putExtra("priority",Integer.toString(n.getPriority()));
                                finish();
                                startActivity(pasarPantalla);
                            }
                        });
                        builder.setNeutralButton("Cerrar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(StartActivity.this, "Has cerrado la ficha", Toast.LENGTH_SHORT).show();
                            }
                        });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }

            }
        });
    }
}