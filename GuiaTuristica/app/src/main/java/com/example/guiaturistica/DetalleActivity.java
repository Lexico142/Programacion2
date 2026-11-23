package com.example.guiaturistica;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleActivity extends AppCompatActivity {

    public static final String EXTRA_NOMBRE = "extra_nombre";
    public static final String EXTRA_IMAGEN = "extra_imagen";
    public static final String EXTRA_DESCRIPCION = "extra_descripcion";
    public static final String EXTRA_DIRECCION = "extra_direccion";
    public static final String EXTRA_TELEFONO = "extra_telefono";
    public static final String EXTRA_URL = "extra_url";

    private String nombre;
    private String descripcion;
    private String direccion;
    private String telefono;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = getIntent();
        nombre = intent.getStringExtra(EXTRA_NOMBRE);
        int imagen = intent.getIntExtra(EXTRA_IMAGEN, R.drawable.ic_launcher_foreground);
        descripcion = intent.getStringExtra(EXTRA_DESCRIPCION);
        direccion = intent.getStringExtra(EXTRA_DIRECCION);
        telefono = intent.getStringExtra(EXTRA_TELEFONO);
        url = intent.getStringExtra(EXTRA_URL);

        if (getSupportActionBar() != null && nombre != null) {
            getSupportActionBar().setTitle(nombre);
        }

        ImageView img = findViewById(R.id.imgLugar);
        TextView txtNombre = findViewById(R.id.txtNombre);
        TextView txtDescripcion = findViewById(R.id.txtDescripcion);
        TextView txtDireccion = findViewById(R.id.txtDireccion);
        TextView txtTelefono = findViewById(R.id.txtTelefono);
        TextView txtUrl = findViewById(R.id.txtUrl);

        img.setImageResource(imagen);
        txtNombre.setText(nombre);
        txtDescripcion.setText(descripcion);
        txtDireccion.setText(direccion);
        txtTelefono.setText(telefono);
        txtUrl.setText(url);

        Button btnMapa = findViewById(R.id.btnMapa);
        Button btnLlamar = findViewById(R.id.btnLlamar);
        Button btnWeb = findViewById(R.id.btnWeb);
        Button btnCompartir = findViewById(R.id.btnCompartir);

        btnMapa.setOnClickListener(v -> abrirMapa());
        btnLlamar.setOnClickListener(v -> llamar());
        btnWeb.setOnClickListener(v -> abrirWeb());
        btnCompartir.setOnClickListener(v -> compartir());
    }

    private void abrirMapa() {
        Intent intent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("geo:0,0?q=" + Uri.encode(direccion)));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.error_sin_app, Toast.LENGTH_SHORT).show();
        }
    }

    private void llamar() {
        Intent intent = new Intent(Intent.ACTION_DIAL,
                Uri.parse("tel:" + telefono));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.error_sin_app, Toast.LENGTH_SHORT).show();
        }
    }

    private void abrirWeb() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.error_sin_app, Toast.LENGTH_SHORT).show();
        }
    }

    private void compartir() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT,
                nombre + "\n" + descripcion + "\n" + direccion + "\n" + url);
        Intent chooser = Intent.createChooser(intent, getString(R.string.btn_compartir));
        try {
            startActivity(chooser);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, R.string.error_sin_app, Toast.LENGTH_SHORT).show();
        }
    }
}
