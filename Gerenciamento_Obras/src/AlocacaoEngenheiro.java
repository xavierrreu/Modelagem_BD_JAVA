public class AlocacaoEngenheiro {
    private int idProjeto;
    private int idEngenheiro;

    // Construtor
    public AlocacaoEngenheiro(int idProjeto, int idEngenheiro) {
        this.idProjeto = idProjeto;
        this.idEngenheiro = idEngenheiro;
    }

    // Getters e Setters
    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public int getIdEngenheiro() {
        return idEngenheiro;
    }

    public void setIdEngenheiro(int idEngenheiro) {
        this.idEngenheiro = idEngenheiro;
    }
}