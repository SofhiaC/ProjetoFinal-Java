public class AlgoritmosOrdenacao {
    public static void bubbleSort(Jogo[] vetor, int tipo) {
        for (int i = 0; i < vetor.length - 1; i++) {
            for (int j = 0; j < vetor.length - i - 1; j++) {
                if (comparar(vetor[j], vetor[j + 1], tipo) > 0) {
                    Jogo temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                }
            }
        }
    }

    private static int comparar(Jogo a, Jogo b, int tipo) {
        switch (tipo) {
            case 1: return a.getNome().compareToIgnoreCase(b.getNome());
            case 2: return a.getGenero().compareToIgnoreCase(b.getGenero());
            case 3: return Integer.compare(a.getAvaliacao(), b.getAvaliacao());
            default: return 0;
        }
    }
}
