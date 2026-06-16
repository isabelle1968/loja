package src;
public class NotificacaoWhatsApp implements NotificacaoStrategy {
    @Override
    public void enviar(Pedido pedido) {
        if (pedido.getTelefone() != null && !pedido.getTelefone().isEmpty())
            System.out.println("WhatsApp enviado para " + pedido.getTelefone());
    }
}
