package com.skyrimmarket.test.page.master;

import com.skyrimmarket.test.page.master.MasterMainPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class MasterOrderPage extends MasterMainPage {
    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-master/div/app-confirm-order/div/form/div[1]/textarea")
    public WebElement commentText;

    @FindBy(xpath = "//*[@id=\"price\"]")
    public WebElement priceEdit;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-master/div/app-confirm-order/div/form/div[3]/button[1]")
    public WebElement closeButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-master/div/app-confirm-order/div/form/div[3]/button[2]")
    public WebElement declineButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-master/div/app-confirm-order/div/form/div[3]/button[3]")
    public WebElement commentButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-master/div/app-confirm-order/div/form/div[3]/button[4]")
    public WebElement approveButton;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div/mat-dialog-container/app-notification-popup/div/app-close-popup/div/img")
    public WebElement notificationExit;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-master/div/app-confirm-order/div/form/div[2]/mat-select/div/div[1]")
    public WebElement contractorChoosingButton;

    public void chooseContractor(int id) throws InterruptedException {
        this.contractorChoosingButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        driver.findElement(By.xpath("/html/body/div[2]/div[2]/div/div/div/mat-option/span")).click();
    }

    public MasterOrderPage(ChromeDriver driver){
        super(driver);
    }
}
