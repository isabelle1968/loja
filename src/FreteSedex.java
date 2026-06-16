package src;
public class FreteSedex implements FreteStrategy {
    @Override
    public double calcularFrete(Pedido pedido) {
        double frete = 30;
        if (pedido.isEmbrulhoPresente()) frete += 5;
        return frete;
    }
}

