import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.internal.EventFiringMouse;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RunBingBot {
	private static WebDriver driver;
	private static ArrayList<IPaddress> IPs = new ArrayList<IPaddress>();
	@BeforeClass
    public static void setup(){
	   	 System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ben\\Downloads\\chromedriver_win32\\chromedriver.exe");
	     driver = new ChromeDriver();
    }
	@Test
	public void botmain() throws Exception{
		try{
			signUpForYahoo(new Account("Joe", "Regular", "TheDoctor90210", 0));
		} catch (Exception e){
			
		}
//		 ArrayList<IPaddress> ips = InfoGenerator.deserialize("Accounts.txt");
//		 for(IPaddress ip : ips){
//			 for(Account a : ip.accounts){
//				 signUpForYahoo(a);
//			 }
//		 }
	}
	
	public void signIn(String email, String password){
		 driver.get("https://login.live.com/login.srf?wa=wsignin1.0&rpsnv=12&ct=1430583495&rver=6.0.5286.0&wp=MBI&wreply=https:%2F%2Fwww.bing.com%2Fsecure%2FPassport.aspx%3Frequrl%3Dhttp%253a%252f%252fwww.bing.com%252fsearch%253fq%253dtest%252c%252btest%2526qs%253dbs%2526form%253dQBLH%2526wlexpsignin%253d1&lc=1033&id=264960");
		 WebElement emailbox = driver.findElement(By.name("login"));
		 WebElement passwordbox = driver.findElement(By.name("passwd"));
		 WebElement signInButton = driver.findElement(By.name("SI"));
		 emailbox.sendKeys(email);
		 passwordbox.sendKeys(password);
		 try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		 signInButton.click();
	}
	
	public void setupAll(){
		
	}

	public void signUpForYahoo(Account a){
		try{
		 driver.get("https://edit.yahoo.com/registration");
		} catch (Exception e){
			signUpForYahoo(a);
		}
		 try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		 WebElement firstNameBox = driver.findElement(By.id("first-name"));
		 WebElement lastNameBox = driver.findElement(By.id("last-name"));
		 WebElement emailBox = driver.findElement(By.id("user-name"));
		 WebElement passwordBox = driver.findElement(By.name("password"));
		 WebElement phoneBox = driver.findElement(By.id("mobile"));
		 WebElement monthBox = driver.findElement(By.id("month"));
		 WebElement dayBox = driver.findElement(By.id("day"));
		 WebElement yearBox = driver.findElement(By.id("year"));
		 WebElement maleButton = driver.findElement(By.id("male"));
		 firstNameBox.sendKeys(a.getFirstName());
		 lastNameBox.sendKeys(a.getLastName());
		 emailBox.sendKeys(a.getEmail());
		 passwordBox.sendKeys(a.getPassword());
		 phoneBox.sendKeys("4258258322");
		 monthBox.click();
		 monthBox.sendKeys(Keys.ARROW_DOWN);
		 dayBox.click();
		 dayBox.sendKeys(Keys.ARROW_DOWN);
		 yearBox.click();
		 for(int i = 0; i < 45; i ++){
			 yearBox.sendKeys(Keys.ARROW_DOWN);
			 try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		 }
		 maleButton.click();
		 passwordBox.click();
		 passwordBox.sendKeys(Keys.ENTER);
	}
	
	public void signUpForAmazon(){
		
	}
	
	public void signUpForMicrosoft(){
		
	}
	
	public void signUpForBingRewards(){
		
	}
}

