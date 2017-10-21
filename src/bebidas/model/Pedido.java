package bebidas.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import bebidas.state.PedidoAberto;
import bebidas.state.PedidoEncerrado;
import bebidas.state.State;

@Entity
@Table(name="Pedido")
public class Pedido {

	@Id
	@Column(name = "idPedido", nullable = false)
	@GenericGenerator(name="generator", strategy="increment")
	@GeneratedValue(generator="generator")
	private int idPedido;

	@Column(name = "nomeCliente")
	private String nomeCliente;

	@JoinColumn(name = "idVinho", referencedColumnName = "idVinho", nullable = false)
	@ManyToOne(fetch = FetchType.EAGER)
	private Vinho vinho;	

	@Column(name = "qtdVinho")
	private int qtdVinho;

	@Column(name = "dtPedido")
	private Date dtPedido;

	@Column(name = "dtEncerramento")
	private Date dtEncerramento;

	@Column(name = "valorTotal")
	private double valorTotal;

	@Column(name = "estadoPedido")
	private String estadoPedido;

	@Transient
	private State pedidoAberto = new PedidoAberto(this);

	@Transient
	private State pedidoEncerrado = new PedidoEncerrado(this);

	@Transient
	private State estadoAtual = pedidoAberto;

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Vinho getVinho() {
		return vinho;
	}

	public void setVinho(Vinho vinho) {
		this.vinho = vinho;
	}

	public int getQtdVinho() {
		return qtdVinho;
	}

	public void setQtdVinho(int qtdVinho) {
		this.qtdVinho = qtdVinho;
	}

	public Date getDtPedido() {
		return dtPedido;
	}

	public void setDtPedido(Date dtPedido) {
		this.dtPedido = dtPedido;
	}

	public Date getDtEncerramento() {
		return dtEncerramento;
	}

	public void setDtEncerramento(Date dtEncerramento) {
		this.dtEncerramento = dtEncerramento;
	}

	public double getValorTotal() {
		return valorTotal;
	}

	public void setValorTotal(double valorTotal) {
		this.valorTotal = valorTotal;
	}

	public String getEstadoPedido() {
		return estadoPedido;
	}

	public void setEstadoPedido(String estadoPedido) {
		this.estadoPedido = estadoPedido;
	}

	public State getPedidoAberto() {
		return pedidoAberto;
	}

	public void setPedidoAberto(State pedidoAberto) {
		this.pedidoAberto = pedidoAberto;
	}

	public State getPedidoEncerrado() {
		return pedidoEncerrado;
	}

	public void setPedidoEncerrado(State pedidoEncerrado) {
		this.pedidoEncerrado = pedidoEncerrado;
	}

	public State getEstadoAtual() {
		return estadoAtual;
	}

	public void setEstadoAtual(State estadoAtual) {
		this.estadoAtual = estadoAtual;
	}

	// Métodos do State
	public String abrirPedido() {
		return estadoAtual.abrir();
	}

	public String encerrarPedido() {
		return estadoAtual.encerrar();
	}
}
