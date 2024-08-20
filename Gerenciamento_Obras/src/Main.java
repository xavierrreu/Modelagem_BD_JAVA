import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Criar as tabelas no banco de dados
        ProjetoDAO projetoDAO = new ProjetoDAO();
        projetoDAO.criarTabela();

        EngenheiroDAO engenheiroDAO = new EngenheiroDAO();
        engenheiroDAO.criarTabela();

        OperarioDAO operarioDAO = new OperarioDAO();
        operarioDAO.criarTabela();

        EquipamentoDAO equipamentoDAO = new EquipamentoDAO();
        equipamentoDAO.criarTabela();

        MaterialDAO materialDAO = new MaterialDAO();
        materialDAO.criarTabela();

        // Inserindo um novo projeto
        Projeto projeto1 = new Projeto(0, "Construção de Ponte", "São Paulo", "2024-09-01", "2025-12-31");
        projetoDAO.inserirProjeto(projeto1);
        System.out.println("Projeto inserido: " + projeto1.getNomeProjeto());

        // Inserindo engenheiros
        Engenheiro engenheiro1 = new Engenheiro(0, "Carlos Silva", "Civil");
        engenheiroDAO.inserirEngenheiro(engenheiro1);
        System.out.println("Engenheiro inserido: " + engenheiro1.getNomeEngenheiro());

        Engenheiro engenheiro2 = new Engenheiro(0, "Ana Souza", "Elétrica");
        engenheiroDAO.inserirEngenheiro(engenheiro2);
        System.out.println("Engenheiro inserido: " + engenheiro2.getNomeEngenheiro());

        // Inserindo operários
        Operario operario1 = new Operario(0, "João Pereira", "Pedreiro");
        operarioDAO.inserirOperario(operario1);
        System.out.println("Operário inserido: " + operario1.getNomeOperario());

        Operario operario2 = new Operario(0, "José Santos", "Carpinteiro");
        operarioDAO.inserirOperario(operario2);
        System.out.println("Operário inserido: " + operario2.getNomeOperario());

        // Inserindo equipamentos
        Equipamento equipamento1 = new Equipamento(0, "Guindaste", "Pesado");
        equipamentoDAO.inserirEquipamento(equipamento1);
        System.out.println("Equipamento inserido: " + equipamento1.getNomeEquipamento());

        Equipamento equipamento2 = new Equipamento(0, "Betoneira", "Leve");
        equipamentoDAO.inserirEquipamento(equipamento2);
        System.out.println("Equipamento inserido: " + equipamento2.getNomeEquipamento());

        // Inserindo materiais
        Material material1 = new Material(0, "Cimento", 100);
        materialDAO.inserirMaterial(material1);
        System.out.println("Material inserido: " + material1.getNomeMaterial());

        Material material2 = new Material(0, "Areia", 200);
        materialDAO.inserirMaterial(material2);
        System.out.println("Material inserido: " + material2.getNomeMaterial());

        // Alocando engenheiros no projeto
        engenheiroDAO.alocarEngenheiroEmProjeto(projeto1.getIdProjeto(), engenheiro1.getIdEngenheiro());
        engenheiroDAO.alocarEngenheiroEmProjeto(projeto1.getIdProjeto(), engenheiro2.getIdEngenheiro());

        // Alocando operários no projeto
        operarioDAO.alocarOperarioEmProjeto(projeto1.getIdProjeto(), operario1.getIdOperario());
        operarioDAO.alocarOperarioEmProjeto(projeto1.getIdProjeto(), operario2.getIdOperario());

        // Utilizando equipamentos no projeto
        equipamentoDAO.utilizarEquipamentoEmProjeto(projeto1.getIdProjeto(), equipamento1.getIdEquipamento());
        equipamentoDAO.utilizarEquipamentoEmProjeto(projeto1.getIdProjeto(), equipamento2.getIdEquipamento());

        // Consumindo materiais no projeto
        materialDAO.consumirMaterialEmProjeto(projeto1.getIdProjeto(), material1.getIdMaterial());
        materialDAO.consumirMaterialEmProjeto(projeto1.getIdProjeto(), material2.getIdMaterial());

        // Listando engenheiros alocados no projeto
        List<Engenheiro> engenheiros = projetoDAO.listarEngenheirosPorProjeto(projeto1.getIdProjeto());
        System.out.println("Engenheiros alocados no projeto " + projeto1.getNomeProjeto() + ":");
        for (Engenheiro eng : engenheiros) {
            System.out.println("- " + eng.getNomeEngenheiro());
        }

        // Listando operários alocados no projeto
        List<Operario> operarios = projetoDAO.listarOperariosPorProjeto(projeto1.getIdProjeto());
        System.out.println("Operários alocados no projeto " + projeto1.getNomeProjeto() + ":");
        for (Operario op : operarios) {
            System.out.println("- " + op.getNomeOperario());
        }

        // Listando equipamentos utilizados no projeto
        List<Equipamento> equipamentos = projetoDAO.listarEquipamentosPorProjeto(projeto1.getIdProjeto());
        System.out.println("Equipamentos utilizados no projeto " + projeto1.getNomeProjeto() + ":");
        for (Equipamento eq : equipamentos) {
            System.out.println("- " + eq.getNomeEquipamento());
        }

        // Listando materiais consumidos no projeto
        List<Material> materiais = projetoDAO.listarMateriaisPorProjeto(projeto1.getIdProjeto());
        System.out.println("Materiais consumidos no projeto " + projeto1.getNomeProjeto() + ":");
        for (Material mat : materiais) {
            System.out.println("- " + mat.getNomeMaterial() + " (Quantidade: " + mat.getQuantidade() + ")");
        }

        // Atualizando um projeto
        projeto1.setNomeProjeto("Construção de Viaduto");
        projetoDAO.atualizarProjeto(projeto1);
        System.out.println("Projeto atualizado: " + projeto1.getNomeProjeto());

        // Atualizando um engenheiro
        engenheiro1.setNomeEngenheiro("Carlos Alberto Silva");
        engenheiroDAO.atualizarEngenheiro(engenheiro1);
        System.out.println("Engenheiro atualizado: " + engenheiro1.getNomeEngenheiro());

        // Atualizando um operário
        operario1.setNomeOperario("João da Silva");
        operarioDAO.atualizarOperario(operario1);
        System.out.println("Operário atualizado: " + operario1.getNomeOperario());

        // Atualizando um equipamento
        equipamento1.setNomeEquipamento("Guindaste Grande");
        equipamentoDAO.atualizarEquipamento(equipamento1);
        System.out.println("Equipamento atualizado: " + equipamento1.getNomeEquipamento());

        // Atualizando um material
        material1.setQuantidade(150);
        materialDAO.atualizarMaterial(material1);
        System.out.println("Material atualizado: " + material1.getNomeMaterial() + " (Quantidade: " + material1.getQuantidade() + ")");
        /*
        // Excluindo um material
        materialDAO.excluirMaterial(material2.getIdMaterial());
        System.out.println("Material excluído: " + material2.getNomeMaterial());

        // Excluindo um equipamento
        equipamentoDAO.excluirEquipamento(equipamento2.getIdEquipamento());
        System.out.println("Equipamento excluído: " + equipamento2.getNomeEquipamento());

        // Excluindo um operário
        operarioDAO.excluirOperario(operario2.getIdOperario());
        System.out.println("Operário excluído: " + operario2.getNomeOperario());

        // Excluindo um engenheiro
        engenheiroDAO.excluirEngenheiro(engenheiro2.getIdEngenheiro());
        System.out.println("Engenheiro excluído: " + engenheiro2.getNomeEngenheiro());

        // Excluindo um projeto
        projetoDAO.excluirProjeto(projeto1.getIdProjeto());
        System.out.println("Projeto excluído: " + projeto1.getNomeProjeto());
         */
    }
}
