package Modelo;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.checkinnow.anfitrion.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static Modelo.ContantesClass.TAG;


public class LugarAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;

    Context contexto;
    List<List<String>> datos;


    public LugarAdapter(Context conexto, List<List<String>> datos)
    {
        this.contexto = conexto;
        this.datos = datos;

        inflater = (LayoutInflater)conexto.getSystemService(conexto.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        final View vista = inflater.inflate(R.layout.adapterlugares, null);

        TextView titulo = (TextView) vista.findViewById(R.id.tituloLugar);
        TextView descrip = (TextView) vista.findViewById(R.id.descipciontext);

        ImageView imagen = (ImageView) vista.findViewById(R.id.imagLugar);

        Log.i(TAG,datos.toString());

        List<String> temp = new ArrayList<String>(datos.get(i));
        Log.i(TAG,"aaaaaaaaa"+datos.toString());
        titulo.setText(temp.get(0));
        descrip.setText(temp.get(2));


        File imgFile = new File(temp.get(1));
        Log.i(TAG,imgFile.toString());
        if (imgFile.exists()) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 2;
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getPath(), options);
            imagen.setImageBitmap(myBitmap);
        }
        else
        {
            Log.i(TAG,"jujujujujujujujujujuj");
        }

/*
        imagen.setTag(i);

        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent visorImagen = new Intent(contexto, VisorImagen.class);
                visorImagen.putExtra("IMG", datosImg[(Integer)v.getTag()]);
                contexto.startActivity(visorImagen);
            }
        });*/


        return vista;
    }

    @Override
    public int getCount() {
        return datos.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
}