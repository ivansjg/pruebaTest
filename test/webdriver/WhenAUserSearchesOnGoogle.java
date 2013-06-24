package webdriver;

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

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.PageFactory;

import static org.openqa.selenium.support.PageFactory.initElements;

import static org.junit.matchers.JUnitMatchers.containsString;

import webdriver.pages.GoogleSearchPage;

public class WhenAUserSearchesOnGoogle extends UnitTest {

	private GoogleSearchPage page;
	private static WebDriver webDriver;

        @BeforeClass
        public static void initWebDriver() {
		webDriver = new HtmlUnitDriver();
        }
	
	@Before
	public void openTheBrowser() {
		page = PageFactory.initElements(webDriver, GoogleSearchPage.class);
		page.open("https://www.google.es/");
	}

	@After
	public void closeTheBrowser() {
//		page.close();
	}

        @AfterClass
        public static void shutdownWebDriver() {
              webDriver.quit();
        }

	@Test
	public void whenTheUserSearchesForCatsTheResultPageTitleShouldContainCats() {
		page.searchFor("cats");
		assertThat(page.getTitle(), containsString("cats") );
	}	

        @Test
        public void failedTest() {
                page.searchFor("cats");
                assertThat(page.getTitle(), containsString("catsaaa") );
        }
}
