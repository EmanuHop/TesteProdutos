package sistemadetestes.pageObject;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProdutoPO extends BasePO{

	@FindBy(id = "btn-adicionar")
	public WebElement btnCriar;
	
	@FindBy(css = "div.modal>div.modal-dialog>div.modal-content>div.modal-body>div.alert>span")
	public WebElement spanMensagem;
	
	@FindBy(id = "codigo")
	public WebElement inputCodigo;
	
	@FindBy(id = "nome")
	public WebElement inputNome;
	
	@FindBy(id = "quantidade")
	public WebElement inputQuantidade;
	
	@FindBy(id = "valor")
	public WebElement inputValor;
	
	@FindBy(id = "data")
	public WebElement inputData;

	@FindBy(id = "btn-salvar")
	public WebElement btnSalvar;
	
	@FindBy(id = "btn-sair")
	public WebElement btnSair;
	
	@FindBy(css = "table.table.table-hover")
	public WebElement tabela;
	
	public ProdutoPO(WebDriver driver) {
		super(driver);
	}
	
	public void abrirModal() {
		btnCriar.click();
		btnCriar.click();
	}
	
	/**
	 * Metodo para retorna a mesangem do erro
	 * @return Mensagem do erro
	 */
	public String obterMensagem() {
		return this.spanMensagem.getText();
	}
	
	/**
	 * Escreve em um input da tela
	 * @param input	= componente da tela em que o texto vai ser inserido
	 * @param texto = texto a ser inserido
	 */
	public void escrever(WebElement input, String texto) {
		input.clear();
		input.sendKeys(texto + Keys.TAB);
	}
	
	/**
	 * Pega a primeira linha da tabela e retorna os dados em forma de array
	 * @return Dados da tupla em um array
	 */
	public List<WebElement> obterPrimeiraLinhaTabela() {
		
		List<WebElement> linhas = tabela.findElements(By.cssSelector("tbody > tr"));
        
        return(linhas.get(0).findElements(By.tagName("td")));
	}
}
