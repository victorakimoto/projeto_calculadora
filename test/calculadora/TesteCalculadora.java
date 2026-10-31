package calculadora;


public class TesteCalculadora {

    static int passaram = 0;
    static int falharam = 0;

    public static void verificar(String entrada, int esperado, int obtido) {

        if (esperado == obtido) {
            System.out.println(entrada + 
                " Esperado: " + esperado +
                " Obtido: " + obtido + 
                " Ok");
                passaram++;
        } else {
            System.out.println(entrada +
            " Esperado: " + esperado +
            " Obtido: " + obtido + 
            " Falhou");
            falharam++;
        }
    }

    public static void main(String[] args) {
        Calculadora calc = new Calculadora();

        //SOMA
        verificar("6 + 7", 13, calc.calc(6, 7, "+"));
        verificar("60 + 70", 130, calc.calc(60, 70, "+"));
        verificar("-6 + 7", 1, calc.calc(-6, 7, "+"));

        //SUBTRAÇÃO
        verificar("7 - 6", 1, calc.calc(7, 6, "-"));
        verificar("70 - 60", 10, calc.calc(70, 60, "-"));
        verificar("100 - 100", 0, calc.calc(100, 100, "-"));

        //MULTIPLICAÇÃO
        verificar("6 * 7", 42, calc.calc(6, 7, "*"));
        verificar("4 * 2", 8, calc.calc(4, 2, "*"));
        verificar("-9 * 6", -54, calc.calc(-9, 6, "*"));

        //DIVISÃO
        verificar("100 / 2", 50, calc.calc(100, 2, "/"));
        verificar("-7 / 2", -3, calc.calc(-7, 2, "/"));
        verificar("9 / 2", 4, calc.calc(9, 2, "/"));

        //DIVISÃO POR ZERO
        verificar("10 / 0", 0, calc.calc(10, 0, "/"));

        //OPERAÇÃO INVÁLIDA
        verificar("10 % 5 (operador inválido)", 0, calc.calc(10, 5, "%"));

        //RESUMO
        System.out.println("Passaram: " + passaram);
        System.out.println("Falharam: " + falharam);
        System.out.println("Testes totais: " + (passaram + falharam));

    }
}