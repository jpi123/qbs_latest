import java.util.List;

import javax.swing.JOptionPane;

import junit.framework.Assert;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class QBS_Login {

	public static WebDriver driver;
	String randomname = RandomStringUtils.randomAlphabetic(4);
	String userfirstname = RandomStringUtils.randomAlphabetic(4);
	String usertempfirstnamestore = userfirstname;
	String userlastname = RandomStringUtils.randomAlphabetic(3);
	String namestore = randomname;
		
		@BeforeSuite
		public void setup()
		{
			System.setProperty("webdriver.chrome.driver","D:\\selenium\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
			
			//Maximize Chrome
			driver.manage().window().maximize();
		
			//Set URL to Open 
	        String baseUrl = "http://192.168.1.182:876/";
	       
	        // launch Chrome and direct it to the Base URL
	        driver.get(baseUrl);
		}
		
		//-------------------------------- Login in QBS
		@Test (priority = 1)
		public void Login() throws InterruptedException
		{
			WebDriverWait wait=new WebDriverWait(driver, 20);
			Thread.sleep(3000);
			//Email Input
			driver.findElement(By.xpath(".//*[@id='Email']")).sendKeys("admin@trimantrallp.com");
			Thread.sleep(3000);
			//Password Input
			driver.findElement(By.xpath(".//*[@id='Password']")).sendKeys("123456");
			Thread.sleep(3000);
		
			//Click on Submit
			WebElement submitbtn;
			submitbtn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='loginForm']/input[2]")));
			submitbtn.click();
				
		}
		
		//------------------------------- Change Password Code
		@Test (priority = 2)
		public void Changepassword()
		{
			if(driver.getTitle().contains("QBSV3 | Change Password"))
			{
				//Pass
				System.out.println("Page title contains \"some expected text\" ");
				
				//Enter Old Password
				driver.findElement(By.xpath(".//*[@id='OldPassword']")).sendKeys("123456");
				
				//Enter New Password
				driver.findElement(By.xpath(".//*[@id='NewPassword']")).sendKeys("123456");
				
				//Enter Confirm Password
				driver.findElement(By.xpath(".//*[@id='ConfirmPassword']")).sendKeys("123456");
				
				//Submit ButtonClick
				driver.findElement(By.xpath(".//*[@id='changePasswordForm']/input[2]")).click();
				
			}	
			else
			{
				//Fail
				Assert.fail();
			}
		}	
		
		//------------------------------------ Admin adding new Reseller
		@Test (priority = 3)
		public void  AddReseller() throws InterruptedException
		{
			try 
			{
				// Click on Reseller Menu
				Thread.sleep(3000);
				driver.findElement(By.xpath(".//*[@id='cssmenu']/ul/li[2]/a")).click();
				
				//Wait for loader
				Thread.sleep(3000);
				
				//Matching Text
				String expectedresult = "RESELLER";
				String getreseller  = driver.findElement(By.xpath(".//*[@class='container']/h2")).getText();
				System.out.println(getreseller);
				
				if (getreseller.equals(expectedresult))
				{
					System.out.println("Pass");
				}
				else
				{
					Assert.fail();
				}
				
				// Click on Add New Button
				driver.findElement(By.xpath(".//a[@href='/Reseller/Add']")).click();
				Thread.sleep(3000);
				
				//Entering New Name
				driver.findElement(By.xpath(".//*[@id='ResellerName']")).sendKeys("Reseller" + namestore);
				Thread.sleep(2000);
				
				//Entering Email
				driver.findElement(By.xpath(".//*[@id='Email']")).sendKeys(namestore + "@nomail.com");
				
				// Scroll Down
				JavascriptExecutor jse = (JavascriptExecutor)driver;
		        jse.executeScript("scroll(0, 250);");
		        
		        //Submit on Reseller
		        driver.findElement(By.xpath(".//*[@id='resellerForm']//button")).click();
		        Thread.sleep(5000);
		        
		        //Check for Success Retailer Added Message
		        String resellersucccessmessage = driver.findElement(By.xpath(".//*[@id='ResultBox']")).getText();
		        System.out.println(resellersucccessmessage);
		        if(resellersucccessmessage.equals("Reseller detail added successfully."))
		        {
		        	System.out.println("Case Passed : Reseller Added Successsfully");
		        }
		        else
		        {
		        	Assert.fail();
		        }
		        
			}
					catch (Exception e) 
					{
						Assert.fail();
					}
		}
		
		//--------------------------------- Adding New Owner/User/SalesRep/Manager
		@Test(priority=4)
		public void AddUser() throws InterruptedException
		{
			try {
				//Click on User Menu
				driver.findElement(By.xpath(".//*[@id='cssmenu']/ul/li[3]/a")).click();
				Thread.sleep(3000);
			
				//Click on Add New Button
				driver.findElement(By.xpath(".//*[@id='btnAddUser']")).click();
				Thread.sleep(3000);
				
				//Select Reseller from Dropdown
		        driver.findElement(By.xpath("//span[@aria-labelledby='select2-ResellerID-container']")).click();
		        Thread.sleep(3000); 
		        //Enter input
		        driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys("Reseller" + namestore,Keys.ENTER);
		        Thread.sleep(3000);
		        
		        //Input FirstName
		        driver.findElement(By.xpath(".//*[@id='FirstName']")).sendKeys("User" + userfirstname);
		        
		        //Input Lastname 
		        driver.findElement(By.xpath(".//*[@id='LastName']")).sendKeys(userlastname);
		        
				//Input Email
		        driver.findElement(By.xpath(".//*[@id='Email']")).sendKeys("User" + namestore + "@nomail.com" );
		        
		        //Select Role
		        driver.findElement(By.xpath("//span[@aria-labelledby='select2-RoleIDs-container']")).click();
		        Thread.sleep(3000); 
		        //Enter input for Role
		        driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys("Owner",Keys.ENTER);
		        Thread.sleep(3000);
		        
		        //Click on Submit
		        driver.findElement(By.xpath(".//*[@id='UserForm']/div[6]/div/button")).click();
		        Thread.sleep(5000);
		        
		        //Getting Success Message
		        String usersuccessmessage = driver.findElement(By.xpath(".//*[@id='ResultBox']")).getText();
		        System.out.println(usersuccessmessage);
		        
		        	if(usersuccessmessage.equals("User detail added successfully."))
		        		{
		        			System.out.println("Case 1: User Added Successfully");
		        		}
		        	else
		        		{
		        			Assert.fail();
		        		}
		        
				}
				catch (Exception e) 
				{
					Assert.fail();
				}
		}
		   
	     //-------------------------------- Adding New Client
			@Test (priority = 5)
	        public void AddClient() throws InterruptedException
			{
				   String randomname = RandomStringUtils.randomAlphabetic(3);
			       String storename = randomname;
			       System.out.println(storename);
				// Client Input Dialog Box
				String inputclient= JOptionPane.showInputDialog("Number of Clients you want to Add?");
			    int client = Integer.parseInt(inputclient);
		        for(int i=0;i<client;i++)
		        { 	
		        	//Click on Client Menu
		        	Thread.sleep(3000);
			        driver.findElement(By.xpath(".//*[@id='cssmenu']/ul/li[4]/a")).click();
			    	
			        //Click on Add New
			        Thread.sleep(3000);
			        driver.findElement(By.xpath(".//a[@href='/Client/Add']")).click();
			        Thread.sleep(3000);
			    
			        //Select Reseller from Dropdown
			        driver.findElement(By.xpath("//span[@aria-labelledby='select2-ResellerID-container']")).click();
			        Thread.sleep(3000);
			        
			        //Enter input
			        driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys("Reseller" + namestore,Keys.ENTER);
			        Thread.sleep(3000);
			        
			        //Select Assign To
			        driver.findElement(By.xpath(".//span[@aria-labelledby='select2-AssignTo-container']")).click();
			        Thread.sleep(3000);  
			        //Enter Input
			        driver.findElement(By.xpath("html/body/span/span/span[1]/input")).sendKeys("User" + usertempfirstnamestore, Keys.ENTER);
			        Thread.sleep(3000);
			        
			        //Input Primary Contact
			        driver.findElement(By.xpath(".//*[@id='PrimaryContact']")).sendKeys("John" + storename);
			        
			        //Input Email
			        driver.findElement(By.xpath(".//*[@id='Email']")).sendKeys("John" + storename + "@nomail.com");
			        
			        //Input Company Name
			        driver.findElement(By.xpath(".//*[@id='CompanyName']")).sendKeys("John" + storename + "Company");
			        
			        //Input Number
			        driver.findElement(By.xpath(".//*[@id='Phone']")).sendKeys("9856985498");
			        
			        // Scroll Down
			     	JavascriptExecutor jse = (JavascriptExecutor)driver;
			        jse.executeScript("scroll(0, 250);");
			        
			        //Submit Button Click
			        driver.findElement(By.xpath(".//input[@type='submit']")).click();
			        Thread.sleep(5000);
			        
			        //Check Success Message for Client Successfully Added
			        //Get Success Message
			        String Clientsuccessmessage = driver.findElement(By.xpath(".//*[@id='ResultBox']")).getText();
			        System.out.println(Clientsuccessmessage);
			        
			        	if(Clientsuccessmessage.equals("Client detail added successfully."))
			        		{
			        			System.out.println("Case 2: Client Added Successfully");
			        		}
			        		else
			        		{
			        			Assert.fail();
			        		}
			        }
			    
			        //Enter text on search field
			        driver.findElement(By.xpath(".//*[@id='ClientGrid_filter']/label/input")).sendKeys("John" + storename + "@nomail.com");
			        Thread.sleep(3000);
			        
			        //Click on Client Name  Link
			        driver.findElement(By.xpath(".//*[@id='ClientGrid']/tbody/tr/td[2]/a")).click();
			        Thread.sleep(3000);
			        
			        //Click on Agreement Link
			        driver.findElement(By.xpath(".//div[1]/div[2]/a[2]")).click();
			        Thread.sleep(3000);
			        
	        //------- For loop for Add Agreement ---------
			    // Agreement Input Dialog Box     
			    String inputagreement= JOptionPane.showInputDialog("Number of Arguments you want to Add?");
			    int arg = Integer.parseInt(inputagreement);
		        for (int k=0;k<arg;k++)
		        {	
		        	try   
        	 		  {   
		        	//Click on Add Agreement Button
			        if(driver.findElement(By.xpath(".//*[@id='btnAddAgreement']")).isDisplayed())
			       	{
			        	driver.findElement(By.xpath(".//*[@id='btnAddAgreement']")).click();
			        	System.out.println("Got link");
			  		}
		        	  } 
		        	 catch(Exception e)     
		        	  {       
		        		 	Thread.sleep(3000);
				        	System.out.println("Not Got link");
					        driver.findElement(By.xpath(".//*[@id='cssmenu']/ul/li[4]/a")).click();
					        
					        //Enter text on search field
					        driver.findElement(By.xpath(".//*[@id='ClientGrid_filter']/label/input")).sendKeys("John" + storename + "@nomail.com");
					        Thread.sleep(3000);
					        
					        //Click on Client Name  Link
					        driver.findElement(By.xpath(".//*[@id='ClientGrid']/tbody/tr/td[2]/a")).click();
					        
					        //Click on Agreement Link
					        driver.findElement(By.xpath(".//div[1]/div[2]/a[2]")).click();
					        Thread.sleep(3000);
					        
					        //Click on Add Agreement Button
					        driver.findElement(By.xpath(".//*[@id='btnAddAgreement']")).click();
		        	  }  
			       
			        //Enter Agreement Start date
			        Thread.sleep(3000);
			        WebElement date = driver.findElement(By.xpath(".//*[@id='StartDate']"));
			        date.click();
			        WebElement searchdate = driver.findElement(By.xpath("//div[7]//table/tbody/tr[1]/td[5]"));
			        searchdate.click();
			        Thread.sleep(3000);
			         
			        //Enter Agreement End date
			        WebElement enddate = driver.findElement(By.xpath(".//*[@id='EndDate']"));
			        enddate.click();
			        WebElement searchendate = driver.findElement(By.xpath("//div[7]//table/tbody/tr[5]/td[4]"));
			        searchendate.click();
			        Thread.sleep(3000);
			        
			        //Click Toggle On Button
			        driver.findElement(By.xpath(".//*[@id='Add']//div/label[2]")).click();
			        Thread.sleep(3000);
			        
			        //Click on Submit Button
			        driver.findElement(By.xpath(".//*[@id='Add']//div[7]//input")).click();
			        Thread.sleep(5000);
			        
			        //Checking Successfully Agreement Added
			        String successagreeement = driver.findElement(By.xpath(".//*[@id='ResultBox']")).getText();
			        System.out.println(successagreeement);
			        
			        if(successagreeement.equals("Agreement detail added successfully."))
			        {
			        	System.out.println("Case 3: Agreement Detail Added Successfully");
			        }
			        else
			        {
			        	Assert.fail();
			        }

			        //------------- For Loop for Campaign Adding
		             
			      //No. of Columns
			        List  col = driver.findElements(By.xpath(".//*[@id='AgreementGrid']/thead/tr/th"));
			        System.out.println("Total No of columns are : " +col.size());
			        
			      //No.of rows
			        List <WebElement> rows = driver.findElements(By.xpath (".//*[@id='AgreementGrid']/tbody/tr/td[1]"));
			       
			        for (int o=0;o<rows.size();o++)
			        {    
			        	String findzero = driver.findElement(By.xpath(".//*[@id='AgreementGrid']/tbody/tr[" + (o+1)+ "]/td[6]")).getText();
			        	Thread.sleep(5000);
		        		int iTest = Integer.parseInt(findzero);
		        		int value = 0;
		        		
			            if(iTest == value)
			            {
			            		//Click on Campaign Link 0 Button
			            		System.out.println("Entered in if loop");
			            		driver.findElement(By.xpath(".//*[@id='AgreementGrid']/tbody/tr[" + (o+1)+ "]/td[6]/a")).click();
			            		Thread.sleep(10000);
			            	 
			            	 	//Campaign User Input Dialog Box
							    String inputcampaign= JOptionPane.showInputDialog("Number of Campaigns you want to Add?");
							    int camp = Integer.parseInt(inputcampaign);
							    for(int l=0;l<camp;l++)
							     {	        
							        //Click on Add new campaign Button
							        driver.findElement(By.xpath(".//*[@id='btnAddCampaign']")).click();
							        Thread.sleep(3000);
							        
							        //Campaign Detail Page Add Date
							        Thread.sleep(3000);
							        WebElement campdate = driver.findElement(By.xpath(".//*[@id='StartDate']"));
							        campdate.click();
							        WebElement campsearchstartdate = driver.findElement(By.xpath("//div[6]//table/tbody/tr[3]/td[2]"));
							        campsearchstartdate.click();
							        Thread.sleep(3000);
							        
							        //Campaign Detail End date
							        WebElement campenddate = driver.findElement(By.xpath(".//*[@id='EndDate']"));
							        campenddate.click();
							        WebElement campsearchendate = driver.findElement(By.xpath("//div[6]//table/tbody/tr[3]/td[6]"));
							        campsearchendate.click();
							        Thread.sleep(3000);
							        
							        // Scroll Down
							     	JavascriptExecutor js = (JavascriptExecutor)driver;
							        js.executeScript("scroll(0, 1000);");
							        Thread.sleep(3000);
							        
							        //Click on Submit
							        driver.findElement(By.xpath(".//button[@type='submit']")).click();			        
							        Thread.sleep(5000);
							        
							        //Check Successfully Campaign Added Message
							        String campaignsuccessmessage = driver.findElement(By.xpath(".//*[@id='ResultBox']")).getText();
							        System.out.println(campaignsuccessmessage);
							        
							        if(campaignsuccessmessage.equals("Campaign added successfully."))
							        {
							        	System.out.println("Case 3: Campaign Added Successfully");
							        }
							        else
							        {
							        	Assert.fail();
							        	
							        }
							    }	    	
			            
			            }
			        }    
		        }   
		        //Closing the Browser Automatically
		          driver.close();
		        }    	
	      }