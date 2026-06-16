package src;
public class PagamentoPix implements PagamentoStrategy {
    @Override
    public void processarPagamento(double total) {
        System.out.println("Gerando QR Code Pix...");
        System.out.println("Aguardando pagamento de R$ " + total);
    }
}