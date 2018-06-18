package com.example.saulosinesio.atividaderec_pdm_saulosinesio.clases;

public class Nota {

    private String titulo, nota;
    private int index;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public Nota(String titulo, String nota) {

        this.titulo = titulo;
        this.nota = nota;
    }
}
