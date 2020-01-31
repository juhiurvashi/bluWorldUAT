package bluAndroid.bluAndroid.TestScripts;

import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
import bluAndroid.bluAndroid.pageObjects.PopUp;
import bluAndroid.bluAndroid.pageObjects.PublicProfileScreen;
import bluAndroid.bluAndroid.pageObjects.SignUpScreen;
import bluAndroid.bluAndroid.util.BaseClass;
import bluAndroid.bluAndroid.util.CommonUtil;
import io.appium.java_client.AppiumDriver;

public class PublicProfileTestRunner extends BaseClass {
	static AppiumDriver<WebElement> driver;
	LoginScreen ls;
	String mobileNo, password;
	MyDetailsScreen mds;
	MenuScreen ms;
	PublicProfileScreen pp;
	PopUp pu;
	CommonFunctions cf;
	String mobilefromPropertiesValue,bluIDfromPropertiesValue,bluID;
	@BeforeMethod
	public void preCondition() throws IOException
	{
		System.out.println("Set Up...."); 
		PublicProfileTestRunner.driver = BaseClass.getAppCapabilities();
		mobileNo = CommonUtil.getPropertyValue("login", "mobileNo");
		password = CommonUtil.getPropertyValue("login", "password");
		mobilefromPropertiesValue=CommonUtil.getPropertyValue("publicProfile", "mobile");
		bluIDfromPropertiesValue=CommonUtil.getPropertyValue("publicProfile", "bluId");
		bluID=CommonUtil.getPropertyValue("login", "bluId");
		ls = new LoginScreen(driver);
		ls.clickLoginLink();
		ls.bluLogin(mobileNo, password);
		mds=new MyDetailsScreen(driver);
		ms=new MenuScreen(driver);
		pp=new PublicProfileScreen(driver);
		pu=new PopUp(driver);
		cf=new CommonFunctions(driver);
	}
	@Test
	public void tc01_ViewPublicProfile()
	{
		System.out.println("tc01_ViewPublicProfile");
		extentTest = extentReports.createTest("tc01_ViewPublicProfile()");
		mds.clickOnMenu();
		ms.clickOnFirstName();
		WebElement title=driver.findElement(By.id("sg.com.blu.android.uat:id/title_tv"));
		Assert.assertEquals(title.getText(), "Public profile","Public profile title displayed");
		WebElement firstName=driver.findElement(By.id("sg.com.blu.android.uat:id/first_name_tv"));
		Assert.assertEquals(firstName.getText(), "Urvashi", "FirstName displayed");
		WebElement bluId=driver.findElement(By.id("sg.com.blu.android.uat:id/blu_id_tv"));
		Assert.assertEquals(bluId.getText(), "BLU147735", "bluId displayed");
		List<WebElement> list=driver.findElements(By.xpath("//android.widget.LinearLayout[2]//android.widget.TextView"));
		assertTrue(list.size()>0) ;

	    System.out.println(list.size());
	    
	    for(int i=0;i<list.size();i++)
	    { String[] seenText = new String[]{"a","b","c"};
	    	if (list.get(i).getText()!= null) {
	        seenText[i] = list.get(i).getText();
	        System.out.println(seenText[i]);
	        
	    }
		//for(int j=0;i<seenText.length;i++)
		if(i==0)
	    Assert.assertEquals(seenText[0], "Parcels sent", "'Parcel sent' text displayed");
		if(i==1)
        Assert.assertEquals(seenText[1], "Parcels collected", "'Parcel collected' text displayed");
		if(i==2)
        Assert.assertEquals(seenText[2], "Friends referred", "'Friends referred' text displayed");
		}
	}
	@Test
	public void tc02_viewFriendProfileByBluID() throws IOException
	{
		System.out.println("tc02_viewFriendProfileByBluID");
		extentTest = extentReports.createTest("tc02_viewFriendProfileByBluID()");
		mds.clickOnMenu();
		ms.clickOnFirstName();
		//WebElement bluId=driver.findElement(By.id("sg.com.blu.android.uat:id/input_text_et"));
		String bluIDfromPropertiesValue=CommonUtil.getPropertyValue("publicProfile", "bluId");
		pp.textField().sendKeys(bluIDfromPropertiesValue);
		Assert.assertEquals(pp.getTitle().getText(), "Public profile","Public profile title displayed");
		pp.getFriendName();
		String friendName=CommonUtil.getPropertyValue("publicProfile", "friendName");
		Assert.assertEquals(pp.getFriendName().getText(), friendName, "FirstName displayed");
		WebElement bluId1=driver.findElement(By.id("sg.com.blu.android.uat:id/bluIdTextView"));
		Assert.assertEquals(bluId1.getText(), "BLU136621", "bluId displayed");
		List<WebElement> list=driver.findElements(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/content']//android.widget.LinearLayout[1]//android.widget.TextView[1]"));
		assertTrue(list.size()>0) ;

	    //System.out.println(list.size());
	    
	    for(int i=0;i<list.size();i++)
	    { String[] seenText = new String[]{"a","b","c"};
	    	if (list.get(i).getText()!= null) {
	        seenText[i] = list.get(i).getText();
	        //System.out.println(seenText[i]);
	        
	    }
		//for(int j=0;i<seenText.length;i++)
		if(i==0)
	    Assert.assertEquals(seenText[0], "Parcels sent", "'Parcels sent' text displayed");
		if(i==1)
        Assert.assertEquals(seenText[1], "Parcels collected", "'Parcels collected' text displayed");
		if(i==2)
        Assert.assertEquals(seenText[2], "Friends referred", "'Friends referred' text displayed");
		}
	    WebElement blockfutureRequest=driver.findElement(By.id("sg.com.blu.android.uat:id/actionTextView"));
	    Assert.assertTrue(blockfutureRequest.isDisplayed());
	}
	@Test
	public void tc03_viewFriendProfileByMobile() throws IOException
	{
		System.out.println("tc03_viewFriendProfileByMobile");
		extentTest = extentReports.createTest("tc03_viewFriendProfileByMobile()");
		mds.clickOnMenu();
		ms.clickOnFirstName();
		//WebElement mobile=driver.findElement(By.id("sg.com.blu.android.uat:id/input_text_et"));
		pp.clickOnMobile();
		
		if(pp.permissionAllowBtn().isDisplayed())
		{
			pp.permissionAllowBtn().click();
		}
		
		pp.textField().sendKeys(mobilefromPropertiesValue);
		WebElement title=driver.findElement(By.id("sg.com.blu.android.uat:id/titleTextView"));
		Assert.assertEquals(title.getText(), "Public profile","Public profile title displayed");
		WebElement firstName=driver.findElement(By.id("sg.com.blu.android.uat:id/firstNameTextView"));
		Assert.assertEquals(firstName.getText(), "Hari", "FirstName displayed");
		WebElement bluId1=driver.findElement(By.id("sg.com.blu.android.uat:id/bluIdTextView"));
		Assert.assertEquals(bluId1.getText(), "BLU136621", "bluId displayed");
		List<WebElement> list=driver.findElements(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/content']//android.widget.LinearLayout[1]//android.widget.TextView[1]"));
		assertTrue(list.size()>0) ;

	    //System.out.println(list.size());
	    
	    for(int i=0;i<list.size();i++)
	    { String[] seenText = new String[]{"a","b","c"};
	    	if (list.get(i).getText()!= null) {
	        seenText[i] = list.get(i).getText();
	      //  System.out.println(seenText[i]);
	        
	    }
		//for(int j=0;i<seenText.length;i++)
		if(i==0)
	    Assert.assertEquals(seenText[0], "Parcels sent", "'Parcels sent' text displayed");
		if(i==1)
        Assert.assertEquals(seenText[1], "Parcels collected", "'Parcels collected' text displayed");
		if(i==2)
        Assert.assertEquals(seenText[2], "Friends referred", "'Friends referred' text displayed");
		}
	    WebElement blockfutureRequest=driver.findElement(By.id("sg.com.blu.android.uat:id/actionTextView"));
	    Assert.assertTrue(blockfutureRequest.isDisplayed());
	}
	@Test
	public void tc04_InvaldBluId()
	{
		System.out.println("tc04_InvaldBluId");
		extentTest = extentReports.createTest("tc04_InvaldBluId()");
		mds.clickOnMenu();
		ms.clickOnFirstName();
		pp.textField().sendKeys("BLU123456");
		WebElement error=driver.findElement(By.id("sg.com.blu.android.uat:id/error_tv"));
		Assert.assertEquals(error.getText(), "Member not found.");
	}
	@Test
	public void tc05_InvaldMobile()
	{
		System.out.println("tc05_InvaldMobile");
		extentTest = extentReports.createTest("tc05_InvaldMobile()");
		mds.clickOnMenu();
		ms.clickOnFirstName();
		pp.clickOnMobile();
		WebElement allow=driver.findElement(By.id("com.android.packageinstaller:id/permission_allow_button"));
		if(allow.isDisplayed())
		{
			allow.click();
		}
		//WebElement mobileTextField=driver.findElement(By.id("sg.com.blu.android.uat:id/input_text_et"));
		pp.textField().sendKeys("91277777");
		WebElement error=driver.findElement(By.id("sg.com.blu.android.uat:id/error_tv"));
		Assert.assertEquals(error.getText(), "Member not found.");
	}
	@Test
	public void tc06_blockMember() throws IOException
	{
		System.out.println("tc06_blockMember");
		extentTest = extentReports.createTest("tc06_blockMember");
		mds.clickOnMenu();
		ms.clickOnFirstName();
		//WebElement bluId=driver.findElement(By.id("sg.com.blu.android.uat:id/input_text_et"));
		String bluIDfromPropertiesValue=CommonUtil.getPropertyValue("publicProfile", "bluId");
		pp.textField().sendKeys(bluIDfromPropertiesValue);
		WebElement blockFutureRequest=driver.findElement(By.id("sg.com.blu.android.uat:id/actionTextView"));
		blockFutureRequest.click();
		Assert.assertEquals(pp.blockAlertTitle().getText(), "Block future requests");
		Assert.assertEquals(pp.blockAlertMsg().getText(), "This member will not be able to send you future requests.");
		WebElement blockBtn=driver.findElement(By.id("android:id/button1"));
		blockBtn.click();
		//Assert.assertEquals(blockFutureRequest.getText(), "Block future requests");//
		//System.out.println(blkFutureRequest.getText());
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 300);
		 * wait.until(ExpectedConditions.elementToBeClickable(pp.getFriendName()));
		 * Assert.assertTrue(pp.getFriendName().isDisplayed());
		 */
	}
	@Test(dependsOnMethods = {"tc06_blockMember"})
	public void tc07_unblockMember() throws IOException
	{
		System.out.println("tc07_unblockMember");
		extentTest = extentReports.createTest("tc07_unblockMember");
		mds.clickOnMenu();
		ms.clickOnFirstName();
		pp.textField().sendKeys(bluIDfromPropertiesValue);
		WebElement unblockFutureRequest=driver.findElement(By.id("sg.com.blu.android.uat:id/actionTextView"));
		unblockFutureRequest.click();
		Assert.assertEquals(pp.blockAlertTitle().getText(), "Unblock future requests");
		Assert.assertEquals(pp.blockAlertMsg().getText(), "You will be able to receive future requests from this user.");
		WebElement unblockBtn=driver.findElement(By.id("android:id/button1"));
		unblockBtn.click();
		//Assert.assertEquals(unblockFutureRequest.getText(), "Block future requests");
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 300);
		 * wait.until(ExpectedConditions.elementToBeClickable(pp.getFriendName()));
		 * Assert.assertTrue(pp.getFriendName().isDisplayed());
		 */
	}
	//@Test
	public void tc08_blockedMemberError() throws IOException
	{
		System.out.println("tc08_blockedMemberError");
		extentTest = extentReports.createTest("tc08_blockedMemberError");
		mds.clickOnMenu();
		ms.clickOnFirstName();
		//WebElement bluId=driver.findElement(By.id("sg.com.blu.android.uat:id/input_text_et"));
		String bluIDfromPropertiesValue=CommonUtil.getPropertyValue("publicProfile", "bluId");
		pp.textField().sendKeys(bluIDfromPropertiesValue);
		WebElement blockFutureRequest=driver.findElement(By.id("sg.com.blu.android.uat:id/actionTextView"));
		blockFutureRequest.click();
		Assert.assertEquals(pp.blockAlertTitle().getText(), "Block future requests");
		Assert.assertEquals(pp.blockAlertMsg().getText(), "This member will not be able to send you future requests.");
		WebElement blockBtn=driver.findElement(By.id("android:id/button1"));
		blockBtn.click();
		pp.clickOnCloseBtn();
		mds.clickOnMenu();
		ms.logout();
		ls.clickLoginLink();
		ls.bluLogin(mobilefromPropertiesValue, password);
		mds.clickOnMenu();
		ms.clickOnFirstName();
		pp.textField().sendKeys(bluID);
		WebElement error=driver.findElement(By.id("sg.com.blu.android.uat:id/error_tv"));
		Assert.assertEquals(error.getText(), "Member not found.");
		pp.clickOnBackButton();
		ms.logout();
		ls.clickLoginLink();
		ls.bluLogin(mobileNo, password);
		pp.textField().sendKeys(bluIDfromPropertiesValue);
		WebElement unblockFutureRequest=driver.findElement(By.id("sg.com.blu.android.uat:id/actionTextView"));
		unblockFutureRequest.click();
		Assert.assertEquals(pp.blockAlertTitle().getText(), "Unblock future requests");
		Assert.assertEquals(pp.blockAlertMsg().getText(), "You will be able to receive future requests from this user.");
		WebElement unblockBtn=driver.findElement(By.id("android:id/button1"));
		unblockBtn.click();
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
