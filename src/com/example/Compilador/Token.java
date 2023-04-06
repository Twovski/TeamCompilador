package com.example.Compilador;

public class Token {
    private final TokenType tipo;
    private final String valor;

    public Token(TokenType _tipo, String _valor) {
        this.tipo = _tipo;
        this.valor = _valor;
    }

    public TokenType getTipo() {
        return tipo;
    }

    public String getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return tipo + "("+ valor +")";
    }
}
