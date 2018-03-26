import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.activation.DataHandler;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;


public class Calendar_demo {

	public static WebDriver driver;
	
	
	
	// TODO Auto-generated method stub
String randomname = RandomStringUtils.randomAlphabetic(4);
String storename = randomname;
	
@BeforeSuite
public void setup()
{
	System.setProperty("webdriver.chrome.driver","D:\\selenium\\chromedriver_win32\\chromedriver.exe");
	driver = new ChromeDriver();
	
	//Maximize Chrome
	driver.manage().window().maximize();

	//Set URL to Open 
    String baseUrl = "https://www.nseindia.com/products/content/equities/equities/archieve_eq.htm";
   
    // launch Chrome and direct it to the Base URL
    driver.get(baseUrl);
}

//-------------------------------- Login in QBS
@Test (priority = 1)
public void Login() throws InterruptedException
{
	WebDriverWait wait=new WebDriverWait(driver, 20);
	
	//Password Input
	driver.findElement(By.xpath(".//*[@id='wrapper_btm']/div[1]//input[3]")).click();
	Thread.sleep(3000);
	
}
	
}
