package src;
public class LimiteDescontoDecorator implements DescontoStrategy {
    private DescontoStrategy wrappee;
    private static final double LIMITE = 0.40;
    public LimiteDescontoDecorator(DescontoStrategy base) { this.wrappee = base; }
    @Override
    public double calcularDesconto(double valor) {
        double desconto = wrappee.calcularDesconto(valor);
        double maximo = valor * LIMITE;
        return Math.min(desconto, maximo);
    }
}

