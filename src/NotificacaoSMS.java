package src;
public class NotificacaoSMS implements NotificacaoStrategy {
    @Override
    public void enviar(Pedido pedido) {
        if (pedido.getTelefone() != null && !pedido.getTelefone().isEmpty())
            System.out.println("SMS enviado para " + pedido.getTelefone());
    }
}