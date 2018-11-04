package com.checkinnow.anfitrion;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MenuAnfitrionActivity extends AppCompatActivity implements MenuAnfitrionFragment.OnFragmentInteractionListener{


    MenuAnfitrionFragment menufragmetn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_anfitrion);


        menufragmetn=new MenuAnfitrionFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.contenedor,menufragmetn).commit();


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
