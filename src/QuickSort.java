//implementa ordenação por nome
/*
 * Quicksort (A as array, low as int, high as int)
 *      if (low < high)
 *          pivot_location = Partition(A, low, high)
 *              Quicksort(A, low, pivot_location)
 *              Quicksort(A, pivot_location + 1, high)
 * Partition (A as array, low as int, high as int)
 *      pivot = A[low]
 *      leftwall = low
 *      for i = low + 1 to high
 *          if (A[i] < pivot) then
 *              swap (A[i], A[leftwall])
 *              leftwall = leftwall + 1
 *      swap (pivot, A[leftwall])
 *      return(leftwall)
 * --> 0(n log n) se escolher o pivot certo se não é 0(n^2)
 */

public class QuickSort {

    // Inicia o QuickSort nos índices inicial e final do vetor
    public static void ordenarPorNome(Jogo[] vetor) {
        quickSort(vetor, 0, vetor.length - 1);
    }

    public static  void quickSort(Jogo[] vetor, int inicio, int fim){

        //definição do pivô e dos "ponteiros"
        var pivo = vetor[inicio].getNome();
        int esquerda = inicio;
        int direita  = fim;

        // Mantém o loop até os ponteiros "esquerda" e "direita" se cruzarem
        while (esquerda <= direita){

            // Move o ponteiro da esquerda até achar um valor maior que o pivô
            while (vetor[esquerda].getNome().compareToIgnoreCase(pivo) < 0) {
                esquerda++;
            }

            // Move o ponteiro da direita até achar um valor menor que o pivô
            while (vetor[direita].getNome().compareToIgnoreCase(pivo) > 0) {
                direita--;
            }

            // Se os ponteiros ainda não se cruzaram, realiza a troca dos elementos
            if (esquerda <= direita){

                Jogo temp = vetor[esquerda];
                vetor[esquerda] = vetor[direita];
                vetor[direita] = temp;
                esquerda++;
                direita--;

            }
        }

        // Divide o vetor em duas partes (antes e depois do pivô)
        // e ordena cada uma recursivamente até tudo ficar em ordem
        if (inicio < direita) quickSort(vetor, inicio, direita);
        if (esquerda < fim) quickSort(vetor, esquerda, fim);

    }
}
