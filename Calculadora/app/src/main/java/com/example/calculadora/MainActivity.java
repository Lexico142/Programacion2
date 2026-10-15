package com.example.calculadora;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editPeso;
    private EditText editAltura;
    private RadioButton radioMetros;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        editPeso = findViewById(R.id.editPeso);
        editAltura = findViewById(R.id.editAltura);
        radioMetros = findViewById(R.id.radioMetros);
        textResultado = findViewById(R.id.textResultado);
        Button botonCalcular = findViewById(R.id.botonCalcular);

        botonCalcular.setOnClickListener(v -> calcular());
    }

    @SuppressLint("DefaultLocale")
    public void calcular() {
        String textoPeso = editPeso.getText().toString().trim();
        String textoAltura = editAltura.getText().toString().trim();

        if (textoPeso.isEmpty()) {
            editPeso.setError(getString(R.string.error_peso_vacio));
            return;
        }
        if (textoAltura.isEmpty()) {
            editAltura.setError(getString(R.string.error_altura_vacia));
            return;
        }

        double peso;
        double altura;

        try {
            peso = Double.parseDouble(textoPeso);
            altura = Double.parseDouble(textoAltura);
        } catch (NumberFormatException e) {
            editPeso.setError(getString(R.string.error_numero));
            return;
        }

        if (peso <= 0) {
            editPeso.setError(getString(R.string.error_peso_invalido));
            return;
        }
        if (altura <= 0) {
            editAltura.setError(getString(R.string.error_altura_invalida));
            return;
        }

        if (!radioMetros.isChecked()) {
            altura = altura / 100.0;
        }

        double imc = calcularImc(peso, altura);
        String categoria = obtenerCategoria(imc);

        textResultado.setText(String.format("IMC: %.2f\n%s", imc, categoria));
    }

    public double calcularImc(double peso, double altura) {
        return peso / (altura * altura);
    }

    public String obtenerCategoria(double imc) {
        if (imc < 18.5) {
            return getString(R.string.categoria_bajo_peso);
        } else if (imc < 25) {
            return getString(R.string.categoria_normal);
        } else if (imc < 30) {
            return getString(R.string.categoria_sobrepeso);
        } else {
            return getString(R.string.categoria_obesidad);
        }
    }
}
