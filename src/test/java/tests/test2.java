package tests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class test2 {

    public WebDriver driver;
    @Before
    public void setupDriver(){
        System.setProperty("webdriver.gecko.driver","drivers/geckodriver.exe");
        driver = new FirefoxDriver();
        String url = "https://www.n11.com/";
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
    }

    @Test
    public void TestSearch(){

        WebElement signbtn= driver.findElement(By.className("btnSignIn"));
        signbtn.click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        WebElement mailbox= driver.findElement(By.id("email"));
        mailbox.click();
        mailbox.sendKeys("bgmustctn44@gmail.com");
        WebElement password = driver.findElement(By.id("password"));
        password.click();
        password.sendKeys("Bgm.123");
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.findElement(By.id("loginButton")).click();

        /* Arama çubuğunda 'Bilgisayar' ifadesinin aranması */
        WebElement searchBox = driver.findElement(By.id("searchData"));
        searchBox.click();
        searchBox.sendKeys("Bilgisayar");
        driver.findElement(By.className("searchBtn")).click();

        /* Arama sonuç sayfalarında 2. sayfanın açılması ve 2. sayfadaki bir ürünün seçilmesi */
        driver.findElement(By.xpath(".//*[@class='pagination']/a[2]")).click();
        driver.findElement(By.xpath(".//*[@id='view']/ul[1]/li[1]/div[1]/div[1]/a[1]")).click();

        WebElement price= driver.findElement(By.xpath(".//*[@class='newPrice']/ins[1]"));
        String priceText= price.getText();

        /* Açılan ürün sayfasında ürünün sepete eklenmesi*/
        WebElement quantityBox = driver.findElement(By.id("quantity"));
        quantityBox.click();
        quantityBox.clear();
        quantityBox.sendKeys("1");
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.xpath(".//*[@class='btnHolder']/a[2]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        /*Sepete gidilmesi*/
        driver.findElement(By.xpath(".//*[@class='myBasketHolder']/a[1]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        /* Sepetteki ürün adetinin artırılması */
        driver.findElement(By.xpath(".//*[@class='spinnerFieldContainer']/span[1]")).click();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        /*Urunun sepetten cıkarılması*/
        driver.findElement(By.xpath(".//*[@class='prodAction']/span[1]")).click();

        /* Ürün sayfasındaki fiyat ile sepetteki fiyatın karşılaştırılması */
        WebElement priceBasket= driver.findElement(By.className("price"));
        String priceText2= priceBasket.getText();
        if(priceText.compareTo(priceText2)>0){

            /* Sepetteki ürün adetinin artırılması */
            WebElement quantityBasket = driver.findElement(By.id("quantity_126686985817"));
            quantityBasket.click();
            quantityBasket.clear();
            quantityBasket.sendKeys("1");
            driver.findElement(By.className("spinnerUp")).click();
        }

        /* Sepetteki ürünlerin boşaltılması */
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.findElement(By.className("removeProd")).click();
    }
    @After
    public void quitDriver(){
        driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
        driver.quit();

    }

}
