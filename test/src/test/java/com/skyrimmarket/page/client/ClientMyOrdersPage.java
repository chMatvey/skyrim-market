package com.skyrimmarket.page.client;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.concurrent.TimeUnit;

public class ClientMyOrdersPage extends ClientMainPage{

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-client/div/app-orders/div/div[2]/div/div")
    public WebElement singleOrder;

    public int getOrdersCount(){
        return driver.findElements(By.xpath("//div[@class=\"order-description\"]")).size();
    }

    public void chooseFilter(int filterId) throws InterruptedException {
        driver.findElement(By.xpath("" +
                "/html/body/app-root/div/div[2]/app-client/div/app-orders/div/div[1]/mat-button-toggle-group/mat-button-toggle["
                + filterId + "]/button")).click();
        TimeUnit.MILLISECONDS.sleep(200);
    }

    public WebElement getOrder(int position){
        return driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-client/div/app-orders/div/div[2]/div["
                + position + "]/div/span[1]"));
    }

    public int getOrderId(int position){
        String el = getOrder(position).getText().split(" ")[1];
        return Integer.parseInt(el);
    }

    public ClientMyOrdersPage(ChromeDriver driver){
        super(driver);
    }

}
