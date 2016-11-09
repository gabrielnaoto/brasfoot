package com.example.tonied.futmanddm.modelo.entidade;

import com.example.tonied.futmanddm.R;

import java.util.HashMap;
import java.util.Map;

public class Regras {

    public static final double MOTIVACAO = 200000;
    public static final double TECNICA = 200000;
    public static final double REABILITACAO = 100000;
    public static final double FISICA = 300000;


    public static int[] getGols(Esquema[] esquemas, int[] atributos) {
        int a1, a2, d1, d2;
        a1 = (int) Math.ceil((esquemas[0].getAtaque() * atributos[0]) / 1000);
        d1 = (int) Math.ceil((esquemas[0].getDefesa() * atributos[0]) / 1000);

        a2 = (int) Math.ceil((esquemas[1].getAtaque() * atributos[1]) / 1000);
        d2 = (int) Math.ceil((esquemas[1].getDefesa() * atributos[1]) / 1000);

        int[] placar = new int[2];

        placar[0] = a1 - d2 < 0 ? 0 : a1 - d2;
        placar[1] = a2 - d1 < 0 ? 0 : a2 - d1;

        return placar;
    }

    public static Double[] valPatr = {300000.0, 400000.0, 500000.0, 600000.0, 700000.0, 800000.0, 900000.0, 1000000.0, 1150000.0, 1200000.0, 1250000.0};
    public static String[] valores = {"300 mil", "400 mil", "500 mil", "600 mil", "700 mil", "800 mil", "900 mil", "1 milhão", "1 milhão 150 mil", "1 milhão 200 mil", "1 milhão 250 mil"};
    public static Double[] valIng = {85.0, 80.0, 70.0, 65.0, 60.0, 55.0, 50.0, 45.0, 40.0, 35.0, 30.0};
    public static String[] ingressos = {"85", "80", "70", "65", "60", "55", "50", "45", "40", "35", "30"};

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

    public static String[] nomeTime = {
            "Arsenal",
            "Atl. Madrid",
            "Barcelona",
            "Bayern",
            "Juventus",
            "Manchester Utd",
            "Paris SG",
            "Real Madrid"
    };

    public static int getPublico(int colocacao) {
        switch (colocacao) {
            case 1:
                return 58000 + (int) Math.random() * 2000;
            case 2:
                return 52000 + (int) Math.random() * 2000;
            case 3:
                return 46000 + (int) Math.random() * 2000;
            case 4:
                return 40000 + (int) Math.random() * 2000;
            case 5:
                return 34000 + (int) Math.random() * 2000;
            case 6:
                return 28000 + (int) Math.random() * 2000;
            case 7:
                return 22000 + (int) Math.random() * 2000;
            case 8:
                return 20000 + (int) Math.random() * 2000;
        }
        return 20000;
    }


    public static Map<String, Integer> getIndicesPorTime() {
        Map<String, Integer> mapa = new HashMap<>();

        mapa.put("Arsenal", 0);
        mapa.put("Atl. Madrid", 1);
        mapa.put("Barcelona", 2);
        mapa.put("Bayern", 3);
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
        System.out.println(time);
        for (int i = 0; i < 4; i++) {
            System.out.println(jogos[i][0] + " x " + jogos[i][1]);
            if (jogos[i][0].equalsIgnoreCase(time)) {
                System.out.println(jogos[i][1]);
                return jogos[i][1];
            }
            if (jogos[i][1].equalsIgnoreCase(time)) {
                System.out.println(jogos[i][0]);
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
                        {"Atl. Madrid", "Bayern"},
                        {"Juventus", "Manchester Utd"},
                        {"Arsenal", "Paris SG"}};
                return valores;
            }
            case 2: {
                String[][] valores = {{"Bayern", "Real Madrid"},
                        {"Barcelona", "Atl. Madrid"},
                        {"Paris SG", "Juventus"},
                        {"Manchester Utd", "Arsenal"}};
                return valores;
            }
            case 3: {
                String[][] valores = {{"Real Madrid", "Atl. Madrid"},
                        {"Bayern", "Barcelona"},
                        {"Arsenal", "Juventus"},
                        {"Manchester Utd", "Paris SG"}};
                return valores;
            }
            case 4: {
                String[][] valores = {{"Juventus", "Real Madrid"},
                        {"Barcelona", "Manchester Utd"},
                        {"Paris SG", "Bayern"},
                        {"Atl. Madrid", "Arsenal"}};
                return valores;
            }
            case 5: {
                String[][] valores = {{"Real Madrid", "Manchester Utd"},
                        {"Barcelona", "Juventus"},
                        {"Bayern", "Arsenal"},
                        {"Atl. Madrid", "Paris SG"}};
                return valores;
            }
            case 6: {
                String[][] valores = {{"Paris SG", "Real Madrid"},
                        {"Arsenal", "Barcelona"},
                        {"Juventus", "Bayern"},
                        {"Manchester Utd", "Atl. Madrid"}};
                return valores;
            }
            case 7: {
                String[][] valores = {{"Real Madrid", "Arsenal"},
                        {"Barcelona", "Paris SG"},
                        {"Bayern", "Manchester Utd"},
                        {"Atl. Madrid", "Juventus"}};
                return valores;
            }
            case 8: {
                String[][] valores = {{"Barcelona", "Real Madrid"},
                        {"Bayern", "Atl. Madrid"},
                        {"Manchester Utd", "Juventus"},
                        {"Paris SG", "Arsenal"}};
                return valores;
            }
            case 9: {
                String[][] valores = {{"Real Madrid", "Bayern"},
                        {"Atl. Madrid", "Barcelona"},
                        {"Juventus", "Paris SG"},
                        {"Arsenal", "Manchester Utd"}};
                return valores;
            }
            case 10: {
                String[][] valores = {{"Atl. Madrid", "Real Madrid"},
                        {"Barcelona", "Bayern"},
                        {"Juventus", "Arsenal"},
                        {"Paris SG", "Manchester Utd"}};
                return valores;
            }
            case 11: {
                String[][] valores = {{"Real Madrid", "Juventus"},
                        {"Manchester Utd", "Barcelona"},
                        {"Bayern", "Paris SG"},
                        {"Arsenal", "Atl. Madrid"}};
                return valores;
            }
            case 12: {
                String[][] valores = {{"Manchester Utd", "Real Madrid"},
                        {"Juventus", "Barcelona"},
                        {"Arsenal", "Bayern"},
                        {"Paris SG", "Atl. Madrid"}};
                return valores;
            }
            case 13: {
                String[][] valores = {{"Real Madrid", "Paris SG"},
                        {"Barcelona", "Arsenal"},
                        {"Bayern", "Juventus"},
                        {"Atl. Madrid", "Manchester Utd"}};
                return valores;
            }
            case 14: {
                String[][] valores = {{"Arsenal", "Real Madrid"},
                        {"Paris SG", "Barcelona"},
                        {"Manchester Utd", "Bayern"},
                        {"Juventus", "Atl. Madrid"}};
                return valores;
            }
        }
        return null;
    }
}
