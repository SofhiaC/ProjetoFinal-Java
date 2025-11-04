import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        TabelaHash tabela = new TabelaHash(10);

        while (true) {
            System.out.println("\n--- Biblioteca de Jogos ---");
            System.out.println("1. Inserir jogo");
            System.out.println("2. Remover jogo");
            System.out.println("3. Buscar jogo");
            System.out.println("4. Exibir jogos ordenados");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Gênero: ");
                    String genero = sc.nextLine();
                    System.out.print("Preço: ");
                    int preco = sc.nextInt();
                    System.out.print("Avaliação: ");
                    int avaliacao = sc.nextInt();
                    tabela.inserir(new Jogo(nome, genero, preco, avaliacao));
                    break;
                case 2:
                    System.out.print("Título a remover: ");
                    String tr = sc.nextLine();
                    if (tabela.remover(tr)) System.out.println("Removido!");
                    else System.out.println("Não encontrado!");
                    break;
                case 3:
                    System.out.print("Título a buscar: ");
                    String tb = sc.nextLine();
                    Jogo j = tabela.buscar(tb);
                    System.out.println(j != null ? j : "Não encontrado!");
                    break;
                case 4:
                    Jogo[] vetor = tabela.exportarVetor();

                    if (vetor.length == 0) {
                        System.out.println("Nenhum jogo cadastrado!");
                        break;
                    }

                    System.out.println("Escolha o critério de ordenação:");
                    System.out.println("1. Por nome");
                    System.out.println("2. Por gênero");
                    System.out.println("3. Por avaliação");
                    int tipo = sc.nextInt();

                    // Exemplo: usar bubble sort (ainda pode trocar por quick ou selection)
                    AlgoritmosOrdenacao.bubbleSort(vetor, tipo);

                    System.out.println("\nJogos ordenados:");
                    for (Jogo jogo : vetor) {
                        System.out.println(jogo);
                    }
                    break;
                case 0:
                    System.out.println("Saindo...");
                    sc.close();
                    return;
            }
        }
    }
}
