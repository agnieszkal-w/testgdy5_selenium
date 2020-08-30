package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DevToMainPage {
    String url = "https://dev.to";
    WebDriver driverInDevToMainPage;

    @FindBy(xpath = "//a[@href='/pod']") // FindBy to jest annotacja do znajdowania elementow na stronie
    WebElement podcastBtn;

    public DevToMainPage(WebDriver driverFromPageObjectTest) {
        this.driverInDevToMainPage = driverFromPageObjectTest;
        // przypisanie zaleznosci przegladarki klasy PageObjectTest do przegladarki uzywanej w klasie DevToMainPage
        driverInDevToMainPage.get(url); // otwieramy strone w przegladarce
        PageFactory.initElements(driverInDevToMainPage,this);
    }

    public DevToPodcastsPage selectPodcast(){
        // WebElement podcastBtn = driverInDevToMainPage.findElement(By.xpath("//a[@href='/pod']")); - to juz niepotrzebne, bo zrobilismy wyzej FinbBy.
        podcastBtn.click();
        // kontruktor ma takie nawiasy () i ma srednik
        return new DevToPodcastsPage(driverInDevToMainPage);
    }
}
