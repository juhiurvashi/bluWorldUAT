package bluAndroid.bluAndroid.TestScripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import bluAndroid.bluAndroid.pageObjects.ExploreLocationsScreen;
import bluAndroid.bluAndroid.pageObjects.LoginScreen;
import bluAndroid.bluAndroid.pageObjects.MapViewScreen;
import bluAndroid.bluAndroid.util.BaseClass;
import bluAndroid.bluAndroid.util.CommonUtil;
import io.appium.java_client.AppiumDriver;

public class ExploreLocationsTestRunner extends BaseClass {
	static AppiumDriver<WebElement> driver;
	LoginScreen ls;
	String mobileNo, password;
	MapViewScreen mvs;
	ExploreLocationsScreen els;;
	@BeforeMethod
	public void preCondition() throws IOException

	{
		System.out.println("Set Up....");
		ExploreLocationsTestRunner.driver = BaseClass.getAppCapabilities();
		mobileNo = CommonUtil.getPropertyValue("login", "mobileNo");
		password = CommonUtil.getPropertyValue("login", "password");
		ls = new LoginScreen(driver);
		ls.clickLoginLink();
		ls.bluLogin(mobileNo, password);
		mvs=new MapViewScreen(driver);
		els=new ExploreLocationsScreen(driver);
	}
	//@Test
	public void tc01_enterBluPortNameOnSearchBox() throws IOException
	{
		System.out.println("tc01_enterBluPortNameOnSearchBox");
		extentTest = extentReports.createTest("tc01_enterBluPortNameOnSearchBox");
		els.clickOnExploreLocations();
		mvs.clickonSearchBox();
		String bluPort=CommonUtil.getPropertyValue("bluPortDetails", "bluPort");
		mvs.enterInSearchBox().sendKeys(bluPort);
		WebElement bluPortName=driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
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
