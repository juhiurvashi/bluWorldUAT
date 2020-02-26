package bluAndroid.bluAndroid.pageObjects;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import bluAndroid.bluAndroid.util.CommonUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;

public class SignUpScreen {
	private static AppiumDriver<WebElement> driver;

	public SignUpScreen() {

	}

	public SignUpScreen(AppiumDriver<WebElement> driver) {
		this.driver = driver;
	}

	public void clickSignUpBtn() {
		driver.findElement(By.id("sg.com.blu.android.uat:id/sign_up_btn")).click();
	}

	public WebElement enterEmail() {
		WebElement email = driver.findElement(
				By.xpath("//android.widget.LinearLayout[@resource-id ='sg.com.blu.android.uat:id/email_inputField']"));
		return email;
	}

	public WebElement enterPassword() {
		WebElement pass = driver.findElement(
				By.xpath("//android.widget.LinearLayout[@resource-id ='sg.com.blu.android.uat:id/password_inputField']"));
		return pass;
	}

	public WebElement enterConPassword() {
		WebElement conPass = driver.findElement(By.xpath(
				"//android.widget.LinearLayout[@resource-id ='sg.com.blu.android.uat:id/confirm_password_inputField']"));
		return conPass;
	}

	public WebElement enterFirstName() {
		WebElement fname = driver.findElement(
				By.xpath("//android.widget.LinearLayout[@resource-id ='sg.com.blu.android.uat:id/first_name_inputField']"));
		return fname;
	}

	public WebElement enterLastName() {
		WebElement lname = driver.findElement(
				By.xpath("//android.widget.LinearLayout[@resource-id ='sg.com.blu.android.uat:id/last_name_inputField']"));
		return lname;
	}

	public WebElement enterMobile() {
		WebElement mobile = driver.findElement(
				By.xpath("//android.widget.EditText[@resource-id ='sg.com.blu.android.uat:id/input_mobile_number_et']"));
		return mobile;
	}

	public WebElement selectDOBbtn() {
		WebElement dob = driver.findElement(
				By.xpath("//android.widget.LinearLayout[@resource-id ='sg.com.blu.android.uat:id/dob_inputField']"));
		return dob;
	}

	public WebElement selectGender() throws IOException {
		WebElement gender = driver.findElement(By.id("sg.com.blu.android.uat:id/female_rb"));
		//String gender = CommonUtil.getPropertyValue("signup", "gender");
		///WebElement gender1=driver.findElement(By.xpath("sg.com.blu.android.uat:id/\"" +gender + "\""));
	//	System.out.println(gender1);
				return gender;
	}

	public WebElement enterReferralCode() {
		WebElement referralCode = driver.findElement(
				By.xpath("//android.widget.LinearLayout[@resource-id ='sg.com.blu.android.uat:id/promo_code_inputField']"));
		return referralCode;
	}
	public static void swipeInListFromLastToFirst(List<WebElement> list) {
		int items = list.size();
		//System.out.println("List Size is: "+ items);
		TouchAction action = new TouchAction(driver);
		PointOption p1 = new PointOption();
		org.openqa.selenium.Point centerOfFirstElement = ((MobileElement) list.get(0)).getCenter();
		org.openqa.selenium.Point centerOfLastElement = ((MobileElement) list.get(items - 1)).getCenter();
		//System.out.println("center of first element: "+centerOfFirstElement+ "  center of last element: "+centerOfLastElement);
		new TouchAction(driver).longPress(p1.point(centerOfFirstElement.x, centerOfFirstElement.y+300))
				.moveTo(p1.point(centerOfLastElement.x, centerOfLastElement.y+80)).release().perform();
		
	}
	public static void swipeInListTillExpectedTextAndTap(List<WebElement> list, String expectedText, int time) {
		int i = 1;
		while (!driver.getPageSource().contains(expectedText)) {
			swipeInListFromLastToFirst(list);
			i++;
			if (i == time)
				break;
		}
		driver.findElement(MobileBy.AndroidUIAutomator("new UiSelector().textContains(\"" +expectedText + "\")")).click();;
	}
	public void selectDOB() throws IOException
	{
		selectDOBbtn().click();
		driver.findElement(By.id("android:id/date_picker_header_year")).click();
		List<WebElement> list=driver.findElements(By.className("android.widget.TextView"));
		String year=CommonUtil.getPropertyValue("signup", "year");
		swipeInListTillExpectedTextAndTap(list, year, 20);
  		int month=Calendar.getInstance().get(Calendar.MONTH)+1;
   		System.out.println(" Current Month is :"+ month);
  		String monthTarget=CommonUtil.getPropertyValue("signup", "month");
  		int resultMonth = Integer.parseInt(monthTarget);
  		if(resultMonth>month)
  		{
  			for(int i=0;i<resultMonth-month;i++)
  			driver.findElement(By.id("android:id/next")).click();	
  		}
  		if(resultMonth<month)
  		{
  			for(int i=0;i<month-resultMonth;i++)
  			driver.findElement(By.id("android:id/prev")).click();	
  		}
		//for(int i=0;i<6;i++)
		//driver.findElement(By.id("android:id/prev")).click();
		//driver.findElement(By.id("android:id/next")).click();
		//String dob=CommonUtil.getPropertyValue("signup", "dob");
  		String dob = CommonUtil.getPropertyValue("signup", "dob");
		System.out.println(dob);
		driver.findElement(By.xpath("//android.view.View[@content-desc=\"" +dob + "\"]")).click();
		//driver.findElement(By.xpath("//android.view.View[@resource-id='android:id/month_view']//android.view.View[contains(text(),'7')]")).click();
		driver.findElement(By.id("android:id/button1")).click();//button 2 for cancel	
	}

