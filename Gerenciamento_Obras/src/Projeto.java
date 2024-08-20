public class Projeto {
    private int idProjeto;
    private String nomeProjeto;
    private String local;
    private String dataInicio;
    private String dataTermino;

    // Construtor
    public Projeto(int idProjeto, String nomeProjeto, String local, String dataInicio, String dataTermino) {
        this.idProjeto = idProjeto;
        this.nomeProjeto = nomeProjeto;
        this.local = local;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
    }

    // Getters e Setters
    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(String dataTermino) {
        this.dataTermino = dataTermino;
    }
}
