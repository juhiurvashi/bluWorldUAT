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

import bluAndroid.bluAndroid.pageObjects.LoginScreen;
import bluAndroid.bluAndroid.pageObjects.MapViewScreen;
import bluAndroid.bluAndroid.pageObjects.MyDetailsScreen;
import bluAndroid.bluAndroid.pageObjects.Wallet;
import bluAndroid.bluAndroid.util.BaseClass;
import bluAndroid.bluAndroid.util.CommonUtil;
import io.appium.java_client.AppiumDriver;

public class CheckOutWithBluTestRunner extends BaseClass {
	static AppiumDriver<WebElement> driver;
	LoginScreen ls;
	String mobileNo, password;
	MapViewScreen mvs;
	@BeforeMethod
	public void preCondition() throws IOException

	{
		System.out.println("Set Up....");
		CheckOutWithBluTestRunner.driver = BaseClass.getAppCapabilities();
		mobileNo = CommonUtil.getPropertyValue("login", "mobileNo");
		password = CommonUtil.getPropertyValue("login", "password");
		ls = new LoginScreen(driver);
		ls.clickLoginLink();
		ls.bluLogin(mobileNo, password);
		mvs=new MapViewScreen(driver);
	}
	@Test
	public void tc_checkOutWithBlu() throws IOException
	{
		System.out.println("tc_checkOutWithBlu");
		extentTest = extentReports.createTest("tc_checkOutWithBlu()");
		WebElement checkOutWithblu=driver.findElement(By.id("sg.com.blu.android.uat:id/checkoutParcelButton"));
		checkOutWithblu.click();
		WebElement seePrices=driver.findElement(By.id("sg.com.blu.android.uat:id/seePricesButton"));
		seePrices.click();
		WebElement seePricesCloseBtn=driver.findElement(By.id("sg.com.blu.android.uat:id/close_btn"));
		seePricesCloseBtn.click();
		WebElement nextBtn=driver.findElement(By.id("sg.com.blu.android.uat:id/next_btn"));
		nextBtn.click();
		mvs.clickonSearchBox();
		String bluPort=CommonUtil.getPropertyValue("bluPortDetails", "bluPort");
		mvs.enterInSearchBox().sendKeys(bluPort);
		WebElement bluPortName=driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		WebElement generateAddress=driver.findElement(By.id("sg.com.blu.android.uat:id/action_btn"));
		generateAddress.click();
		WebElement title=driver.findElement(By.id("sg.com.blu.android.uat:id/title_tv"));
		Assert.assertTrue(title.isDisplayed());
		WebElement address=driver.findElement(By.xpath("//android.widget.RelativeLayout[@resource-id='sg.com.blu.android.uat:id/addressToCopyTextView'][1]//android.widget.TextView"));
		System.out.println(address.getText());
		String addressFromPropertiesFile=CommonUtil.getPropertyValue("bluPortDetails", "address");
		Assert.assertEquals(address.getText(), addressFromPropertiesFile);
		WebElement copyAll=driver.findElement(By.id("sg.com.blu.android.uat:id/copyAllButton"));
		copyAll.click();
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
