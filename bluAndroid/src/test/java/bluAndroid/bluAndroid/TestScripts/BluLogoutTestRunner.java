package bluAndroid.bluAndroid.TestScripts;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import bluAndroid.bluAndroid.pageObjects.CommonFunctions;
import bluAndroid.bluAndroid.pageObjects.LoginScreen;
import bluAndroid.bluAndroid.pageObjects.MenuScreen;
import bluAndroid.bluAndroid.pageObjects.MyDetailsScreen;
import bluAndroid.bluAndroid.pageObjects.SettingsScreen;
import bluAndroid.bluAndroid.util.BaseClass;
import bluAndroid.bluAndroid.util.CommonUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.offset.PointOption;

public class BluLogoutTestRunner extends BaseClass {
	
	static AppiumDriver<WebElement> driver;
	LoginScreen ls;
	String mobileNo, password;
	MyDetailsScreen mds;
	MenuScreen ms;
	CommonFunctions cf;
	SettingsScreen ss;
	@BeforeMethod
	public void preCondition() throws IOException

	{
		System.out.println("Set Up....");
		BluLogoutTestRunner.driver = BaseClass.getAppCapabilities();
		mobileNo = CommonUtil.getPropertyValue("login", "mobileNo");
		password = CommonUtil.getPropertyValue("login", "password");
		ls = new LoginScreen(driver);
		ls.clickLoginLink();
		ls.bluLogin(mobileNo, password);
		mds=new MyDetailsScreen(driver);
		ms=new MenuScreen(driver);
		cf=new  CommonFunctions(driver);
		ss=new SettingsScreen(driver);
	}
	
	@Test
	public void tc01_logout()
	{
		System.out.println("tc01_logout");
		extentTest = extentReports.createTest("tc01_logout()");
		ms.logout();
		WebElement logIn=driver.findElement(By.id("sg.com.blu.android.uat:id/log_in_btn"));
		WebElement signup=driver.findElement(By.id("sg.com.blu.android.uat:id/sign_up_btn"));
		assertTrue(logIn.isDisplayed());
		assertTrue(signup.isDisplayed());
	}
	@Test
	public void tc02_resetPassword()
	{
		System.out.println("tc02_resetPassword");
		extentTest = extentReports.createTest("tc02_resetPassword");
		ms.clickOnSettings();
		ss.clickOnResetpassword();
		WebElement currentPassword=driver.findElement(By.id("sg.com.blu.android.uat:id/current_password_inputField"));
		currentPassword.sendKeys("Admin@123");
		WebElement newPassword=driver.findElement(By.id("sg.com.blu.android.uat:id/new_password_inputField"));
		newPassword.sendKeys("Admin@12345");
		WebElement confirmPassword=driver.findElement(By.id("sg.com.blu.android.uat:id/confirm_password_inputField"));
		confirmPassword.sendKeys("Admin@12345");
		WebElement submitBtn=driver.findElement(By.id("sg.com.blu.android.uat:id/submit_btn"));
		submitBtn.click();
		WebElement backToHome=driver.findElement(By.id("sg.com.blu.android.uat:id/backToHomeButton"));
		assertTrue(backToHome.isDisplayed());
		backToHome.click();
		ms.clickOnSettings();
		ss.clickOnResetpassword();
		currentPassword.sendKeys("Admin@12345");
		newPassword.sendKeys("Admin@123");
		confirmPassword.sendKeys("Admin@123");
		submitBtn.click();
		
	}
	
