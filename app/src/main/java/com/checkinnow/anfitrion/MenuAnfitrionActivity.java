package com.checkinnow.anfitrion;


import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MenuAnfitrionActivity extends AppCompatActivity{


   // MenuAnfitrionFragment menufragmetn;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_anfitrion);


        MenuAnfitrionFragment menufragmetn=new MenuAnfitrionFragment();
        View view = findViewById(R.id.contenedor);

        if(view == null){
        }
        else{
            MenuAnfitrionFragment crear1 = new MenuAnfitrionFragment();
            FragmentManager fm = getFragmentManager();
            FragmentTransaction fragmentTransaction = fm.beginTransaction();
            fragmentTransaction.replace(R.id.contenedor, crear1); //donde fragmentContainer_id es el ID del FrameLayout donde tu Fragment está contenido.
            fragmentTransaction.commit();
        }


    }

}
