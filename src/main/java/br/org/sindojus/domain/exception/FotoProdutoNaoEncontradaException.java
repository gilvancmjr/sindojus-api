package br.org.sindojus.domain.exception;

public class FotoProdutoNaoEncontradaException extends EntidadeNaoEncontradaException {

    private static final long serialVersionUID = 1L;

    public FotoProdutoNaoEncontradaException(String mensagem) {
        super(mensagem);
    }

    public FotoProdutoNaoEncontradaException(Long noticiaId) {
        this(String.format("Não existe um cadastro de foto para a noticia com código %d", noticiaId));
    }
}