package eu.paquete.reto2_uf2_frases_animo;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class StartActivity extends AppCompatActivity {

    protected TextView label1;
    protected Button boton1;
    protected BaseDatosSQL db;

    private String contenidoFrase="";
    private String frases="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        label1 = (TextView) findViewById(R.id.label1_start);
        boton1 = (Button) findViewById(R.id.boton1_start);

        db = new BaseDatosSQL(this);

        //He simplificado al máximo lo que pides y el resultado es el requerido. Genero la frase aleatoria en la consulta SQL

        //Mostramos la frase aleatoria en el TextView
        frases = db.getRamdomFrase();
        label1.append("\""+frases+"\"");

        //Creamos el evento para refrescar la frase cada vez que apretemos el botón
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                label1.setText("");
                frases = db.getRamdomFrase();
                label1.append("\""+frases+"\"");
                Toast.makeText(StartActivity.this, "\""+frases+"\"", Toast.LENGTH_SHORT).show();
            }
        });
    }
}