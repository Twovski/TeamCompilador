package com.example.Compilador;

import java.util.ArrayList;
import java.util.Scanner;
/*
ANALIZADOR LEXICO
*/
public class Lexico {
    String input;
    ArrayList<Token> tokens = new ArrayList<>();

    String identificador = "(?!(if|true|false|int|float|bool|string|or|and|not)\\b)[a-zA-Z_][a-zA-Z0-9_]*$";
    String entero = "^-?\\d+$";
    String flotante = "^-?\\d+(\\.\\d+)?$";
    String cadena = "\"[^\"]*\"";
    String boleano = "\\b(true|false)\\b";
    String tipoBoleano = "bool";
    String tipoCadena = "string";
    String tipoFlotante = "float";
    String tipoEntero = "int";

    String parentesisAbre = "[(]";
    String parentesisCierra = "[)]";
    String llaveAbre = "[{]";
    String llaveCierra = "[}]";

    //String delimitador = "[{}()]";

    String mayorQue = ">";
    String menorQue = "<";
    String menorIgualQue = "<=";
    String mayorIgualQue = ">=";
    String igualQue = "==";
    String noIgualQue = "!=";

    //String comparador = "(>=|<=|!=|==|[><])";

    String operadorSuma = "[+]";
    String operadorMultiplicacion = "[*]";
    String operadorDivision = "/";
    String operadorResta = "-";

    //String operadorAritmetico = "[/*\\-+]";

    String operadorOR = "or";
    String operadorAND = "and";
    String operadorNOT = "not";

    //String operadorlogico = "\\b(and|or|not)\\b";

    String operadorAsignacion = "=";
    String defineTipo = ":";
    String ifAbre = "if";

    public Lexico(String _input){
        input = procesarTexto(_input);
    }


    public void addToken(String token){
        if (isIdentificador(token))
            tokens.add(new Token(TokenType.IDENTIFICADOR,token));
        else if (isEntero(token))
            tokens.add(new Token(TokenType.ENTERO,token));
        else if (isflotante(token))
            tokens.add(new Token(TokenType.FLOTANTE,token));
        else if (isCadena(token))
            tokens.add(new Token(TokenType.CADENA,token));
        else if (isBoleano(token))
            tokens.add(new Token(TokenType.BOLEANO,token));
        /*
        else if (isDelimitador(token))
            tokens.add(token + " -> DELIMITADOR");
        else if (isComparador(token))
            tokens.add(token + " -> COMPARADOR");
        else if (isOperadorAritmetico(token))
            tokens.add(token + " -> OPERADOR_ARITMETICO");
        else if (isOperadorLogico(token))
            tokens.add(token + " -> OPERADOR_LOGICO");
        */
        else if (isParentesisAbre(token))
            tokens.add(new Token(TokenType.PARENTESIS_ABRE,token));
        else if (isParentesisCierra(token))
            tokens.add(new Token(TokenType.PARENTESIS_CIERRA,token));
        else if (isLlaveAbre(token))
            tokens.add(new Token(TokenType.LLAVE_ABRE,token));
        else if (isLlaveCierra(token))
            tokens.add(new Token(TokenType.LLAVE_CIERRA,token));
        else if (isMayorQue(token))
            tokens.add(new Token(TokenType.MAYOR_QUE,token));
        else if (isMenorQue(token))
            tokens.add(new Token(TokenType.MENOR_QUE,token));
        else if (isMenorIgualQue(token))
            tokens.add(new Token(TokenType.MENOR_IGUAL_QUE,token));
        else if (isMayorIgualQue(token))
            tokens.add(new Token(TokenType.MAYOR_IGUAL_QUE,token));
        else if (isIgualQue(token))
            tokens.add(new Token(TokenType.IGUAL_QUE,token));
        else if (isNoIgualQue(token))
            tokens.add(new Token(TokenType.NO_IGUAL_QUE,token));
        else if (isOperadorSuma(token))
            tokens.add(new Token(TokenType.OPERADOR_SUMA,token));
        else if (isOperadorMultiplicacion(token))
            tokens.add(new Token(TokenType.OPERADOR_MULTIPLICACION,token));
        else if (isOperadorDivision(token))
            tokens.add(new Token(TokenType.OPERADOR_DIVISION,token));
        else if (isOperadorResta(token))
            tokens.add(new Token(TokenType.OPERADOR_RESTA,token));
        else if (isOperadorOr(token))
            tokens.add(new Token(TokenType.OPERADOR_OR,token));
        else if (isOperadorAnd(token))
            tokens.add(new Token(TokenType.OPERADOR_AND,token));
        else if (isOperadorNot(token))
            tokens.add(new Token(TokenType.OPERADOR_NOT,token));

        else if (isOperadorAsignacion(token))
            tokens.add(new Token(TokenType.OPERADOR_ASIGNACION,token));
        else if (isdefineTipo(token))
            tokens.add(new Token(TokenType.DEFINE_TIPO,token));
        else if (isIfAbre(token))
            tokens.add(new Token(TokenType.IF_ABRE,token));
        else if (isTipoBoleano(token))
            tokens.add(new Token(TokenType.TIPO_BOLEANO,token));
        else if (isTipoCadena(token))
            tokens.add(new Token(TokenType.TIPO_CADENA,token));
        else if (isTipoFlotante(token))
            tokens.add(new Token(TokenType.TIPO_FLOTANTE,token));
        else if (isTipoEntero(token))
            tokens.add(new Token(TokenType.TIPO_ENTERO,token));
        else {
            tokens.add(new Token(TokenType.DESCONOCIDO,token));
        }
    }

