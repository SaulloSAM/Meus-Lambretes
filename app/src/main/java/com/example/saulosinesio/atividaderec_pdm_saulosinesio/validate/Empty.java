package com.example.saulosinesio.atividaderec_pdm_saulosinesio.validate;

public class Empty {

    /**
     * Verifica se o valor está vazio.
     * @param value
     * @return boolena
     */
    public static boolean isEmpty (String value) {
        if(value.equals("")){ return true; }
        return false;
    }

    /**
     * Verifica se o valor está vazio.
     * @param value
     * @return boolean
     */
    public static boolean isEmpty (Long value) {
        if(value.equals("")){ return true; }
        return false;
    }

}
