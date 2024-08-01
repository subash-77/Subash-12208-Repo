package com.subash.SeleniumExample;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class UpdateJewelery_Test {
	private WebDriver driver;

	@BeforeEach
	public void setUp() {
		driver = new ChromeDriver();

	}

	@AfterEach
	public void tearDown() {
		driver.quit();
	}

	@Test
	void updateJewelry() throws InterruptedException {
		driver.get("http://localhost:3000");
		driver.findElement(By.linkText("Login")).click();
	    driver.findElement(By.name("email")).sendKeys("ssubash56781@gmail.com");
	    driver.findElement(By.name("password")).sendKeys("123456");
	    driver.findElement(By.name("L-btn")).click();
	    Thread.sleep(4000);
	    driver.findElement(By.linkText("VIEW JEWELRY")).click();
		driver.get("http://localhost:3000/editjewelry/29");
		driver.manage().window().maximize();
		driver.findElement(By.id("name")).sendKeys("Gold Ring");
		driver.findElement(By.id("description")).sendKeys("Comfortable");
		driver.findElement(By.id("price")).sendKeys("20000");
		Thread.sleep(5000);
		driver.findElement(By.id("stock")).sendKeys("3");

	}
}
