package bluAndroid.bluAndroid.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class PopUp {
	private static AppiumDriver<WebElement> driver;
	public PopUp()
	{
		
	}
	public PopUp(AppiumDriver<WebElement> driver)
	{
		this.driver=driver;
	}
	public WebElement alertTitle()
	{
		WebElement alertTitle=driver.findElement(By.id("android:id/alertTitle"));
		return alertTitle;
	}
	public WebElement alertMsg()
	{
		WebElement alertMsg=driver.findElement(By.id("android:id/message"));
		return alertMsg;
	}
	public void clickBtn1()
	{
		WebElement btn1=driver.findElement(By.id("android:id/button1"));
		btn1.click();
	}
	public void clickBtn2()
	{
		WebElement btn2=driver.findElement(By.id("android:id/button2"));
		btn2.click();;
	}
}
