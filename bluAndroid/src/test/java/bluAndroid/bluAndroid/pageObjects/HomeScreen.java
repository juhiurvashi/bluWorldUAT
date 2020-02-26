package bluAndroid.bluAndroid.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class HomeScreen {
private static AppiumDriver<WebElement> driver;
public HomeScreen()
{
	
}
public HomeScreen(AppiumDriver<WebElement> driver)
{
	this.driver=driver;
}
public void clickOnMenu()
{
	WebElement menu=driver.findElement(By.id("sg.com.blu.android.uat:id/menuButton"));
	menu.click();
}
public WebElement sendParcel() { 
	WebElement sendparcel=driver.findElement(By.id("sg.com.blu.android.uat:id/sendParcelButton"));
	return sendparcel;
	
}
public void clickOnRequestForParcel()
{
	driver.findElement(By.id("sg.com.blu.android.uat:id/requestParcelButton")).click();
}

public void checkOutWithblu()
{
	WebElement checkOutWithblu=driver.findElement(By.id("sg.com.blu.android.uat:id/checkoutParcelButton"));
	checkOutWithblu.click();
}
}
