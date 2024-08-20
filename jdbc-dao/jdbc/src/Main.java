package dao;


import java.util.List;

public class Main {
    public static void main(String[] args) {
        AutorDAO autorDAO = new AutorDAO();
        LivroDAO livroDAO = new LivroDAO();

        autorDAO.criarTabela();
        livroDAO.criarTabelas();
        // Inserindo um novo autor
        Autor novoAutor = new Autor(0, "J.K. Rowling", "Britânica");
        autorDAO.inserirAutor(novoAutor);
        System.out.println("Autor inserido: " + novoAutor.getNome());

        // Inserindo um novo livro para o autor
        Livro novoLivro = new Livro(0, "Harry Potter e a Pedra Filosofal", 1997, novoAutor.getIdAutor());
        livroDAO.inserirLivro(novoLivro);
        System.out.println("Livro inserido: " + novoLivro.getTitulo());

        // Listando todos os autores
        List<Autor> autores = autorDAO.listarAutores();
        System.out.println("Lista de autores:");
        for (Autor autor : autores) {
            System.out.println("ID: " + autor.getIdAutor() + ", Nome: " + autor.getNome() + ", Nacionalidade: " + autor.getNacionalidade());
        }

        // Listando todos os livros
        List<Livro> livros = livroDAO.listarLivros();
        System.out.println("Lista de livros:");
        for (Livro livro : livros) {
            System.out.println("ID: " + livro.getIdLivro() + ", Título: " + livro.getTitulo() + ", Ano: " + livro.getAnoPublicacao() + ", ID Autor: " + livro.getIdAutor());
        }

        // Atualizando informações de um autor
        Autor autorAtualizado = autores.get(0);
        autorAtualizado.setNome("J.K. Rowling (Atualizado)");
        autorDAO.atualizarAutor(autorAtualizado);
        System.out.println("Autor atualizado: " + autorAtualizado.getNome());

        // Atualizando informações de um livro
        Livro livroAtualizado = livros.get(0);
        livroAtualizado.setTitulo("Harry Potter e a Câmara Secreta");
        livroAtualizado.setAnoPublicacao(1998);
        livroDAO.atualizarLivro(livroAtualizado);
        System.out.println("Livro atualizado: " + livroAtualizado.getTitulo());

        // Listando livros por autor específico
        List<Livro> livrosPorAutor = livroDAO.listarLivrosPorAutor(novoAutor.getIdAutor());
        System.out.println("Livros de " + novoAutor.getNome() + ":");
        for (Livro livro : livrosPorAutor) {
            System.out.println("ID: " + livro.getIdLivro() + ", Título: " + livro.getTitulo());
        }

        // Excluindo um livro
      //  livroDAO.excluirLivro(livroAtualizado.getIdLivro());
        //System.out.println("Livro excluído: " + livroAtualizado.getTitulo());

        // Excluindo um autor
        //autorDAO.excluirAutor(autorAtualizado.getIdAutor());
        //System.out.println("Autor excluído: " + autorAtualizado.getNome());
    }
}
