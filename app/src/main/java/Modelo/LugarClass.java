package Modelo;

import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class LugarClass {

    private double latitude;
    private double longitud;
    private double valor;
    private String nombre;
    private String tipo;
    private String ID;
    private String path;
    //private List<Uri> lugares;

    public LugarClass() {
        //lugares = new ArrayList<Uri>();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    //public List<Uri> getLugares() {
    //    return lugares;
    //}

   // public void setLugares(List<Uri> lugares) {
   //     this.lugares = lugares;
   // }

    @Override
    public String toString() {
        return "LugarClass{" +
                "latitude=" + latitude +
                ", longitud=" + longitud +
                ", valor=" + valor +
                ", nombre='" + nombre + '\'' +
                ", tipo='" + tipo + '\'' +
                ", ID='" + ID + '\'' +
                ", path='" + path + '\'' +
                //", lugares=" + lugares +
                '}';
    }

   /* public int agregarlugar(Uri uri) {
        this.lugares.add(uri);
        return 1;
    }*/

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
