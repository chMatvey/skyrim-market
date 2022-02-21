package com.skyrimmarket.page.employee;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class EmployeeOrdersPage extends EmployeeMainPage{

    public int getOrdersCount(){
        return driver.findElements(By.xpath("//div[@class=\"order-description\"]")).size();
    }

        public WebElement getOrder(int position, int ordersTypeId){
        String orderType = "";
        switch (ordersTypeId){
            case 1:
                orderType = "my";
                break;
            case 2:
                orderType = "available";
                break;
            case 3:
                orderType = "completed";
                break;
        }
        if (this.getOrdersCount() >= 2){
            return driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-employee/div/app-" + orderType +"-orders/div/app-order-list/div/div["
                    + position + "]/div/div"));
        }
        else{
            return driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-employee/div/app-" + orderType + "-orders/div/app-order-list/div/div/div"));
        }
    }

    public EmployeeOrdersPage(ChromeDriver driver){
        super(driver);
    }

}
