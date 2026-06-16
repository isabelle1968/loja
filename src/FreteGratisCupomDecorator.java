package src;
public class FreteGratisCupomDecorator implements FreteStrategy {
    private FreteStrategy wrappee;
    public FreteGratisCupomDecorator(FreteStrategy base) { this.wrappee = base; }
    @Override
    public double calcularFrete(Pedido pedido) {
        double frete = wrappee.calcularFrete(pedido);
        // Não zera se for SEDEX
        if (pedido.getTipoFrete() == TipoFrete.SEDEX) return frete;
        return 0;
    }
}