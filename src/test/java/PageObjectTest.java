import PageObjects.DevToMainPage;
import PageObjects.DevToPodcastsPage;
import PageObjects.DevToSinglePodcastsPage;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;

public class PageObjectTest {

    WebDriver driverInPageObjectTest; // to jest obiekt typu WebDriver

    @Before
    public void setup(){
        System.setProperty("webdriver.chrome.driver","D:\\Gitlekcja\\chromedriver.exe");
        driverInPageObjectTest = new ChromeDriver(); // tu okreslamy jego przeznaczenie i czym jest - ma byc ChromeDriver
        driverInPageObjectTest.manage().window().maximize();
        driverInPageObjectTest.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void test(){

        DevToMainPage devToMainPage = new DevToMainPage(driverInPageObjectTest); // tworzymy obiekt klasy DevToMainPage i nazywa sie devToMainPage
        // przekazujemy przegladarke z PageObjectTest do DevToMainPage, do kontruktora
        // konktruktor tworzy nam nowy obiekt devToMainPage
        // to musimy zmienic na to co nizej - devToMainPage.selectPodcast();
        DevToPodcastsPage devToPodcastsPage = devToMainPage.selectPodcast();
        DevToSinglePodcastsPage devToSinglePodcastPage = devToPodcastsPage.selectPodcastFromList(2);
        devToSinglePodcastPage.playPodcast();
        assertTrue("Podcast's wasn't played",devToSinglePodcastPage.isPodcastPlaying);
    }

}

