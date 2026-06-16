package src;
public class PagamentoPayPal implements PagamentoStrategy {
    @Override
    public void processarPagamento(double total) {
        System.out.println("Processando pagamento PayPal...");
        System.out.println("Total cobrado: R$ " + total);
    }
}

