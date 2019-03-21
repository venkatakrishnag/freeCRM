package Action;

import Base.Logz;
import Page.LoginPage;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import sun.rmi.runtime.Log;

public class LoginAction extends LoginPage {
    public LoginAction(WebDriver driver) throws Exception {
        super(driver);
    }

    public void PerformAdminLogin(String struserID,String strpassword)throws Exception{
        Logz.Message("Enter user name");
        userName().sendKeys(struserID);
        Logz.Message("User Name Entered as"+struserID);
        Logz.Message("Enter Password");
        password().sendKeys(strpassword);
        Logz.Message("Entered Password as"+strpassword);
        Assert.assertNotNull(password(),"password text box not empty");
        Logz.Message("click on LoginButton");
        WaitForElement(2);
        login().click();
        Logz.Message("loginButton clicked");
        String title=driver.getTitle();
        System.out.println(title);
        validateTitle(title,"CRMPRO");
    }

}
