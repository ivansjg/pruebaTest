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

import static org.junit.matchers.JUnitMatchers.containsString;

import play.test.*;
import play.mvc.*;
import play.mvc.Http.*;
import models.*;

import play.Logger;

//public class ApplicationTest extends FunctionalTest {
// @RunWith(JUnit4.class)  
 public class ApplicationTest extends UnitTest{  
      private static WebDriver webDriver = null;       
      private static String url = "https://www.google.es/";  
      /**  
       * Called only once when the class is loaded into Junit framework  
       */  
      @BeforeClass  
      public static void beforeClass(){  
Logger.info("[*] En beforeClass 1 %s", webDriver);
           //webDriver = new FirefoxDriver();     //launches FireFox browser,   
webDriver = new HtmlUnitDriver();
Logger.info("[*] En beforeClass 2 %s", webDriver);
      }  
      /**  
       * called every time before testcases i.e., methods which are marked with @Test annotation  
       */  
      @Before  
      public void beforeTestCaseMethod() throws InterruptedException{  
Logger.info("[*] En beforeTestCaseMethod");
           webDriver.get(url);                                   //Load the website  
//           Thread.sleep(5000);  
//Logger.info("%s", webDriver.getPageSource());
      }  
      /**  
       * Test case to search hello world text in google.  
       */  
      @Test  
      public void testHelloWorldSearch() throws InterruptedException{  
Logger.info("[*] En testHelloWorldSearch");
           WebElement googleSearchBox = webDriver.findElement(By.name("q"));  
           googleSearchBox.sendKeys("Hello World");  
//           WebElement googleSearchButton = webDriver.findElement(By.name("q")); //sometimes Search button id will be "gbqfba"  
//           googleSearchButton.click();       
		googleSearchBox.submit();
//           Thread.sleep(5000);  
		assertThat(webDriver.getTitle(), containsString("Hello World"));
      }  
      /**  
       * called every time before testcases i.e., methods which are marked with @Test annotation  
       */  
      @After  
      public void afterTestCaseMethod() throws InterruptedException{  
Logger.info("[*] En afterTestCaseMethod");
           webDriver.get(url);                                   //Optional, revert to page like before testcase.  
//           Thread.sleep(2000);  
      }  
      /**  
       * Called only once when the class is unloaded from Junit framework  
       */  
      @AfterClass  
      public static void afterClass(){  
Logger.info("[*] En afterClass");
           webDriver.quit();                                   //Close the firefox   
      }  
 }

/*
    @Test
    public void testThatIndexPageWorks() {
        Response response = GET("/");
        assertIsOk(response);
        assertContentType("text/html", response);
        assertCharset(play.Play.defaultWebEncoding, response);
    }
}
*/
