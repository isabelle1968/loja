package src;
public class PagamentoBoleto implements PagamentoStrategy {
    @Override
    public void processarPagamento(double total) {
        System.out.println("Gerando boleto...");
        System.out.println("Boleto no valor de R$ " + total);
    }
}