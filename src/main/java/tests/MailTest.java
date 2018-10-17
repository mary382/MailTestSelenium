package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import steps.Steps;

public class MailTest {
    private WebDriver driver;

    private Steps steps;
    private final String USERNAME = "maryia.sidaruk@mail.ru";
    private final String PASSWORD = "masha878363";
    private final String ADRESS = "mashasidoruk@mail.ru";
    private final String SUBJECT = "TEST";
    private final String BODY = "Text";

    @BeforeClass(description = "Init browser")
    public void setUp() {
        steps = new Steps();
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
        steps.createNewMail(ADRESS,SUBJECT,BODY);
        Assert.assertTrue(steps.isMailPresentedInDraft(ADRESS, SUBJECT,BODY));
    }

    @Test(dependsOnMethods = "testMailIsPresentedInDraft")
    public void testMailWasSend(){
        steps.sendMail();
        Assert.assertTrue(steps.isMailPresentedInDraft(ADRESS,SUBJECT,BODY));
        Assert.assertTrue(steps.isMailPresentedInSend(ADRESS,SUBJECT,BODY));
        steps.logOff();
    }

   @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
        steps.closeDriver();

    }
}
