package Modelo;

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
    private List<String> nombreimagenes;

    public LugarClass() {
        nombreimagenes = new ArrayList<String>();
    }

    public List<String> getNombreimagenes() {
        return nombreimagenes;
    }

    public void setNombreimagenes(List<String> nombreimagenes) {
        this.nombreimagenes = nombreimagenes;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

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
                '}';
    }

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
