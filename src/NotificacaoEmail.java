package src;
public class NotificacaoEmail implements NotificacaoStrategy {
    @Override
    public void enviar(Pedido pedido) {
        System.out.println("Email enviado para " + pedido.getEmail());
    }
}