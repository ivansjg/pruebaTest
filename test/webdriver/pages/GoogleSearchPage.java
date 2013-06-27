package webdriver.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.FindBy;

import play.Logger;

public class GoogleSearchPage {

	protected WebDriver driver;
	
	@FindBy(name="qno")
	private WebElement searchField;	

	@FindBy(name="btnK")
	private WebElement searchButton;

	public GoogleSearchPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public void open(String url) {
		driver.get(url);
	}

	public void close() {
		driver.quit();
	}
	
	public String getTitle() {
		return driver.getTitle();
	}

	public void searchFor(String searchTerm) {
//Logger.info("[*] %s", driver.getPageSource());
		searchField.sendKeys(searchTerm);
//		searchButton.click();
		searchField.submit();
	}

	public void typeSearchTerm(String searchTerm) {
		searchField.sendKeys(searchTerm);
	}
	
	public void clickOnSearch() {
//		searchButton.click();
		searchField.submit();
	}
}
