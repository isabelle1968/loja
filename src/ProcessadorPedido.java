package src;
import java.util.ArrayList;
import java.util.List;

public class ProcessadorPedido {
    private Pedido pedido;
    private DescontoStrategy desconto;
    private FreteStrategy frete;
    private ImpostoStrategy imposto;
    private PagamentoStrategy pagamento;
    private List<NotificacaoStrategy> notificacoes;

    public ProcessadorPedido(Pedido pedido) {
        this.pedido = pedido;
        this.desconto = criarDesconto();
        this.frete = criarFrete();
        this.imposto = new ImpostoPorEstado(pedido.getEstado());
        this.pagamento = criarPagamento();
        this.notificacoes = criarNotificacoes();
    }

    private DescontoStrategy criarDesconto() {
        List<DescontoStrategy> lista = new ArrayList<>();
        lista.add(new DescontoCliente(pedido.getTipoCliente()));
        if (pedido.getCupom() != null && pedido.getCupom() != TipoCupom.FRETEGRATIS) {
            lista.add(new DescontoCupom(pedido.getCupom()));
        }
        // Soma dos descontos via lambda
        DescontoStrategy soma = valor -> {
            double total = 0;
            for (DescontoStrategy d : lista) total += d.calcularDesconto(valor);
            return total;
        };
        return new LimiteDescontoDecorator(soma);
    }

    private FreteStrategy criarFrete() {
        FreteStrategy base;
        switch (pedido.getTipoFrete()) {
            case SEDEX: base = new FreteSedex(); break;
            case LOGGI: base = new FreteLoggi(); break;
            case GRATIS: base = new FreteGratis(); break;
            default: base = new FreteSedex();
        }
        if (pedido.getCupom() == TipoCupom.FRETEGRATIS) {
            return new FreteGratisCupomDecorator(base);
        }
        return base;
    }

    private PagamentoStrategy criarPagamento() {
        PagamentoStrategy base;
        switch (pedido.getFormaPagamento()) {
            case CARTAO: base = new PagamentoCartao(); break;
            case PIX: base = new PagamentoPix(); break;
            case BOLETO: base = new PagamentoBoleto(); break;
            case PAYPAL: base = new PagamentoPayPal(); break;
            default: base = new PagamentoCartao();
        }
        if (pedido.getFormaPagamento() == FormaPagamento.PAYPAL) {
            return new PayPalComTaxaDecorator(base);
        }
        return base;
    }

    private List<NotificacaoStrategy> criarNotificacoes() {
        List<NotificacaoStrategy> lista = new ArrayList<>();
        lista.add(new NotificacaoEmail());
        lista.add(new NotificacaoSMS());
        lista.add(new NotificacaoWhatsApp());
        return lista;
    }

    public void processar() {
        double valor = pedido.getValorProdutos();
        double desc = desconto.calcularDesconto(valor);
        double imp = imposto.calcularImposto(valor);
        double freteValor = frete.calcularFrete(pedido);
        double total = valor - desc + imp + freteValor;

        System.out.println("=== PROCESSANDO PEDIDO ===");
        System.out.println("Cliente: " + pedido.getCliente());
        System.out.println("Produtos: R$ " + valor);
        System.out.println("Desconto: -R$ " + desc);
        System.out.println("Imposto: +R$ " + imp);
        System.out.println("Frete: +R$ " + freteValor);
        System.out.println("Total: R$ " + total);

        pagamento.processarPagamento(total);

        for (NotificacaoStrategy n : notificacoes) {
            n.enviar(pedido);
        }

        System.out.println("Pedido finalizado!\n");
    }
}

