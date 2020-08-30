package PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class DevToPodcastsPage {
    WebDriver driverInDevToPodcastPage; //to jest pusty driver, tu trzeba przekazac drivera z zaleznosciami

    @FindBy(tagName = "h3")
    List<WebElement> podcastList;

    public DevToPodcastsPage(WebDriver driverFromDevToMainPage) {
        this.driverInDevToPodcastPage = driverFromDevToMainPage;
        PageFactory.initElements(driverInDevToPodcastPage, this);
    }

    public DevToSinglePodcastsPage selectPodcastFromList(Integer podcastNumber){ // to wszystko i ponizej to jest metoda wybierania podcastu. nazwa metody z malej litery
        WebDriverWait wait = new WebDriverWait(driverInDevToPodcastPage, 10);
        wait.until(ExpectedConditions.urlToBe("https://dev.to/pod"));
        WebElement selectedPodcast = podcastList.get(podcastNumber);
        selectedPodcast.click();
        return new DevToSinglePodcastsPage(driverInDevToPodcastPage, wait); // alt + enter i z gory zniknal void i jest tylko public Dev..
    }
}
