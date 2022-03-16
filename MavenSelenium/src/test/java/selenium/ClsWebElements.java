package selenium;

import java.time.Duration;
import java.util.List;
import java.util.function.Function;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import static org.junit.Assert.assertEquals;

public class ClsWebElements<Public> extends Locators
{
	
	public static Wait<WebDriver> objFluentWait;
    public static WebDriverWait objExplicitWait;
    public static String strAction = "";
	
    
    public WebElement getGetWebElement(String pstrLocator)
    {
        try
        {
            WebElement pobjElement = ClsBrowser.objDriver.findElement(By.xpath(pstrLocator));
            return pobjElement;
        }
        catch (Exception pobjException)
        {
        	System.out.println("The element was ("+ pstrLocator +") not located in the page");
           return null;
        }
    }
	
	
    public List<WebElement> getWebList(String pstrLocator)
    {
        try
        {
            List<WebElement> pobjElement = ClsBrowser.objDriver.findElements(By.xpath(pstrLocator));
            return pobjElement;
        }
        catch (Exception pobjException)
        {
        	System.out.println("The element was ("+ pstrLocator +") not located in the page");
            return null;
        }
    }
    
    
    public Object GetFluentWait(final String pstrLocator) 
    {
    	try 
    	{
    		// Waiting 30 seconds for an element to be present on the page, checking
    	 	   // for its presence once every 5 seconds.
    	    	objFluentWait = new FluentWait<WebDriver>(ClsBrowser.objDriver)
    	 	       .withTimeout(Duration.ofSeconds(30L))
    	 	       .pollingEvery(Duration.ofSeconds(3L))
    	 	       .ignoring(NoSuchElementException.class);
    	 	       
    	    	//Get Web Element and perform action
    	    	WebElement objElement = objFluentWait.until(new Function<WebDriver, WebElement>() {
    	   	     public WebElement apply(WebDriver driver) {
    	   	       return driver.findElement(By.xpath(pstrLocator));
    	   	     }
    	   	   });
    	    	
    	    	return objElement;
    	}
    	catch(Exception e) 
    	{
    		System.out.println("The element was ("+ pstrLocator +") not located in the page");
            return null;
    	}
    }
    
    
    public boolean Click(final String pstrLocator) 
	{
		WebElement objElement = (WebElement) GetFluentWait(pstrLocator);
		objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, 10);
		objExplicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(pstrLocator)));
		objElement.click();
		return false;
	}
    
    
    public boolean SendKeys(final String pstrLocator, String pValue)
	{
		WebElement objElement = (WebElement) GetFluentWait(pstrLocator);
		objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, 10);
		objExplicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pstrLocator)));
		objElement.clear();
		objElement.sendKeys(pValue);
		return true;
	}
    
    
    public void WaitForElement(final String pstrLocator) 
    {
    	objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, 10);
    	objExplicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pstrLocator)));
    }
    
    public void WaitForElementClickable(final String pstrLocator) 
    {
    	objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, 10);
    	objExplicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(pstrLocator)));
    	objExplicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(pstrLocator)));
    }
	//new method created to get the text
    public void GetText (final String pstrLocator)
	{
		objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, 10);
		objExplicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pstrLocator)));
		System.out.println(getGetWebElement(pstrLocator).getText());

	}

    public void ClickLink (final String Link){
		WebElement objElement = ClsBrowser.objDriver.findElement(By.linkText(Link));
		objElement.click();
	}

    /*public void accepetAlert (){
		Alert alert = ClsBrowser.objDriver.switchTo().alert();
	}
	public void assertAlert (String pValue){
		Alert alert = ClsBrowser.objDriver.switchTo().alert();
		assertEquals(pValue, alert.getText());
		alert.accept();

	}*/

	public boolean isDsiplayed(String pstrLocator) {

		boolean Flag = false;

		try {
			WaitForElement(pstrLocator);
			Flag = true;
			System.out.println("alert displayed");

		} catch (NoAlertPresentException ex) {
			ex.printStackTrace();
			System.out.println("alert not displayed");
		}

		return Flag;

	}
	public void AcceptAlert()
	{
		WebDriverWait wait = new WebDriverWait(ClsBrowser.objDriver, 3000);
		wait.until(ExpectedConditions.alertIsPresent());
		Alert alert = ClsBrowser.objDriver.switchTo().alert();
		alert.accept();
	}


	public String GetAlertText()
	{
		WebDriverWait wait = new WebDriverWait(ClsBrowser.objDriver, 3000);
		wait.until(ExpectedConditions.alertIsPresent());
		//Alert alert = ClsBrowser.objDriver.switchTo().alert();
		String alertMessage = ClsBrowser.objDriver.switchTo().alert().getText();
		return alertMessage;
	}

	public String getTitle (final String pstrLocator){
		WebElement objElement = (WebElement) GetFluentWait(pstrLocator);
		objExplicitWait = new WebDriverWait(ClsBrowser.objDriver, 10);
		objExplicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(pstrLocator)));
		return objElement.getText();

	}
	public void VerifyBookAdded (){
		List <WebElement> lsBook = ClsBrowser.objDriver.findElements(By.xpath(BookAdded));
		if(lsBook.size()>0){
			for (WebElement book : lsBook){
				if(book.getText().equalsIgnoreCase(BookName)){
					System.out.println("The book was added");
				}
			}
		}
		else{
			System.out.println(BookName + " wasn´t found");
		}

	}
	public void Zoom(){
		for(int i=0; i<5; i++){
			ClsBrowser.objDriver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL,Keys.SUBTRACT));
		}
	}

    public void zoomOut (final String pstrLocator){
		while (isDsiplayed(pstrLocator) == false){
			ClsBrowser.objDriver.findElement(By.tagName("html")).sendKeys(Keys.chord(Keys.CONTROL, Keys.SUBTRACT));
			System.out.println("zoom out");
			isDsiplayed(pstrLocator);

		}
	}
}
