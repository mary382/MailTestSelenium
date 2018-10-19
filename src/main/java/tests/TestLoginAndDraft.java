package tests;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import steps.Steps;
import utils.Utils;

public class TestLoginAndDraft {

    private Steps steps;
    private final String USERNAME = "maryia.sidaruk@mail.ru";
    private final String PASSWORD = "masha878363";
    private final String ADRESS = "leha.2409@mail.ru";//"mashasidoruk@mail.ru";
    private String SUBJECT = Utils.getRandomString(20);
    private final String BODY = Utils.getRandomString(20);

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
    Assert.assertTrue(steps.saveMailToDraft(ADRESS,SUBJECT,BODY));
    }

    @Test(dependsOnMethods = "createNewMail")
    public void testMailIsPresentedInDraft() {
        steps.createNewMail(ADRESS,SUBJECT,BODY);
        Assert.assertTrue(steps.saveMailToDraft(ADRESS, SUBJECT,BODY));
    }

    @Test(dependsOnMethods = "testMailIsPresentedInDraft")
    public void testMailWasSend(){
        steps.sendMail();
        Assert.assertTrue(steps.saveMailToDraft(ADRESS,SUBJECT,BODY));
        Assert.assertTrue(steps.moveMailFromDraftToSend(ADRESS,SUBJECT,BODY));
        steps.logOff();
    }

   @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
        steps.closeDriver();
    }
}
