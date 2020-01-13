package bluAndroid.bluAndroid.TestScripts;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;

import bluAndroid.bluAndroid.pageObjects.LoginScreen;
import bluAndroid.bluAndroid.pageObjects.MapViewScreen;
import bluAndroid.bluAndroid.pageObjects.MenuScreen;
import bluAndroid.bluAndroid.pageObjects.MyDetailsScreen;
import bluAndroid.bluAndroid.pageObjects.PopUp;
import bluAndroid.bluAndroid.pageObjects.SavedLocationsScreen;
import bluAndroid.bluAndroid.pageObjects.SignUpScreen;
import bluAndroid.bluAndroid.util.BaseClass;
import bluAndroid.bluAndroid.util.CommonUtil;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.touch.offset.PointOption;

public class SavedLocationsTestRunner extends BaseClass {
	static AppiumDriver<WebElement> driver;
	LoginScreen ls;
	String mobileNo, password;
	MapViewScreen mvs;
	SavedLocationsScreen sls;
	MenuScreen ms;
	MyDetailsScreen mds;
	PopUp pu;
	SignUpScreen su;
	@BeforeMethod
	public void preCondition() throws IOException

	{
		System.out.println("Set Up....");
		SavedLocationsTestRunner.driver = BaseClass.getAppCapabilities();
		mobileNo = CommonUtil.getPropertyValue("login", "mobileNo");
		password = CommonUtil.getPropertyValue("login", "password");
		ls = new LoginScreen(driver);
		ls.clickLoginLink();
		ls.bluLogin(mobileNo, password);
		mvs=new MapViewScreen(driver);
		sls=new SavedLocationsScreen(driver);
		ms= new MenuScreen(driver);
		mds=new MyDetailsScreen(driver);
		pu=new PopUp(driver);
		su=new SignUpScreen(driver);
	}
	@Test
	public void tc01_saveALocation() throws IOException
	{
		System.out.println("tc01_saveALocation");
		extentTest = extentReports.createTest("tc01_saveALocation");
		mds.clickOnMenu();
		ms.clickOnSavedLocations();
		sls.clickOnSaveALocationLink();
		mvs.clickonSearchBox();
		String bluPort=CommonUtil.getPropertyValue("bluPortDetails", "bluPort");
		mvs.enterInSearchBox().sendKeys(bluPort);
		WebElement bluPortName=driver.findElement(By.id("sg.com.blu.android.uat:id/textViewBluPortName"));
		bluPortName.click();
		Assert.assertTrue(mvs.bluPortPin().isDisplayed());
		Assert.assertTrue(mvs.selectedBluPortName().isDisplayed());
		sls.selectActionBtn();
		System.out.println(sls.listOfSavedAddress().size());
		for (int i = 0; i < sls.listOfSavedAddress().size(); i++) {
			System.out.println(sls.listOfSavedAddress().get(i).getText());
			if(i==sls.listOfSavedAddress().size()-1)
			{
				System.out.println(sls.listOfSavedAddress().get(sls.listOfSavedAddress().size()-1).getText());
				Assert.assertEquals(sls.listOfSavedAddress().get(sls.listOfSavedAddress().size()-1).getText(), bluPort);
			}
		}
		
	}
	@Test(dependsOnMethods = "tc01_saveALocation" )
	public void tc02_removeALocation() throws IOException
	{
		System.out.println("tc02_removeALocation");
		extentTest = extentReports.createTest("tc02_removeALocation()");
		mds.clickOnMenu();
		ms.clickOnSavedLocations();
		sls.listOfhearticons();
		for (int i = 0; i < sls.listOfhearticons().size(); i++) {
				sls.listOfhearticons().get(sls.listOfhearticons().size()-1).click();
		}
		Assert.assertEquals(pu.alertTitle().getText(), "Remove location");
		Assert.assertEquals(pu.alertMsg().getText(), "Are you sure you want to remove this from your saved locations?");
		pu.clickBtn1();
		System.out.println(sls.listOfSavedAddress().size());
		for (int i = 0; i < sls.listOfSavedAddress().size(); i++) {
			String bluPort=CommonUtil.getPropertyValue("bluPortDetails", "bluPort");
			System.out.println(sls.listOfSavedAddress().get(i).getText());
				Assert.assertNotEquals(sls.listOfSavedAddress().get(i).getText(), bluPort);
		}
		
	}
	@Test
	public void tc03_saveABluHomeLocation() throws IOException
	{
		System.out.println("tc03_saveABluHomeLocation");
		extentTest = extentReports.createTest("tc03_saveABluHomeLocation");
		mds.clickOnMenu();
		ms.clickOnSavedLocations();
		sls.clickOnBluHome();
		sls.clickOnSaveALocationLink();
		sls.bluHomeAddress1().sendKeys("357 Admiralty drive");
		sls.bluHomeAddress2().sendKeys("#7 174");
		sls.bluHomePostalCode().sendKeys("750357");
		((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
		sls.clickOnSaveLocationBluHomeBtn();
		System.out.println(sls.listOfSavedAddress().size());
		int n=sls.listOfSavedAddress().size();
		System.out.println(n);
		for (int i = 0; i < sls.listOfSavedAddress().size(); i++) {
			System.out.println(sls.listOfSavedAddress().get(i).getText());
			if(i==sls.listOfhearticons().size()-1)
			{
				System.out.println(sls.listOfSavedAddress().get(sls.listOfhearticons().size()-1).getText());
				Assert.assertEquals(sls.listOfSavedAddress().get(sls.listOfhearticons().size()-1).getText(), "357 Admiralty drive");
				break;
			}
		}
		
	}
	@Test(dependsOnMethods = "tc03_saveABluHomeLocation" )
	public void tc04_removeBluHomeLocation() throws IOException
	{
		System.out.println("tc04_removeBluHomeLocation");
		extentTest = extentReports.createTest("tc04_removeBluHomeLocation");
		mds.clickOnMenu();
		ms.clickOnSavedLocations();
		sls.clickOnBluHome();
		//sls.listOfhearticons();
		for (int i = 0; i < sls.listOfhearticons().size(); i++) {
				sls.listOfhearticons().get(sls.listOfhearticons().size()-1).click();
		}
		Assert.assertEquals(pu.alertTitle().getText(), "Remove location");
		Assert.assertEquals(pu.alertMsg().getText(), "Are you sure you want to remove this from your saved locations?");
		pu.clickBtn1();
		System.out.println(sls.listOfSavedAddress().size());
		for (int i = 0; i < sls.listOfSavedAddress().size(); i++) {
			System.out.println(sls.listOfSavedAddress().get(i).getText());
				Assert.assertNotEquals(sls.listOfSavedAddress().get(i).getText(), "357 Admiralty drive");
		}
		
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
