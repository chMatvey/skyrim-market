package com.skyrimmarket.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id = "username")
    public WebElement usernameInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(className = "login__form__submit")
    public WebElement loginButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-login/div/form/div[3]/div/a")
    public  WebElement registerButton;

    @FindBy(xpath = "/html/body/div/div[2]/div/mat-dialog-container/app-error-popup/div/p")
    public WebElement errorPopup;

    @FindBy(xpath = "/html/body/div/div[2]/div/mat-dialog-container/app-error-popup/div/app-close-popup/div/img")
    public WebElement closeErrorPopup;

    public LoginPage(ChromeDriver driver){
        PageFactory.initElements(driver, this);
    }

}
