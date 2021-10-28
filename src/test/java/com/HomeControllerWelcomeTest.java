package com;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.containsString;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import io.github.bonigarcia.wdm.managers.ChromeDriverManager;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomeControllerWelcomeTest {


	private WebDriver driver;
	
	@LocalServerPort
	private int port;
	
	@BeforeEach
	public void setUp() {
		ChromeDriverManager.getInstance().setup();
		driver = new ChromeDriver();
	}
	
	
	@Test
	public void welcomePageTest() {
		driver.get(String.format("http://127.0.0.1:%s", port));
		assertThat(driver.findElement(By.tagName("body")).getText(), containsString("Welcome, Mohammad Tufail Ahmed."));
	}



}
