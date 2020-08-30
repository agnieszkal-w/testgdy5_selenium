package PageObjects;

import org.openqa.selenium.WebDriver;

public class DevToMainPage {
    String url = "https://dev.to";
    WebDriver driverInDevToMainPage;

    public DevToMainPage(WebDriver driverFromPageObjectTest) {
        this.driverInDevToMainPage = driverFromPageObjectTest;
        // przypisanie zaleznosci przegladarki klasy PageObjectTest do przegladarki uzywanej w klasie DevToMainPage
        driverInDevToMainPage.get(url); // otwieramy strone w przegladarce
    }
}
