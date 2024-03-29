package com.abiltondias.cursosmc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.abiltondias.cursosmc.domain.Categoria;
import com.abiltondias.cursosmc.domain.Cidade;
import com.abiltondias.cursosmc.domain.Cliente;
import com.abiltondias.cursosmc.domain.Endereco;
import com.abiltondias.cursosmc.domain.Estado;
import com.abiltondias.cursosmc.domain.ItemPedido;
import com.abiltondias.cursosmc.domain.Pagamento;
import com.abiltondias.cursosmc.domain.PagamentoComBoleto;
import com.abiltondias.cursosmc.domain.PagamentoComCartao;
import com.abiltondias.cursosmc.domain.Pedido;
import com.abiltondias.cursosmc.domain.Produto;
import com.abiltondias.cursosmc.domain.enums.EstadoPagamento;
import com.abiltondias.cursosmc.domain.enums.TipoCliente;
import com.abiltondias.cursosmc.repositories.CategoriaRepository;
import com.abiltondias.cursosmc.repositories.CidadesRepository;
import com.abiltondias.cursosmc.repositories.ClientesRepository;
import com.abiltondias.cursosmc.repositories.EnderecosRepository;
import com.abiltondias.cursosmc.repositories.EstadosRepository;
import com.abiltondias.cursosmc.repositories.ItensPedidosRepository;
import com.abiltondias.cursosmc.repositories.PagamentosRepository;
import com.abiltondias.cursosmc.repositories.PedidosRepository;
import com.abiltondias.cursosmc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	ProdutoRepository produtoRepository;
	
	@Autowired
	CidadesRepository cidadesRepository;
	
	@Autowired
	EstadosRepository estadosRepository;
	
	@Autowired
	EnderecosRepository enderecosRepository;
	
	@Autowired
	ClientesRepository clientesRepository;
	
	@Autowired
	PedidosRepository pedidosRepository;
	
	@Autowired
	PagamentosRepository pagamentosRepository;
	
	@Autowired
	ItensPedidosRepository itensPedidosRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, " Escritorio");
		
		Produto p1 = new Produto(null,"Computador", 2000.00);
		Produto p2 = new Produto(null,"Impressora", 800.00);
		Produto p3 = new Produto(null,"Mouse", 80.00);

		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null,"Minas Gerais");
		Estado est2 = new Estado(null,"São Paulo");
		
		Cidade c1 = new Cidade(null,"Uberlândia",est1);
		Cidade c2 = new Cidade(null,"São Paulo", est2);
		Cidade c3 = new Cidade(null,"Campinas", est2);
		
		est1.getCidade().addAll(Arrays.asList(c1));
		est2.getCidade().addAll(Arrays.asList(c2,c3));
		
		estadosRepository.saveAll(Arrays.asList(est1,est2));
		cidadesRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cl1 = new Cliente(null, "Josué Dias", "josuedias@gmail.com", "172192168-01", TipoCliente.PESSOAFISICA);
		cl1.getTelefones().addAll(Arrays.asList("985262563", "85321754589"));
		Endereco e1 = new Endereco(null, "Rua das Genios", "100", "Casa A", "Estudiosos", " 60856-156", cl1, c1);
		Endereco e2 = new Endereco(null, "Rua dos Sabidos", "1000", "Casa de Esquina","Logo Ali", " 60656-154", cl1, c3);
		
		cl1.getEnderecos().addAll(Arrays.asList(e1,e2));
		clientesRepository.saveAll(Arrays.asList(cl1));
		enderecosRepository.saveAll(Arrays.asList(e1,e2));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2019 10:32"), cl1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 14:02"), cl1, e2);
		
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cl1.setPedidos(Arrays.asList(ped1,ped2));
		pedidosRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentosRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		ItemPedido ip1 = new ItemPedido(p1, ped1, 00.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(p3, ped1, 00.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(p2, ped2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itensPedidosRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	}

}
