package com.skyrimmarket.page.client;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class ClientMakeOrderPage extends ClientMainPage{
    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/div[1]/div/mat-select/div/div[1]")
    public WebElement selectServiceButton;



    @FindBy(xpath = "//*[@id=\"person\"]")
    public WebElement personEdit;

    @FindBy(xpath = "//*[@id=\"address\"]")
    public WebElement addressEdit;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div[1]/div[1]/div[1]/mat-select/div/div[1]/span/span")
    public WebElement orderType;

    @FindBy(xpath = "//*[@id=\"title\"]/div/div[1]")
    public WebElement robbedPickpoketingTitleEdit;

    @FindBy(xpath = "//*[@id=\"description\"]")
    public WebElement descriptionPickpoketingTextEdit;

    @FindBy(xpath = "//*[@id=\"item\"]/mat-select/div/div[1]")
    public WebElement itemPickpoketingEdit;

    @FindBy(xpath = "//*[@id=\"title\"]/div/div[1]")
    public WebElement robbedSweepTitleEdit;

    @FindBy(xpath = "//*[@id=\"description\"]")
    public WebElement descriptionSweepTextEdit;

    @FindBy(xpath = "//*[@id=\"item\"]/mat-select/div/div[1]")
    public WebElement itemSweepEdit;

    @FindBy(xpath = "//*[@id=\"item\"]/mat-select/div/div[1]")
    public WebElement itemForgeryEdit;

    @FindBy(xpath = "//*[@id=\"description\"]")
    public WebElement descriptionForgeryTextEdit;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div/mat-option[1]/span/ngx-mat-select-search/div/input")
    public WebElement itemEnterItem;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/div[2]/button[2]")
    public WebElement createOrderButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/div[2]/button[1]")
    public WebElement closeButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/div[2]/button[2]")
    public WebElement declineOrderButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/div[2]/button[3]")
    public WebElement applyOrderButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div/div[2]/button[1]")
    public WebElement backButton;

    @FindBy(xpath = "//*[@id=\"close-popup\"]")
    public WebElement exitNotification;

    public WebElement getRobbedTitle(int id){
        String xPath = "/html/body/div[1]/div[2]/div/div/div/mat-option[" + id + "]/span";
        return this.driver.findElement(By.xpath(xPath));
    }

    public WebElement selectOrderType(int id){
        String xPath = "/html/body/div[1]/div[2]/div/div/div/mat-option[" + (id) + "]/span";
        return this.driver.findElement(By.xpath(xPath));
    }

    public WebElement getItem(int id){
        String xPath = "/html/body/div[1]/div[2]/div/div/div/mat-option[" + (id + 1) + "]/span";
        return this.driver.findElement(By.xpath(xPath));
    }
    public ClientMakeOrderPage(ChromeDriver driver){
        super(driver);
    }

}
