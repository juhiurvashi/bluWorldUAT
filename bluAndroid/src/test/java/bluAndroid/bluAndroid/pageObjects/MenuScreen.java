package bluAndroid.bluAndroid.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class MenuScreen {
	private static AppiumDriver<WebElement> driver;
	public MenuScreen()
	{
		
	}
	 
	public MenuScreen(AppiumDriver<WebElement> driver)
	{
		this.driver=driver;
	}
	
	public void logout()
	{
		WebElement menu=driver.findElement(By.id("sg.com.blu.android.uat:id/menuButton"));
		menu.click();
		WebElement logout=driver.findElement(By.id("sg.com.blu.android.uat:id/logOutTextView"));
		logout.click();
	}
	
	public void clickOnPricing()
	{
		WebElement pricing=driver.findElement(By.id("sg.com.blu.android.uat:id/pricingTextView"));
		pricing.click();
	}
	public void clickOnPointsAndRewards()
	{
		WebElement pointsRewards=driver.findElement(By.id("sg.com.blu.android.uat:id/pointsRewardsTextView"));
		pointsRewards.click();
	}
	public void clickOnSettings()
	{
		WebElement menu=driver.findElement(By.id("sg.com.blu.android.uat:id/menuButton"));
		menu.click();
		WebElement settings=driver.findElement(By.id("sg.com.blu.android.uat:id/settingsTextView"));
		settings.click();
	}
	public void clickOnSavedLocations()
	{
		WebElement savedLocations=driver.findElement(By.id("sg.com.blu.android.uat:id/savedLocationsTextView"));
		savedLocations.click();
	}
	public void clickOnMyParcels()
	{
		WebElement myParcels=driver.findElement(By.id("sg.com.blu.android.uat:id/myParcelsTextView"));
		myParcels.click();
	}
	public void clickOnFirstName()
	{
		WebElement firstName=driver.findElement(By.id("sg.com.blu.android.uat:id/firstNameTextView"));
		firstName.click();
	}
	public void clickOnAvailablePoints()
	{
		WebElement availablePoints=driver.findElement(By.id("sg.com.blu.android.uat:id/availablePointsTextView"));
		availablePoints.click();
	}
}
