package steps;

import drivers.DriverSingleton;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.*;

public class Steps {
    private WebDriver driver;

    public void initBrowser(){
        driver= DriverSingleton.getDriver();
    }

    public void closeDriver(){
        DriverSingleton.closeDriver();
    }

    public void loginMail(String username, String password){
        LoginPage homePage=new LoginPage(driver);
        homePage.openPage();
        homePage.login(username,password);
    }

    public boolean isLoggedIn(String username){
        LoginPage loginPage=new LoginPage(driver);
        return loginPage.homePageStatus(username);
    }


    public void createNewMail(String adress,String subject, String text) {
        MailPage mailPage = new MailPage(driver);
        mailPage.writeNewMailButton();
        mailPage.fillAdressField(adress);
        mailPage.fillSubjectField(subject);
        mailPage.fillFrame(text);
        mailPage.saveButtonClick();




        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isMailPresentedInDraft(String adress,String subject,String text) {
        DraftPage draft_page = new DraftPage(driver);
        draft_page.openPage();
       return  draft_page.findMailInDraft(adress,subject,text);
    }

   public void sendMail(){
        DraftPage draftPage=new DraftPage(driver);
        draftPage.sendButtonClick();
   }

   public boolean isMailPresentedInSend(String adress, String subject, String text){
        SendPage sendPage=new SendPage(driver);
        sendPage.openPage();
        sendPage.sentItemsClick();
        return sendPage.verifySentFolder(adress, subject, text);
   }

   public void checkSends(String adress, String subject){
        SendPage sendPage=new SendPage(driver);
        sendPage.finderMail(adress,subject);
   }

   public void sendMailAndGoToSends(){
       sendMail();
       SendPage sendPage=new SendPage(driver);
       sendPage.openPage();
      // sendPage.clickSelectMailButton();
       driver.findElement(By.id("//a[@class='js-href b-datalist__item__link']")).click();
       //sendPage.clickMoveToSpamButton();
       driver.findElement(By.id("//span[@class='b-toolbar__btn__text b-toolbar__btn__text_pad'][text()='Спам']")).click();


   }




   public boolean isMailPresentedInSpam(String adress, String subject, String text){
       SpamPage spamPage=new SpamPage(driver);
       spamPage.openPage();
       return spamPage.findMailInSpam(adress,subject,text);
   }

   public void logOff(){
        MailPage mailPage=new MailPage(driver);
        mailPage.clickLogOff();
   }
}
