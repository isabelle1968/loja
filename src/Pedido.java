package src;
public class Pedido {
    private String cliente;
    private String email;
    private String telefone;
    private Estado estado;
    private TipoCliente tipoCliente;
    private TipoCupom cupom;
    private FormaPagamento formaPagamento;
    private TipoFrete tipoFrete;
    private double valorProdutos;
    private boolean embrulhoPresente;

    public Pedido(String cliente, String email, String telefone, Estado estado,
                  TipoCliente tipoCliente, TipoCupom cupom, FormaPagamento formaPagamento,
                  TipoFrete tipoFrete, double valorProdutos, boolean embrulhoPresente) {
        this.cliente = cliente;
        this.email = email;
        this.telefone = telefone;
        this.estado = estado;
        this.tipoCliente = tipoCliente;
        this.cupom = cupom;
        this.formaPagamento = formaPagamento;
        this.tipoFrete = tipoFrete;
        this.valorProdutos = valorProdutos;
        this.embrulhoPresente = embrulhoPresente;
    }

    public String getCliente() { return cliente; }
    public String getEmail() { return email; }
    public String getTelefone() { return telefone; }
    public Estado getEstado() { return estado; }
    public TipoCliente getTipoCliente() { return tipoCliente; }
    public TipoCupom getCupom() { return cupom; }
    public FormaPagamento getFormaPagamento() { return formaPagamento; }
    public TipoFrete getTipoFrete() { return tipoFrete; }
    public double getValorProdutos() { return valorProdutos; }
    public boolean isEmbrulhoPresente() { return embrulhoPresente; }
}