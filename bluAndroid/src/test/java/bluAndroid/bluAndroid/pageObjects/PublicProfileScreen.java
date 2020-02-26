package bluAndroid.bluAndroid.pageObjects;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import bluAndroid.bluAndroid.util.CommonUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;

public class PublicProfileScreen {
	public static AppiumDriver<WebElement> driver;
	public PublicProfileScreen()
	{
		
	}
	public PublicProfileScreen(AppiumDriver<WebElement> driver2)
	{
		this.driver=driver2;
	}
	public WebElement getTitle() throws IOException
	{
		WebElement title=driver.findElement(By.id("sg.com.blu.android.uat:id/titleTextView"));
		return title;
	}
	public void clickOnBackButton()
	{
		driver.findElement(By.xpath("//android.widget.RelativeLayout[@resource-id='sg.com.blu.android.uat:id/topBar']//android.widget.ImageButton[@resource-id='sg.com.blu.android.uat:id/backButton']")).click();
	}
	public void clickOnCloseBtn()
	{
		WebElement x=driver.findElement(By.xpath("//android.widget.RelativeLayout[@resource-id='sg.com.blu.android.uat:id/topBar']//android.widget.ImageButton[@resource-id='sg.com.blu.android.uat:id/closeButton']"));
		x.click();
	}
	public WebElement getFriendName() throws IOException
	{
		WebElement firstName=driver.findElement(By.id("sg.com.blu.android.uat:id/firstNameTextView"));
		return firstName;
	}
	public WebElement unblock() throws IOException
	{
		WebElement unblock=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/content']//android.widget.LinearLayout[3]//android.widget.TextView[@resource-id='sg.com.blu.android.uat:id/actionTextView']"));
		return unblock;
	}
	public WebElement blockAlertTitle() throws IOException
	{
		WebElement block=driver.findElement(By.id("android:id/alertTitle"));
		return block;
	}
	public WebElement blockAlertMsg() throws IOException
	{
		WebElement block=driver.findElement(By.id("android:id/message"));
		return block;
	}
	public void clickOnMobile()
	{
		WebElement mobile=driver.findElement(By.xpath("//android.widget.HorizontalScrollView[@resource-id='sg.com.blu.android.uat:id/tabLayout']//androidx.appcompat.app.ActionBar.Tab[2]"));
		mobile.click();
	}
	public WebElement textField()
	{
		WebElement TextField=driver.findElement(By.id("sg.com.blu.android.uat:id/input_text_et"));
		return TextField;
	}
	public WebElement permissionAllowBtn()
	{
		WebElement allow=driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
		return allow;
	}
}
