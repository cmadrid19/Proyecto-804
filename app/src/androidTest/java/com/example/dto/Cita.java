package com.example.dto;

public class Cita {

    int _id;
    String fecha;
    String hora;
    String asunto;

    public Cita(String fecha, String hora, String asunto) {
        this.fecha = fecha;
        this.hora = hora;
        this.asunto = asunto;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }


    @Override
    public String toString() {
        return "Cita{" +
                "_id=" + _id +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}
