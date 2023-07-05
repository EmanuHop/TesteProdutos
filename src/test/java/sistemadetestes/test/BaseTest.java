package sistemadetestes.test;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;

public abstract class BaseTest{
	
	protected static WebDriver driver;
	private static final String URL_BASE = "file:///C:/Users/Emanuel/Senac/selenium-lab-clayton/selenium-lab-main/sistema/produtos.html";
	private static final String PATH_DRIVE = "src/test/resources/msedgedriver.exe";
	
	@BeforeClass
	public static void iniciar() {
		System.setProperty("webdriver.edge.driver", PATH_DRIVE);
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.get(URL_BASE);
	}
	
	@AfterClass
	public static void finalizar() {
		driver.quit();
	}
	
}