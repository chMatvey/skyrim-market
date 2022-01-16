package com.skyrimmarket.test.page.client;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ClientMainPage {

    ChromeDriver driver;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/app-toolbar/div/div[1]/div/a[1]")
    public  WebElement makeOrderButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/app-toolbar/div/div[1]/div/a[2]")
    public  WebElement myOrdersButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/app-toolbar/div/div[2]/div[1]")
    public  WebElement usernameButton;

    @FindBy(xpath = "/html/body/div[2]/div[2]/div/div/div/button")
    public  WebElement logoutButton;

    public ClientMainPage(ChromeDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

}
