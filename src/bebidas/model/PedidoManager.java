package bebidas.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bebidas.dao.PedidoDAO;
import bebidas.dao.VinhoDAO;

public class PedidoManager {

	public static String criarPedido(int idVinho, String nomeCliente, int qtdVinho) {
		VinhoDAO vinhoDAO = new VinhoDAO();
		PedidoDAO pedidoDAO = new PedidoDAO();
		
		Vinho vinho = vinhoDAO.selecionarPorId(idVinho);
		
		Pedido pedido = new Pedido();
		pedido.setVinho(vinho);
		pedido.setNomeCliente(nomeCliente);
		pedido.setQtdVinho(qtdVinho);
		pedido.setDtPedido(new Date());
		pedido.setValorTotal(qtdVinho * vinho.getPrecoVinho());
		pedido.setEstadoPedido("Aberto");
		
		pedidoDAO.inserir(pedido);
		
		//TODO: fazer o tratamento de erro
		
		return "Pedido cadastrado com sucesso";
		
	}
	
	public static String encerrarPedido(int idPedido) {
		VinhoDAO vinhoDAO = new VinhoDAO();
		PedidoDAO pedidoDAO = new PedidoDAO();
		
		Pedido pedido = pedidoDAO.selecionarPorId(idPedido);
		
		// Tenta fazer a transi��o de estados com o State
		String result = pedido.encerrarPedido();
		if( result.contains("ERRO:")) {
			return result;
		}
		
		// Atualiza o vinho
		Vinho vinho = pedido.getVinho();
		int qtdFinal = vinho.getQtdEstoque()-pedido.getQtdVinho();
		vinho.setQtdEstoque(qtdFinal);
		vinhoDAO.atualizar(vinho);
		
		// Atualiza o pedido no BD
		pedidoDAO.atualizar(pedido);
		
		//TODO: fazer o tratamento de erro
		
		return "Pedido encerrado com sucesso!";
		
	}

	public static List<Pedido> consultarPedidoPorEstado(String estadoPedido) {
		PedidoDAO pedidoDAO = new PedidoDAO();
		List<Pedido> lista = new ArrayList<Pedido>();
		if( estadoPedido.equals("todos")) {
			lista = pedidoDAO.selecionarTodos();
		} else {
			lista = pedidoDAO.selecionarPorEstado(estadoPedido);
		}
		return lista;
	}
}
