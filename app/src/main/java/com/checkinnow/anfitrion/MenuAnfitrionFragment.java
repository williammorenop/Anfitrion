
package com.checkinnow.anfitrion;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuAnfitrionFragment extends Fragment
{

    private String mParam1;
    private String mParam2;
    private Button botonagregaractividad;



    public MenuAnfitrionFragment()
    {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        View v = inflater.inflate(R.layout.fragment_menu_anfitrion,  container, false);
        botonagregaractividad = (Button) v.findViewById(R.id.button);
        botonagregaractividad.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                AgregarLugarFragment agregarlugar= new AgregarLugarFragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedor, agregarlugar);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return v;
    }

}
