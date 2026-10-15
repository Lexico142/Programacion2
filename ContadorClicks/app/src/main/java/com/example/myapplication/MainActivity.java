package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private int intContador = 0;
    private TextView contador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Button botonReiniciar = findViewById(R.id.button);
        Button botonPulsar = findViewById(R.id.button2);
        Button botonRestar = findViewById(R.id.button3);
        contador = findViewById(R.id.textView);

        if (savedInstanceState != null) {
            intContador = savedInstanceState.getInt(KEY_CONTADOR, 0);
        }
        contador.setText(String.valueOf(intContador));

        botonPulsar.setOnClickListener(v -> {
            intContador++;
            contador.setText(String.valueOf(intContador));
        });

        botonRestar.setOnClickListener(v -> {
            if (intContador > 0) {
                intContador--;
                contador.setText(String.valueOf(intContador));
            }
        });

        botonReiniciar.setOnClickListener(v -> {
            intContador = 0;
            contador.setText("0");
        });
    }

    private static final String KEY_CONTADOR = "contador";

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_CONTADOR, intContador);
    }
}
