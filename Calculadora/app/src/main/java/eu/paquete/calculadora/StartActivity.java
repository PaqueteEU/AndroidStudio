package eu.paquete.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class StartActivity extends AppCompatActivity {

    //Creamos cada atributo de los componentes
    protected TextView label1;
    protected TextView label2;
    protected EditText caja1;
    protected EditText caja2;
    protected Button boton1;
    protected Button boton2;
    protected Button boton3;
    protected Button boton4;

    private String contenidoCaja1 = "";
    private String contenidoCaja2 = "";
    private float op1 = 0.0f;
    private float op2 = 0.0f;
    private float resultado = 0.0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referenciamos las vistas
        label1 = (TextView) findViewById(R.id.label1_start);
        label2 = (TextView) findViewById(R.id.label2_start);
        caja1 = (EditText) findViewById(R.id.caja1_start);
        caja2 = (EditText) findViewById(R.id.caja2_start);
        boton1 = (Button) findViewById(R.id.boton1_start);
        boton2 = (Button) findViewById(R.id.boton2_start);
        boton3 = (Button) findViewById(R.id.boton3_start);
        boton4 = (Button) findViewById(R.id.boton4_start);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    contenidoCaja1 = caja1.getText().toString();
                    contenidoCaja2 = caja2.getText().toString();

                    op1 = Float.parseFloat(contenidoCaja1);//parseamos el string a float
                    op2 = Float.parseFloat(contenidoCaja2);

                    resultado = op1 + op2;

                    label2.setText(Float.toString(resultado));//para mostrarlo lo volvemos a pasar a String
                } catch (Exception e) {
                    label2.setText("ERROR: Los datos deben ser numericos");
                }
            }
        });
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    contenidoCaja1 = caja1.getText().toString();
                    contenidoCaja2 = caja2.getText().toString();

                    op1 = Float.parseFloat(contenidoCaja1);
                    op2 = Float.parseFloat(contenidoCaja2);

                    resultado = op1 - op2;

                    label2.setText(Float.toString(resultado));
                } catch (Exception e) {
                    label2.setText("ERROR: Los datos deben ser numericos");
                }
            }
        });
        boton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    contenidoCaja1 = caja1.getText().toString();
                    contenidoCaja2 = caja2.getText().toString();

                    op1 = Float.parseFloat(contenidoCaja1);
                    op2 = Float.parseFloat(contenidoCaja2);

                    resultado = op1 * op2;

                    label2.setText(Float.toString(resultado));
                } catch (Exception e) {
                    label2.setText("ERROR: Los datos deben ser numericos");
                }
            }
        });
        boton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    contenidoCaja1 = caja1.getText().toString();
                    contenidoCaja2 = caja2.getText().toString();

                    op1 = Float.parseFloat(contenidoCaja1);
                    op2 = Float.parseFloat(contenidoCaja2);

                    if (op2==0.0f){
                        label2.setText("El operador 2 de una division no puede ser 0");
                    }
                    else{
                        resultado = op1 / op2;

                        label2.setText(Float.toString(resultado));
                    }

                } catch (Exception e) {
                    label2.setText("ERROR: Los datos deben ser numericos");
                }
            }
        });
    }
}