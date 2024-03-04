package com.example.memerchSpring2;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
class ClientEntity {

    private @Id @GeneratedValue Integer clientID;
    private String dni;
    private String name;
    private String surname;
    private String address;

    protected ClientEntity() {
        this(null, null, null, null);
    }

    ClientEntity(String dni, String name, String surname, String address) {
        this.clientID = null;
        this.dni = dni;
        this.name = name;
        this.surname = surname;
        this.address = address;
    }

    ClientEntity(String name) {
        this.clientID = null;
        this.name = name;
    }

    public Integer getClientID() {
        return clientID;
    }

    public void setClientID(Integer clientID) {
        this.clientID = clientID;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
