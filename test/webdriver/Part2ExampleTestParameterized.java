package webdriver;

import junit.framework.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import static org.junit.matchers.JUnitMatchers.containsString;

import webdriver.pages.Part2ExamplePage;

import play.test.*;

import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)

/**
 * Part2ExampleTest uses original WebDriver PageFactory.
 * This process is smoother than the previous example.
 *
 * @see <a href="http://elisarver.com/blog/2012/12/09/wrapping-webelement-1/">Wrapping WebElement Part 1</a>
 */
public class Part2ExampleTestParameterized extends UnitTest {
    @Parameter(0)
    public String height;

    @Parameter(1)
    public String weight;

    @Parameter(2)
    public String bmi;

    @Parameter(3)
    public String bmiCategory;

    @Parameters
    public static List<Object[]> parameters() {
	return Arrays.asList(
		new Object[][] {
			{"160","45","17.6","Underweight"},
			{"168","70","24.8","Normal"},
			{"181","89","27.2","Overweight"},
			{"178","100","31.6","Obesity"}
		}
	);
    }

    @Test
    public void someTest() {
        System.out.println("height: " + height + " weight: " + weight + " bmi: " + bmi + " bmiCategory: " + bmiCategory);
    }

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
