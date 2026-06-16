package src;
public class DescontoCupom implements DescontoStrategy {
    private TipoCupom cupom;
    public DescontoCupom(TipoCupom cupom) { this.cupom = cupom; }
    @Override
    public double calcularDesconto(double valor) {
        switch (cupom) {
            case NATAL10: return valor * 0.10;
            case BLACKFRIDAY: return valor * 0.25;
            default: return 0;
        }
    }
}
