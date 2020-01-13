package bluAndroid.bluAndroid.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class SavedLocationsScreen {
	private static AppiumDriver<WebElement> driver;
	public SavedLocationsScreen()
	{
		
	}
	public SavedLocationsScreen(AppiumDriver<WebElement> driver)
	{
		this.driver=driver;
	}
	public void clickOnSaveALocationLink()
	{
		WebElement saveALocation=driver.findElement(By.id("sg.com.blu.android.uat:id/saveLocationTextView"));
		saveALocation.click();
	}
	public void clickOnSaveLocationBluHomeBtn()
	{
		WebElement saveLocationBtn=driver.findElement(By.id("sg.com.blu.android.uat:id/saveBluHomeButton"));
		saveLocationBtn.click();
	}
	public void selectActionBtn()
	{
		WebElement saveLocationBtn=driver.findElement(By.id("sg.com.blu.android.uat:id/action_btn"));
		saveLocationBtn.click();
	}
	public List<WebElement> listOfSavedAddress()
	{
		List<WebElement> list=driver.findElements(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='sg.com.blu.android.uat:id/recyclerView']//android.widget.RelativeLayout//android.widget.LinearLayout//android.widget.TextView[1]"));
		return list;
	}
	public List<WebElement> listOfhearticons()
	{
		List<WebElement> listOfHeartIcons=driver.findElements(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='sg.com.blu.android.uat:id/recyclerView']//android.widget.RelativeLayout//android.widget.ImageButton[@resource-id='sg.com.blu.android.uat:id/favourite_btn']"));
		return listOfHeartIcons;
	}
	public void clickOnBluHome()
	{
		WebElement bluHome=driver.findElement(By.xpath("//androidx.appcompat.app.ActionBar.Tab[@content-desc='bluHome']"));
		bluHome.click();
	}
	public WebElement bluHomeAddress1()
	{
		WebElement bluHomeAddress1=driver.findElement(By.id("sg.com.blu.android.uat:id/bluHomeFormAddress1InputField"));
		return bluHomeAddress1;
	}
	public WebElement bluHomeAddress2()
	{
		WebElement bluHomeAddress2=driver.findElement(By.id("sg.com.blu.android.uat:id/bluHomeFormAddress2InputField"));
		return bluHomeAddress2;
	}
	public WebElement bluHomePostalCode()
	{
		WebElement bluHomePostalCode=driver.findElement(By.id("sg.com.blu.android.uat:id/bluHomeFormPostalCodeInputField"));
		return bluHomePostalCode;
	}
}
