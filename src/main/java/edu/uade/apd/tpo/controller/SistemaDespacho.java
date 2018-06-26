package edu.uade.apd.tpo.controller;

public class SistemaDespacho {

    private static SistemaDespacho instance;

    private SistemaDespacho() {

    }

    public static SistemaDespacho getInstance() {
        if (instance == null) {
            instance = new SistemaDespacho();
        }
        return instance;
    }

}
