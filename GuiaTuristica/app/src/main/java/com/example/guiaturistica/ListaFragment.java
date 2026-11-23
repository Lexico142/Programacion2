package com.example.guiaturistica;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class ListaFragment extends Fragment {

    private static final String ARG_CATEGORIA = "categoria";

    public static ListaFragment newInstance(String categoria) {
        ListaFragment fragment = new ListaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_CATEGORIA, categoria);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String categoria = getArguments() != null
                ? getArguments().getString(ARG_CATEGORIA, "Monumentos")
                : "Monumentos";

        final List<Lugar> lugares = obtenerLugares(categoria);

        List<String> nombres = new ArrayList<>();
        for (Lugar l : lugares) {
            nombres.add(l.nombre);
        }

        ListView listView = view.findViewById(R.id.listaLugares);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                requireContext(),
                android.R.layout.simple_list_item_1,
                nombres);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, v, position, id) -> {
            Lugar lugar = lugares.get(position);
            Intent intent = new Intent(requireContext(), DetalleActivity.class);
            intent.putExtra(DetalleActivity.EXTRA_NOMBRE, lugar.nombre);
            intent.putExtra(DetalleActivity.EXTRA_IMAGEN, lugar.imagenResId);
            intent.putExtra(DetalleActivity.EXTRA_DESCRIPCION, lugar.descripcion);
            intent.putExtra(DetalleActivity.EXTRA_DIRECCION, lugar.direccion);
            intent.putExtra(DetalleActivity.EXTRA_TELEFONO, lugar.telefono);
            intent.putExtra(DetalleActivity.EXTRA_URL, lugar.url);
            startActivity(intent);
        });
    }

    private List<Lugar> obtenerLugares(String categoria) {
        List<Lugar> lista = new ArrayList<>();
        switch (categoria) {
            case "Monumentos":
                lista.add(new Lugar(
                        "Sagrada Família",
                        R.drawable.ic_launcher_foreground,
                        "Basílica diseñada por Antoni Gaudí, obra maestra del modernismo catalán.",
                        "Carrer de Mallorca, 401, 08013 Barcelona",
                        "+34932080414",
                        "https://sagradafamilia.org"));
                lista.add(new Lugar(
                        "Park Güell",
                        R.drawable.ic_launcher_foreground,
                        "Parque público con jardines y elementos arquitectónicos de Gaudí.",
                        "Carrer d'Olot, 5, 08024 Barcelona",
                        "+34932120312",
                        "https://parkguell.barcelona"));
                lista.add(new Lugar(
                        "Casa Batlló",
                        R.drawable.ic_launcher_foreground,
                        "Edificio modernista de Gaudí, conocido como la Casa de los Huesos.",
                        "Passeig de Gràcia, 43, 08007 Barcelona",
                        "+34932160720",
                        "https://www.casabatllo.es"));
                lista.add(new Lugar(
                        "Catedral de Barcelona",
                        R.drawable.ic_launcher_foreground,
                        "Catedral gótica dedicada a Santa Eulalia, en el Barrio Gótico.",
                        "Pla de la Seu, s/n, 08002 Barcelona",
                        "+34933151513",
                        "https://catedralbcn.org"));
                break;
            case "Restaurantes":
                lista.add(new Lugar(
                        "Tickets",
                        R.drawable.ic_launcher_foreground,
                        "Restaurante de tapas del chef Albert Adrià, con estrella Michelin.",
                        "Avinguda del Paral·lel, 164, 08015 Barcelona",
                        "+34932646696",
                        "https://www.ticketsbar.es"));
                lista.add(new Lugar(
                        "Cal Pep",
                        R.drawable.ic_launcher_foreground,
                        "Famoso bar de tapas y pescado fresco junto al Born.",
                        "Plaça de les Olles, 8, 08003 Barcelona",
                        "+34933796183",
                        "https://www.calpep.com"));
                lista.add(new Lugar(
                        "El Xampanyet",
                        R.drawable.ic_launcher_foreground,
                        "Taberna histórica de cava y tapas en el Born, fundada en 1929.",
                        "Carrer de Montcada, 22, 08003 Barcelona",
                        "+34933319703",
                        "https://www.elxampanyet.com"));
                lista.add(new Lugar(
                        "Can Solé",
                        R.drawable.ic_launcher_foreground,
                        "Restaurante de paella y arroces junto al puerto, desde 1903.",
                        "Carrer de Sant Carles, 4, 08003 Barcelona",
                        "+34933215012",
                        "https://www.cansole.cat"));
                break;
            case "Ocio":
            default:
                lista.add(new Lugar(
                        "Museu Picasso",
                        R.drawable.ic_launcher_foreground,
                        "Museo dedicado a la obra temprana de Pablo Picasso.",
                        "Carrer de Montcada, 15-23, 08003 Barcelona",
                        "+34932563000",
                        "https://www.museupicasso.bcn.cat"));
                lista.add(new Lugar(
                        "Camp Nou",
                        R.drawable.ic_launcher_foreground,
                        "Estadio del FC Barcelona, uno de los más grandes de Europa.",
                        "Carrer d'Arístides Maillol, 12, 08028 Barcelona",
                        "+34902470189",
                        "https://www.fcbarcelona.es"));
                lista.add(new Lugar(
                        "La Rambla",
                        R.drawable.ic_launcher_foreground,
                        "Paseo emblemático de Barcelona lleno de tiendas, flores y terrazas.",
                        "La Rambla, 08002 Barcelona",
                        "+34932850424",
                        "https://www.barcelonaturisme.com"));
                lista.add(new Lugar(
                        "Mercat de la Boqueria",
                        R.drawable.ic_launcher_foreground,
                        "Mercado municipal con productos frescos y puestos gastronómicos.",
                        "La Rambla, 91, 08001 Barcelona",
                        "+34933183034",
                        "https://www.boqueria.barcelona"));
                break;
        }
        return lista;
    }

    public static class Lugar {
        public final String nombre;
        public final int imagenResId;
        public final String descripcion;
        public final String direccion;
        public final String telefono;
        public final String url;

        public Lugar(String nombre, int imagenResId, String descripcion,
                     String direccion, String telefono, String url) {
            this.nombre = nombre;
            this.imagenResId = imagenResId;
            this.descripcion = descripcion;
            this.direccion = direccion;
            this.telefono = telefono;
            this.url = url;
        }
    }
}
