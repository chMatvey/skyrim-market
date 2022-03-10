package com.skyrimmarket.page.master;

        import org.openqa.selenium.By;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.openqa.selenium.support.FindBy;

public class MasterOrdersPage extends MasterMainPage {

    @FindBy(xpath = "/html/body/app-root/div/div[2]/app-master/div/app-orders/div/div[2]/div/div")
    public WebElement singleOrder;
///html/body/app-root/div/div[2]/app-master/div/app-orders-for-master/div/app-order-list/div/div/div/div
    public int getOrdersCount(){
        return driver.findElements(By.xpath("//div[@class=\"order-description\"]")).size();
    }

    public WebElement getOrderByID(int orderId){
        int real_numba = -1;
        int num = driver.findElements(By.xpath("//div[@class=\"order-list__order form-control ng-star-inserted\"]")).size();
        for (int i = 0; i < num; ++i){
            WebElement el = driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-master/div/app-orders-for-master/div/app-order-list/div/div["
                    + String.valueOf(i + 1) + "]/div/span[1]"));
            int orId = Integer.valueOf(el.getText().split(" ")[1]);
            if (orId == orderId){
                real_numba = i + 1;
            }
        }
        if (real_numba >= 0) {
            return driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-master/div/app-orders-for-master/div/app-order-list/div/div[" +
                    real_numba + "]/div"));
        }
        else{
            return null;
        }
    }

    public WebElement getOrder(int position){
        int num = driver.findElements(By.xpath("//div[@class=\"order-list__order form-control ng-star-inserted\"]")).size();
        if (num >= 2) {
            return driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-master/div/app-orders-for-master/div/app-order-list/div/div["
                    + position + "]/div/span[1]"));
        }
        else{
            return driver.findElement(By.xpath("/html/body/app-root/div/div[2]/app-master/div/app-orders-for-master/div/app-order-list/div/div/div/span[1]"));
        }
    }

    public MasterOrdersPage(ChromeDriver driver){
        super(driver);
    }

}
