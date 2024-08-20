import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EngenheiroDAO {
    private Connection conexao;

    public EngenheiroDAO() {
        this.conexao = ConexaoBD.getInstancia().getConexao();
    }

    public void criarTabela() {
        String sqlEngenheiro = "CREATE TABLE IF NOT EXISTS Engenheiro (" +
                "ID_Engenheiro INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nome_Engenheiro TEXT NOT NULL," +
                "Especialidade TEXT" +
                ");";

        String sqlAlocacaoEngenheiro = "CREATE TABLE IF NOT EXISTS Alocacao_Engenheiro (" +
                "ID_Projeto INTEGER," +
                "ID_Engenheiro INTEGER," +
                "PRIMARY KEY (ID_Projeto, ID_Engenheiro)," +
                "FOREIGN KEY (ID_Projeto) REFERENCES Projeto(ID_Projeto)," +
                "FOREIGN KEY (ID_Engenheiro) REFERENCES Engenheiro(ID_Engenheiro)" +
                ");";

        try (Statement stmt = conexao.createStatement()) {
            stmt.execute(sqlEngenheiro);
            stmt.execute(sqlAlocacaoEngenheiro);
            System.out.println("Tabela Engenheiro e Alocacao_Engenheiro criadas com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserirEngenheiro(Engenheiro engenheiro) {
        String sql = "INSERT INTO Engenheiro (Nome_Engenheiro, Especialidade) VALUES (?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, engenheiro.getNomeEngenheiro());
            stmt.setString(2, engenheiro.getEspecialidade());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    engenheiro.setIdEngenheiro(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarEngenheiro(Engenheiro engenheiro) {
        String sql = "UPDATE Engenheiro SET Nome_Engenheiro = ?, Especialidade = ? WHERE ID_Engenheiro = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, engenheiro.getNomeEngenheiro());
            stmt.setString(2, engenheiro.getEspecialidade());
            stmt.setInt(3, engenheiro.getIdEngenheiro());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirEngenheiro(int idEngenheiro) {
        String sql = "DELETE FROM Engenheiro WHERE ID_Engenheiro = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idEngenheiro);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Engenheiro> listarEngenheiros() {
        List<Engenheiro> engenheiros = new ArrayList<>();
        String sql = "SELECT * FROM Engenheiro";

        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Engenheiro engenheiro = new Engenheiro(
                        rs.getInt("ID_Engenheiro"),
                        rs.getString("Nome_Engenheiro"),
                        rs.getString("Especialidade")
                );
                engenheiros.add(engenheiro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return engenheiros;
    }

    public void alocarEngenheiroEmProjeto(int idProjeto, int idEngenheiro) {
        String sql = "INSERT INTO Alocacao_Engenheiro (ID_Projeto, ID_Engenheiro) VALUES (?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.setInt(2, idEngenheiro);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void desalocarEngenheiroDeProjeto(int idProjeto, int idEngenheiro) {
        String sql = "DELETE FROM Alocacao_Engenheiro WHERE ID_Projeto = ? AND ID_Engenheiro = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.setInt(2, idEngenheiro);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
