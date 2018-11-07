package com.checkinnow.anfitrion;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.List;

import Modelo.LugarAdapter;
import Modelo.LugarClass;

import static Modelo.ContantesClass.PATHLUGARESANFITRION;
import static Modelo.ContantesClass.TAG;


public class VerlugaresFragment extends Fragment {

    List<List<String>> datos;
    List<String> temp;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private StorageReference mStorageRef;
    private View v;
    private ListView list;


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

        loadUsers();

        return v;
    }

    //////////////////////////////////////////////TRAERLUGARES/////////////////////////////////////////////////////////////////////////////////////////////
    public void loadUsers() {
        myRef = database.getReference(PATHLUGARESANFITRION);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int i = 0;
                temp = new ArrayList<String>();
                for (DataSnapshot singleSnapshot : dataSnapshot.getChildren()) {
                    LugarClass lugar = singleSnapshot.getValue(LugarClass.class);
                    Log.i(TAG, lugar.toString());
                    temp.add(lugar.getNombre());
                    Log.i(TAG, "SALI");
                    StorageReference lugarRef = mStorageRef.child(lugar.getPath()).child(lugar.getNombreimagenes().get(0));
                    Log.i(TAG, lugarRef.toString());
                    File localFile = null;
                    try {
                        localFile = File.createTempFile("images_" + lugar.getNombreimagenes().get(0), "jpg");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, localFile.toString());

                    lugarRef.getFile(localFile)
                            .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                    Log.i(TAG, "EXITO");

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception exception) {
                            Log.i(TAG, "FALLA");
                        }
                    });

                    temp.add(localFile.getPath());
                    temp.add("Tipo: " + lugar.getTipo() + "\nValor: " + String.valueOf(lugar.getValor()));
                    Log.i(TAG, "ddddddddd"+temp.toString());
                    datos.add(temp);
                }
                Log.i(TAG, "bbbbbb" + datos.toString());
                list.setAdapter(new LugarAdapter(getContext(), datos));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w(TAG, "error en la consulta", databaseError.toException());
            }
        });

    }

    //////////////////////////////////////////////TRAERLUGARES--/////////////////////////////////////////////////////////////////////////////////////////////


}
