package webdriver;

import junit.framework.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.matchers.JUnitMatchers.containsString;

import webdriver.pages.Part2ExamplePage;

import play.test.*;

/**
 * Part2ExampleTest uses original WebDriver PageFactory.
 * This process is smoother than the previous example.
 *
 * @see <a href="http://elisarver.com/blog/2012/12/09/wrapping-webelement-1/">Wrapping WebElement Part 1</a>
 */
public class Part2ExampleTest extends UnitTest {
    @Test
    public void simple() {
        WebDriver localDriver = new HtmlUnitDriver();
        Part2ExamplePage page = new Part2ExamplePage(localDriver);

	localDriver.get("https://www.google.es");

//        Assert.assertFalse(page.checkBox.isChecked());
//        page.checkBox.check();
//        Assert.assertTrue(page.checkBox.isChecked());

	page.textInput.set("cats");
        assertThat(page.textInput.getText(), containsString("cats") );
	page.textInput.submit();
        assertThat(localDriver.getTitle(), containsString("cats") );


        localDriver.close();
    }
}
