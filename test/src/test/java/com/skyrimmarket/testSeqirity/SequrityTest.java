package com.skyrimmarket.testSeqirity;

import com.skyrimmarket.page.LoginPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SequrityTest {
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
        loginPage.usernameInput.sendKeys(Keys.LEFT_CONTROL + "a");
        loginPage.usernameInput.sendKeys(username);
        loginPage.passwordInput.sendKeys(Keys.LEFT_CONTROL + "a");
        loginPage.passwordInput.sendKeys(password);
        loginPage.loginButton.click();
    }

    @Test
    void wrongPasswords() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        login(driver, "client", "lol");
        TimeUnit.MILLISECONDS.sleep(500);
        assertEquals("Bad credentials", loginPage.errorPopup.getText());
        loginPage.closeErrorPopup.click();

        login(driver, "master", "lol");
        TimeUnit.MILLISECONDS.sleep(500);
        assertEquals("Bad credentials", loginPage.errorPopup.getText());
        loginPage.closeErrorPopup.click();

        login(driver, "employee", "lol");
        TimeUnit.MILLISECONDS.sleep(500);
        assertEquals("Bad credentials", loginPage.errorPopup.getText());
        loginPage.closeErrorPopup.click();

        login(driver, "student", "lol");
        TimeUnit.MILLISECONDS.sleep(500);
        assertEquals("Bad credentials", loginPage.errorPopup.getText());
        loginPage.closeErrorPopup.click();
    }

    @Test
    void rightClientPassword() throws InterruptedException{
        LoginPage loginPage = new LoginPage(driver);
        login(driver, "client", "client");
        TimeUnit.MILLISECONDS.sleep(500);
        assertEquals("http://localhost:4200/client", driver.getCurrentUrl());
    }

    @Test
    void rightStudentPassword() throws InterruptedException{
        LoginPage loginPage = new LoginPage(driver);
        login(driver, "student", "student");
        TimeUnit.MILLISECONDS.sleep(500);
        assertEquals("http://localhost:4200/student", driver.getCurrentUrl());
    }

    @Test
    void rightMasterPassword() throws InterruptedException{
        LoginPage loginPage = new LoginPage(driver);
        login(driver, "master", "master");
        TimeUnit.MILLISECONDS.sleep(500);
        assertEquals("http://localhost:4200/master", driver.getCurrentUrl());
    }

    @Test
    void rightEmployeePassword() throws InterruptedException{
        LoginPage loginPage = new LoginPage(driver);
        login(driver, "employee", "employee");
        TimeUnit.MILLISECONDS.sleep(500);
        assertEquals("http://localhost:4200/employee", driver.getCurrentUrl());
    }

    @Test
    void fromClientToOthers() throws InterruptedException{
        login(driver, "client", "client");
        TimeUnit.MILLISECONDS.sleep(500);

        driver.get("http://localhost:4200/employee");
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());

        driver.get("http://localhost:4200/master");
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());

        driver.get("http://localhost:4200/student");
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());

        driver.get("http://localhost:4200/client");
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/client", driver.getCurrentUrl());
    }

    @Test
    void fromMasterToOthers() throws InterruptedException{
        login(driver, "master", "master");
        TimeUnit.MILLISECONDS.sleep(500);

        driver.get("http://localhost:4200/employee");
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());

        driver.get("http://localhost:4200/master");
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/master", driver.getCurrentUrl());

        driver.get("http://localhost:4200/student");
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());

        driver.get("http://localhost:4200/client");
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());
    }

    @Test
    void fromEmployeeToOthers() throws InterruptedException{
        login(driver, "employee", "employee");
        TimeUnit.MILLISECONDS.sleep(500);

        driver.get("http://localhost:4200/employee");
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/employee", driver.getCurrentUrl());

        driver.get("http://localhost:4200/master");
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());

        driver.get("http://localhost:4200/student");
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());

        driver.get("http://localhost:4200/client");
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());
    }

    @Test
    void fromStudentToOthers() throws InterruptedException{
        login(driver, "student", "student");
        TimeUnit.MILLISECONDS.sleep(500);

        driver.get("http://localhost:4200/employee");
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());

        driver.get("http://localhost:4200/master");
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());

        driver.get("http://localhost:4200/student");
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/student", driver.getCurrentUrl());

        driver.get("http://localhost:4200/client");
        TimeUnit.MILLISECONDS.sleep(200);
        assertEquals("http://localhost:4200/login", driver.getCurrentUrl());
    }

}
