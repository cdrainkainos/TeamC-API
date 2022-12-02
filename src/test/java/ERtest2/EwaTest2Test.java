package com.kainos.ea.UI;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNot.not;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import java.util.*;
import java.net.MalformedURLException;
import java.net.URL;
import static org.openqa.selenium.remote.CapabilityType.BROWSER_VERSION;
public class EwaTest2Test {
  private WebDriver driver;
  protected final static String browserVersion = System.getProperty(BROWSER_VERSION);
  private Map<String, Object> vars;
  JavascriptExecutor js;
  @Before
  public void setUp() {
    WebDriverManager.chromedriver().browserVersion(browserVersion).setup();
    driver = new ChromeDriver();
    js = (JavascriptExecutor) driver;
    vars = new HashMap<String, Object>();
  }
  @After
  public void tearDown() {
    driver.quit();
  }
  @Test
  public void ewaTest2() {
    driver.get("http://localhost:3000/jobRoles");
    assertEquals("Principle Data Architect",driver.findElement(By.id("1")).getText());
    //find 1st job role and click on it
    driver.findElement(By.id("1")).click();
    //find link to sharepoint
    //assertEquals("As a Principal Data Architect in Kainos, youll be accountable for successful delivery of data solutions across multiple customers",driver.findElement(By.xpath(html[1]/body[1]/table[1]/tbody[1]/tr[2]/td[2]/a[1]).getText()));
//    driver.findElement(By.cssSelector("td:nth-child(1)")).getText("As a Principal Data Architect in Kainos, youll be accountable for successful delivery of data solutions across multiple customers");
//    driver.findElement(By.linkText("View on Sharepoint")).click();
    driver.close();
  }
}
