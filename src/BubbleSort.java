//implementa ordenação por ano
/*
 * for i from 1 to N
 *      for j from 0 to N-1
 *          if a[j] < a[j + 1]
 *              swap (s[j], a[j+1])
 * --> 0(n^2)
 */
public class BubbleSort {

    public static void ordenarPorAno(Jogo[] vetor) {
        int n = vetor.length;
        boolean trocou;

        // Laço externo controla as passagens
        for (int i = 0; i < n - 1; i++) {
            trocou = false;

            // Laço interno faz as comparações
            for (int j = 0; j < n - i - 1; j++) {
                if (vetor[j].getLancamento() > vetor[j + 1].getLancamento()) {
                    // troca os elementos
                    Jogo temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                    trocou = true;
                }
            }

            // se não houver trocas, já está ordenado
            if (!trocou) break;
        }
    }
}