	@Test
	public void tc03_resetPasswordErrorWhenActorhasNotProvidedAnythingAndClicksOnSubmit()
	{
		System.out.println("tc03_resetPasswordErrorWhenActorhasNotProvidedAnythingAndClicksOnSubmit");
		extentTest = extentReports.createTest("tc03_resetPasswordErrorWhenActorhasNotProvidedAnythingAndClicksOnSubmit");
		ms.clickOnSettings();
		ss.clickOnResetpassword();
		WebElement submitBtn=driver.findElement(By.id("sg.com.blu.android.uat:id/submit_btn"));
		submitBtn.click();
		WebElement currentPasswordError=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/current_password_inputField']//android.widget.TextView"));
		Assert.assertEquals(currentPasswordError.getText(), "Enter current password");
		WebElement newPasswordError=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/new_password_inputField']//android.widget.TextView[@resource-id='sg.com.blu.android.uat:id/error_tv']"));
		Assert.assertEquals(newPasswordError.getText(), "Enter new password");
		
	}
	@Test
	public void tc04_NonMatchingPassword()
	{
		System.out.println("tc04_NonMatchingPassword");
		extentTest = extentReports.createTest("tc04_NonMatchingPassword");
		ms.clickOnSettings();
		ss.clickOnResetpassword();
		WebElement currentPassword=driver.findElement(By.id("sg.com.blu.android.uat:id/current_password_inputField"));
		currentPassword.sendKeys("Admin@123");
		WebElement newPassword=driver.findElement(By.id("sg.com.blu.android.uat:id/new_password_inputField"));
		newPassword.sendKeys("Admin@12345");
		WebElement confirmPassword=driver.findElement(By.id("sg.com.blu.android.uat:id/confirm_password_inputField"));
		confirmPassword.sendKeys("Admin@1234");
		WebElement submitBtn=driver.findElement(By.id("sg.com.blu.android.uat:id/submit_btn"));
		submitBtn.click();
		WebElement confirmPasswordError=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/confirm_password_inputField']//android.widget.TextView"));
		Assert.assertEquals(confirmPasswordError.getText(), "Passwords do not match");
		
	}
	@Test
	public void tc05_Invalidpassword()
	{
		System.out.println("tc05_Invalidpassword");
		extentTest = extentReports.createTest("tc05_Invalidpassword");
		ms.clickOnSettings();
		ss.clickOnResetpassword();
		WebElement currentPassword=driver.findElement(By.id("sg.com.blu.android.uat:id/current_password_inputField"));
		currentPassword.sendKeys("Admin");
		WebElement newPassword=driver.findElement(By.id("sg.com.blu.android.uat:id/new_password_inputField"));
		newPassword.sendKeys("12345");
		WebElement submitBtn=driver.findElement(By.id("sg.com.blu.android.uat:id/submit_btn"));
		submitBtn.click();
		WebElement currentPasswordError=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/current_password_inputField']//android.widget.TextView"));
		Assert.assertEquals(currentPasswordError.getText(), "Password should be 8 or more letters, with at least one uppercase letter, one lowercase letter, and one number or symbol.");
		WebElement newPasswordError=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/new_password_inputField']//android.widget.TextView"));
		Assert.assertEquals(newPasswordError.getText(), "Password should be 8 or more letters, with at least one uppercase letter, one lowercase letter, and one number or symbol.");
		WebElement confirmPasswordError=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/confirm_password_inputField']//android.widget.TextView"));
		Assert.assertEquals(confirmPasswordError.getText(), "Confirm new password.");
		
	}
	@Test
	public void tc06_deactivateAccount()
	{
		System.out.println("tc06_deactivateAccount");
		extentTest = extentReports.createTest("tc06_deactivateAccount");
		ms.clickOnSettings();
		WebElement deactivateAccount=driver.findElement(By.id("sg.com.blu.android.uat:id/deactivate_account_container"));
		List<WebElement> list=driver.findElements(By.className("android.widget.RadioGroup"));
		deactivateAccount.click();
		WebElement reason=driver.findElement(By.xpath("//android.widget.RadioGroup[@resource-id='sg.com.blu.android.uat:id/radioGroup']//android.widget.RelativeLayout"));
		reason.click();
		cf.swipeUp(list);	 
	}
	@AfterMethod
	public void getResult(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.SKIP) {
			System.out.println("Skipped extent report");
			extentTest.skip(MarkupHelper.createLabel(testResult.getName() + " Test Case SKIPPED", ExtentColor.BLUE));
			extentTest.skip(testResult.getThrowable());
			// extentTest.log(Status.SKIP, MarkupHelper.createLabel(testResult.getName() + "
			// - Test Case Skipped", ExtentColor.ORANGE));
		} else if (testResult.getStatus() == ITestResult.FAILURE) {
			extentTest.fail(MarkupHelper.createLabel(testResult.getName() + " Test case FAILED due to below issues:",
					ExtentColor.RED));
			extentTest.fail(testResult.getThrowable());
			String screenshotPath = CommonUtil.takesScreenShotFailed(driver, testResult.getName());
			extentTest.addScreenCaptureFromPath(screenshotPath);
		} else if (testResult.getStatus() == ITestResult.SUCCESS) {
			extentTest.pass(MarkupHelper.createLabel(testResult.getName() + " Test Case PASSED", ExtentColor.GREEN));
			String screenshotPath = CommonUtil.takesScreenShot(driver, testResult.getName());

			extentTest.addScreenCaptureFromPath(screenshotPath);
		}

	}
}
