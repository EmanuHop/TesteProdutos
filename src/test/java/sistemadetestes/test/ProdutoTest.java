package sistemadetestes.test;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;
import sistemadetestes.pageObject.ProdutoPO;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProdutoTest extends BaseTest{

	private static ProdutoPO produtoPage;
	
	@BeforeClass
	public static void prepararTestes() {
		produtoPage = new ProdutoPO(driver);
	}
	
	/**
	 * Teste para caso todos os campos estejam preenchidos corretamente.
	 * Esperado: Sucesso
	 * Resultado: Sucesso, o produto foi cadastrado e verificado pelos campos. Contudo o formato da data é salvo em outro padrao
	 */
	@Test
	public void TC001_nenhumCampoNulo() {
		String codigo = "1234";
		String nome = "Calculadora";
		String quantidade = "1";
		String valor = "10";
		String data = "12/12/1212";
		
		produtoPage.abrirModal();
		produtoPage.escrever(produtoPage.inputCodigo, codigo);
		produtoPage.escrever(produtoPage.inputNome, nome);
		produtoPage.escrever(produtoPage.inputQuantidade, quantidade);
		produtoPage.escrever(produtoPage.inputValor, valor);
		produtoPage.escrever(produtoPage.inputData, data);
		produtoPage.btnSalvar.click();
		
		List<WebElement> linhas = produtoPage.obterPrimeiraLinhaTabela();
		assertEquals(codigo, linhas.get(0).getText());
		assertEquals(nome, linhas.get(1).getText());
		assertEquals(quantidade, linhas.get(2).getText());
		assertEquals(valor, linhas.get(3).getText());
		assertEquals("1212-12-12", linhas.get(4).getText());
	}
	
	/**
	 * Teste para caso o campo do valor esteja vazio
	 * Esperado: Mensagem para preencher os campos
	 * Recebido: Mensagem para preencher os campos
	 */
	@Test
	public void TC002_valorVazio() {
		produtoPage.abrirModal();
		produtoPage.escrever(produtoPage.inputCodigo, "1234");
		produtoPage.escrever(produtoPage.inputNome, "Calculadora");
		produtoPage.escrever(produtoPage.inputQuantidade, "1");
		produtoPage.escrever(produtoPage.inputValor, "");
		produtoPage.escrever(produtoPage.inputData, "12/12/1212");
		produtoPage.btnSalvar.click();
		
		String mensagem = produtoPage.obterMensagem();
		assertEquals("Todos os campos são obrigatórios para o cadastro!", mensagem);
	}
	
	/**
	 * Teste para caso o campo da data esteja nulo
	 * Esperado: Mensagem para preencher os campos
	 * Recebido: Mensagem para preencher os campos
	 */
	@Test
	public void TC003_dataNulo() {
		produtoPage.abrirModal();
		produtoPage.escrever(produtoPage.inputCodigo, "1234");
		produtoPage.escrever(produtoPage.inputNome, "Calculadora");
		produtoPage.escrever(produtoPage.inputQuantidade, "1");
		produtoPage.escrever(produtoPage.inputValor, "10");
		produtoPage.btnSalvar.click();
		
		String mensagem = produtoPage.obterMensagem();
		assertEquals("Todos os campos são obrigatórios para o cadastro!", mensagem);
	}
	
	/**
	 * Teste para caso todos os campo estejam vazios
	 * Esperado: Mensagem para preencher os campos
	 * Recebido: Mensagem para preencher os campos
	 */
	@Test
	public void TC032_todosOsValoresNulos() {
		produtoPage.abrirModal();
		produtoPage.escrever(produtoPage.inputCodigo, "");
		produtoPage.escrever(produtoPage.inputNome, "");
		produtoPage.escrever(produtoPage.inputQuantidade, "");
		produtoPage.escrever(produtoPage.inputValor, "");
		produtoPage.escrever(produtoPage.inputData, "");
		produtoPage.btnSalvar.click();
		
		String mensagem = produtoPage.obterMensagem();
		assertEquals("Todos os campos são obrigatórios para o cadastro!", mensagem);
	}
	
	/**
	 * Teste para inserir um valor diferente de um tipo numerico no campo de valor
	 * Esperado: Erro ao inserir
	 * Resultado: Produto inserido
	 * FALHA NO TESTE
	 */
	@Test
	public void TC033_valorComoString() {		
		produtoPage.abrirModal();
		produtoPage.escrever(produtoPage.inputCodigo, "0001");
		produtoPage.escrever(produtoPage.inputNome, "Calculadora");
		produtoPage.escrever(produtoPage.inputValor, "abc");
		produtoPage.escrever(produtoPage.inputQuantidade, "50");
		produtoPage.escrever(produtoPage.inputData, "12/12/1212");
		
		String mensagem = produtoPage.obterMensagem();
		assertEquals("Preencha todos os campos corretamente", mensagem);
	}
}
