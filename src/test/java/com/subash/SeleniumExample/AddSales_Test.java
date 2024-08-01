package com.subash.SeleniumExample;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

class AddSales_Test {

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
	void addSales() throws InterruptedException {
		driver.get("http://localhost:3000");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Login")).click();
	    driver.findElement(By.name("email")).sendKeys("ssubash56781@gmail.com");
	    driver.findElement(By.name("password")).sendKeys("123456");
	    driver.findElement(By.name("L-btn")).click();
	    Thread.sleep(4000);
	    driver.findElement(By.linkText("ADD SALES")).click();
	    Thread.sleep(4000);
		driver.findElement(By.id("saledate")).sendKeys("0012-12-12");
		driver.findElement(By.id("quantitysold")).sendKeys("12");
	}

	@Test
	void inValid_AddSales() throws InterruptedException {
		driver.get("http://localhost:3000");
		driver.manage().window().maximize();
		driver.findElement(By.linkText("Login")).click();
	    driver.findElement(By.name("email")).sendKeys("ssubash56781@gmail.com");
	    driver.findElement(By.name("password")).sendKeys("123456");
	    driver.findElement(By.name("L-btn")).click();
	    Thread.sleep(4000);
	    driver.findElement(By.linkText("ADD SALES")).click();
	    Thread.sleep(4000);
		driver.findElement(By.id("saledate")).sendKeys("");
		driver.findElement(By.id("quantitysold")).sendKeys("");

		driver.findElement(By.linkText("ADD SALES")).click();
	}
}
