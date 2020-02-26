package bluAndroid.bluAndroid.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class MapViewScreen {
	private static AppiumDriver<WebElement> driver;
	public MapViewScreen()
	{
		
	}
	public MapViewScreen(AppiumDriver<WebElement> driver)
	{
		this.driver=driver;
	}
	public void clickonSearchBox()
	{
		WebElement searchBox=driver.findElement(By.id("sg.com.blu.android.uat:id/search_tv"));
		searchBox.click();
	}
	public WebElement enterInSearchBox()
	{
		WebElement searchBox=driver.findElement(By.id("sg.com.blu.android.uat:id/search_et"));
		return searchBox;
	}
	public WebElement bluPortPin()
	{
		WebElement bluPortPin=driver.findElement(By.xpath("//android.view.View[@content-desc='Google Map']/android.view.View"));
		return bluPortPin;
	}
	public WebElement selectedBluPortName()
	{
		WebElement selectedBluPortName=driver.findElement(By.id("sg.com.blu.android.uat:id/bluport_info_name_tv"));
		return selectedBluPortName;
	}
}
