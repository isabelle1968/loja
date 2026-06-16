package src;
public class PayPalComTaxaDecorator implements PagamentoStrategy {
    private PagamentoStrategy wrappee;
    public PayPalComTaxaDecorator(PagamentoStrategy base) { this.wrappee = base; }
    @Override
    public void processarPagamento(double total) {
        double taxa = total * 0.03;
        double totalFinal = total + taxa;
        System.out.println("Aplicando taxa PayPal de 3%: + R$ " + taxa);
        wrappee.processarPagamento(totalFinal);
    }
}

