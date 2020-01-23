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
public void clickOnDeactivateAccount()
{
	WebElement deactivateAccount=driver.findElement(By.id("sg.com.blu.android.uat:id/deactivate_account_container"));
	deactivateAccount.click();
}
public void clickOnDeactivateAccountBtn()
{
	WebElement deactivateAccountBtn=driver.findElement(By.id("sg.com.blu.android.uat:id/deactivate_my_account_btn"));
	deactivateAccountBtn.click();
}
public void selectReason()
{
	WebElement reason=driver.findElement(By.xpath("//android.widget.RadioGroup[@resource-id='sg.com.blu.android.uat:id/radioGroup']//android.widget.RelativeLayout"));
	//List<WebElement> list=driver.findElements(By.className("android.widget.RadioGroup"));
	reason.click();
}
public WebElement deactivateAlertTitle()
{
	WebElement alertTitle=driver.findElement(By.id("sg.com.blu.android.uat:id/alertTitle"));
	return alertTitle;
}
}
