package com.checkinnow.anfitrion;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuAnfitrionFragment extends Fragment {

    private Button botonagregaractividad;
    private Button botonverlugares;

    public MenuAnfitrionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_menu_anfitrion, container, false);
        botonagregaractividad = (Button) v.findViewById(R.id.Agregarbutton);
        botonverlugares = (Button) v.findViewById(R.id.verbutton);
        botonagregaractividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               agregarLugar();
            }
        });

        botonverlugares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verLugares();
            }
        });

        return v;
    }

    private void verLugares() {
        VerlugaresFragment verlugares = new VerlugaresFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedor, verlugares);
        transaction.addToBackStack("agregarFragverlugar");
        transaction.commit();
    }

    private void agregarLugar() {
        AgregarLugarFragment agregarlugar = new AgregarLugarFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.contenedor, agregarlugar);
        transaction.addToBackStack("agregarFragagregarlugar");
        transaction.commit();
    }


}
