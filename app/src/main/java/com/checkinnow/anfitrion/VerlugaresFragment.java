package com.checkinnow.anfitrion;

import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Modelo.LugarAdapter;
import Modelo.LugarClass;

import static Modelo.ContantesClass.PATHLUGARESANFITRION;
import static Modelo.ContantesClass.TAG;


public class VerlugaresFragment extends Fragment implements DatePickerDialog.OnDateSetListener{

    List<List<String>> datos;
    List<String> temp;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private StorageReference mStorageRef;
    private View v;
    private ListView list;
    private static final String CERO = "0";
    private static final String BARRA = "/";

    public final Calendar c = Calendar.getInstance();
    final int mes = c.get(Calendar.MONTH);
    final int dia = c.get(Calendar.DAY_OF_MONTH);
    final int anio = c.get(Calendar.YEAR);

    public VerlugaresFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        mStorageRef = FirebaseStorage.getInstance().getReference();
        datos = new ArrayList<List<String>>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_verlugares, container, false);

        list = (ListView) v.findViewById(R.id.Lugareslistview);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                obtenerFecha();
            }
        });


        loadUsers();

        return v;
    }

    //////////////////////////////////////////////TRAERLUGARES/////////////////////////////////////////////////////////////////////////////////////////////
    public void loadUsers() {
        myRef = database.getReference(PATHLUGARESANFITRION);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    LugarClass lugar = singleSnapshot.getValue(LugarClass.class);
                    temp = new ArrayList<String>();
                    Log.i(TAG, lugar.toString());
                    temp.add(lugar.getNombre());
                    Log.i(TAG, "SALI");
                    StorageReference lugarRef = mStorageRef.child(lugar.getPath()).child(lugar.getNombreimagenes().get(0));
                    Log.i(TAG, lugarRef.toString());
                    File localFile = null;

                    try {
                        localFile = File.createTempFile("images_" + lugar.getNombreimagenes().get(0), ".png");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    Log.i(TAG, localFile.toString());

                    lugarRef.getFile(localFile)
                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    Log.i(TAG, "EXITO");
                                    LugarAdapter temp = (LugarAdapter) list.getAdapter();
                                    list.setAdapter(temp);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Log.i(TAG, "FALLA");
                        }
                    });

                    //temp.add(localFile.getPath());
                    temp.add("");
                    temp.add("Tipo: " + lugar.getTipo() + "\nValor: " + String.valueOf(lugar.getValor()));
                    datos.add(temp);
                }
                list.setAdapter(new LugarAdapter(getContext(), datos));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "error en la consulta", databaseError.toException());
            }
        });

    }
    //////////////////////////////////////////////TRAERLUGARES--/////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

    }

    public void onDateSet(DatePickerDialog view, int year, int month, int dayOfMonth) {
        //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
        final int mesActual = month + 1;
        //Formateo el día obtenido: antepone el 0 si son menores de 10
        String diaFormateado = (dayOfMonth < 10)? CERO + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
        //Formateo el mes obtenido: antepone el 0 si son menores de 10
        String mesFormateado = (mesActual < 10)? CERO + String.valueOf(mesActual):String.valueOf(mesActual);
        //Muestro la fecha con el formato deseado
        //TextoFecha.setText(diaFormateado + BARRA + mesFormateado + BARRA + year);
    }

    private void obtenerFecha(){

        DatePickerDialog recogerFecha = new DatePickerDialog(getActivity());
        onDateSet(recogerFecha,anio,mes,dia);
        recogerFecha.show();

    }



}