    private boolean isOperadorNot(String token) { return token.matches(operadorNOT);}
    private boolean isOperadorAnd(String token) { return token.matches(operadorAND);}
    private boolean isOperadorOr(String token) { return token.matches(operadorOR);}
    private boolean isOperadorResta(String token) { return token.matches(operadorResta);}
    private boolean isOperadorDivision(String token) { return  token.matches(operadorDivision);}
    private boolean isOperadorMultiplicacion(String token) { return token.matches(operadorMultiplicacion);}
    private boolean isOperadorSuma(String token) { return token.matches(operadorSuma);}
    private boolean isNoIgualQue(String token) { return token.matches(noIgualQue);}
    private boolean isIgualQue(String token) { return token.matches(igualQue);}
    private boolean isMayorIgualQue(String token) { return token.matches(mayorIgualQue);}
    private boolean isMenorIgualQue(String token) { return  token.matches(menorIgualQue);}
    private boolean isMenorQue(String token) { return token.matches(menorQue);}
    private boolean isMayorQue(String token) { return token.matches(mayorQue);}
    private boolean isLlaveCierra(String token) { return token.matches(llaveCierra);}
    private boolean isLlaveAbre(String token) { return token.matches(llaveAbre);}
    private boolean isParentesisCierra(String token) { return token.matches(parentesisCierra);}
    private boolean isParentesisAbre(String token) { return token.matches(parentesisAbre);}

    public String procesarTexto(String _input){
        return _input.replaceAll("(:|=|[()]|[{}]|[*/+-]|>=|<=|!=|==|[><]|not|or|and)"," $1 ");
    }

    public boolean isIdentificador(String token){return token.matches(identificador);}
    public boolean isEntero(String token){return token.matches(entero);}
    public boolean isflotante(String token){return token.matches(flotante);}
    public boolean isCadena(String token){return token.matches(cadena);}
    public boolean isBoleano(String token){return token.matches(boleano);}
    /*
    public boolean isDelimitador(String token){return token.matches(delimitador);}
    public boolean isComparador(String token){return token.matches(comparador);}
    public boolean isOperadorAritmetico(String token){return token.matches(operadorAritmetico);}
    public boolean isOperadorLogico(String token){return token.matches(operadorlogico);}
    */
    public boolean isOperadorAsignacion(String token){return token.matches(operadorAsignacion);}
    public boolean isIfAbre(String token){return token.matches(ifAbre);}
    public boolean isdefineTipo(String token){return token.matches(defineTipo);}
    public boolean isTipoBoleano(String token){return token.matches(tipoBoleano);}
    public boolean isTipoCadena(String token){return token.matches(tipoCadena);}
    public boolean isTipoFlotante(String token){return token.matches(tipoFlotante);}
    public boolean isTipoEntero(String token){return token.matches(tipoEntero);}

    public static void main(String[] args) {
        String a = """
                a:bool=true
                if(){}
                b:float=1.0
                x:float=1.0
                c:float=b+x/1.0
                nota
                """;
        Lexico lexico = new Lexico(a);

        Scanner scanner = new Scanner(lexico.input);

        while (scanner.hasNext()) {
            String token = scanner.next();
            lexico.addToken(token);
            System.out.println(token);

        }
        System.out.println("===================");
        for (Token item: lexico.tokens){
            System.out.println(item);
        }

    }

}


