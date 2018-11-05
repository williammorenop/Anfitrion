package com.checkinnow.anfitrion;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapaSeleccionFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private Marker lastmarker;
    MapView mMapView;
    View v;
    private SupportMapFragment mapFragment;

    public MapaSeleccionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v= inflater.inflate(R.layout.fragment_mapa_seleccion, container, false);

            MapsInitializer.initialize(this.getActivity());
            mMapView = (MapView) v.findViewById(R.id.map);
            mMapView.onCreate(savedInstanceState);
            mMapView.getMapAsync(this);


        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState); mMapView.onSaveInstanceState(outState);
    }
    @Override
    public void onLowMemory()
    {
        super.onLowMemory();
        mMapView.onLowMemory();
    }
    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

            mMap = googleMap;

            // Add a marker in Sydney and move the camera
            //LatLng bogota2 = new LatLng(4.397908 , -74.076066);
       /*mMap.addMarker(new MarkerOptions().position(bogota2).title("Marcador en Plaza de Bolívar").snippet("test").alpha(1f));
            mMap.moveCamera(CameraUpdateFactory.zoomTo(10));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(bogota2));*/

            mMap.getUiSettings().setZoomControlsEnabled(true);

            mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
                @Override
                public void onMapLongClick(LatLng latLng) {

                    if(lastmarker != null)
                    {
                        lastmarker.remove();
                    }
                    lastmarker=mMap.addMarker(new MarkerOptions().position(latLng).title("Aqui"));
                }
            });

    }
}
