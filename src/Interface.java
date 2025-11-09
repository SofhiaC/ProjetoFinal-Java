import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Interface {

    private TabelaHash tabela = new TabelaHash(10);
    private ObservableList<Jogo> listaJogos = FXCollections.observableArrayList();
    private TableView<Jogo> tableView = new TableView<>();

    public Parent criarInterface() {
        // --- Campos de inserção ---
        TextField nomeField = criarCampo("Nome");
        TextField generoField = criarCampo("Gênero");
        TextField precoField = criarCampo("Preço");
        TextField avaliacaoField = criarCampo("Avaliação");
        TextField lancamentoField = criarCampo("Ano de lançamento");

        Button inserirBtn = new Button("Adicionar Jogo");
        inserirBtn.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold; "
                + "-fx-background-radius: 8; -fx-padding: 8 16; -fx-cursor: hand;");
        inserirBtn.setOnMouseEntered(e -> inserirBtn.setStyle("-fx-background-color: #27ae60; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-padding: 8 16;"));
        inserirBtn.setOnMouseExited(e -> inserirBtn.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-padding: 8 16;"));

        HBox form = new HBox(10, nomeField, generoField, precoField, avaliacaoField, lancamentoField, inserirBtn);
        form.setPadding(new Insets(10));
        form.setAlignment(Pos.CENTER);

        // --- Colunas da tabela ---
        TableColumn<Jogo, String> nomeCol = new TableColumn<>("Nome");
        nomeCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getNome()));

        TableColumn<Jogo, String> generoCol = new TableColumn<>("Gênero");
        generoCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getGenero()));

        TableColumn<Jogo, Integer> precoCol = new TableColumn<>("Preço");
        precoCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getPreco()).asObject());

        TableColumn<Jogo, Integer> avaliacaoCol = new TableColumn<>("Avaliação");
        avaliacaoCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getAvaliacao()).asObject());

        TableColumn<Jogo, Integer> lancamentoCol = new TableColumn<>("Ano");
        lancamentoCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getLancamento()).asObject());

        // --- Coluna de exclusão ---
        TableColumn<Jogo, Void> acaoCol = new TableColumn<>("Ações");
        acaoCol.setCellFactory(param -> new TableCell<>() {
            private final Button excluirBtn = new Button("Excluir");
            {
                excluirBtn.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; -fx-font-weight: bold; "
                        + "-fx-background-radius: 6; -fx-cursor: hand;");
                excluirBtn.setOnAction(e -> {
                    Jogo jogo = getTableView().getItems().get(getIndex());
                    tabela.remover(jogo.getNome());
                    atualizarLista();
                });
                setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
                setAlignment(Pos.CENTER);
            }
            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                setGraphic(empty ? null : excluirBtn);
            }
        });

        // --- Monta tabela ---
        tableView.getColumns().setAll(nomeCol, generoCol, precoCol, avaliacaoCol, lancamentoCol, acaoCol);
        tableView.setItems(listaJogos);
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tableView.setPlaceholder(new Label("Nenhum jogo cadastrado"));
        tableView.setStyle("""
            -fx-background-color: white;
            -fx-border-color: #d0d0d0;
            -fx-border-radius: 8;
            -fx-background-radius: 8;
            -fx-table-header-border-color: transparent;
            -fx-background-color: #b8b8b8ff;
        """);


        // --- Cabeçalho roxo claro ---
        tableView.widthProperty().addListener((obs, oldVal, newVal) -> {
            tableView.lookupAll(".column-header-background").forEach(node ->
                    node.setStyle("-fx-background-color: #d9c6f0;")
            );
        });

        // --- Botões de ordenação ---
        Button ordenarNomeBtn = criarBotaoOrdenacao("Ordenar por Nome");
        Button ordenarGeneroBtn = criarBotaoOrdenacao("Ordenar por Gênero");
        Button ordenarAnoBtn = criarBotaoOrdenacao("Ordenar por Ano");

        ordenarNomeBtn.setOnAction(e -> ordenar("nome"));
        ordenarGeneroBtn.setOnAction(e -> ordenar("genero"));
        ordenarAnoBtn.setOnAction(e -> ordenar("ano"));

        HBox ordenacoes = new HBox(10, ordenarNomeBtn, ordenarGeneroBtn, ordenarAnoBtn);
        ordenacoes.setPadding(new Insets(10));
        ordenacoes.setAlignment(Pos.CENTER);

        // --- Layout principal ---
        VBox layout = new VBox(15, form, ordenacoes, tableView);
        layout.setPadding(new Insets(15));
        layout.setStyle("-fx-background-color: #f2f2f2;"); // fundo cinza clarinho

        // --- Inserção ---
        inserirBtn.setOnAction(e -> {
            try {
                String nome = nomeField.getText();
                String genero = generoField.getText();
                int preco = Integer.parseInt(precoField.getText());
                int avaliacao = Integer.parseInt(avaliacaoField.getText());
                int lancamento = Integer.parseInt(lancamentoField.getText());

                Jogo jogo = new Jogo(nome, genero, preco, avaliacao, lancamento);
                tabela.inserir(jogo);
                atualizarLista();

                nomeField.clear();
                generoField.clear();
                precoField.clear();
                avaliacaoField.clear();
                lancamentoField.clear();

            } catch (NumberFormatException ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Preço, Avaliação e Ano devem ser números!");
                alert.showAndWait();
            }
        });

        return layout;
    }

    // --- Cria campos padronizados ---
    private TextField criarCampo(String placeholder) {
        TextField campo = new TextField();
        campo.setPromptText(placeholder);
        campo.setStyle("""
            -fx-background-color: white;
            -fx-background-radius: 8;
            -fx-border-radius: 8;
            -fx-border-color: #cccccc;
            -fx-padding: 6;
        """);
        return campo;
    }

    // --- Cria botões de ordenação padronizados ---
    private Button criarBotaoOrdenacao(String texto) {
        Button btn = new Button(texto);
        btn.setStyle("-fx-background-color: #a29bfe; -fx-text-fill: white; -fx-font-weight: bold; "
                + "-fx-background-radius: 8; -fx-padding: 8 16; -fx-cursor: hand;");
        btn.setOnMouseEntered(e -> btn.setStyle("-fx-background-color: #6c5ce7; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-padding: 8 16;"));
        btn.setOnMouseExited(e -> btn.setStyle("-fx-background-color: #a29bfe; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 8; -fx-padding: 8 16;"));
        return btn;
    }

    private void atualizarLista() {
        Jogo[] jogos = tabela.exportarVetor();
        listaJogos.setAll(jogos);
    }

    private void ordenar(String tipo) {
        Jogo[] jogos = tabela.exportarVetor();

        // Aqui você depois chama seu algoritmo de ordenação correspondente
        switch (tipo) {
            case "nome":
                QuickSort.ordenarPorNome(jogos);
                break;
            case "genero":
                InsertionSort.ordenarPorGenero(jogos); // exemplo
                break;
            case "ano":
                BubbleSort.ordenarPorAno(jogos); // exemplo
                break;
        }

        listaJogos.setAll(jogos);
    }
}
