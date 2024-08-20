public class Material {
    private int idMaterial;
    private String nomeMaterial;
    private int quantidade;

    // Construtor
    public Material(int idMaterial, String nomeMaterial, int quantidade) {
        this.idMaterial = idMaterial;
        this.nomeMaterial = nomeMaterial;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public String getNomeMaterial() {
        return nomeMaterial;
    }

    public void setNomeMaterial(String nomeMaterial) {
        this.nomeMaterial = nomeMaterial;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
