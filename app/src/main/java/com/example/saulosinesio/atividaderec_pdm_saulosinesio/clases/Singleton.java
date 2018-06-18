package com.example.saulosinesio.atividaderec_pdm_saulosinesio.clases;

import java.util.ArrayList;
import java.util.List;

public class Singleton {

    // Singleton
    private static final Singleton INSTANCE = new Singleton();
    private Singleton() {}
    public static Singleton getInstance() { return INSTANCE; }

    // Nota
    private List<Nota> notas = new ArrayList<>();

    public List<Nota> getListNotas () {
        return notas;
    }

    public void addNota (String titulo, String descricao) {
        Nota nota = new Nota(titulo, descricao);
        notas.add(nota);
        nota.setIndex(notas.size() - 1);
    }

    public void removeNota (int index){

    }

    public void removeAllNotas () {
        notas = new ArrayList<>();
    }
}
