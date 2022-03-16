package TestCases;

import javafx.scene.input.InputMethodTextRun;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;
import selenium.Blocks;
import selenium.ClsBrowser;

public class TestCase_Exec extends Blocks
{
	public String URL; 
	
	@Before
	public void setup() 
	{
		URL= "https://demoqa.com/login";
		//URL = "https://demoqa.com/";
		ClsBrowser.BrowserName = "Chrome";
		OpenBrowser();
	} 
	
	@Test
	public void FillElementsInput()
	{
		//GO TO LOGIN PAGE
 		NavigateToUrl(URL);

		 //LOGIN
		WaitForElement("//button[@id = 'login']");
		LogIn();

		//go to bookstore
		WaitForElement("//div[contains(text(),'Profile')]");
		Click(storeButton);

		//select book
		selectBook();

		//alertDsiplayed();
		Assert.assertEquals("Book added to your collection.", GetAlertText());
		AcceptAlert();

		//book found on profile
		searchBook();

		//Delete book
		WaitForElement("//button[contains(text(),'Delete All Books')]");
		Click("//button[contains(text(),'Delete All Books')]");

		//accept delete message
		wantToDelete();





















		/*//Go to URL QA DEMO
		NavigateToUrl(URL);
		
		//Go to Elements Menu
		WaitForElement("//h5[text()='Elements']");
		WaitForElementClickable("//h5[text()='Elements']");
		Click("//h5[text()='Elements']");
		
		//Wait for Elements Page
		WaitForElement("//div[@class='main-header' and text()='Elements']");
		
		//Click TextBoxes
		WaitForElement("//li[span[text()='Text Box']]");
		Click("//li[span[text()='Text Box']]");
		
		//SendKeys to inputs
		WaitForElement("//input[@id='userName']");
		SendKeys("//input[@id='userName']", "Test UserName");
		//SendKeys to email
		SendKeys("//input[@placeholder= 'name@example.com']", "vazquezredgardo@gmail.com");
		//SendKeys current address
		SendKeys("//textarea[@placeholder = 'Current Address']", "sending current address");
		//SendKeys permanent address
		SendKeys("//textarea[@id = 'permanentAddress']", "sending permanent address");
		//Click submit button
		Click("//button[@id = 'submit']");
		//wait for the element and then gets the text form the previous fields
		WaitForElement("//textarea[@id = 'permanentAddress']");
		GetText("//div[@id = 'output']");*/


	}
	
	@After
	public void tearDown() 
	{
		CloseBrowser();
	}
	
	

}
