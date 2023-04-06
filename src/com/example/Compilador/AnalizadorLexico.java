package com.example.Compilador;

import java.util.ArrayList;
import java.util.Scanner;
public class AnalizadorLexico {
    ArrayList<String> tokens = new ArrayList<>();

    String identificador = "(?!(if|true|false|int|float|bool|string|or|and|not)\\b)[a-zA-Z_][a-zA-Z0-9_]*$";
    String entero = "^-?\\d+$";
    String flotante = "^-?\\d+(\\.\\d+)?$";
    String cadena = "\"[^\"]*\"";
    String boleano = "\\b(true|false)\\b";
    String tipoBoleano = "bool";
    String tipoCadena = "string";
    String tipoFlotante = "float";
    String tipoEntero = "int";
/*
    String parentesisAbre = "(";
    String parentesisCierra = ")";
    String llaveAbre = "{";
    String llaveCierra = "}";
*/
    String delimitador = "[{}()]";
/*
    String mayorQue = ">"
    String menorQue = "<"
    String menorIgualQue = "<="
    String mayorIgualQue = ">="
    String igualQue = "=="
    String noIgualQue = "!="
*/
    String comparador = "(>=|<=|!=|==|[><])";
/*
    String operadorSuma = "+"
    String operadorMultiplicacion = "*"
    String operadorDivision = "/"
    String operadorResta = "-"
*/
    String operadorAritmetico = "[/*\\-+]";
/*
    String operadorOR = "or"
    String operadorAND = "and"
    String operadorNOT = "not"
*/
    String operadorlogico = "\\b(and|or|not)\\b";

    String operadorAsignacion = "=";
    String defineTipo = ":";
    String ifAbre = "if";


    public void addToken(String token){
        if (isIdentificador(token))
            tokens.add(token + " -> IDENTIFICADOR");
        else if (isEntero(token))
            tokens.add(token + " -> ENTERO");
        else if (isflotante(token))
            tokens.add(token + " -> FLOTANTE");
        else if (isCadena(token))
            tokens.add(token + " -> CADENA");
        else if (isBoleano(token))
            tokens.add(token + " -> BOLEANO");
        else if (isDelimitador(token))
            tokens.add(token + " -> DELIMITADOR");
        else if (isComparador(token))
            tokens.add(token + " -> COMPARADOR");
        else if (isOperadorAritmetico(token))
            tokens.add(token + " -> OPERADOR_ARITMETICO");
        else if (isOperadorLogico(token))
            tokens.add(token + " -> OPERADOR_LOGICO");
        else if (isOperadorAsignacion(token))
            tokens.add(token + " -> OPERADOR_ASIGNACION");
        else if (isdefineTipo(token))
            tokens.add(token + " -> DEFINE_TIPO");
        else if (isIfAbre(token))
            tokens.add(token + " -> IF_ABRE");
        else if (isTipoBoleano(token))
            tokens.add(token + " -> TIPO_BOLEANO");
        else if (isTipoCadena(token))
            tokens.add(token + "-> TIPO_CADENA");
        else if (isTipoFlotante(token))
            tokens.add(token + "-> TIPO_FLOTANTE");
        else if (isTipoEntero(token))
            tokens.add(token + "-> TIPO_ENTERO");
        else {
            tokens.add("DESCONOCIDO");
        }
    }

    public boolean isIdentificador(String token){return token.matches(identificador);}
    public boolean isEntero(String token){return token.matches(entero);}
    public boolean isflotante(String token){return token.matches(flotante);}
    public boolean isCadena(String token){return token.matches(cadena);}
    public boolean isBoleano(String token){return token.matches(boleano);}
    public boolean isDelimitador(String token){return token.matches(delimitador);}
    public boolean isComparador(String token){return token.matches(comparador);}
    public boolean isOperadorAritmetico(String token){return token.matches(operadorAritmetico);}
    public boolean isOperadorLogico(String token){return token.matches(operadorlogico);}
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
        a = a.replaceAll("(:|=|[()]|[{}]|[*/+-]|>=|<=|!=|==|[><]|not|or|and)"," $1 ");

        Scanner scanner = new Scanner(a);
        AnalizadorLexico analizadorLexico = new AnalizadorLexico();
        System.out.println(a);

        while (scanner.hasNext()) {
            String token = scanner.next();
            analizadorLexico.addToken(token);
            System.out.println(token);

        }
        System.out.println("===================");
        for (String item: analizadorLexico.tokens){
            System.out.println(item);
        }

    }

}


