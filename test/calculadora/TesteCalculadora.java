package calculadora;

/**
 * Testes unitários da classe {@link Calculadora}, feitos sem framework.
 *
 * <p>Cada teste imprime a entrada, o resultado esperado, o resultado obtido
 * e se passou ou falhou. No final, o programa mostra quantos testes passaram
 * e quantos falharam.</p>
 *
 * <p>Grupos de teste: soma, subtração, multiplicação, divisão, divisão por
 * zero e operador inválido.</p>
 *
 * @author Victor Akimoto Ferreira
 * @version 1.0
 */
public class TesteCalculadora {

    /** Quantidade de testes que passaram até o momento. */
    static int passaram = 0;

    /** Quantidade de testes que falharam até o momento. */
    static int falharam = 0;

    /**
     * Compara um resultado esperado com o obtido e registra se o teste passou.
     *
     * @param entrada  texto que descreve a conta testada, por exemplo "6 + 7"
     * @param esperado valor que a conta deveria produzir
     * @param obtido   valor que a {@link Calculadora} realmente produziu
     */
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

    /**
     * Executa uma conta que deve dar erro e confere se a exceção lançada
     * é do tipo esperado.
     *
     * <p>O teste falha se nenhuma exceção for lançada ou se for lançada
     * uma de outro tipo. A mensagem da exceção é mostrada com
     * {@link Calculadora#exibirErro(String)}.</p>
     *
     * @param entrada        texto que descreve a conta testada, por exemplo "10 / 0"
     * @param tipoEsperado   nome simples da exceção esperada, como "ArithmeticException"
     * @param calc           calculadora que executará a conta
     * @param primeiroNumero primeiro operando
     * @param segundoNumero  segundo operando
     * @param operador       símbolo da operação
     */
    public static void verificarExcecao(
            String entrada,
            String tipoEsperado,
            Calculadora calc,
            int primeiroNumero,
            int segundoNumero,
            String operador) {

        // Começa assumindo que nada foi lançado; se a conta não der erro,
        // esse texto continua valendo e o teste falha na comparação final
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

        // A comparação fica fora do try/catch para existir só em um lugar
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

    /**
     * Executa todos os casos de teste e mostra o resumo no final.
     *
     * @param args argumentos de linha de comando (não são usados)
     */
    public static void main(String[] args) {

        Calculadora calc = new Calculadora();

        int obtido;

        // Nos casos normais: calcula, mostra o resultado e depois confere

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

        // DIVISÃO (a divisão é inteira: 9 / 2 dá 4 e -7 / 2 dá -3)
        obtido = calc.calcular(100, 2, "/");
        calc.exibirResultado(obtido);
        verificar("100 / 2", 50, obtido);

        obtido = calc.calcular(-7, 2, "/");
        calc.exibirResultado(obtido);
        verificar("-7 / 2", -3, obtido);

        obtido = calc.calcular(9, 2, "/");
        calc.exibirResultado(obtido);
        verificar("9 / 2", 4, obtido);

        // DIVISÃO POR ZERO (deve lançar ArithmeticException)
        verificarExcecao("10 / 0", "ArithmeticException",
            calc, 10, 0, "/");

        verificarExcecao("-5 / 0", "ArithmeticException",
            calc, -5, 0, "/");

        // OPERADOR INVÁLIDO (deve lançar IllegalArgumentException)
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