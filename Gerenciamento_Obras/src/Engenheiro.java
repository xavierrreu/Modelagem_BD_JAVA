public class Engenheiro {
    private int idEngenheiro;
    private String nomeEngenheiro;
    private String especialidade;

    // Construtor
    public Engenheiro(int idEngenheiro, String nomeEngenheiro, String especialidade) {
        this.idEngenheiro = idEngenheiro;
        this.nomeEngenheiro = nomeEngenheiro;
        this.especialidade = especialidade;
    }

    // Getters e Setters
    public int getIdEngenheiro() {
        return idEngenheiro;
    }

    public void setIdEngenheiro(int idEngenheiro) {
        this.idEngenheiro = idEngenheiro;
    }

    public String getNomeEngenheiro() {
        return nomeEngenheiro;
    }

    public void setNomeEngenheiro(String nomeEngenheiro) {
        this.nomeEngenheiro = nomeEngenheiro;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }
}
