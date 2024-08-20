import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EquipamentoDAO {
    private Connection conexao;

    public EquipamentoDAO() {
        this.conexao = ConexaoBD.getInstancia().getConexao();
    }

    public void criarTabela() {
        String sqlEquipamento = "CREATE TABLE IF NOT EXISTS Equipamento (" +
                "ID_Equipamento INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nome_Equipamento TEXT NOT NULL," +
                "Tipo TEXT" +
                ");";

        String sqlUsoEquipamento = "CREATE TABLE IF NOT EXISTS Uso_Equipamento (" +
                "ID_Projeto INTEGER," +
                "ID_Equipamento INTEGER," +
                "PRIMARY KEY (ID_Projeto, ID_Equipamento)," +
                "FOREIGN KEY (ID_Projeto) REFERENCES Projeto(ID_Projeto)," +
                "FOREIGN KEY (ID_Equipamento) REFERENCES Equipamento(ID_Equipamento)" +
                ");";

        try (Statement stmt = conexao.createStatement()) {
            stmt.execute(sqlEquipamento);
            stmt.execute(sqlUsoEquipamento);
            System.out.println("Tabela Equipamento e Uso_Equipamento criadas com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserirEquipamento(Equipamento equipamento) {
        String sql = "INSERT INTO Equipamento (Nome_Equipamento, Tipo) VALUES (?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, equipamento.getNomeEquipamento());
            stmt.setString(2, equipamento.getTipo());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    equipamento.setIdEquipamento(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarEquipamento(Equipamento equipamento) {
        String sql = "UPDATE Equipamento SET Nome_Equipamento = ?, Tipo = ? WHERE ID_Equipamento = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, equipamento.getNomeEquipamento());
            stmt.setString(2, equipamento.getTipo());
            stmt.setInt(3, equipamento.getIdEquipamento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirEquipamento(int idEquipamento) {
        String sql = "DELETE FROM Equipamento WHERE ID_Equipamento = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idEquipamento);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Equipamento> listarEquipamentos() {
        List<Equipamento> equipamentos = new ArrayList<>();
        String sql = "SELECT * FROM Equipamento";

        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Equipamento equipamento = new Equipamento(
                        rs.getInt("ID_Equipamento"),
                        rs.getString("Nome_Equipamento"),
                        rs.getString("Tipo")
                );
                equipamentos.add(equipamento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipamentos;
    }

    public void utilizarEquipamentoEmProjeto(int idProjeto, int idEquipamento) {
        String sql = "INSERT INTO Uso_Equipamento (ID_Projeto, ID_Equipamento) VALUES (?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.setInt(2, idEquipamento);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerEquipamentoDeProjeto(int idProjeto, int idEquipamento) {
        String sql = "DELETE FROM Uso_Equipamento WHERE ID_Projeto = ? AND ID_Equipamento = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.setInt(2, idEquipamento);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
