# compilador

## gramatica

~~~ plain
programa -> (declaracion)* | (sentencia)*
declaracion -> (declaracion_variable)*

declaracion_variable -> (identificador ":" tipo) | (identificador ":" tipo "=" valor)

identificador -> (letra|"_") (letra|digito|"_")* 

letra -> ((a-z)|(A-Z))
digito -> (0-9)

tipo -> "int" | "float" | "bool" | "string"
valor -> entero | flotante | boleano | cadena

entero -> ("-" | "") (0-9)+
flotante -> ("-" | "") (0-9)+ "." (0-9)+
boleano -> "true" | "false"
cadena -> (caracter)*
caracter -> cualquier carácter ASCII excepto comillas dobles o simples

sentencia -> (asignacion)* | (condicional)*

asignacion -> asignacion_numerica | asignacion_boleana | asignacion_cadena 

expresion -> expresion_aritmetica | expresion_boleana

asignacion_numerica -> identificador "=" expresion_aritmetica   

expresion_aritmetica -> termino (( "+" | "-" ) termino)*
termino -> factor (( "*" | "/" ) factor)*
factor -> entero | flotante | identificador | expresion_aritmetica

asignacion_boleana -> identificador "=" boleano
asignacion_cadena -> identificador "=" cadena

condicional -> condicional_if
operador_comparacion -> (">"|"<"|"<="|">="|"=="|"!=")

expresion_boleana -> expresion_boleana_prima |
                     "not" expresion_boleana
                     expresion_boleana "and" expresion_boleana
                     expresion_boleana "or" expresion_boleana     

expresion_boleana_prima -> expresion_aritmetica (operador_comparacion) expresion_aritmetica

condicional_if -> "if" "(" expresion_boleana ")" bloque

bloque = "{" (sentencias)* (declaraciones)* "}"
~~~