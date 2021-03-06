package com.skyrimmarket.page.client;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

public class ClientPayOrderPage extends ClientMainPage{

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div[2]/app-pay-form/form/div[2]/mat-select/div/div[2]")
    public WebElement payWayComboBox;

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div/div/mat-option/span")
    public WebElement payWayCash;

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-order/div/div[2]/app-pay-form/form/div[3]/button")
    public WebElement applyButton;

    public ClientPayOrderPage(ChromeDriver driver){
        super(driver);
    }

}
