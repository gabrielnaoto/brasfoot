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

    public String[][] getCalendario(int semana) {
        switch (semana) {
            case 1: {
                String[][] valores = {{"Real Madrid", "Barcelona"},
                        {"Atlético Madrid", "Bayern"},
                        {"Juventus", "Manchester United"},
                        {"Arsenal", "Paris Saint-Germain"}};
                return valores;
            }
            case 2: {
                String[][] valores = {{"Bayern", "Real Madrid"},
                        {"Barcelona", "Atlético Madrid"},
                        {"Paris Saint-Germain", "Juventus"},
                        {"Manchester United", "Arsenal"}};
                return valores;
            }
            case 3: {
                String[][] valores = {{"Real Madrid", "Atlético Madrid"},
                        {"Bayern", "Barcelona"},
                        {"Arsenal", "Juventus"},
                        {"Manchester United", "Paris Saint-Germain"}};
                return valores;
            }
            case 4: {
                String[][] valores = {{"Juventus", "Real Madrid"},
                        {"Barcelona", "Manchester United"},
                        {"Paris Saint-Germain", "Bayern"},
                        {"Atlético Madrid", "Arsenal"}};
                return valores;
            }
            case 5: {
                String[][] valores = {{"Real Madrid", "Manchester United"},
                        {"Barcelona", "Juventus"},
                        {"Bayern", "Arsenal"},
                        {"Atlético Madrid", "Paris Saint-Germain"}};
                return valores;
            }
            case 6: {
                String[][] valores = {{"Paris Saint-Germain", "Real Madrid"},
                        {"Arsenal", "Barcelona"},
                        {"Juventus", "Bayern"},
                        {"Manchester United", "Atlético Madrid"}};
                return valores;
            }
            case 7: {
                String[][] valores = {{"Real Madrid", "Arsenal"},
                        {"Barcelona", "Paris Saint-Germain"},
                        {"Bayern", "Manchester United"},
                        {"Atlético Madrid", "Juventus"}};
                return valores;
            }
            case 8: {
                String[][] valores = {{"Barcelona", "Real Madrid"},
                        {"Bayern", "Atlético Madrid"},
                        {"Manchester United", "Juventus"},
                        {"Paris Saint-Germain", "Arsenal"}};
                return valores;
            }
            case 9: {
                String[][] valores = {{"Real Madrid", "Bayern"},
                        {"Atlético Madrid", "Barcelona"},
                        {"Juventus", "Paris Saint-Germain"},
                        {"Arsenal", "Manchester United"}};
                return valores;
            }
            case 10: {
                String[][] valores = {{"Atlético Madrid", "Real Madrid"},
                        {"Barcelona", "Bayern"},
                        {"Juventus", "Arsenal"},
                        {"Paris Saint-Germain", "Manchester United"}};
                return valores;
            }
            case 11: {
                String[][] valores = {{"Real Madrid", "Juventus"},
                        {"Manchester United", "Barcelona"},
                        {"Bayern", "Paris Saint-Germain"},
                        {"Arsenal", "Atlético Madrid"}};
                return valores;
            }
            case 12: {
                String[][] valores = {{"Manchester United", "Real Madrid"},
                        {"Juventus", "Barcelona"},
                        {"Arsenal", "Bayern"},
                        {"Paris Saint-Germain", "Atlético Madrid"}};
                return valores;
            }
            case 13: {
                String[][] valores = {{"Real Madrid", "Paris Saint-Germain"},
                        {"Barcelona", "Arsenal"},
                        {"Bayern", "Juventus"},
                        {"Atlético Madrid", "Manchester United"}};
                return valores;
            }
            case 14: {
                String[][] valores = {{"Arsenal", "Real Madrid"},
                        {"Paris Saint-Germain", "Barcelona"},
                        {"Manchester United", "Bayern"},
                        {"Juventus", "Atlético Madrid"}};
                return valores;
            }
        }
        return null;
    }
}
