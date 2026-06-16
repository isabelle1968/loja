package src;
public class FreteGratis implements FreteStrategy {
    @Override
    public double calcularFrete(Pedido pedido) {
        return 0;
    }
}
