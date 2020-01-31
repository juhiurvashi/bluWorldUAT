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

import bluAndroid.bluAndroid.pageObjects.LoginScreen;
import bluAndroid.bluAndroid.pageObjects.MyDetailsScreen;
import bluAndroid.bluAndroid.pageObjects.SignUpScreen;
import bluAndroid.bluAndroid.util.BaseClass;
import bluAndroid.bluAndroid.util.CommonUtil;
import io.appium.java_client.AppiumDriver;

public class BluMyDetailsTestRunner extends BaseClass {
	static AppiumDriver<WebElement> driver;
	LoginScreen ls;
	String mobileNo, password;
	MyDetailsScreen mds;
	SignUpScreen su;
	@BeforeMethod
	public void preCondition() throws IOException

	{
		System.out.println("Set Up....");
		BluMyDetailsTestRunner.driver = BaseClass.getAppCapabilities();
		mobileNo = CommonUtil.getPropertyValue("login", "mobileNo");
		password = CommonUtil.getPropertyValue("login", "password");
		ls = new LoginScreen(driver);
		ls.clickLoginLink();
		ls.bluLogin(mobileNo, password);
		mds=new MyDetailsScreen(driver);
		su=new SignUpScreen(driver);
	}

	public void displayText()
	{

	    System.out.println("i will display all the text and each of their languages");

	    // I find all the elements on the page as such
	    List<WebElement> list = driver.findElements(By.xpath("//*"));


	    assertTrue(list.size()>0) ;

	    System.out.println(list.size());

	    for(int i=0;i<list.size();i++)
	    { if (list.get(i).getText()!= null) {
	        String SeenText = list.get(i).getText();
	        System.out.println(SeenText);

	    }}

	}
	@Test
	public void tc01_editFirstName() throws IOException {
		System.out.println("tc01_editFirstName");
		extentTest = extentReports.createTest("tc01_editFirstName()");
		mds.clickOnMyDetails();
		mds.clickOneditIconFirstName();
		WebElement firstNameEdit=driver.findElement(By.id("sg.com.blu.android.uat:id/input_text_et"));
		firstNameEdit.sendKeys("Hari");
		WebElement selectTick=driver.findElement(By.id("sg.com.blu.android.uat:id/tick_btn"));
		selectTick.click();
		WebElement displayFirstname=driver.findElement(By.id("sg.com.blu.android.uat:id/display_tv"));
		System.out.println(displayFirstname.getText());
		Assert.assertEquals(displayFirstname.getText(), "Hari");
		mds.clickOneditIconFirstName();
		firstNameEdit.sendKeys("Urvashi");
		selectTick.click();
		System.out.println(displayFirstname.getText());
	}
	@Test
	public void tc02_UnableToEditFirstName() throws IOException {
		System.out.println("tc02_UnableToEditFirstName");
		extentTest = extentReports.createTest("tc02_UnableToEditFirstName()");
		mds.clickOnMyDetails();
		mds.clickOneditIconFirstName();
		WebElement firstNameEdit=driver.findElement(By.id("sg.com.blu.android.uat:id/input_text_et"));
		firstNameEdit.sendKeys("Hari@");
		WebElement selectTick=driver.findElement(By.id("sg.com.blu.android.uat:id/tick_btn"));
		selectTick.click();
		//WebElement invalidFirstNameError=driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='sg.com.blu.android.uat:id/first_name_profileDisplayField']//android.widget.TextView[@resource-id='sg.com.blu.android.uat:id/error_tv']"));
		WebElement invalidFirstNameError=driver.findElement(By.id("sg.com.blu.android.uat:id/error_tv"));
		System.out.println(invalidFirstNameError.getText());
		Assert.assertEquals(invalidFirstNameError.getText(), "Only alphabets and spaces allowed.");
		//mds.clickOneditIconFirstName();
		firstNameEdit.sendKeys("Urvashi");
		selectTick.click();
	}
	@Test
	public void tc03_editLastName() throws IOException {
		System.out.println("tc03_editLastName");
		extentTest = extentReports.createTest("tc03_editLastName()");
		mds.clickOnMyDetails();
		mds.clickOneditIconLastName();
		WebElement lastNameEdit=driver.findElement(By.id("sg.com.blu.android.uat:id/input_text_et"));
		lastNameEdit.sendKeys("Lee");
		WebElement selectTick=driver.findElement(By.id("sg.com.blu.android.uat:id/tick_btn"));
		selectTick.click();
		WebElement displayLastname=driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='sg.com.blu.android.uat:id/last_name_profileDisplayField']//android.widget.TextView[@resource-id='sg.com.blu.android.uat:id/display_tv']"));
		System.out.println(displayLastname.getText());
		Assert.assertEquals(displayLastname.getText(), "Lee");
		mds.clickOneditIconLastName();
		lastNameEdit.sendKeys("Gupta");
		selectTick.click();
	}
	@Test
	public void tc04_unableToEditLastName() throws IOException {
		System.out.println("tc04_unableToEditLastName");
		extentTest = extentReports.createTest("tc04_unableToEditLastName");
		mds.clickOnMyDetails();
		mds.clickOneditIconLastName();
		WebElement lastNameEdit=driver.findElement(By.id("sg.com.blu.android.uat:id/input_text_et"));
		lastNameEdit.sendKeys("Lee@");
		WebElement selectTick=driver.findElement(By.id("sg.com.blu.android.uat:id/tick_btn"));
		selectTick.click();
		WebElement invalidLastNameError=driver.findElement(By.id("sg.com.blu.android.uat:id/error_tv"));
		System.out.println(invalidLastNameError.getText());
		Assert.assertEquals(invalidLastNameError.getText(), "Only alphabets and spaces allowed.");
		//mds.clickOneditIconFirstName();
		lastNameEdit.sendKeys("Gupta");
		selectTick.click();
		
	}
	@Test
	public void tc05_editEmail() throws IOException {
		System.out.println("tc05_editEmail");
		extentTest = extentReports.createTest("tc05_editEmail()");
		mds.clickOnMyDetails();
		mds.clickOneditIconEmail();
		WebElement emailEdit=driver.findElement(By.id("sg.com.blu.android.uat:id/input_text_et"));
		emailEdit.sendKeys("test@test.com");
		WebElement selectTick=driver.findElement(By.id("sg.com.blu.android.uat:id/tick_btn"));
		selectTick.click();
		WebElement displayEmail=driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='sg.com.blu.android.uat:id/email_profileDisplayField']//android.widget.TextView[@resource-id='sg.com.blu.android.uat:id/display_tv']"));
		System.out.println(displayEmail.getText());
		Assert.assertEquals(displayEmail.getText(), "test@test.com");
		mds.clickOneditIconEmail();
		emailEdit.sendKeys("urvashi@blu.com.sg");
		selectTick.click();
	}
	@Test
	public void tc06_editEmailAlreadyExists() throws IOException {
		System.out.println("tc06_editEmailAlreadyExists");
		extentTest = extentReports.createTest("tc06_editEmailAlreadyExists()");
		mds.clickOnMyDetails();
		mds.clickOneditIconEmail();
		WebElement emailEdit=driver.findElement(By.id("sg.com.blu.android.uat:id/input_text_et"));
		emailEdit.sendKeys("juhiurvashi@gmail.com");
		WebElement selectTick=driver.findElement(By.id("sg.com.blu.android.uat:id/tick_btn"));
		selectTick.click();
		WebElement displayMsg=driver.findElement(By.id("android:id/message"));
		System.out.println(displayMsg.getText());
		Assert.assertEquals(displayMsg.getText(), "Email address already used.");
		driver.findElement(By.id("android:id/button1")).click();
		WebElement displayEmail=driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='sg.com.blu.android.uat:id/email_profileDisplayField']//android.widget.TextView[@resource-id='sg.com.blu.android.uat:id/display_tv']"));
		Assert.assertEquals(displayEmail.getText(), "urvashi@blu.com.sg");
	}
	@Test
	public void tc07_editMobileAlreadyExists() throws IOException {
		System.out.println("tc07_editMobileAlreadyExists");
		extentTest = extentReports.createTest("tc07_editMobileAlreadyExists()");
		mds.clickOnMyDetails();
		System.out.println("");
		mds.clickOneditIconMobile();
		WebElement mobileEdit=driver.findElement(By.id("sg.com.blu.android.uat:id/input_mobile_number_et"));
		mobileEdit.sendKeys("98582028");
		WebElement selectTick=driver.findElement(By.id("sg.com.blu.android.uat:id/tick_btn"));
		selectTick.click();
		WebElement displayMsg=driver.findElement(By.id("android:id/message"));
		System.out.println(displayMsg.getText());
		Assert.assertEquals(displayMsg.getText(), "Mobile Number already used.");
		driver.findElement(By.id("android:id/button1")).click();
		WebElement displayMobile=driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='sg.com.blu.android.uat:id/mobile_number_profileDisplayField']//android.widget.TextView[@resource-id='sg.com.blu.android.uat:id/display_tv']"));
		Assert.assertEquals(displayMobile.getText(), "+65 94561718");
	}
	//@Test
	public void tc06_editDOB() throws IOException {
		System.out.println("tc05_editDOB");
		extentTest = extentReports.createTest("tc05_editDOB()");
		mds.clickOnMyDetails();
		mds.clickOneditIconDOB();
		WebElement dobEdit=driver.findElement(By.id("sg.com.blu.android.uat:id/input_date_et"));
		dobEdit.click();
		mds.selectDOB();
		WebElement selectTick=driver.findElement(By.id("sg.com.blu.android.uat:id/tick_btn"));
		selectTick.click();
		WebElement displayDOB=driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='sg.com.blu.android.uat:id/dob_profileDisplayField']//android.widget.TextView[@resource-id='sg.com.blu.android.uat:id/display_tv']"));
		System.out.println(displayDOB.getText());
		Assert.assertEquals(displayDOB.getText(), "Date of birth: 10 Mar 2000");
		mds.clickOneditIconDOB();
		dobEdit.click();
		su.selectDOB();
	}
	@Test
	public void tc07_editGender() throws IOException {
		System.out.println("tc07_editGender");
		extentTest = extentReports.createTest("tc07_editGender()");
		mds.clickOnMyDetails();
		mds.clickOneditIconGender();
		WebElement male=driver.findElement(By.id("sg.com.blu.android.uat:id/male_rb"));
		WebElement female=driver.findElement(By.id("sg.com.blu.android.uat:id/female_rb"));
		male.click();
		WebElement selectTick=driver.findElement(By.id("sg.com.blu.android.uat:id/tick_btn"));
		selectTick.click();
		WebElement displayGender=driver.findElement(By.xpath("//android.widget.FrameLayout[@resource-id='sg.com.blu.android.uat:id/gender_profileDisplayField']//android.widget.TextView[@resource-id='sg.com.blu.android.uat:id/display_tv']"));
		System.out.println(displayGender.getText());
		Assert.assertEquals(displayGender.getText(), "Male");
		mds.clickOneditIconGender();
		female.click();
		selectTick.click();
	}
	@Test
	public void tc08_referAFriend() throws IOException {
		System.out.println("tc08_referAFriend");
		extentTest = extentReports.createTest("tc08_referAFriend()");
		mds.clickOnMyDetails();
		mds.referAFriend().click();
		WebElement referAFriend=driver.findElement(By.id("sg.com.blu.android.uat:id/title_tv"));
		WebElement bluID=driver.findElement(By.id("sg.com.blu.android.uat:id/promoCodeTextView"));
		Assert.assertEquals(referAFriend.getText(), "Refer a friend");
		Assert.assertEquals(bluID.getText(), "BLU147735");
		WebElement shareCode=driver.findElement(By.id("sg.com.blu.android.uat:id/sharePromoCodeButton"));
		shareCode.click();
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
