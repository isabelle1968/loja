package src;
public class FreteLoggi implements FreteStrategy {
    @Override
    public double calcularFrete(Pedido pedido) {
        double frete = 15;
        if (pedido.isEmbrulhoPresente()) frete += 5;
        return frete;
    }
}