package br.udesc.ddm.brasfoot.modelo.entidade;

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

}
