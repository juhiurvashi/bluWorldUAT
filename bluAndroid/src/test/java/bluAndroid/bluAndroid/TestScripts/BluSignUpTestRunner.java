package bluAndroid.bluAndroid.TestScripts;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import bluAndroid.bluAndroid.pageObjects.CommonFunctions;
import bluAndroid.bluAndroid.pageObjects.SignUpScreen;
import bluAndroid.bluAndroid.util.BaseClass;
import bluAndroid.bluAndroid.util.CommonUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;

public class BluSignUpTestRunner extends BaseClass {
	public static AppiumDriver<WebElement> driver;
	SignUpScreen su;
	CommonFunctions cf;
	@BeforeMethod
	public void preCondition() throws IOException

	{
		System.out.println("Set Up....");
		BluSignUpTestRunner.driver = BaseClass.getAppCapabilities();

		su = new SignUpScreen(driver);
		cf=new CommonFunctions(driver);
	}
	


	@Test 
	public void A_S02_TC01_signupWithAllDetails() throws IOException, InterruptedException {
		System.out.println("A_S02_TC01_signupWithAllDetails");
		extentTest = extentReports.createTest("A_S02_TC01_signupWithAllDetails()");
		System.out.println("Sign up");
		su.clickSignUpBtn();
		su.bluSignUp();       
		String expected=CommonUtil.getPropertyValue("signup", "OTPScreenText");
		WebElement otpScreenText= driver.findElement(By.id("sg.com.blu.android.uat:id/recipient_tv"));
		String actual=otpScreenText.getText();
		Assert.assertEquals(actual, expected);
		System.out.println("Textmatched"+ expected+actual);
	}
	//@Test
	public void A_S02_TC02_signupWithoutReferralCode() throws IOException
	{
		System.out.println("A_S02_TC02_signupWithoutReferralCode");
		extentTest = extentReports.createTest("A_S02_TC02_signupWithoutReferralCode");
		su.clickSignUpBtn();
		su.signupWithoutReferralCode();
		String expected=CommonUtil.getPropertyValue("signup", "OTPScreenText");
		WebElement otpScreenText= driver.findElement(By.id("sg.com.blu.android.uat:id/recipient_tv"));
		String actual=otpScreenText.getText();
		Assert.assertEquals(actual, expected);
	}
	//@Test
	public void A_S02_TC03_signupWithoutGenderAndRefferalCode() throws IOException
	{
		System.out.println("A_S02_TC03_signupWithoutGenderAndRefferalCode");
		extentTest = extentReports.createTest("A_S02_TC03_signupWithoutGenderAndRefferalCode()");
		su.signupWithoutGenderAndRefferalCode();
		String expected=CommonUtil.getPropertyValue("signup", "OTPScreenText");
		WebElement otpScreenText= driver.findElement(By.id("sg.com.blu.android.uat:id/recipient_tv"));
		String actual=otpScreenText.getText();
		Assert.assertEquals(actual, expected);
	}
	//@Test
	public void A_S02_TC04_signupWithoutDOBGenderAndRefferalCode() throws IOException
	{
		System.out.println("A_S02_TC04_signupWithoutDOBGenderAndRefferalCode");
		extentTest = extentReports.createTest("A_S02_TC04_signupWithoutDOBGenderAndRefferalCode()");
		su.signupWithoutDOBGenderAndRefferalCode();
		String expected=CommonUtil.getPropertyValue("signup", "OTPScreenText");
		WebElement otpScreenText= driver.findElement(By.id("sg.com.blu.android.uat:id/recipient_tv"));
		String actual=otpScreenText.getText();
		Assert.assertEquals(actual, expected);
	}
	@Test
	public void A_S02_TC05_signupManDetails() throws IOException
	{
		System.out.println("A_S02_TC05_signupManDetails");
		extentTest = extentReports.createTest("A_S02_TC05_signupManDetails");
		su.bluSignUpManDetails();
		String expected=CommonUtil.getPropertyValue("signup", "OTPScreenText");
		WebElement otpScreenText= driver.findElement(By.id("sg.com.blu.android.uat:id/recipient_tv"));
		String actual=otpScreenText.getText();
		Assert.assertEquals(actual, expected);
	}
	@Test
	public void A_S02_TC06_signupInvalidEmail() throws IOException
	{
		System.out.println("A_S02_TC06_signupInvalidEmail");
		extentTest = extentReports.createTest("A_S02_TC06_signupInvalidEmail()");
		String invalidEmail= CommonUtil.getPropertyValue("signup", "invalidEmail");
		String password = CommonUtil.getPropertyValue("signup", "password");
		String conPassword = CommonUtil.getPropertyValue("signup", "conPassword");
		String firstName = CommonUtil.getPropertyValue("signup", "firstName");
		String mobile = CommonUtil.getPropertyValue("signup", "mobile");
		su.clickSignUpBtn();
		su.enterEmail().sendKeys(invalidEmail);
		su.enterPassword().sendKeys(password);
		su.enterConPassword().sendKeys(conPassword);
		su.enterFirstName().sendKeys(firstName);
		su.enterMobile().sendKeys(mobile);
		su.clickSignUpBtn();
		String expected="Invalid email address.";
		WebElement error= driver.findElement(By.id("sg.com.blu.android.uat:id/error_tv"));
		String actual=error.getText();
		Assert.assertEquals(actual, expected);
		
	}
	@Test
	public void A_S02_TC07_signupRegisteredEmail() throws IOException
	{
		System.out.println("A_S02_TC07_signupRegisteredEmail");
		extentTest = extentReports.createTest("A_S02_TC07_signupRegisteredEmail()");
		String usedEmail= CommonUtil.getPropertyValue("signup", "usedEmail");
		String password = CommonUtil.getPropertyValue("signup", "password");
		String conPassword = CommonUtil.getPropertyValue("signup", "conPassword");
		String firstName = CommonUtil.getPropertyValue("signup", "firstName");
		String mobile = CommonUtil.getPropertyValue("signup", "mobile");
		su.clickSignUpBtn();
		su.enterEmail().sendKeys(usedEmail);
		su.enterPassword().sendKeys(password);
		su.enterConPassword().sendKeys(conPassword);
		su.enterFirstName().sendKeys(firstName);
		su.enterMobile().sendKeys(mobile);
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		su.clickSignUpBtn();
		String expected2="Email address already used.";
		WebElement error= driver.findElement(By.id("sg.com.blu.android.uat:id/error_tv"));
		String actual2=error.getText();
		Assert.assertEquals(actual2, expected2);
	}
	@Test
	public void A_S02_TC08_signupInvalidPassword() throws IOException
	{
		System.out.println("A_S02_TC08_signupInvalidPassword");
		extentTest = extentReports.createTest("A_S02_TC08_signupInvalidPassword()");
		String email= CommonUtil.getPropertyValue("signup", "email");
		String invalidpassword = CommonUtil.getPropertyValue("signup", "invalidPass");
		String conPassword = CommonUtil.getPropertyValue("signup", "conPassword");
		String firstName = CommonUtil.getPropertyValue("signup", "firstName");
		String mobile = CommonUtil.getPropertyValue("signup", "mobile");
		su.clickSignUpBtn();
		su.enterEmail().sendKeys(email);
		su.enterPassword().sendKeys(invalidpassword);
		su.enterConPassword().sendKeys(conPassword);
		su.enterFirstName().sendKeys(firstName);
		su.enterMobile().sendKeys(mobile);
		su.clickSignUpBtn();
		String expected="Password should be 8 or more letters, with at least one uppercase letter, one lowercase letter, and one number or symbol.";
		WebElement passError = driver.findElement(
				By.xpath("//android.widget.LinearLayout[@resource-id ='sg.com.blu.android.uat:id/password_inputField']//android.widget.TextView[@resource-id ='sg.com.blu.android.uat:id/error_tv']"));
		String actual=passError.getText();
		Assert.assertEquals(actual, expected);
		
	}
	@Test
	public void A_S02_TC09_signupNonMatchingPassword() throws IOException
	{
		System.out.println("A_S02_TC09_signupNonMatchingPassword");
		extentTest = extentReports.createTest("A_S02_TC09_signupNonMatchingPassword()");
		String email= CommonUtil.getPropertyValue("signup", "email");
		String password = CommonUtil.getPropertyValue("signup", "password");;
		String conPassword = CommonUtil.getPropertyValue("signup", "nonMatchingPass");
		String firstName = CommonUtil.getPropertyValue("signup", "firstName");
		String mobile = CommonUtil.getPropertyValue("signup", "mobile");
		su.clickSignUpBtn();
		su.enterEmail().sendKeys(email);
		su.enterPassword().sendKeys(password);
		su.enterConPassword().sendKeys(conPassword);
		su.enterFirstName().sendKeys(firstName);
		su.enterMobile().sendKeys(mobile);
		cf.swiptToBottom();
		su.clickSignUpBtn();
		String expected="Passwords do not match";
		WebElement passError = driver.findElement(
				By.xpath("//android.widget.LinearLayout[@resource-id ='sg.com.blu.android.uat:id/confirm_password_inputField']//android.widget.TextView[@resource-id ='sg.com.blu.android.uat:id/error_tv']"));
		String actual=passError.getText();
		Assert.assertEquals(actual, expected);
		
	}
	@Test
	public void A_S02_TC10_signupInvalidFirstname() throws IOException
	{
		System.out.println("A_S02_TC10_signupInvalidFirstname");
		extentTest = extentReports.createTest("A_S02_TC10_signupInvalidFirstname()");
		String email= CommonUtil.getPropertyValue("signup", "email");
		String password = CommonUtil.getPropertyValue("signup", "password");;
		String conPassword = CommonUtil.getPropertyValue("signup", "nonMatchingPass");
		String invalidfirstName = CommonUtil.getPropertyValue("signup", "invalidFirstName");
		String mobile = CommonUtil.getPropertyValue("signup", "mobile");
		su.clickSignUpBtn();
		su.enterEmail().sendKeys(email);
		su.enterPassword().sendKeys(password);
		su.enterConPassword().sendKeys(conPassword);
		su.enterFirstName().sendKeys(invalidfirstName);
		su.enterMobile().sendKeys(mobile);
		su.clickSignUpBtn();
		String expected="Only alphabets and spaces allowed.";
		WebElement passError = driver.findElement(
				By.xpath("//android.widget.LinearLayout[@resource-id ='sg.com.blu.android.uat:id/first_name_inputField']//android.widget.TextView[@resource-id ='sg.com.blu.android.uat:id/error_tv']"));
		String actual=passError.getText();
		Assert.assertEquals(actual, expected);
		
	}
	@Test
	public void A_S02_TC11_signupInvalidMobile() throws IOException
	{
		System.out.println("A_S02_TC11_signupInvalidMobile");
		extentTest = extentReports.createTest("A_S02_TC11_signupInvalidMobile()");
		String email= CommonUtil.getPropertyValue("signup", "email");
		String password = CommonUtil.getPropertyValue("signup", "password");
		String conPassword = CommonUtil.getPropertyValue("signup", "conPassword");
		String firstName = CommonUtil.getPropertyValue("signup", "firstName");
		String mobile = CommonUtil.getPropertyValue("signup", "invalidMobile");
		String usedmobile = CommonUtil.getPropertyValue("signup", "usedMobile");
		su.clickSignUpBtn();
		su.enterEmail().sendKeys(email);
		su.enterPassword().sendKeys(password);
		su.enterConPassword().sendKeys(conPassword);
		su.enterFirstName().sendKeys(firstName);
		su.enterMobile().sendKeys(mobile);
		cf.swiptToBottom();
		su.clickSignUpBtn();
		//	Enter your date of birth.
		String expected="Enter a valid mobile number.";
		WebElement Error = driver.findElement(
				By.xpath("//android.widget.LinearLayout[@resource-id ='sg.com.blu.android.uat:id/mobile_number_inputField']//android.widget.TextView[@resource-id ='sg.com.blu.android.uat:id/error_tv']"));
		String actual=Error.getText();
		Assert.assertEquals(actual, expected);
		
	}
	@Test
	public void A_S02_TC12_signupRegisteredMobile() throws IOException
	{
		System.out.println("A_S02_TC12_signupRegisteredMobile");
		extentTest = extentReports.createTest("A_S02_TC12_signupRegisteredMobile()");
		String email= CommonUtil.getPropertyValue("signup", "email");
		String password = CommonUtil.getPropertyValue("signup", "password");
		String conPassword = CommonUtil.getPropertyValue("signup", "conPassword");
		String firstName = CommonUtil.getPropertyValue("signup", "firstName");
		String mobile = CommonUtil.getPropertyValue("signup", "invalidMobile");
		String usedmobile = CommonUtil.getPropertyValue("signup", "usedMobile");
		su.clickSignUpBtn();
		su.enterEmail().sendKeys(email);
		su.enterPassword().sendKeys(password);
		su.enterConPassword().sendKeys(conPassword);
		su.enterFirstName().sendKeys(firstName);
		su.enterMobile().sendKeys(usedmobile);
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		su.clickSignUpBtn();
		WebElement Error = driver.findElement(
				By.xpath("//android.widget.LinearLayout[@resource-id ='sg.com.blu.android.uat:id/mobile_number_inputField']//android.widget.TextView[@resource-id ='sg.com.blu.android.uat:id/error_tv']"));
		String expected2="Mobile Number already used.";
		String actual2=Error.getText();
		Assert.assertEquals(actual2, expected2);
	}
	@Test
	public void A_S02_TC13_signupInvalidRefferalCode() throws IOException
	{
		System.out.println("A_S02_TC13_signupInvalidRefferalCode");
		extentTest = extentReports.createTest("A_S02_TC13_signupInvalidRefferalCode()");
		String email= CommonUtil.getPropertyValue("signup", "email");
		String password = CommonUtil.getPropertyValue("signup", "password");
		String conPassword = CommonUtil.getPropertyValue("signup", "conPassword");
		String firstName = CommonUtil.getPropertyValue("signup", "firstName");
		String mobile = CommonUtil.getPropertyValue("signup", "mobile");
		String invalidreferralCode = CommonUtil.getPropertyValue("signup", "invalidReferralCode");
		su.clickSignUpBtn();
		su.enterEmail().sendKeys(email);
		su.enterPassword().sendKeys(password);
		su.enterConPassword().sendKeys(conPassword);
		su.enterFirstName().sendKeys(firstName);
		su.enterMobile().sendKeys(mobile);
		cf.swiptToBottom();
		su.enterReferralCode().sendKeys(invalidreferralCode);
		su.clickSignUpBtn();
		String expected="Enter a valid referral code.";
		WebElement passError = driver.findElement(
				By.xpath("//android.widget.LinearLayout[@resource-id ='sg.com.blu.android.uat:id/promo_code_inputField']//android.widget.TextView[@resource-id ='sg.com.blu.android.uat:id/error_tv']"));
		String actual=passError.getText();
		Assert.assertEquals(actual, expected);
	}
	@Test
	public void A_S02_TC14_signupWithoutEmail() throws IOException
	{
		System.out.println("A_S02_TC14_signupWithoutEmail");
		extentTest = extentReports.createTest("A_S02_TC14_signupWithoutEmail()");
		String password = CommonUtil.getPropertyValue("signup", "password");
		String conPassword = CommonUtil.getPropertyValue("signup", "conPassword");
		String firstName = CommonUtil.getPropertyValue("signup", "firstName");
		String mobile = CommonUtil.getPropertyValue("signup", "mobile");
		su.clickSignUpBtn();
		su.enterPassword().sendKeys(password);
		su.enterConPassword().sendKeys(conPassword);
		su.enterFirstName().sendKeys(firstName);
		su.enterMobile().sendKeys(mobile);
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		su.clickSignUpBtn();
		String expected="Enter your email address.";
		WebElement otpScreenText= driver.findElement(By.id("sg.com.blu.android.uat:id/error_tv"));
		String actual=otpScreenText.getText();
		Assert.assertEquals(actual, expected);
	}
	@Test
	public void A_S02_TC15_signupWithoutPassword() throws IOException
	{
		System.out.println("A_S02_TC15_signupWithoutPassword");
		extentTest = extentReports.createTest("A_S02_TC15_signupWithoutPassword()");
		String email= CommonUtil.getPropertyValue("signup", "email");
		String conPassword = CommonUtil.getPropertyValue("signup", "conPassword");
		String firstName = CommonUtil.getPropertyValue("signup", "firstName");
		String mobile = CommonUtil.getPropertyValue("signup", "mobile");			
		su.clickSignUpBtn();
		su.enterEmail().sendKeys(email);
		su.enterConPassword().sendKeys(conPassword);
		su.enterFirstName().sendKeys(firstName);
		su.enterMobile().sendKeys(mobile);	
		cf.swiptToBottom();
		su.clickSignUpBtn();
		String expected="Enter password.";
		WebElement passError = driver.findElement(
				By.xpath("//android.widget.LinearLayout[@resource-id ='sg.com.blu.android.uat:id/password_inputField']//android.widget.TextView[@resource-id ='sg.com.blu.android.uat:id/error_tv']"));
		String actual=passError.getText();
		Assert.assertEquals(actual, expected);
		String expected2="Passwords do not match";
		WebElement Error = driver.findElement(
				By.xpath("//android.widget.LinearLayout[@resource-id ='sg.com.blu.android.uat:id/confirm_password_inputField']//android.widget.TextView[@resource-id ='sg.com.blu.android.uat:id/error_tv']"));
		String actual2=Error.getText();
		Assert.assertEquals(actual2, expected2);
	}
	@Test
	public void A_S02_TC16_signupWithoutConfirmingPass() throws IOException
	{
		System.out.println("A_S02_TC16_signupWithoutConfirmingPass");
		extentTest = extentReports.createTest("A_S02_TC16_signupWithoutConfirmingPass");
		String email= CommonUtil.getPropertyValue("signup", "email");
		String password = CommonUtil.getPropertyValue("signup", "password");
		String firstName = CommonUtil.getPropertyValue("signup", "firstName");
		String mobile = CommonUtil.getPropertyValue("signup", "mobile");
		su.clickSignUpBtn();
		su.enterEmail().sendKeys(email);
		su.enterPassword().sendKeys(password);
		su.enterFirstName().sendKeys(firstName);
		su.enterMobile().sendKeys(mobile);
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		su.clickSignUpBtn();
		String expected="Passwords do not match.";
		WebElement Error = driver.findElement(
				By.xpath("//android.widget.LinearLayout[@resource-id ='sg.com.blu.android.uat:id/confirm_password_inputField']//android.widget.TextView[@resource-id ='sg.com.blu.android.uat:id/error_tv']"));
		String actual=Error.getText();
		Assert.assertEquals(actual, expected);
	}
	@Test
	public void A_S02_TC17_signupWithoutFirstName() throws IOException
	{
		System.out.println("A_S02_TC17_signupWithoutFirstName");
		extentTest = extentReports.createTest("A_S02_TC17_signupWithoutFirstName()");
		String email= CommonUtil.getPropertyValue("signup", "email");
		String password = CommonUtil.getPropertyValue("signup", "password");
		String conPassword = CommonUtil.getPropertyValue("signup", "conPassword");
		String mobile = CommonUtil.getPropertyValue("signup", "mobile");
		su.clickSignUpBtn();
		su.enterEmail().sendKeys(email);
		su.enterPassword().sendKeys(password);
		su.enterConPassword().sendKeys(conPassword);
		su.enterMobile().sendKeys(mobile);
		cf.swiptToBottom();
		su.clickSignUpBtn();
		String expected="Enter your first name.";
		WebElement mobileError = driver.findElement(
				By.xpath("//android.widget.LinearLayout[@resource-id ='sg.com.blu.android.uat:id/first_name_inputField']//android.widget.TextView[@resource-id ='sg.com.blu.android.uat:id/error_tv']"));
		String actual=mobileError.getText();
		Assert.assertEquals(actual, expected);
	}
	@Test
	public void A_S02_TC18_signupWithoutMobileNumber() throws IOException
	{
		System.out.println("A_S02_TC18_signupWithoutMobileNumber");
		extentTest = extentReports.createTest("A_S02_TC18_signupWithoutMobileNumber()");
		String email= CommonUtil.getPropertyValue("signup", "email");
		String password = CommonUtil.getPropertyValue("signup", "password");
		String conPassword = CommonUtil.getPropertyValue("signup", "conPassword");
		String firstName = CommonUtil.getPropertyValue("signup", "firstName");
		su.clickSignUpBtn();
		su.enterEmail().sendKeys(email);
		su.enterPassword().sendKeys(password);
		su.enterConPassword().sendKeys(conPassword);
		su.enterFirstName().sendKeys(firstName);
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		su.clickSignUpBtn();
		String expected="Enter your mobile number.";
		WebElement mobileError = driver.findElement(
				By.xpath("//android.widget.LinearLayout[@resource-id ='sg.com.blu.android.uat:id/mobile_number_inputField']//android.widget.TextView[@resource-id ='sg.com.blu.android.uat:id/error_tv']"));
		String actual=mobileError.getText();
		Assert.assertEquals(actual, expected);
	}
	@AfterMethod
	public void getResult(ITestResult testResult) throws IOException
	{
		if (testResult.getStatus() == ITestResult.FAILURE) {
		extentTest.fail( MarkupHelper
				.createLabel(testResult.getName() + " Test case FAILED due to below issues:", ExtentColor.RED));
		extentTest.fail(testResult.getThrowable());
		String screenshotPath =CommonUtil.takesScreenShotFailed(driver, testResult.getName());
				  extentTest.addScreenCaptureFromPath(screenshotPath);
	} else if (testResult.getStatus() == ITestResult.SUCCESS) { 
		extentTest.pass(
				MarkupHelper.createLabel(testResult.getName() + " Test Case PASSED", ExtentColor.GREEN));
		String screenshotPath =CommonUtil.takesScreenShot(driver, testResult.getName());
		
		extentTest.addScreenCaptureFromPath(screenshotPath);
	} else if(testResult.getStatus() == ITestResult.SKIP) {
		extentTest.skip(
				MarkupHelper.createLabel(testResult.getName() + " Test Case SKIPPED", ExtentColor.BLUE));
		extentTest.skip(testResult.getThrowable());
	} 
	 
	}

}
