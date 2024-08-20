import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OperarioDAO {
    private Connection conexao;

    public OperarioDAO() {
        this.conexao = ConexaoBD.getInstancia().getConexao();
    }

    public void criarTabela() {
        String sqlOperario = "CREATE TABLE IF NOT EXISTS Operario (" +
                "ID_Operario INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nome_Operario TEXT NOT NULL," +
                "Funcao TEXT" +
                ");";

        String sqlAlocacaoOperario = "CREATE TABLE IF NOT EXISTS Alocacao_Operario (" +
                "ID_Projeto INTEGER," +
                "ID_Operario INTEGER," +
                "PRIMARY KEY (ID_Projeto, ID_Operario)," +
                "FOREIGN KEY (ID_Projeto) REFERENCES Projeto(ID_Projeto)," +
                "FOREIGN KEY (ID_Operario) REFERENCES Operario(ID_Operario)" +
                ");";

        try (Statement stmt = conexao.createStatement()) {
            stmt.execute(sqlOperario);
            stmt.execute(sqlAlocacaoOperario);
            System.out.println("Tabela Operario e Alocacao_Operario criadas com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserirOperario(Operario operario) {
        String sql = "INSERT INTO Operario (Nome_Operario, Funcao) VALUES (?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, operario.getNomeOperario());
            stmt.setString(2, operario.getFuncao());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    operario.setIdOperario(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarOperario(Operario operario) {
        String sql = "UPDATE Operario SET Nome_Operario = ?, Funcao = ? WHERE ID_Operario = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, operario.getNomeOperario());
            stmt.setString(2, operario.getFuncao());
            stmt.setInt(3, operario.getIdOperario());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirOperario(int idOperario) {
        String sql = "DELETE FROM Operario WHERE ID_Operario = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idOperario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Operario> listarOperarios() {
        List<Operario> operarios = new ArrayList<>();
        String sql = "SELECT * FROM Operario";

        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Operario operario = new Operario(
                        rs.getInt("ID_Operario"),
                        rs.getString("Nome_Operario"),
                        rs.getString("Funcao")
                );
                operarios.add(operario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return operarios;
    }

    public void alocarOperarioEmProjeto(int idProjeto, int idOperario) {
        String sql = "INSERT INTO Alocacao_Operario (ID_Projeto, ID_Operario) VALUES (?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.setInt(2, idOperario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void desalocarOperarioDeProjeto(int idProjeto, int idOperario) {
        String sql = "DELETE FROM Alocacao_Operario WHERE ID_Projeto = ? AND ID_Operario = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.setInt(2, idOperario);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
