import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class RunBingBot {
	private static WebDriver driver;
	private static ArrayList<IPaddress> IPs = new ArrayList<IPaddress>();
	public static void main(String[] args){
		setup();
		// ArrayList<IPaddress> ips = InfoGenerator.deserialize("Accounts.txt");
		// int done = 0;
		// for(int i = 3; i < ips.size(); i++){
		// System.out.println("------------Connecting to Server in " +
		// ips.get(i).getCountry() + "------------");
		// String PROXY = ips.get(i).getIP() + ":" + ips.get(i).getPort();
//			String PROXY = "24.157.37.61:8080";
//			Proxy proxy = new Proxy();
//			proxy.setHttpProxy(PROXY).setFtpProxy(PROXY).setSslProxy(PROXY);
//			DesiredCapabilities cap = new DesiredCapabilities();
//			cap.setCapability(CapabilityType.PROXY, proxy);
		driver = new ChromeDriver();
		signIn("bzabback@hotmail.com", "Cimpala123");
		search30Times();
		driver.close();
		updateStatus("Shutting down");
		// //for(int j = 0; j < 5; j++){
		// signUpForYahoo(ips.get(i).getAccounts().get(j));
		// System.out.println(ips.get(i).getAccounts().get(j));
		// done++;
		// System.out.println("Time: " + ((double)System.nanoTime()/1000000000)
		// + "\nCompleted: " + done + "\nRate: " +
		// ((double)done/(double)System.nanoTime()) * 1000000000);
		// //}
		// driver.close();
		// }
	}
	
	public static void setup() {
		updateStatus("Starting web driver");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ben\\.eclipse\\chromedriver.exe");
	}

	public static void signIn(String email, String password) {
		updateStatus("Signing in to Microsoft account");
		driver.get("https://login.live.com/login.srf?wa=wsignin1.0&rpsnv=12&ct=1430583495&rver=6.0.5286."
				+ "0&wp=MBI&wreply=https:%2F%2Fwww.bing.com%2Fsecure%2FPassport.aspx%3Frequrl%3Dhttp%253a%252f%252f"
				+ "www.bing.com%252fsearch%253fq%253dtest%252c%252btest%2526qs%253dbs%2526form%253dQBLH%2526wlexp"
				+ "signin%253d1&lc=1033&id=264960");
		waitForElementToLoad(By.name("login"));
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

	public static void search30Times() { //searches 30 times, as many searches as is allowed per day
		updateStatus("Beginning search");
		Random r = new Random();
		WebElement searchBox = waitForElementToLoad(By.name("q"));
		searchBox.sendKeys(InfoGenerator.getRandomName() + Keys.ENTER);
		int extra = r.nextInt(30);
		for (int i = 0; i < 25 + extra; i++) {
			updateStatus("Searching");
			WebElement suggestedResultTable = null;
			int j = 1;
			while (j < 5) { // If there are suggested results click the first
							// one
				suggestedResultTable = tryToGetElement(By.xpath("//*[@id=\"b_context\"]/li[" + j + "]/h2"));
				if (suggestedResultTable != null && suggestedResultTable.getText().equals("Related searches")) {
					break;
				}
				j++;
			}
			if (suggestedResultTable != null) {
				updateStatus("Waiting before clicking related search result");
				sleep(2000);
				if (tryToGetElement(By.xpath("//*[@id=\"b_context\"]/li[" + j + "]/ul/li[1]/a")) != null) {
					try{
						tryToGetElement(By.xpath("//*[@id=\"b_context\"]/li[" + j + "]/ul/li[1]/a")).click();
					}catch(WebDriverException e){
						driver.get("http://bing.com");
						searchBox = waitForElementToLoad(By.name("q"));
						searchBox.sendKeys(InfoGenerator.getRandomName() + Keys.ENTER);
						System.gc();
					}
				}
			} else {// no suggestions, so click second result and go back to
					// bing.com
				if (tryToGetElement(By.xpath("//*[@id=\"b_results\"]/li[2]/h2/a")) != null) {
					driver.findElement(By.xpath("//*[@id=\"b_results\"]/li[2]/h2/a")).click();
					updateStatus("Waiting on current webpage before next search");
					sleep(1000 + r.nextInt(1800000));// wait for 1 second to 30 minutes
													// before next search
				}
				driver.get("http://bing.com");
				searchBox = waitForElementToLoad(By.name("q"));
				searchBox.sendKeys(InfoGenerator.getRandomName() + Keys.ENTER);
				System.gc();
			}
		}
		updateStatus("Searching complete");
	}

	public static void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void signUpForYahoo(Account a) {
		try {
			driver.get("https://edit.yahoo.com/registration");
		} catch (Exception e) {
			signUpForYahoo(a);
		}
		sleep(1000);
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
		for (int i = 0; i < 45; i++) {
			yearBox.sendKeys(Keys.ARROW_DOWN);
		}
		yearBox.sendKeys(Keys.ENTER);
		maleButton.sendKeys(Keys.SPACE);
		passwordBox.sendKeys(Keys.ENTER);
		sleep(1000);
		// TODO: INSERT CAPTCHA BOX XPATH
		// waitForElementToLoadByXPath();
	}

	public void signUpForAmazon() {

	}

	public void signUpForMicrosoft() {

	}

	public void signUpForBingRewards() {

	}

	public static WebElement waitForElementToLoad(By arg) {
		int i = 0;
		while (tryToGetElement(arg) == null) {
			sleep(10);
			i++;
			if (i >= 20000) {
				throw new Error("Error in: RunBingBot - waitForElementToLoad(By arg) - line 155:"
						+ "\nProgram Terminated because page took more than 3 minutes to load");
			}
		}
		return tryToGetElement(arg);
	}

	private static WebElement tryToGetElement(By arg) {// returns element if it exists,
												// otherwise returns null
		try {
			WebElement x = driver.findElement(arg);
			return x;
		} catch (Exception e) {
			return null;
		}
	}
	
	private static void updateStatus(String message){
		Date d = new Date();
		System.out.println(d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds() + " --- " + message);
		System.gc();
	}
	
	
	
}
