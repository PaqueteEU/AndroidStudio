package eu.paquete.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    protected TextView label1;
    protected TextView label2;
    protected EditText caja1;
    protected Button boton1;

    private String contenidoCaja1 = "";//Variable para guardar el contenidod e la caja1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("Esta es una traza al principio...");//Las trazas sirven para verifica valores de variables y pruebas

        Toast.makeText(this, "Iniciando app...", Toast.LENGTH_SHORT).show();//Genera un mensaje al iniciar la app

        label1 = (TextView) findViewById(R.id.label1_start);//Esta linea es obligatoria. Referencia de un atributo de la interfaz
        label2 = (TextView) findViewById(R.id.label2_start);
        caja1 = (EditText) findViewById(R.id.caja1_start);
        boton1 = (Button) findViewById(R.id.boton1_start);

        //Creamos un evento. en este caso para el boton. Hay que crearla debajo de su referencia (findViewById)
        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contenidoCaja1 = caja1.getText().toString();
                if (contenidoCaja1.equals("")) {
                    Toast.makeText(StartActivity.this, "Campo obligatorio", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(StartActivity.this, "Ok", Toast.LENGTH_LONG).show();
                    label2.setText(contenidoCaja1);
                }
            }
        });
    }
}
