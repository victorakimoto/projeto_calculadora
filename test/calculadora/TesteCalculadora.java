package calculadora;

public class TesteCalculadora {

    static int passaram = 0;
    static int falharam = 0;

    public static void verificar(String entrada, int esperado, int obtido) {

        if (esperado == obtido) {
            System.out.println(entrada +
                "  Esperado: " + esperado +
                "  Obtido: " + obtido +
                "  Ok");
            passaram++;

        } else {
            System.out.println(entrada +
                "  Esperado: " + esperado +
                "  Obtido: " + obtido +
                "  Falhou");
            falharam++;
        }
    }

    public static void verificarExcecao(
            String entrada,
            String tipoEsperado,
            Calculadora calc,
            int primeiroNumero,
            int segundoNumero,
            String operador) {

        String tipoObtido = "nenhuma exceção";

        try {
            calc.calcular(primeiroNumero, segundoNumero, operador);

        } catch (ArithmeticException erro) {

            tipoObtido = erro.getClass().getSimpleName();
            calc.exibirErro(erro.getMessage());

        } catch (IllegalArgumentException erro) {

            tipoObtido = erro.getClass().getSimpleName();
            calc.exibirErro(erro.getMessage());
        }

        if (tipoEsperado.equals(tipoObtido)) {
            System.out.println(entrada +
                "  Esperado: " + tipoEsperado +
                "  Obtido: " + tipoObtido +
                "  Ok");
            passaram++;

        } else {
            System.out.println(entrada +
                "  Esperado: " + tipoEsperado +
                "  Obtido: " + tipoObtido +
                "  Falhou");
            falharam++;
        }
    }

    public static void main(String[] args) {

        Calculadora calc = new Calculadora();

        int obtido;

        // SOMA
        obtido = calc.calcular(6, 7, "+");
        calc.exibirResultado(obtido);
        verificar("6 + 7", 13, obtido);

        obtido = calc.calcular(60, 70, "+");
        calc.exibirResultado(obtido);
        verificar("60 + 70", 130, obtido);

        obtido = calc.calcular(-6, 7, "+");
        calc.exibirResultado(obtido);
        verificar("-6 + 7", 1, obtido);

        // SUBTRAÇÃO
        obtido = calc.calcular(7, 6, "-");
        calc.exibirResultado(obtido);
        verificar("7 - 6", 1, obtido);

        obtido = calc.calcular(70, 60, "-");
        calc.exibirResultado(obtido);
        verificar("70 - 60", 10, obtido);

        obtido = calc.calcular(100, 100, "-");
        calc.exibirResultado(obtido);
        verificar("100 - 100", 0, obtido);

        // MULTIPLICAÇÃO
        obtido = calc.calcular(6, 7, "*");
        calc.exibirResultado(obtido);
        verificar("6 * 7", 42, obtido);

        obtido = calc.calcular(4, 2, "*");
        calc.exibirResultado(obtido);
        verificar("4 * 2", 8, obtido);

        obtido = calc.calcular(-9, 6, "*");
        calc.exibirResultado(obtido);
        verificar("-9 * 6", -54, obtido);

        // DIVISÃO
        obtido = calc.calcular(100, 2, "/");
        calc.exibirResultado(obtido);
        verificar("100 / 2", 50, obtido);

        obtido = calc.calcular(-7, 2, "/");
        calc.exibirResultado(obtido);
        verificar("-7 / 2", -3, obtido);

        obtido = calc.calcular(9, 2, "/");
        calc.exibirResultado(obtido);
        verificar("9 / 2", 4, obtido);

        // DIVISÃO POR ZERO
        verificarExcecao("10 / 0", "ArithmeticException",
            calc, 10, 0, "/");

        verificarExcecao("-5 / 0", "ArithmeticException",
            calc, -5, 0, "/");

        // OPERADOR INVÁLIDO
        verificarExcecao("10 % 5", "IllegalArgumentException",
            calc, 10, 5, "%");

        verificarExcecao("10 x 5", "IllegalArgumentException",
            calc, 10, 5, "x");

        // RESUMO
        System.out.println("Passaram: " + passaram);
        System.out.println("Falharam: " + falharam);
        System.out.println("Testes totais: " + (passaram + falharam));
    }
}