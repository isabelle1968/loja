package src;
public class PagamentoCartao implements PagamentoStrategy {
    @Override
    public void processarPagamento(double total) {
        System.out.println("Validando cartão...");
        System.out.println("Cobrando R$ " + total + " no cartão.");
    }
}