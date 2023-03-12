package br.org.sindojus.domain.exception;

public class NoticiaNaoEncontrada extends EntidadeNaoEncontradaException{

    private static final long serialVersionUID = 1L;

    public NoticiaNaoEncontrada(String mensagem) {
        super(mensagem);
    }

    public NoticiaNaoEncontrada(Long noticiaId) {
        super(String.format("Não existe um cadastro de noticia com código %d", noticiaId));
    }
    
}
