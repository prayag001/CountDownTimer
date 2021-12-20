package StepDefinations;

import java.io.IOException;

import org.testng.annotations.BeforeTest;

import TestRunner.Runner;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import lib.resources.custFns;

public class TimerCucumber extends Runner{

	public static custFns custFns;
	String url = null;
	int counterVal;

	@BeforeTest(alwaysRun = true)
	public void setUp() {
		custFns = new custFns();
	System.out.println("Hellloo");
	}
	

	@Given("^browser is open$")
	public void browser_is_open() throws Throwable {
		System.out.println("Launching browser" + "...");

		/* navigating to URL */
		url = custFns.getData().getProperty("url");
	  
	}

	@And("^user is on home page$")
	public void user_is_on_home_page() throws IOException {

		System.out.println("Navigating to URL " + url);
		custFns.startApp(url);

	}

	@When("^user enters timer value in text field$")
	public void user_enters_timer_value_in_text_field() throws IOException {

		/* sending count down timer value in text field */
		custFns.sendValue();

		counterVal = custFns.counterValue();
		System.out.println("Countdown Timer Value entered by User is " + counterVal + " Sec");

		custFns.submit();
	}

	@Then("^verify time is decreased every second till timer is expired$")
	public void verify_time_is_decreased_every_second_till_timer_is_expired() {

		int countToZero = custFns.countDownToZero();
		System.out.println("Verifying countDown Timer Value is decreased to zero every second :- ");

		/*
		 * check if timer value entered by user is decreased by 1 Sec and equals to zero
		 */
		for (int i = counterVal; i >= countToZero; i--) {
			System.out.println("Time remaining is = " + i + " Sec");
		}

	}

	@And("^alert pop is displayed$")
	public void alert_pop_is_displayed() {

		custFns.alertCheck();
		String timeExpired = custFns.verifyTimeExpried();
		System.out.println(timeExpired);
	}

}
