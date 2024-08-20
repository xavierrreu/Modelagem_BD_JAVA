import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialDAO {
    private Connection conexao;

    public MaterialDAO() {
        this.conexao = ConexaoBD.getInstancia().getConexao();
    }

    public void criarTabela() {
        String sqlMaterial = "CREATE TABLE IF NOT EXISTS Material (" +
                "ID_Material INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Nome_Material TEXT NOT NULL," +
                "Quantidade INTEGER NOT NULL" +
                ");";

        String sqlConsumoMaterial = "CREATE TABLE IF NOT EXISTS Consumo_Material (" +
                "ID_Projeto INTEGER," +
                "ID_Material INTEGER," +
                "PRIMARY KEY (ID_Projeto, ID_Material)," +
                "FOREIGN KEY (ID_Projeto) REFERENCES Projeto(ID_Projeto)," +
                "FOREIGN KEY (ID_Material) REFERENCES Material(ID_Material)" +
                ");";

        try (Statement stmt = conexao.createStatement()) {
            stmt.execute(sqlMaterial);
            stmt.execute(sqlConsumoMaterial);
            System.out.println("Tabela Material e Consumo_Material criadas com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserirMaterial(Material material) {
        String sql = "INSERT INTO Material (Nome_Material, Quantidade) VALUES (?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, material.getNomeMaterial());
            stmt.setInt(2, material.getQuantidade());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    material.setIdMaterial(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void atualizarMaterial(Material material) {
        String sql = "UPDATE Material SET Nome_Material = ?, Quantidade = ? WHERE ID_Material = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, material.getNomeMaterial());
            stmt.setInt(2, material.getQuantidade());
            stmt.setInt(3, material.getIdMaterial());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirMaterial(int idMaterial) {
        String sql = "DELETE FROM Material WHERE ID_Material = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idMaterial);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Material> listarMateriais() {
        List<Material> materiais = new ArrayList<>();
        String sql = "SELECT * FROM Material";

        try (Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Material material = new Material(
                        rs.getInt("ID_Material"),
                        rs.getString("Nome_Material"),
                        rs.getInt("Quantidade")
                );
                materiais.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return materiais;
    }

    public void consumirMaterialEmProjeto(int idProjeto, int idMaterial) {
        String sql = "INSERT INTO Consumo_Material (ID_Projeto, ID_Material) VALUES (?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.setInt(2, idMaterial);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removerMaterialDeProjeto(int idProjeto, int idMaterial) {
        String sql = "DELETE FROM Consumo_Material WHERE ID_Projeto = ? AND ID_Material = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idProjeto);
            stmt.setInt(2, idMaterial);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
