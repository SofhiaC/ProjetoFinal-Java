public class Jogo {
    private String nome;
    private String genero;
    private int preco;
    private int avaliacao;
    private int lancamento;

    public Jogo(String nome, String genero, int preco, int avaliacao, int lancamento) {
        this.nome = nome;
        this.genero = genero;
        this.preco = preco;
        this.avaliacao = avaliacao;
        this.lancamento = lancamento;
    }

    //métodos
    public String getNome() {
        return nome;
    }
    public String getGenero() {
        return genero;
        //System.out.println(j1.getGenero());
    }
    public int getPreco() {
        return preco;
    }
    public int getAvaliacao() {
        return avaliacao;
    }
    public int getLancamento() {
        return lancamento;
    }

    @Override
    public String toString(){ //prepara a string ex: The Witcher 3 - RPG   ($150) ☆5 (achei fofo assim)
        return nome + " - " + genero + " - " + lancamento + "   (R$" + preco + ")" + " ☆" + avaliacao;
    }

    //  O hashCode() gera um número inteiro baseado no objeto, que seria a sua posição base
//  O equals() vai comparar se os dois objetos realmente são iguais
//  eles andam juntos obrigatoriamente
    @Override
    public int hashCode(){
        return nome.toLowerCase().hashCode(); //converte tudo do nome do jogo pra minúsculo e dps gera um número hash pra ele
    }

    //  Aqui é onde ocorre a comparação entre objeto é o mesmo na memória
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true; //compara com a memória
        if (!(obj instanceof Jogo)) return false; //se for nulo ou outra coisa ent não é igual

        Jogo outro = (Jogo) obj; //faz o tal do casting (conversao de Object do equals pra Jogo)
        return this.nome.equalsIgnoreCase(outro.nome); //compara os titulos ignorando M e m
    }

    //... :)

}
