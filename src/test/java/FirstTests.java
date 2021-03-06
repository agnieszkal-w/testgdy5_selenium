import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FirstTests {

    WebDriver driver; // inicjalizacja pustej przeglądarki

    public void highlightElement(WebDriver driver, WebElement element){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].setAttribute('style', 'background: green; border: 3px solid blue;');", element);
    }

    @Before // sekcja before, a własciwe metoda setup wykona sie przed kazdym testem
    public void setup(){
        System.setProperty("webdriver.chrome.driver","D:\\Gitlekcja\\chromedriver.exe");
        driver = new ChromeDriver(); //przypisanie do ustej przeglądarki, przegladarki chrome do wykonywania testow
        driver.manage().window().maximize();
        driver.get("https://dev.to");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //przy kazdym kolejnym findElement/s poczekaj 10s zanim wywalisz error, co sekunde sprawdzaj czy element  jest juz dostepny
    }

    @Test
    public void goToPodcastsAndSelectThirdOneAndPlayIt() {
        WebElement podcastBtn = driver.findElement(By.xpath("//a[@href='/pod']"));
        highlightElement(driver, podcastBtn); // potrzeby aby widziec, czy element jest znaleziony
        podcastBtn.click();
        //przejscie na strone z podcastami
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("https://dev.to/pod"));
        //sprawdzaj co sekunde czy url ma wartosc https://dev/to/pod, jeśli w ciągu 10 sekund nie ma takiej wartości - wywal błąd
        List<WebElement> podcasts = driver.findElements(By.tagName("h3"));
        //for (WebElement podcast: podcasts) {
        //    highlightElement(driver,podcast) ;      }
        // }
        WebElement thirdPodcast = podcasts.get(2); // numer podcastu 2 oznacza trzeci, 0 to pierwszy i tak dalej
        thirdPodcast.click();
        WebElement recordBtn = driver.findElement(By.className("record-wrapper"));
        recordBtn.click();

        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("status-message"))));
        recordBtn.getAttribute("class");
        String recordBtnClassAttribute = recordBtn.getAttribute( "class");

        boolean isPlaying = recordBtnClassAttribute.contains("playing");

        assertTrue( "podcast wasn't played", isPlaying);
    }

    @Test
    public void goToWeekAndSelectFirstBlog(){
        WebElement weekBtn = driver.findElement(By.xpath("//a[@href='/top/week']"));
        weekBtn.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.urlToBe("https://dev.to/top/week"));
//        List<WebElement> blogs = driver.findElements(By.tagName("h2"));
//        WebElement firstBlog = blogs.get(2);
        WebElement firstBlog = driver.findElement(By.className("crayons-story__title"));
        String firstBlogText = firstBlog.getText();
        firstBlog.click();
        String blogTitle = driver.findElement(By.cssSelector(".crayons-article__header__meta > h1:first-child")).getText();
        assertEquals(blogTitle,firstBlogText);
        // test
    }
}
