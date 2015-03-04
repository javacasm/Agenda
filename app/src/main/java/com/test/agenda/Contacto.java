package com.test.agenda;

/**
 * Created by javacasm on 04/03/2015.
 * Clase de negocio
 */
public class Contacto {

    public int id;
    public String name;
    public String phone;

    public Contacto(int _id,String _name,String _phone)
    {this.id=_id;
    this.name=_name;
    this.phone=_phone;}

    @Override
    public String toString()
    {
        return "Name:"+name+" phone:"+phone;
    }
}
