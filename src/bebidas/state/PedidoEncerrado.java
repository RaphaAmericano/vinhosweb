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
		return("ERRO: n�o � poss�vel abrir um pedido j� encerrado");		
	}

	@Override
	public String encerrar() {
		return("ERRO: o pedido j� est� encerrado.");		
	}
}