package com.checkinnow.anfitrion;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import Modelo.LugarClass;

import static Modelo.ContantesClass.PATHLUGARESANFITRION;
import static Modelo.ContantesClass.TAG;


public class VerlugaresFragment extends Fragment {

    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private View v;


    public VerlugaresFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database= FirebaseDatabase.getInstance();


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_verlugares, container, false);



        return v;
    }

    //////////////////////////////////////////////TRAERLUGARES/////////////////////////////////////////////////////////////////////////////////////////////
    public void loadUsers() {
        myRef = database.getReference(PATHLUGARESANFITRION);
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    LugarClass myUser = singleSnapshot.getValue(LugarClass.class);
                    Log.i(TAG, "Encontró Lugar: " + myUser.getNombre());
                    String name = myUser.getNombre();
                    //int age = myUser.getAge();
                    //Toast.makeText(MapHomeActivity.this, name + ":" + age, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "error en la consulta", databaseError.toException());
            }
        });
    }

    //////////////////////////////////////////////TRAERLUGARES--/////////////////////////////////////////////////////////////////////////////////////////////





}
