package com.locacao.exception;


public class AlugueisException {

    public class imovelNaoEncontrado extends RuntimeException {
        public imovelNaoEncontrado(String aluguel) {
            super("Imovel não encontrado: " + aluguel);
        }
    }
    
}
