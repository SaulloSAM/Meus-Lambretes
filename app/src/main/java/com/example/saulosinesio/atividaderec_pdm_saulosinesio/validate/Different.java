package com.example.saulosinesio.atividaderec_pdm_saulosinesio.validate;

public class Different {

    /**
     * Verifica se o valor X é diferente do valor Y
     * @param x
     * @param y
     * @return boolean
     */
    public static boolean isDifferent (String x, String y){
        if(x.equals(y)){ return false; }
        return true;
    }
}
