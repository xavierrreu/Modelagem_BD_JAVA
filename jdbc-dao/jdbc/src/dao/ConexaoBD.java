package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ConexaoBD {
    private static ConexaoBD instancia;
    private Connection conexao;

    private ConexaoBD() {
        try {
            String url = "jdbc:sqlite:meuBancoDeDados.db"; // Caminho do arquivo do banco de dados SQLite
            conexao = DriverManager.getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ConexaoBD getInstancia() {
        if (instancia == null) {
            instancia = new ConexaoBD();
        }
        return instancia;
    }

    public Connection getConexao() {
        return conexao;
    }

    // Método para criar as tabelas no banco de dados
    public void criarTabelas() {
        String sqlAutor = "CREATE TABLE IF NOT EXISTS Autor (" +
                "ID_Autor INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nome TEXT NOT NULL," +
                "Nacionalidade TEXT" +
                ");";

        String sqlLivro = "CREATE TABLE IF NOT EXISTS Livro (" +
                "ID_Livro INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Titulo TEXT NOT NULL," +
                "Ano_Publicacao INTEGER," +
                "ID_Autor INTEGER," +
                "FOREIGN KEY(ID_Autor) REFERENCES Autor(ID_Autor)" +
                ");";

        try (Statement stmt = conexao.createStatement()) {
            // Executa as declarações SQL para criar as tabelas
            stmt.execute(sqlAutor);
            stmt.execute(sqlLivro);
            System.out.println("Tabelas criadas com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

