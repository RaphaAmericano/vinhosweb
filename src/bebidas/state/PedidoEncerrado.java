package bebidas.state;

import bebidas.model.Pedido;

public class PedidoEncerrado implements State {

	Pedido pedido;	
	
	public PedidoEncerrado(Pedido pedido) {
		super();
		this.pedido = pedido;
	}
	
	@Override
	public String abrir() {
		return("ERRO: não é possível abrir um pedido já encerrado");		
	}

	@Override
	public String encerrar() {
		return("ERRO: o pedido já está encerrado.");		
	}
}