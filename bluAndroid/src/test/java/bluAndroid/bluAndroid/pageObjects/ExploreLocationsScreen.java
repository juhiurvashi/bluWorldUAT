package bluAndroid.bluAndroid.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class ExploreLocationsScreen {
	private static AppiumDriver<WebElement> driver;
	public ExploreLocationsScreen()
	{
		
	}
	public ExploreLocationsScreen(AppiumDriver<WebElement> driver)
	{
		this.driver=driver;
	}
	public void clickOnExploreLocations()
	{
		WebElement exploreLocations=driver.findElement(By.id("sg.com.blu.android.uat:id/exploreLocationsButton"));
		exploreLocations.click();
	}
	
}
