package com.hepexta.jaxrs.bank.model;

public class Client {
    private String id;
    private String name;

    public Client(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
