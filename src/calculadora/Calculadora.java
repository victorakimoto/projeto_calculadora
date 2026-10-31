package calculadora;

public class Calculadora {
    

    public int calcular(int primeiroNumero, int segundoNumero, String operador) {

        switch (operador) {

            case "+":
                return primeiroNumero + segundoNumero;

            case "-":
                return primeiroNumero - segundoNumero;

            case "*":
                return primeiroNumero * segundoNumero;

            case "/":
                return dividir(primeiroNumero, segundoNumero);

            default:
                throw new IllegalArgumentException(
                    "Operador inválido!"
                );
        }
    }

    private int dividir(int dividendo, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException(
                "Não é possível dividir por zero!"
            );
        }
        return dividendo / divisor;
    }

    public void exibirResultado(int resultado) {
        System.out.println("Resultado: " + resultado);
    }

    public void exibirErro(String mensagem) {
        System.out.println("Erro: " + mensagem);
    }
}