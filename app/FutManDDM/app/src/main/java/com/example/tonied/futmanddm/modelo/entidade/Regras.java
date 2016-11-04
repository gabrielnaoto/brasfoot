package com.example.tonied.futmanddm.modelo.entidade;

public class Regras {

    public static final double CUSTO_PALESTRA = 10000;
    public static final double CUSTO_TREINO_EXTRA = 50000;
    public static final double CUSTO_RECUPERACAO_FISICA_PARCIAL = 5000;
    public static final double CUSTO_RECUPERACAO_FISICA_TOTAL = 5000;

    public static int[] getGols(Esquema[] esquemas, int[] atributos) {
        int a1, a2, d1, d2;
        a1 = esquemas[0].getAtaque() * atributos[0];
        d1 = esquemas[0].getDefesa() * atributos[0];

        a2 = esquemas[1].getAtaque() * (int) (atributos[1] * 0.9);
        d2 = esquemas[1].getDefesa() * (int) (atributos[1] * 0.9);

        int[] placar = new int[2];

        placar[0] = a1 - d2 < 0 ? 0 : a1 - d2;
        placar[1] = a2 - d1 < 0 ? 0 : a2 - d1;

        return placar;
    }

    public String[][] getCalendario (int semana){
        switch (semana){
            case 1:{
                return {{"Real Madrid", "Barcelona"},
                        {"Atlético Madrid", "Bayern"},
                        {"Juventus", "Manchester United"},
                        {"Arsenal", "Paris Saint-Germain"}};
            }
            case 2:{
                return {{"Bayern","Real Madrid"},
                        {"Barcelona","Atlético Madrid"},
                        {"Paris Saint-Germain","Juventus"},
                        {"Manchester United","Arsenal}};
            }
            case 3:{
                return {{"Real Madrid","Atlético Madrid"},
                        {"Bayern","Barcelona"},
                        {"Arsenal","Juventus"},
                        {"Manchester United","Paris Saint-Germain"}};
            }
            case 4:{
                return {{"Juventus","Real Madrid"},
                        {"Barcelona","Manchester United"},
                        {"Paris Saint-Germain","Bayern"},
                        {"Atlético Madrid","Arsenal"}};
            }
            case 5:{
                return {{"Real Madrid","Manchester United"},
                        {"Barcelona","Juventus"},
                        {"Bayern","Arsenal"},
                        {"Atlético Madrid","Paris Saint-Germain"}};
            }
            case 6:{
                return {{"Paris Saint-Germain","Real Madrid"},
                        {"Arsenal","Barcelona"},
                        {"Juventus","Bayern"},
                        {"Manchester United","Atlético Madrid"}};
            }
            case 7:{
                return {{"Real Madrid","Arsenal"},
                        {"Barcelona","Paris Saint-Germain"},
                        {"Bayern","Manchester United"},
                        {"Atlético Madrid","Juventus"}};
            }
            case 8:{
                return {{"Barcelona","Real Madrid"},
                        {"Bayern","Atlético Madrid"},
                        {"Manchester United","Juventus"},
                        {"Paris Saint-Germain","Arsenal"}};
            }
            case 9:{
                return {{"Real Madrid","Bayern"},
                        {"Atlético Madrid","Barcelona"},
                        {"Juventus","Paris Saint-Germain"},
                        {"Arsenal","Manchester United"}};
            }
            case 10:{
                return {{"Atlético Madrid","Real Madrid"},
                        {"Barcelona","Bayern"},
                        {"Juventus","Arsenal"},
                        {"Paris Saint-Germain","Manchester United"}};
            }
            case 11:{
                return {{"Real Madrid","Juventus"},
                        {"Manchester United","Barcelona"},
                        {"Bayern","Paris Saint-Germain"},
                        {"Arsenal","Atlético Madrid"}};
            }
            case 12:{
                return {{"Manchester United","Real Madrid"},
                        {"Juventus","Barcelona"},
                        {"Arsenal","Bayern"},
                        {"Paris Saint-Germain","Atlético Madrid"}};
            }
            case 13:{
                return {{"Real Madrid","Paris Saint-Germain"},
                        {"Barcelona","Arsenal"},
                        {"Bayern","Juventus"},
                        {"Atlético Madrid","Manchester United"}};
            }
            case 14:{
                return {{"Arsenal","Real Madrid"},
                        {"Paris Saint-Germain","Barcelona"},
                        {"Manchester United","Bayern"},
                        {"Juventus","Atlético Madrid"}};
            }
        }
    }
}
