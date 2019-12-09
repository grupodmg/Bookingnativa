package com.example.booking;

public class HotelModelo {
    private String titulo,descripcion;
    private int fotohotel;

    public HotelModelo() {
    }

    public HotelModelo(String titulo,String descripcion,int fotohotel) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fotohotel = fotohotel;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getFotohotel() {
        return fotohotel;
    }

    public void setFotohotel(int fotohotel) {
        this.fotohotel = fotohotel;
    }
}
