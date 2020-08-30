package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DevToSinglePodcastsPage {

    @FindBy(className = "record-wrapper")
    WebElement recordBtn;

    WebDriver driverInDevToSinglePodcastPage;
    WebDriverWait wait;

    public boolean isPodcastPlaying;

    public DevToSinglePodcastsPage(WebDriver driverFromDevToPodcastPage, WebDriverWait _wait) {
        this.driverInDevToSinglePodcastPage = driverFromDevToPodcastPage;
        PageFactory.initElements(driverInDevToSinglePodcastPage; this);
        this.wait = _wait; // podloga aby odroznic, ktory wait jest ktory i skad sie wzial. _wait jest "nowy"
    }

    public void playPodcast(){
        recordBtn.click();
        wait.until(ExpectedConditions.invisibilityOf(driverInDevToSinglePodcastPage.findElement(By.className("status-message"))));
        String recordBtnClassAttribute = recordBtn.getAttribute( "class");
        isPodcastPlaying = recordBtnClassAttribute.contains("playing");
    }
}
