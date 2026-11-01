package calculadora;

/**
 * Motor de cálculos básicos do módulo financeiro do FinançApp.
 *
 * <p>A classe realiza as quatro operações aritméticas com números inteiros.
 * Quando recebe uma entrada que não pode ser calculada, ela lança uma exceção
 * com uma mensagem clara, em vez de devolver um valor que se confunda com um
 * resultado válido.</p>
 *
 * <p>Operadores aceitos:</p>
 * <ul>
 *     <li>{@code "+"}: soma</li>
 *     <li>{@code "-"}: subtração</li>
 *     <li>{@code "*"}: multiplicação</li>
 *     <li>{@code "/"}: divisão inteira</li>
 * </ul>
 *
 * @author Victor Akimoto Ferreira
 * @version 1.0
 */
public class Calculadora {

    /**
     * Executa a operação indicada sobre dois números inteiros.
     *
     * <p>A divisão é inteira: o resultado é truncado em direção ao zero,
     * então {@code 9 / 2} resulta em {@code 4} e {@code -7 / 2} resulta
     * em {@code -3}.</p>
     *
     * @param primeiroNumero primeiro operando da conta
     * @param segundoNumero  segundo operando da conta (o divisor, no caso da divisão)
     * @param operador       texto com o símbolo da operação: "+", "-", "*" ou "/"
     * @return o resultado da operação
     * @throws ArithmeticException      se o operador for "/" e o segundo número for zero
     * @throws IllegalArgumentException se o operador não for um dos quatro aceitos
     */
    public int calcular(int primeiroNumero, int segundoNumero, String operador) {

        // O switch escolhe a operação pelo texto do operador.
        // Cada case devolve o resultado direto, então não há variável de apoio.
        switch (operador) {

            case "+":
                return primeiroNumero + segundoNumero;

            case "-":
                return primeiroNumero - segundoNumero;

            case "*":
                return primeiroNumero * segundoNumero;

            case "/":
                // A divisão tem uma regra extra (o zero), por isso ficou em método próprio
                return dividir(primeiroNumero, segundoNumero);

            default:
                // Qualquer outro texto cai aqui; lançar exceção evita que um
                // operador digitado errado passe despercebido
                throw new IllegalArgumentException(
                    "Operador inválido!"
                );
        }
    }

    /**
     * Divide dois números inteiros, recusando a divisão por zero.
     *
     * <p>O método é privado porque só o {@link #calcular(int, int, String)}
     * precisa dele; quem usa a classe passa sempre pelo método principal.</p>
     *
     * @param dividendo número que será dividido
     * @param divisor   número pelo qual o dividendo será dividido
     * @return o quociente inteiro da divisão
     * @throws ArithmeticException se o divisor for zero
     */
    private int dividir(int dividendo, int divisor) {
        // A checagem vem antes da conta: dividir inteiros por zero em Java
        // já lançaria um erro, mas aqui a mensagem fica explicada para o usuário
        if (divisor == 0) {
            throw new ArithmeticException(
                "Não é possível dividir por zero!"
            );
        }
        return dividendo / divisor;
    }

    /**
     * Mostra no console o resultado de uma operação.
     *
     * @param resultado valor que será exibido
     */
    public void exibirResultado(int resultado) {
        System.out.println("Resultado: " + resultado);
    }

    /**
     * Mostra no console uma mensagem de erro, com o prefixo "Erro: ".
     *
     * @param mensagem texto que descreve o erro, normalmente a mensagem
     *                 de uma exceção lançada por {@link #calcular(int, int, String)}
     */
    public void exibirErro(String mensagem) {
        System.out.println("Erro: " + mensagem);
    }
}