package com.skyrimmarket.testUI;

import com.skyrimmarket.page.LoginPage;
import com.skyrimmarket.page.RegisterPage;
import com.skyrimmarket.page.client.ClientMainPage;
import com.skyrimmarket.page.client.ClientMyOrdersPage;
import com.skyrimmarket.page.employee.EmployeeMainPage;
import com.skyrimmarket.page.master.MasterAnalyticPage;
import com.skyrimmarket.page.master.MasterMainPage;
import com.skyrimmarket.page.master.MasterOrdersPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserInterfaceTests {
    ChromeDriver driver = null;

    @BeforeEach
    void setUp() {
        if (driver == null) {
            Map<String, Object> prefs = new HashMap<String, Object>();
            prefs.put("profile.default_content_setting_values.notifications", 1);
            ChromeOptions options = new ChromeOptions();
            options.setExperimentalOption("prefs", prefs);
            System.setProperty("webdriver.chrome.driver", "C:\\chromedriver\\chromedriver.exe");
            driver = new ChromeDriver(options);
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
            driver.get("http://localhost:4200/");
        }
    }


    @AfterEach
    void driverDown(){
        driver.close();
    }

    void login(ChromeDriver driver, String username, String password){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.usernameInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(password);
        loginPage.loginButton.click();
    }

    @Test
    void loginPageTest() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.registerButton.click();                                   // Переход на страницу в регистрации

        String registerUrl = driver.getCurrentUrl();
        assertEquals(registerUrl, "http://localhost:4200/register");
        RegisterPage registerPage = new RegisterPage(driver);

        registerPage.loginButton.click();
        String loginUrl = driver.getCurrentUrl();
        assertEquals(loginUrl, "http://localhost:4200/login");
    }

    @Test
    void clientPagesTest() throws InterruptedException {
        login(driver, "client", "client");
        ClientMainPage clientMainPage = new ClientMainPage(driver);
        TimeUnit.MILLISECONDS.sleep(500);
        clientMainPage.closePopup.click();
        TimeUnit.MILLISECONDS.sleep(500);

        clientMainPage.makeOrderButton.click();
        String makeOrderUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:4200/client/order", makeOrderUrl);

        clientMainPage.myOrdersButton.click();
        String myOrdersUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:4200/client/orders", myOrdersUrl);

        ClientMyOrdersPage clientMyOrdersPage = new ClientMyOrdersPage(driver);
        if (clientMyOrdersPage.getOrdersCount() > 0) {
            int id = clientMyOrdersPage.getOrderId(1);
            clientMyOrdersPage.getOrder(1).click();
            String myOrderUrl = driver.getCurrentUrl();
            assertEquals("http://localhost:4200/client/order/" + id, myOrderUrl);
        }

        clientMainPage.usernameButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        clientMainPage.logoutButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        String loginUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:4200/login", loginUrl);
    }

    @Test
    void masterPagesTest() throws InterruptedException {
        login(driver, "master", "master");
        TimeUnit.MILLISECONDS.sleep(500);

        MasterMainPage masterMainPage = new MasterMainPage(driver);
        String masterMainUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:4200/master", masterMainUrl);

        masterMainPage.createEmployeeButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/master/employees/create", driver.getCurrentUrl());

        masterMainPage.studentsButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/master/students", driver.getCurrentUrl());

        masterMainPage.employeesButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/master/employees", driver.getCurrentUrl());

        masterMainPage.analyticsButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/master/analytic", driver.getCurrentUrl());

        MasterAnalyticPage masterAnalyticPage = new MasterAnalyticPage(driver);
        masterAnalyticPage.ordersButton.click();
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/master/orders", driver.getCurrentUrl());

        MasterOrdersPage masterOrdersPage = new MasterOrdersPage(driver);

        if (masterOrdersPage.getOrdersCount() > 0) {
            String id = masterOrdersPage.getOrder(1).getText().split(" ")[1];
            masterOrdersPage.getOrder(1).click();
            TimeUnit.MILLISECONDS.sleep(200);
            assertEquals("http://localhost:4200/master/order/" + id, driver.getCurrentUrl());
        }

        masterOrdersPage.usernameButton.click();
        masterOrdersPage.logoutButton.click();
        String loginUrl = driver.getCurrentUrl();
        assertEquals("http://localhost:4200/login", loginUrl);

    }

    @Test
    void employeePagesTest() throws InterruptedException{
        login(driver, "employee", "employee");
        TimeUnit.MILLISECONDS.sleep(500);
        assertEquals("http://localhost:4200/employee", driver.getCurrentUrl());

        EmployeeMainPage employeeMainPage = new EmployeeMainPage(driver);
        employeeMainPage.availableOrdersButton.click();
        assertEquals("http://localhost:4200/employee/available-orders", driver.getCurrentUrl());

        employeeMainPage.completedOrdersButton.click();
        assertEquals("http://localhost:4200/employee/completed-orders", driver.getCurrentUrl());

        employeeMainPage.myOrdersButton.click();
        assertEquals("http://localhost:4200/employee/my-orders", driver.getCurrentUrl());

        employeeMainPage.usernameButton.click();
        TimeUnit.MILLISECONDS.sleep(500);
        employeeMainPage.logoutButton.click();
        TimeUnit.MILLISECONDS.sleep(500);
        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());

    }

}
