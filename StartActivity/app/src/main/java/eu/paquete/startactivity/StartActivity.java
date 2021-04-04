package eu.paquete.startactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class StartActivity extends AppCompatActivity {

    protected EditText caja1;
    protected Button boton1;
    protected Button boton2;
    protected TextView label1;
    protected TextView label2;

    private String contenidoCaja1 = "";
    private double dolar = 0.84f;
    private double euro = 1.19f;
    private double resultadoDolares = 0.0f;
    private double resultadoEuros = 0.0f;
    private String verResultado = "";
    private DecimalFormat formatoUSD = new DecimalFormat("$0.00");
    private DecimalFormat formatoEU = new DecimalFormat("€0.00");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        caja1 = (EditText) findViewById(R.id.caja1_start);
        boton1 = (Button) findViewById(R.id.boton1_start);
        boton2 = (Button) findViewById(R.id.boton2_start);
        label1 = (TextView) findViewById(R.id.label1_start);
        label2 = (TextView) findViewById(R.id.label2_start);

        boton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            caja1.setError(null);
                try {
                    contenidoCaja1 = caja1.getText().toString();
                    if (contenidoCaja1.equals("")) {
                        caja1.setError("Debe informar una cantidad");
                        caja1.requestFocus();

                    } else {
                    double cantidad = Double.parseDouble(contenidoCaja1);

                        resultadoDolares = cantidad * dolar;

                        label2.setText(formatoEU.format(cantidad)+" Euros =");
                        label1.setText(formatoUSD.format(resultadoDolares)+" Dólares USD");
                    }

                } catch (Exception e) {

                    caja1.setError("ERROR: Los datos deben ser numéricos");
                    caja1.requestFocus();
                }
            }


        });
        boton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                caja1.setError(null);
                try {
                    contenidoCaja1 = caja1.getText().toString();
                    if (contenidoCaja1.equals("")) {
                        caja1.setError("Debe informar una cantidad");
                        caja1.requestFocus();

                    } else {
                        double cantidad = Double.parseDouble(contenidoCaja1);

                        resultadoEuros = cantidad * euro;

                        label2.setText(formatoUSD.format(cantidad)+" Dólares USD =");
                        label1.setText(formatoEU.format(resultadoDolares)+" Euros");
                    }

                } catch (Exception e) {

                    caja1.setError("ERROR: Los datos deben ser numéricos");
                    caja1.requestFocus();
                }
            }


        });

    }
}