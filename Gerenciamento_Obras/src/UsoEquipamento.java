public class UsoEquipamento {
    private int idProjeto;
    private int idEquipamento;

    // Construtor
    public UsoEquipamento(int idProjeto, int idEquipamento) {
        this.idProjeto = idProjeto;
        this.idEquipamento = idEquipamento;
    }

    // Getters e Setters
    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public int getIdEquipamento() {
        return idEquipamento;
    }

    public void setIdEquipamento(int idEquipamento) {
        this.idEquipamento = idEquipamento;
    }
}