	public void bluSignUp() throws IOException {
		String email = CommonUtil.getPropertyValue("signup", "email");
		String password = CommonUtil.getPropertyValue("signup", "password");
		String conPassword = CommonUtil.getPropertyValue("signup", "conPassword");
		String firstName = CommonUtil.getPropertyValue("signup", "firstName");
		String lastName = CommonUtil.getPropertyValue("signup", "lastName");
		String mobile = CommonUtil.getPropertyValue("signup", "mobile");
		
		String gender = CommonUtil.getPropertyValue("signup", "gender");
		String referralCode = CommonUtil.getPropertyValue("signup", "referralCode");
		enterEmail().sendKeys(email);
		enterPassword().sendKeys(password);
		enterConPassword().sendKeys(conPassword);
		enterFirstName().sendKeys(firstName);
		//enterLastName().sendKeys(lastName);
		enterMobile().sendKeys(mobile);
		//selectDOB();
		//selectGender().click();
		enterReferralCode().sendKeys(referralCode);
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		clickSignUpBtn();
	}
	public void bluSignUpManDetails() throws IOException
	{
		String email = CommonUtil.getPropertyValue("signup", "email");
		String password = CommonUtil.getPropertyValue("signup", "password");
		String conPassword = CommonUtil.getPropertyValue("signup", "conPassword");
		String firstName = CommonUtil.getPropertyValue("signup", "firstName");
		String lastName = CommonUtil.getPropertyValue("signup", "lastName");
		String mobile = CommonUtil.getPropertyValue("signup", "mobile");
		
		String gender = CommonUtil.getPropertyValue("signup", "gender");
		String referralCode = CommonUtil.getPropertyValue("signup", "referralCode");
		clickSignUpBtn();
		enterEmail().sendKeys(email);
		enterPassword().sendKeys(password);
		enterConPassword().sendKeys(conPassword);
		enterFirstName().sendKeys(firstName);
		enterMobile().sendKeys(mobile);
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		clickSignUpBtn();
	}
	
	public void signupWithoutReferralCode() throws IOException
	{
		String email = CommonUtil.getPropertyValue("signup", "email");
		String password = CommonUtil.getPropertyValue("signup", "password");
		String conPassword = CommonUtil.getPropertyValue("signup", "conPassword");
		String firstName = CommonUtil.getPropertyValue("signup", "firstName");
		String lastName = CommonUtil.getPropertyValue("signup", "lastName");
		String mobile = CommonUtil.getPropertyValue("signup", "mobile");

		enterEmail().sendKeys(email);
		enterPassword().sendKeys(password);
		enterConPassword().sendKeys(conPassword);
		enterFirstName().sendKeys(firstName);
		enterLastName().sendKeys(lastName);
		enterMobile().sendKeys(mobile);
		selectDOB();
		selectGender().click();
		clickSignUpBtn();
	}
	public void signupWithoutGenderAndRefferalCode() throws IOException
	{
		clickSignUpBtn();
		String email = CommonUtil.getPropertyValue("signup", "email");
		String password = CommonUtil.getPropertyValue("signup", "password");
		String conPassword = CommonUtil.getPropertyValue("signup", "conPassword");
		String firstName = CommonUtil.getPropertyValue("signup", "firstName");
		String lastName = CommonUtil.getPropertyValue("signup", "lastName");
		String mobile = CommonUtil.getPropertyValue("signup", "mobile");
		enterEmail().sendKeys(email);
		enterPassword().sendKeys(password);
		enterConPassword().sendKeys(conPassword);
		enterFirstName().sendKeys(firstName);
		enterLastName().sendKeys(lastName);
		enterMobile().sendKeys(mobile);
		selectDOB();
		clickSignUpBtn();
	}
	public void signupWithoutDOBGenderAndRefferalCode() throws IOException
	{
		clickSignUpBtn();
		String email = CommonUtil.getPropertyValue("signup", "email");
		String password = CommonUtil.getPropertyValue("signup", "password");
		String conPassword = CommonUtil.getPropertyValue("signup", "conPassword");
		String firstName = CommonUtil.getPropertyValue("signup", "firstName");
		String lastName = CommonUtil.getPropertyValue("signup", "lastName");
		String mobile = CommonUtil.getPropertyValue("signup", "mobile");
		enterEmail().sendKeys(email);
		enterPassword().sendKeys(password);
		enterConPassword().sendKeys(conPassword);
		enterFirstName().sendKeys(firstName);
		enterLastName().sendKeys(lastName);
		enterMobile().sendKeys(mobile);
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		clickSignUpBtn();
	}
}
