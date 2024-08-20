import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjetoDAO {
    private Connection conexao;

    public ProjetoDAO() {
        this.conexao = ConexaoBD.getInstancia().getConexao();
    }

    public void criarTabela() {
        String sqlProjeto = "CREATE TABLE IF NOT EXISTS Projeto (" +
                "ID_Projeto INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nome_Projeto TEXT NOT NULL," +
                "Local TEXT NOT NULL," +
                "Data_Inicio TEXT," +
                "Data_Termino TEXT" +
                ");";

        try (Statement stmt = conexao.createStatement()) {
            stmt.execute(sqlProjeto);
            System.out.println("Tabela Projeto criada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserirProjeto(Projeto projeto) {
        String sql = "INSERT INTO Projeto (Nome_Projeto, Local, Data_Inicio, Data_Termino) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setString(2, projeto.getLocal());
            stmt.setString(3, projeto.getDataInicio());
            stmt.setString(4, projeto.getDataTermino());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    projeto.setIdProjeto(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarProjeto(Projeto projeto) {
        String sql = "UPDATE Projeto SET Nome_Projeto = ?, Local = ?, Data_Inicio = ?, Data_Termino = ? WHERE ID_Projeto = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, projeto.getNomeProjeto());
            stmt.setString(2, projeto.getLocal());
            stmt.setString(3, projeto.getDataInicio());
            stmt.setString(4, projeto.getDataTermino());
            stmt.setInt(5, projeto.getIdProjeto());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirProjeto(int idProjeto) {
        String sql = "DELETE FROM Projeto WHERE ID_Projeto = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Projeto> listarProjetos() {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM Projeto";

        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Projeto projeto = new Projeto(
                        rs.getInt("ID_Projeto"),
                        rs.getString("Nome_Projeto"),
                        rs.getString("Local"),
                        rs.getString("Data_Inicio"),
                        rs.getString("Data_Termino")
                );
                projetos.add(projeto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return projetos;
    }

    public List<Engenheiro> listarEngenheirosPorProjeto(int idProjeto) {
        List<Engenheiro> engenheiros = new ArrayList<>();
        String sql = "SELECT e.* FROM Engenheiro e " +
                "INNER JOIN Alocacao_Engenheiro ae ON e.ID_Engenheiro = ae.ID_Engenheiro " +
                "WHERE ae.ID_Projeto = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Engenheiro engenheiro = new Engenheiro(
                            rs.getInt("ID_Engenheiro"),
                            rs.getString("Nome_Engenheiro"),
                            rs.getString("Especialidade")
                    );
                    engenheiros.add(engenheiro);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return engenheiros;
    }

    public List<Operario> listarOperariosPorProjeto(int idProjeto) {
        List<Operario> operarios = new ArrayList<>();
        String sql = "SELECT o.* FROM Operario o " +
                "INNER JOIN Alocacao_Operario ao ON o.ID_Operario = ao.ID_Operario " +
                "WHERE ao.ID_Projeto = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Operario operario = new Operario(
                            rs.getInt("ID_Operario"),
                            rs.getString("Nome_Operario"),
                            rs.getString("Funcao")
                    );
                    operarios.add(operario);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return operarios;
    }

    public List<Equipamento> listarEquipamentosPorProjeto(int idProjeto) {
        List<Equipamento> equipamentos = new ArrayList<>();
        String sql = "SELECT e.* FROM Equipamento e " +
                "INNER JOIN Uso_Equipamento ue ON e.ID_Equipamento = ue.ID_Equipamento " +
                "WHERE ue.ID_Projeto = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Equipamento equipamento = new Equipamento(
                            rs.getInt("ID_Equipamento"),
                            rs.getString("Nome_Equipamento"),
                            rs.getString("Tipo")
                    );
                    equipamentos.add(equipamento);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return equipamentos;
    }

    public List<Material> listarMateriaisPorProjeto(int idProjeto) {
        List<Material> materiais = new ArrayList<>();
        String sql = "SELECT m.* FROM Material m " +
                "INNER JOIN Consumo_Material cm ON m.ID_Material = cm.ID_Material " +
                "WHERE cm.ID_Projeto = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Material material = new Material(
                            rs.getInt("ID_Material"),
                            rs.getString("Nome_Material"),
                            rs.getInt("Quantidade")
                    );
                    materiais.add(material);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materiais;
    }
}
