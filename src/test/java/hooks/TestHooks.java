package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class TestHooks {

    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Before
    public void setup(){

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

    // Verifica se deve rodar em modo headless (ex: CI/CD)
        if (System.getProperty("headless") != null && System.getProperty("headless").equals("true")) {
            options.addArguments("--headless=new");
            System.out.println("🔧 Executando testes em modo HEADLESS");
        } else {
            System.out.println("🖥️ Executando testes com interface gráfica");
        }

        // Argumentos necessários para modo headless no GitHub Actions
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--disable-extensions");
        options.addArguments("--remote-allow-origins=*");

        WebDriver wd = new ChromeDriver(options);
        wd.manage().window().maximize();

        driver.set(wd);
    }

    @After
    public void tearDown(){
        if (driver.get() != null){
            driver.get().quit();
            driver.remove();
        }
    }

    public static WebDriver getDriver(){
        return driver.get();
    }
}
