package bluAndroid.bluAndroid.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class SettingsScreen {
private AppiumDriver<WebElement> driver;
public SettingsScreen()
{
	
}
public SettingsScreen(AppiumDriver<WebElement> driver)
{
	this.driver=driver;
}

public void clickOnResetpassword()
{
	WebElement resetPassword=driver.findElement(By.id("sg.com.blu.android.uat:id/reset_password_container"));
	resetPassword.click();
}
}
