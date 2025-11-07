//implementa ordenação por genero
/*
 * for i : 1 to length(A) - 1
 *      j = i
 *          while j > 0 and A[j-1] > A[j]
 *              swap A[j] and A[j-1]
 *                  j = j -1
 * --> 0(n^2)
 */

public class InsertionSort {
    public static void ordenarPorGenero(Jogo[] vetor) {
        for (int i = 1; i < vetor.length; i++) {
            Jogo atual = vetor[i];
            int j = i - 1;
            while (j >= 0 && vetor[j].getGenero().compareToIgnoreCase(atual.getGenero()) > 0) {
                vetor[j + 1] = vetor[j];
                j--;
            }
            vetor[j + 1] = atual;
        }
    }
}
