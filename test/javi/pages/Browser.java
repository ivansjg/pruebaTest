package javi.pages;

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

public class Browser {
    
    private static WebDriver driver = new HtmlUnitDriver();

    private static String baseURL = "http://localhost:9512";
    
    public static WebDriver driver() {
        return driver;
    }

    public static void open(String url) {
        driver.get(baseURL + url);
    }

    public static void close() {
        driver.close();
    }

}