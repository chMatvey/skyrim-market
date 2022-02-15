package com.skyrimmarket.test.page.client;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClientMakeOrderPage extends ClientMainPage{
    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/div[1]/div/mat-select/div/div[1]")
    public WebElement selectServiceButton;



    @FindBy(xpath = "//*[@id=\"person\"]")
    public WebElement personEdit;

    @FindBy(xpath = "//*[@id=\"address\"]")
    public WebElement addressEdit;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div[1]/div[1]/div[1]/mat-select/div/div[1]/span/span")
    public WebElement orderType;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/app-pickpocketing-order-form/form/div[2]/mat-select/div/div[1]")
    public WebElement robbedPickpoketingTitleEdit;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/app-pickpocketing-order-form/form/div[4]/textarea")
    public WebElement descriptionPickpoketingTextEdit;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/app-pickpocketing-order-form/form/div[3]/app-search-select/mat-select/div/div[1]")
    public WebElement itemPickpoketingEdit;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/app-sweep-order-form/form/div[2]/mat-select/div/div[1]")
    public WebElement robbedSweepTitleEdit;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/app-sweep-order-form/form/div[4]/textarea")
    public WebElement descriptionSweepTextEdit;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/app-sweep-order-form/form/div[3]/app-search-select/mat-select/div/div[1]")
    public WebElement itemSweepEdit;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/app-forgery-order-form/form/div[3]/app-search-select/mat-select/div/div[1]")
    public WebElement itemForgeryEdit;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/app-forgery-order-form/form/div[4]/textarea")
    public WebElement descriptionForgeryTextEdit;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div/div/div/mat-option[1]/span/ngx-mat-select-search/div/input")
    public WebElement itemEnterItem;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/div[2]/button[2]")
    public WebElement createOrderButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/div[2]/button[2]")
    public WebElement declineOrderButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/div[2]/button[3]")
    public WebElement applyOrderButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/div[2]/button[1]")
    public WebElement backButton;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div/mat-dialog-container/app-notification-popup/div/app-close-popup/div/img")
    public WebElement exitNotification;

    public WebElement getRobbedTitle(int id){
        String xPath = "/html/body/div[2]/div[2]/div/div/div/mat-option[" + id + "]/span";
        return this.driver.findElement(By.xpath(xPath));
    }

    public WebElement selectOrderType(int id){
        String xPath = "/html/body/div[2]/div[2]/div/div/div/mat-option[" + (id) + "]/span";
        return this.driver.findElement(By.xpath(xPath));
    }

    public WebElement getItem(int id){
        String xPath = "/html/body/div[2]/div[2]/div/div/div/mat-option[" + (id + 1) + "]/span";
        return this.driver.findElement(By.xpath(xPath));
    }
    public ClientMakeOrderPage(ChromeDriver driver){
        super(driver);
    }

}
