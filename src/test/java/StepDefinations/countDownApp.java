package StepDefinations;


import java.io.IOException;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import lib.resources.custFns;

public class countDownApp {
	
	public static custFns custFns;

	@BeforeTest(alwaysRun = true)
	public void setUp() {
		custFns = new custFns();

	}

	@Test
	public void Timer() throws InterruptedException, IOException {

		System.out.println("Launching browser" + "...");

		/*navigating to URL*/
		String url = custFns.getData().getProperty("url");
		System.out.println("Navigating to URL " + url);
		custFns.startApp(url);
		
		/*sending count down timer value in text field*/
		custFns.sendValue();
		
		int counterVal = custFns.counterValue();
		System.out.println("Countdown Timer Value entered by User is " + counterVal + " Sec");
		
		custFns.submit();
		
		int countToZero = custFns.countDownToZero();
		System.out.println("Verifying countDown Timer Value is decreased to zero every second :- ");

		/*check if timer value entered by user is decreased by 1 Sec and equals to zero*/
		for (int i = counterVal; i >= countToZero; i--) {
			System.out.println("Time remaining is = " + i + " Sec");
		}
	}

	@Test (dependsOnMethods="Timer")
	public void finalCheck() {

		custFns.alertCheck();
		String timeExpired = custFns.verifyTimeExpried();
		System.out.println(timeExpired);
	}
	
	@AfterTest(alwaysRun = true)
	public void tearDown() {
		lib.resources.custFns.driver.quit();
	}

}
