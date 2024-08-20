package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    private Connection conexao;

    public LivroDAO() {
        this.conexao = ConexaoBD.getInstancia().getConexao();
    }
    public void criarTabelas() {
        String sqlLivro = "CREATE TABLE IF NOT EXISTS Livro (" +
                "ID_Livro INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Titulo TEXT NOT NULL," +
                "Ano_Publicacao INTEGER," +
                "ID_Autor INTEGER," +
                "FOREIGN KEY(ID_Autor) REFERENCES Autor(ID_Autor)" +
                ");";
        try (Statement stmt = conexao.createStatement()) {
            // Executa as declarações SQL para criar as tabelas
            stmt.execute(sqlLivro);
            System.out.println("Tabelas criadas com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void inserirLivro(Livro livro) {
        String sql = "INSERT INTO Livro (Titulo, Ano_Publicacao, ID_Autor) VALUES (?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setInt(2, livro.getAnoPublicacao());
            stmt.setInt(3, livro.getIdAutor());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    livro.setIdLivro(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarLivro(Livro livro) {
        String sql = "UPDATE Livro SET Titulo = ?, Ano_Publicacao = ?, ID_Autor = ? WHERE ID_Livro = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, livro.getTitulo());
            stmt.setInt(2, livro.getAnoPublicacao());
            stmt.setInt(3, livro.getIdAutor());
            stmt.setInt(4, livro.getIdLivro());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirLivro(int idLivro) {
        String sql = "DELETE FROM Livro WHERE ID_Livro = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idLivro);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Livro> listarLivros() {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM Livro";

        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Livro livro = new Livro(rs.getInt("ID_Livro"), rs.getString("Titulo"), rs.getInt("Ano_Publicacao"), rs.getInt("ID_Autor"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }

    public List<Livro> listarLivrosPorAutor(int idAutor) {
        List<Livro> livros = new ArrayList<>();
        String sql = "SELECT * FROM Livro WHERE ID_Autor = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idAutor);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Livro livro = new Livro(rs.getInt("ID_Livro"), rs.getString("Titulo"), rs.getInt("Ano_Publicacao"), rs.getInt("ID_Autor"));
                    livros.add(livro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return livros;
    }
}
