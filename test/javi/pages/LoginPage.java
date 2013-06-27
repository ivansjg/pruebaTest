package javi.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.By;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedCondition;
import play.Logger;

import java.util.List;

import java.util.NoSuchElementException;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;


public class LoginPage {

    protected WebDriver driver;
    
    private String baseURL = "/login";

    @FindBy(id="loginForm")
    //@CacheLookUp
    private WebElement loginForm;

    @FindBy(id="input_email")
    //@CacheLookUp
    private WebElement inputEmail; 

    @FindBy(id="input_password")
    //@CacheLookUp
    private WebElement inputPassword;

    @FindBy(id="btn_submitLoginForm")
    //@CacheLookUp
    private WebElement btnSubmitLoginForm;

    @FindBy(id="recoverAccountLink")
    //@CacheLookUp
    private WebElement recoverAccountLink;

    /**
     *  Constructor
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }
    
    /**
     *  Opens the page in the browser.
     *
     *  @param  url URL to open
     */
    public void open(String url) {
        driver.get(url);
    }

    /**
     *  Closes the browser
     */
    public void close() {
        driver.quit();
    }
    
    /**
     *  Gets the page's title
     *
     *  @return     the title of the page.
     */
    public String getTitle() {
        return driver.getTitle();
    }

    /**
     *  Determines if all the required elements are present in the page.
     *  That is, returning 1 means that everything is correct. 
     *  If there is any of them that is not present, NoSuchElementException is thrown.
     *     
     *  @return 1 in case all elements are present or an exception if not.
     */
    public Integer hasAllElements() {
        boolean allVisible = true;

        allVisible = driver.findElement(By.id("loginForm")).isDisplayed();
        allVisible = allVisible && driver.findElement(By.id("input_email")).isDisplayed();
        allVisible = allVisible && driver.findElement(By.id("input_password")).isDisplayed();
        allVisible = allVisible && driver.findElement(By.id("btn_submitLoginForm")).isDisplayed();
        allVisible = allVisible && driver.findElement(By.id("recoverAccountLink")).isDisplayed();
        
        return allVisible ? 1 : 0;
    }

    /**
     *  Determines if all the links <a> elements are working.
     *     
     *  @return 1 links are not bronken links.
     */
    public Integer hasAllLinksActive() {
        //Default result in case there is no link to check.
        int result = 1;
        
        //Gets all the links in the page
        List<WebElement> links = driver.findElements(By.tagName("a"));
        
        //In case there are links to check their status
        if(links.size()>0){
            
            for (WebElement link : links) {
                
                //Get the href link
                String href = link.getAttribute("href");
                //Ignore the mailto links
                if(!href.contains("mailto")){
                    
                    //Open the link in a new browser
                    WebDriver tmpWebDriver = new HtmlUnitDriver();
                    tmpWebDriver.get(href);

                    //In case the page is not found
                    if(tmpWebDriver.getTitle().contains(params.TestParams.pageNotFoundMessage)){
                        //Mark the result as negative
                        result = 0;
                    }
                    
                    //Close the browser
                    tmpWebDriver.quit();
                }
            }
        }
        
        return result;
    }

    /**
     *  Returns the value of the attribute named as "value" of the given (id) HTML element.
     *
     *  @param  id  name of the element of which return its attribute "value"
     *  @return the value of the attribute named "value"
     */
    public String getElementValue(String id) {
        
        String result = "";

        result = driver.findElement(By.id(id)).getAttribute("value");

        return result;

    }

    /**
     *  Emulates the login process
     *
     *  @param  id  name of the element of which return its attribute "value"
     *  @return the value of the attribute named "value"
     */
    public Integer testLogin(String userType, String user, String password) {
        int result = 0;

        inputEmail.sendKeys(user);
        inputPassword.sendKeys(password);
        loginForm.submit();
        
        final String userTypeTmp = userType;

        try {
            
            (new WebDriverWait(driver, 10)).until(
                new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver d) {
                        
                        //In case the user did not login correctly
                        if(userTypeTmp.equals(params.TestParams.userTypeAny)) {
                            return d.getTitle().contains(params.TestParams.userLoginPageTitle);
                        //In case the user was not registered
                        }else if(userTypeTmp.equals(params.TestParams.userTypeNonRegistered)) {
                            return d.getTitle().contains(params.TestParams.userLoginPageTitle);
                        //In case the user was a citizen
                        }else if(userTypeTmp.equals(params.TestParams.userTypeCitizen)) {
                            return d.getTitle().contains(params.TestParams.userLoggedPageTitle);
                        //In case the user was a politician
                        }else if(userTypeTmp.equals(params.TestParams.userTypePolitician)) {
                            return d.getTitle().contains(params.TestParams.userLoggedPageTitle);
                        //In case the user was an administrator
                        }else if(userTypeTmp.equals(params.TestParams.userTypeAdministrator)) {
                            return d.getTitle().contains(params.TestParams.userLoggedPageTitle);
                        }else{
                            return false;
                        }
                    }
                }
            );

        }catch (Error e) {            
            Logger.info("PETA");
            return 0;
        }

        return 1;
    }


}
