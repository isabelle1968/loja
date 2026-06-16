package src;
public class DescontoCliente implements DescontoStrategy {
    private TipoCliente tipo;
    public DescontoCliente(TipoCliente tipo) { this.tipo = tipo; }
    @Override
    public double calcularDesconto(double valor) {
        switch (tipo) {
            case VIP: return valor * 0.10;
            case FUNCIONARIO: return valor * 0.30;
            case PREMIUM: return valor * 0.15;
            default: return 0;
        }
    }
}

