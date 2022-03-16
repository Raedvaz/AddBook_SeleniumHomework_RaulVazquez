package selenium;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;

public class Blocks extends ClsBrowser{

    //login
    public void LogIn (){
        SendKeys(userPath,userName);
        SendKeys(pswdPath,pswd);
        Click(logButton);
    }
    //select book
    public void selectBook(){
        WaitForElement("//div[contains(text(),'Book Store')]");
        ClickLink(BookName);
        WaitForElement("//label[contains(text(),'Git Pocket Guide')]");
        Click(addButton);
    }
    public void searchBook(){
        JavascriptExecutor js = (JavascriptExecutor)ClsBrowser.objDriver;
        WaitForElement("//label[contains(text(),'Git Pocket Guide')]");
        js.executeScript("window.scrollBy(0,1000)");
        WaitForElement(profile);
        Click(profile);
        //Assert.assertEquals("Git Pocket Guide",getTitle("//span[@class='mr-2']"));
        //GetText("//span[@class='mr-2']");
        VerifyBookAdded();
    }
    public void wantToDelete(){
        WaitForElement("//button[@id ='closeSmallModal-ok']");
        Click("//button[@id ='closeSmallModal-ok']");
        Assert.assertEquals("All Books deleted.", GetAlertText());
        AcceptAlert();
    }

}
