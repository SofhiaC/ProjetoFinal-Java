import java.util.LinkedList;

public class TabelaHash {
    private LinkedList<Jogo>[] tabela;
    private int tamanho;

    @SuppressWarnings("unchecked") //frescura pra esconder aviso msm mas eu achei legal, é recomendável pra hash map genérico
    public TabelaHash(int tamanho){
        this.tamanho = tamanho;
        tabela = new LinkedList[tamanho]; //aqui diz q o id da linked list vai corresponder ao atributo tamanho
        for (int i = 0; i < tamanho; i++){ //calculo já que começa em zero a Linked List
            tabela[i] = new LinkedList<>(); //instancia nova lista vazia para cada valor de i da tabela já que vamos preencher com um conjunto de informações dos jogos
        }
    }

    private int hash(String nome){
        return Math.abs(nome.toLowerCase().hashCode() % tamanho);
    }

    //inserção recursiva
    public void inserir(Jogo jogo){
        int indice = hash(jogo.getNome());
        inserirRec(tabela[indice], jogo, 0);
    }

    private void inserirRec(LinkedList<Jogo> lista, Jogo jogo, int posicao){
        if (posicao == lista.size()){
            lista.add(jogo);
            return;
        }
        if (lista.get(posicao).equals(jogo)){
            System.out.println("O jogo inserido já existe.");
            return;
        }
        inserirRec(lista, jogo, posicao + 1);
    }

    //busca
    public Jogo buscar(String nome){
        int indice = hash(nome);
        return buscarRec(tabela[indice], nome, 0);
    }

    private Jogo buscarRec(LinkedList<Jogo> lista, String nome, int posicao){
        if (posicao == lista.size()) return null;
        if (lista.get(posicao).getNome().equalsIgnoreCase(nome))
            return lista.get(posicao);
        return buscarRec(lista, nome, posicao + 1);
    }

    //remover
    public boolean remover(String nome){
        int indice = hash(nome);
        return removerRec(tabela[indice], nome, 0);
    }

    private boolean removerRec(LinkedList<Jogo> lista, String nome, int posicao){
        if (posicao == lista.size()) return false;
        if (lista.get(posicao).getNome().equalsIgnoreCase(nome)){
            lista.remove(posicao);
            return true;
        }
        return removerRec(lista, nome, posicao + 1);
    }

    //exportar pro vetor (copia os dados e manda da tabela hash até o array simples/vetor
    //teoricamente na tabela hash eles estão
    public Jogo[] exportarVetor(){
        Jogo[] vetor = new Jogo[contarElementos()];
        preencherRec(vetor, 0, 0);
        return vetor;
    }

    private int preencherRec(Jogo[] vetor, int indiceTabela, int posicaoAtual){
        if (indiceTabela == tamanho) return posicaoAtual;
        for (Jogo jogo : tabela[indiceTabela]){
            vetor[posicaoAtual++] = jogo;
        }
        return preencherRec(vetor, indiceTabela + 1, posicaoAtual);
    }

    private int contarElementos(){
        int total = 0;
        for (LinkedList<Jogo> lista : tabela) total += lista.size();
        return total;
    }

    //... :)

}
