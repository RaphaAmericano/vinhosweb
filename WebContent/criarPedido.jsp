<%@include file="_import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%@include file="_header.jsp"%>
</head>
<body>
	<%@include file="_cabecalho.jsp"%>
	
	<%
		// TODO: Caso tenha ocorrido erro na criação do pedido, recuperar os valores para que o formulário já venha preenchido
	
		List<Vinho> vinhos = VinhoManager.consultarTodosVinhos();
	
		if( vinhos == null || vinhos.isEmpty() ) {
	%>	
		<div class="container"> 
			<div class="alert alert-danger">		
				<strong>Não há vinhos cadastrados.</strong>
			</div>
		</div>		
	<%
		} else {
	%>

	<!--  CONTAINER DE MENSAGENS -->
	<%@include file="_containerMensagens.jsp"%>

		
	 <!-- CADASTRO DE NOVO PEDIDO -->
	<div class="container">
	
		<!-- Botões -->	
		
		<form action="CriaPedido.do" method="post">
			<fieldset>
				<legend>Novo Pedido</legend>				
					
					<div class="form-group">
		        		<label for="vinho">Vinho</label>		
						<select class="form-control" id="vinho" name="vinho" required>
							<% 
								for( Vinho vinho : vinhos ) { 
							%>
									<option value="<%=vinho.getIdVinho()%>"><%=vinho.getNomeVinho()%></option>
							<% 
								} 
							%>
			  			</select>			  				
					</div>
					
					<!-- TODO: substituir o campo nomeCliente por uma combobox que traga os nomes dos clientes cadastrados -->					
					<div class="form-group">	
		        		<label for="nomeCliente">Cliente</label>					
						<input type="text" class="form-control" id="nomeCliente" name="nomeCliente" maxlength="150" required"/>						
					</div>
					
					<!-- TODO: permitir a seleção de mais de um vinho por pedido, cada um com sua quantidade -->
					<div class="form-group">
						<label for="qtdVinho">Quantidade</label>		
						<input type="number" min="1" class="form-control" id="qtdVinho" name="qtdVinho" required"/>
					</div>	
							
			</fieldset>
			<button type="submit" class="btn btn-primary">Criar Pedido</button>
		</form>		
	</div>
	<!-- fim .container da pagina -->
	<%
		}
	%>
</body>
</html>
