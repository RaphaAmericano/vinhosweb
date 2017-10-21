package bebidas.state;

import java.util.Date;

import bebidas.model.Pedido;
import bebidas.model.Vinho;

public class PedidoAberto implements State {

	Pedido pedido;	
	
	public PedidoAberto(Pedido pedido) {
		super();
		this.pedido = pedido;
	}

	@Override
	public String abrir() {
		return("ERRO: o pedido já está aberto.");		
	}

	@Override
	public String encerrar() {
		//Verifica se pode fechar o pedido (estoque suficiente)		
		Vinho vinho = pedido.getVinho();
		int qtdFinal = vinho.getQtdEstoque()-pedido.getQtdVinho();
		if( qtdFinal < 0 ) {
			return "ERRO: Não foi possível encerrar o pedido: Estoque insuficiente!";
		}
		pedido.setEstadoAtual(pedido.getPedidoEncerrado());
		pedido.setEstadoPedido("Encerrado");
		pedido.setDtEncerramento(new Date());
		return("Pedido encerrado com sucesso.");
	}
}