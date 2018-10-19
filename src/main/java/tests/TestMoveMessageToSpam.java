package tests;

import org.openqa.selenium.WebDriver;
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
    public void createNewMailAndSaveInDraft() {
        steps.createNewMail(ADRESS, SUBJECT, BODY);
        Assert.assertTrue(steps.saveMailToDraft(ADRESS,SUBJECT,BODY));
    }

    @Test(dependsOnMethods = "createNewMailAndSaveInDraft")
    public void testIsMailInSpam(){
    steps.sendMailAndGoToSends();
    Assert.assertTrue(steps.moveMailFromDraftToSpam(ADRESS,SUBJECT,BODY));
    steps.logOff();
    }

    @AfterClass
    public void stopBrowser() {
        steps.closeDriver();
    }
}
