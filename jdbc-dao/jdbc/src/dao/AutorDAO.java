package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutorDAO {

    private Connection conexao;

    public AutorDAO() {
        this.conexao = ConexaoBD.getInstancia().getConexao();
    }

    public void criarTabela() {
        String sqlAutor = "CREATE TABLE IF NOT EXISTS Autor (" +
                "ID_Autor INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nome TEXT NOT NULL," +
                "Nacionalidade TEXT" +
                ");";
        try (Statement stmt = conexao.createStatement()) {
            // Executa as declarações SQL para criar as tabelas
            stmt.execute(sqlAutor);
            System.out.println("Tabelas criadas com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void inserirAutor(Autor autor) {
        String sql = "INSERT INTO Autor (Nome, Nacionalidade) VALUES (?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    autor.setIdAutor(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarAutor(Autor autor) {
        String sql = "UPDATE Autor SET Nome = ?, Nacionalidade = ? WHERE ID_Autor = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, autor.getNome());
            stmt.setString(2, autor.getNacionalidade());
            stmt.setInt(3, autor.getIdAutor());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirAutor(int idAutor) {
        String sql = "DELETE FROM Autor WHERE ID_Autor = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idAutor);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Autor> listarAutores() {
        List<Autor> autores = new ArrayList<>();
        String sql = "SELECT * FROM Autor";

        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Autor autor = new Autor(rs.getInt("ID_Autor"), rs.getString("Nome"), rs.getString("Nacionalidade"));
                autores.add(autor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return autores;
    }
}
