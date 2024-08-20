public class ConsumoMaterial {
    private int idProjeto;
    private int idMaterial;

    // Construtor
    public ConsumoMaterial(int idProjeto, int idMaterial) {
        this.idProjeto = idProjeto;
        this.idMaterial = idMaterial;
    }

    // Getters e Setters
    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }
}
