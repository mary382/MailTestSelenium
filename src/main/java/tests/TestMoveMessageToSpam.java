package tests;

import com.sun.xml.internal.xsom.impl.scd.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import steps.Steps;

public class TestMoveMessageToSpam {

    private WebDriver driver;

    private Steps steps;
    private final String USERNAME = "maryia.sidaruk@mail.ru";
    private final String PASSWORD = "masha878363";
    private final String ADRESS = "mashasidoruk@mail.ru";
    private final String SUBJECT = "TEST";
    private final String BODY = "Text";

    @BeforeClass
    public void setUp(){
        steps=new Steps();
        steps.initBrowser();
    }

    @Test
    public void loginMail() {
        steps.loginMail(USERNAME, PASSWORD);
        Assert.assertTrue(steps.isLoggedIn(USERNAME));
    }

    @Test(dependsOnMethods = "loginMail")
    public void createNewMail() {
        steps.createNewMail(ADRESS, SUBJECT, BODY);
        Assert.assertTrue(steps.isMailPresentedInDraft(ADRESS,SUBJECT,BODY));
    }

    @Test(dependsOnMethods = "createNewMail")
    public void testMailIsPresentedInDraft() {
       // steps.createNewMail(ADRESS,SUBJECT,BODY);
        Assert.assertTrue(steps.isMailPresentedInDraft(ADRESS, SUBJECT,BODY));
    }

    @Test(dependsOnMethods = "testMailIsPresentedInDraft")
    public void testIsMailInSpam(){

    steps.sendMailAndGoToSends();
    Assert.assertTrue(steps.isMailPresentedInSpam(ADRESS,SUBJECT,BODY));
        steps.logOff();
    }


   // @AfterClass
    //public void stopBrowser() {
     //   steps.closeDriver();
    //}

}
