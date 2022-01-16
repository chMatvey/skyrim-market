package com.skyrimmarket.test.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
    @FindBy(id = "username")
    public WebElement usernameInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(id = "confirmPassword")
    public  WebElement confirmPasswordInput;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-register/div/form/div[4]/div/a")
    public WebElement loginButton;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-register/div/form/div[4]/button")
    public  WebElement registerButton;

    public RegisterPage(ChromeDriver driver){
        PageFactory.initElements(driver, this);
    }
}
