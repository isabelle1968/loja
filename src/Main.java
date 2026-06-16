package src;
public class Main {
    public static void main(String[] args) {
        // Teste 1: VIP, NATAL10, SEDEX, PayPal
        Pedido p1 = new Pedido("Ana", "ana@email.com", "11999999999",
                Estado.SP, TipoCliente.VIP, TipoCupom.NATAL10,
                FormaPagamento.PAYPAL, TipoFrete.SEDEX, 500.0, true);
        new ProcessadorPedido(p1).processar();

        // Teste 2: PREMIUM, sem cupom, LOGGI, PIX
        Pedido p2 = new Pedido("Carlos", "carlos@email.com", "11888888888",
                Estado.RJ, TipoCliente.PREMIUM, null,
                FormaPagamento.PIX, TipoFrete.LOGGI, 1000.0, false);
        new ProcessadorPedido(p2).processar();

        // Teste 3: FRETEGRATIS com LOGGI (zera frete)
        Pedido p3 = new Pedido("Maria", "maria@email.com", "11777777777",
                Estado.MG, TipoCliente.COMUM, TipoCupom.FRETEGRATIS,
                FormaPagamento.BOLETO, TipoFrete.LOGGI, 300.0, true);
        new ProcessadorPedido(p3).processar();

        // Teste 4: FRETEGRATIS com SEDEX (não zera)
        Pedido p4 = new Pedido("João", "joao@email.com", "11666666666",
                Estado.SP, TipoCliente.VIP, TipoCupom.FRETEGRATIS,
                FormaPagamento.CARTAO, TipoFrete.SEDEX, 400.0, false);
        new ProcessadorPedido(p4).processar();
    }
}