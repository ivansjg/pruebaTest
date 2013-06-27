package javi;

 import org.junit.After;
 import org.junit.AfterClass;
 import org.junit.Before;
 import org.junit.BeforeClass;
 import org.junit.Test;
 import org.junit.runner.RunWith;
 import org.junit.runners.JUnit4;
 import org.openqa.selenium.By;
 import org.openqa.selenium.WebDriver;
 import org.openqa.selenium.WebElement;
 import org.openqa.selenium.firefox.FirefoxDriver;
 import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import org.junit.*;
import play.test.*;
import play.Logger;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.PageFactory.initElements;

import org.fest.assertions.Assertions;

import javi.pages.LoginPage;

import play.modules.siena.SienaFixtures;

public class LoginTest extends UnitTest {

    private LoginPage page;
    private static WebDriver webDriver;
    private String baseURL = "http://localhost:9512/login";

    @BeforeClass
    public static void initDB() {
        SienaFixtures.loadModels("/data/prueba.yml");
    }

    @Before
    public void openTheBrowser() {
        webDriver = new HtmlUnitDriver();
        page = PageFactory.initElements(webDriver, LoginPage.class);
        page.open(baseURL);
    }

    @After
    public void closeTheBrowser() {
        page.close();
    }

    /**
     *  Checks that all the required HTML elements are present and visible in the page.
     */
    @Test
    public void testRequiredElementsVisible() {
        Assertions.assertThat(page.hasAllElements()).isEqualTo(1);
    }

    /**
     *  Checks that 
     */
    @Test
    public void testLoginIncomplete() {
        Assertions.assertThat(page.testLogin(params.TestParams.userTypeAny, "user", "")).isEqualTo(1);
    }

    @Test
    public void testLoginCitizen() {
        Assertions.assertThat(page.testLogin(params.TestParams.userTypeCitizen, "citizen", "password")).isEqualTo(1);   
    }

    @Test
    public void testLoginPolitician() {
        Assertions.assertThat(page.testLogin(params.TestParams.userTypePolitician, "politician", "password")).isEqualTo(1);
    }

    @Test
    public void testLoginAdministrator() {
        Assertions.assertThat(page.testLogin(params.TestParams.userTypeAdministrator, "admin", "password")).isEqualTo(1);
    }

    @Test
    public void testLoginNonRegistered() {
        Assertions.assertThat(page.testLogin(params.TestParams.userTypeNonRegistered, "nonRegistered", "password")).isEqualTo(1);
    }

    @Test
    public void testRecoverAccountLinkActive() {
        Assertions.assertThat(page.hasAllLinksActive()).isEqualTo(1);
    }

}
