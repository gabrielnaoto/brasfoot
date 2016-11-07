package com.example.tonied.futmanddm.modelo.entidade;

import com.example.tonied.futmanddm.R;

import java.util.HashMap;
import java.util.Map;

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

    public static Double[] valPatr = {1000000.0, 900000.0, 800000.0, 750000.0, 600000.0, 700000.0, 750000.0, 800000.0, 450000.0, 800000.0, 350000.0};

    public static int[] patrocinadores = {
            R.drawable.p5nike,
            R.drawable.p5adidas,
            R.drawable.p4puma,
            R.drawable.p4reebok,
            R.drawable.p3mizuno,
            R.drawable.p3underarmour,
            R.drawable.p3umbro,
            R.drawable.p2lotto,
            R.drawable.p2olympikus,
            R.drawable.p1topper,
            R.drawable.p1penalty
    };

    public static String[] n_patrocinadores = {
            "Nike",
            "Adidas",
            "Puma",
            "Reebok",
            "Mizuno",
            "Under Armour",
            "Umbro",
            "Lotto",
            "Olympikus",
            "Topper",
            "Penalty"
    };

    public static int[] e_patrocinadores = {
            5,
            5,
            4,
            4,
            3,
            3,
            3,
            2,
            2,
            1,
            1
    };

    public static String[] valores = {"1kk", "900k", "800k", "750k", "600k", "700k", "750k", "800k", "450k", "800k", "350k"};
    public static String[] ingressos = {"80", "85", "70", "65", "60", "50", "50", "45", "50", "40", "35"};
    public static String[] nomeTime = {
            "Arsenal",
            "Atl. Madrid",
            "Barcelona",
            "Bayern Munique",
            "Juventus",
            "Manchester Utd",
            "Paris SG",
            "Real Madrid"
    };


    public static Map<String, Integer> getIndicesPorTime() {
        Map<String, Integer> mapa = new HashMap<>();


        mapa.put("Arsenal", 0);
        mapa.put("Atl. Madrid", 1);
        mapa.put("Barcelona", 2);
        mapa.put("Bayern Munique", 3);
        mapa.put("Juventus", 4);
        mapa.put("Manchester Utd", 5);
        mapa.put("Paris SG", 6);
        mapa.put("Real Madrid", 7);

        return mapa;
    }

    public static int[] times = {
            R.drawable.earsenal,
            R.drawable.eatlmadrid,
            R.drawable.ebarcelona,
            R.drawable.ebayern,
            R.drawable.ejuventus,
            R.drawable.emanunited,
            R.drawable.epsg,
            R.drawable.erealm
    };

    public static String getAdversario(String time, int semana) {
        String[][] jogos = getCalendario(semana);
        for (int i = 0; i < 4; i++) {
            System.out.println(jogos[i][0] + jogos[i][1]);
            if (jogos[i][0] == time) {
                return jogos[i][1];
            } else {
                return jogos[i][0];
            }
        }
        return null;
    }

    public static boolean isCasa(String time, int semana) {
        String[][] jogos = getCalendario(semana);
        for (int i = 0; i < 4; i++) {
            if (jogos[i][0] == time) {
                return true;
            }
        }
        return false;
    }

    public static String[][] getCalendario(int semana) {
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
