package bluAndroid.bluAndroid.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class Wallet {
	private static AppiumDriver<WebElement> driver;
		public Wallet()
		{
			
		}
		public Wallet(AppiumDriver<WebElement> driver)
		{
			this.driver=driver;
		}
		public void clickOnWalletIcon()
		{
			WebElement wallet= driver.findElement(By.id("sg.com.blu.android.uat:id/walletButton"));
			wallet.click();
		}
		public WebElement topUpBtn()
		{
			WebElement topUpBtn= driver.findElement(By.id("sg.com.blu.android.uat:id/topUpWalletButtonContainer"));
			return topUpBtn;
		}
		
		public void clickOnTopUp()
		{
			WebElement topUp= driver.findElement(By.id("sg.com.blu.android.uat:id/topUpWalletButtonContainer"));
			topUp.click();
		}
		public void clickOnPaymentMethods()
		{
			WebElement paymentMethods= driver.findElement(By.id("sg.com.blu.android.uat:id/paymentMethodsButtonContainer"));
			paymentMethods.click();
		}
		public WebElement checkAddPaymentMethodBtn()
		{
			WebElement paymentMethods= driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/addPaymentMethodContainer']//android.widget.TextView"));
			return paymentMethods;
		}
		public WebElement recentActivity()
		{
			WebElement recentActivity= driver.findElement(By.id("sg.com.blu.android.uat:id/recentActivityTextView"));
			return recentActivity;
		}
		public void clickOnSeeMore()
		{
			WebElement seeMore= driver.findElement(By.id("sg.com.blu.android.uat:id/seeMoreButton"));
			seeMore.click();
		}
		public WebElement clickOnSearchBox()
		{
			WebElement searchBox= driver.findElement(By.id("sg.com.blu.android.uat:id/searchEditText"));
			searchBox.click();
			return searchBox;
		}
		public void clickOnTransactions()
		{
			WebElement a=driver.findElement(By.id("android.widget.RelativeLayout[1]"));
			a.click();
		}
		public void transactionDetails()
		{
			WebElement list=driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='sg.com.blu.android.uat:id/paymentHistoryRecylerView']//android.widget.RelativeLayout[1]"));
			list.click();
		/*
		 * List<WebElement>
		 * list=driver.findElements(By.id("//android.widget.LinearLayout")); for(int
		 * i=0;i<list.size();i++) { System.out.println(list.get(i).getText()); }
		 */
		}
		public void selectValues()
		{
			WebElement topUpvalue=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/topUpVolumeContainer']//android.widget.Button[1]"));
			topUpvalue.click();
		}
		public WebElement topUpSibling()
		{
			WebElement topUpvalue=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/topUpVolumeContainer']/following-sibling::*//android.widget.TextView"));
			return topUpvalue;
		}
		public WebElement topUpSibling1()
		{
			WebElement topUpvalue=driver.findElement(By.xpath("//android.widget.LinearLayout[@resource-id='sg.com.blu.android.uat:id/topUpVolumeContainer']/following-sibling::*//android.widget.TextView"));
			return topUpvalue;
		}
		public void clickOnSubmit()
		{
			WebElement submit=driver.findElement(By.id("sg.com.blu.android.uat:id/submitButton"));
			submit.click();
		}
		public void clickOnSendReceipt()
		{
			WebElement receipt=driver.findElement(By.id("sg.com.blu.android.uat:id/resendReceiptButton"));
			receipt.click();
		}
		public WebElement sendReceiptMsg()
		{
			WebElement msg=driver.findElement(By.id("android:id/message"));
			return msg;
		}
		public void clickOnOK()
		{
			WebElement ok=driver.findElement(By.id("android:id/button1"));
			ok.click();
		}
		public WebElement checkPrefferedMethod()
		{
			WebElement preferredMethod=driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='sg.com.blu.android.uat:id/paymentMethodsRecyclerView']//android.widget.LinearLayout"));
			return preferredMethod;
		}
		public void selectPreferredMethod()
		{
			WebElement preferredMethod=driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='sg.com.blu.android.uat:id/paymentMethodsRecyclerView']//android.widget.LinearLayout"));
			preferredMethod.click();
		}
		public WebElement topUpSuccessMsg()
		{
			WebElement msg=driver.findElement(By.xpath("//android.widget.ScrollView[@resource-id='sg.com.blu.android.uat:id/nestedScroll']//android.widget.LinearLayout//android.widget.TextView[1]"));
			return msg;
		}
		public WebElement cardTextView()
		{
			WebElement card=driver.findElement(By.id("sg.com.blu.android.uat:id/cardTextView"));
			return card;
		}
		public WebElement cardImageView()
		{
			WebElement card=driver.findElement(By.id("sg.com.blu.android.uat:id/cardBrandImageView"));
			return card;
		}
		public WebElement tickImageView()
		{
			WebElement msg=driver.findElement(By.xpath("//android.widget.ScrollView[@resource-id='sg.com.blu.android.uat:id/nestedScroll']//android.widget.LinearLayout//android.widget.ImageView"));
			return msg;
		}
		public WebElement topUpAmount()
		{
			WebElement amt=driver.findElement(By.id("sg.com.blu.android.uat:id/amountTextView"));
			return amt;
		}
		public WebElement dateTime()
		{
			WebElement date=driver.findElement(By.id("sg.com.blu.android.uat:id/dateTimeTextView"));
			return date;
		}
		public void clickOnclose()
		{
			WebElement close=driver.findElement(By.id("sg.com.blu.android.uat:id/closeButton"));
			close.click();
		}
		public WebElement walletAmount()
		{
			WebElement date=driver.findElement(By.id("sg.com.blu.android.uat:id/walletBalanceTextView"));
			return date;
		}
		public void clickOnRemovePaymentMethod()
		{
			WebElement remove=driver.findElement(By.xpath("//androidx.recyclerview.widget.RecyclerView[@resource-id='sg.com.blu.android.uat:id/paymentMethodsRecyclerView']//android.widget.LinearLayout[1]//android.widget.ImageButton[@resource-id='sg.com.blu.android.uat:id/deleteCardButton']"));
			remove.click();
		}
		public void clickOnDeleteCardButton()
		{
			WebElement delete=driver.findElement(By.id("sg.com.blu.android.uat:id/deleteCardButton"));
			delete.click();
		}
		public WebElement removePaymentmethodTitle()
		{
			WebElement title=driver.findElement(By.id("sg.com.blu.android.uat:id/title_tv"));
			return title;
		}
		public void clickOnAddPaymentMethod()
		{
			WebElement add=driver.findElement(By.id("sg.com.blu.android.uat:id/addPaymentMethodContainer"));
			add.click();
		}
		public WebElement enterCardNumber()
		{
			WebElement no=driver.findElement(By.id("sg.com.blu.android.uat:id/et_card_number"));
			return no;
		}
		public WebElement enterExpiry()
		{
			WebElement expiry=driver.findElement(By.id("sg.com.blu.android.uat:id/et_expiry_date"));
			return expiry;
		}
		public WebElement enterCVC()
		{
			WebElement cvc=driver.findElement(By.id("sg.com.blu.android.uat:id/et_cvc_number"));
			return cvc;
		}
		public WebElement cardTextView1()
		{
			WebElement card=driver.findElement(By.id("sg.com.blu.android.uat:id/cardNumberTextView"));
			return card;
		}
		
		public void clickOnConfirm()
		{
			WebElement confirm=driver.findElement(By.id("sg.com.blu.android.uat:id/submitButton"));
			Assert.assertTrue(confirm.isDisplayed());
			confirm.click();
		}
		public void addCardDetails()
		{
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_4));
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_2));
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_4));
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_2));
			
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_4));
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_2));
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_4));
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_2));
			
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_4));
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_2));
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_4));
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_2));
			
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_4));
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_2));
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_4));
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_2));
			
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_0));
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_4));
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_2));
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_0));
			
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_1));
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_2));
			((AndroidDriver<WebElement>) driver).pressKey(new KeyEvent(AndroidKey.DIGIT_3));
			
		}
}
