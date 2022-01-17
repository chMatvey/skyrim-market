package com.skyrimmarket.test.page.master;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MasterMainPage {

    protected ChromeDriver driver;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-master/app-toolbar/div/div[1]/div/a")
    public  WebElement ordersButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-master/app-toolbar/div/div[2]/div[1]")
    public  WebElement usernameButton;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div/div/div/button")
    public  WebElement logoutButton;

    public MasterMainPage(ChromeDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
