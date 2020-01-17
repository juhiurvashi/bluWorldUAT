package bluAndroid.bluAndroid.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumDriver;

public class PointsAndRewardsScreen {
private AppiumDriver<WebElement> driver;
public PointsAndRewardsScreen()
{
	
}
public PointsAndRewardsScreen(AppiumDriver<WebElement> driver)
{
	this.driver=driver;
}

public List<WebElement> rewardOverviewList()
{
	List<WebElement> list=driver.findElements(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='sg.com.blu.android.uat:id/recyclerView']//android.widget.RelativeLayout"));
	return list;
}
public List<WebElement> rewardNameList()
{
	List<WebElement> list=driver.findElements(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='sg.com.blu.android.uat:id/recyclerView']//android.widget.RelativeLayout//android.widget.TextView[1]"));
	return list;
}
public WebElement redeemBtn()
{
	WebElement btn=driver.findElement(By.id("sg.com.blu.android.uat:id/redeemButton"));
	return btn;	
}
public WebElement rewardName()
{
	WebElement name=driver.findElement(By.id("sg.com.blu.android.uat:id/promotionTitleTextView"));
	return name;	
}
public WebElement rewardDescription()
{
	WebElement des=driver.findElement(By.id("sg.com.blu.android.uat:id/descriptionTextView"));
	return des;	
}
public WebElement requiredText1()
{
	WebElement c=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/containerCredits'][1]//android.widget.LinearLayout[1]//android.widget.TextView[1]"));
	return c;	
}
public WebElement requiredValue1()
{
	WebElement c=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/containerCredits'][1]//android.widget.LinearLayout[1]//android.widget.TextView[1]"));
	return c;	
}
public WebElement availableText1()
{
	WebElement c=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/containerCredits'][1]//android.widget.LinearLayout[2]//android.widget.TextView[1]"));
	return c;	
}
public WebElement requiredText2()
{
	WebElement c=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/containerPoints'][1]//android.widget.LinearLayout[1]//android.widget.TextView[1]"));
	return c;	
}
public WebElement requiredValue2()
{
	WebElement c=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/containerPoints']//android.widget.LinearLayout[1]//android.widget.TextView[1]"));
	return c;	
}
public WebElement availableText2()
{
	WebElement c=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/containerPoints'][1]//android.widget.LinearLayout[2]//android.widget.TextView[1]"));
	return c;	
}
public List<WebElement> pointsCreditsList()
{
	List<WebElement> list=driver.findElements(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/container']//android.widget.LinearLayout"));
	return list;
}
public WebElement redeemedRewardTextMsg()
{
	WebElement c=driver.findElement(By.id("sg.com.blu.android.uat:id/textViewDescription"));
	return c;	
}
public WebElement alertTitle()
{
	WebElement alertTitle=driver.findElement(By.id("sg.com.blu.android.uat:id/alertTitle"));
	return alertTitle;
}
public void clickBtn1()
{
	WebElement btn1=driver.findElement(By.id("android:id/button1"));
	btn1.click();
}
public WebElement promoCodeTab()
{
	WebElement promoCode=driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='sg.com.blu.android.uat:id/fragment_container']//android.widget.HorizontalScrollView[@resource-id='sg.com.blu.android.uat:id/tabLayout']//android.widget.LinearLayout//androidx.appcompat.app.ActionBar.Tab[2]//android.widget.TextView"));
	return promoCode;
}
public WebElement promoCodeTextField()
{
	WebElement promoCodeTextField=driver.findElement(By.id("sg.com.blu.android.uat:id/promoCodeEditText"));
	return promoCodeTextField;
}
public WebElement myRewards()
{
	WebElement x=driver.findElement(By.xpath("//android.widget.HorizontalScrollView[@resource-id='sg.com.blu.android.uat:id/tabLayout']//androidx.appcompat.app.ActionBar.Tab[2]"));
	return x;
}
public WebElement rewardDiscoverableViaPromoCode()
{
	WebElement promoCodeReward=driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='sg.com.blu.android.uat:id/promoCodeContainer']//androidx.recyclerview.widget.RecyclerView[@resource-id='sg.com.blu.android.uat:id/recyclerView']//android.widget.TextView[1]"));
	return promoCodeReward;
}
}
