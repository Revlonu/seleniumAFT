import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class homeworkseleniumone {

    private WebDriver driver;
    private WebDriverWait wait;

    private final static String BASE_URL = "https://www.rgs.ru/";

    @Before
    public void StartUp(){
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        wait = new WebDriverWait(driver, 10);
        // Перейти по ссылке http://www.rgs.ru
        driver.get(BASE_URL);
    }

    @Test
    public void testScenario() throws InterruptedException {
        // Выбрать Меню
        WebElement menu = driver.findElement(By.xpath("//a[@data-toggle='dropdown']"));
        menu.click();
        //Выбрать категорию - ДМС
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'ДМС')]")));
        WebElement dms = driver.findElement(By.xpath("//a[contains(text(),'ДМС')]"));
        dms.click();
        // Проверить наличие заголовка - Добровольное медицинское страхование
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'добровольное медицинское страхование')]")));
        // Нажать на кнопку - Отправить заявку
        WebElement sendRequest = driver.findElement(By.xpath("//a[contains(text(),'Отправить заявку')]"));
        sendRequest.click();
        //Проверить, что открылась страница , на которой
        //присутствует текст - Заявка на добровольное
        //медицинское страхование
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//b[contains(text(),'Заявка на добровольное медицинское страхование')]")));
        //7.Заполнить поля
        //Имя, Фамилия, Отчество, Регион, Телефон,
        //Эл. почта - qwertyqwerty,
        //Комментарии, Я согласен на обработку
        String stringLastName =  "Мартаков";
        WebElement lastName = driver.findElement(By.xpath("//input[@name='LastName']"));
        lastName.sendKeys(stringLastName);

        String stringFirstName =  "Александр";
        WebElement firstName = driver.findElement(By.xpath("//input[@name='FirstName']"));
        firstName.sendKeys(stringFirstName);

        String stringMiddleName =  "Владимирович";
        WebElement middleName = driver.findElement(By.xpath("//input[@name='MiddleName']"));
        middleName.sendKeys(stringMiddleName);

        WebElement regoinSelect = driver.findElement(By.xpath("//select[@name='Region']"));
        Select select = new Select(regoinSelect);
        select.selectByIndex(1);

        String stringPhone =  "9999999999";
        WebElement phone = driver.findElement(By.xpath("//*[@id=\"applicationForm\"]/div[2]/div[5]/input"));
        phone.click();
        phone.sendKeys(stringPhone);

        String stringEmail =  "qwertyqwerty";
        WebElement email = driver.findElement(By.xpath("//input[@name='Email']"));
        email.sendKeys(stringEmail);

        String stringComment =  "Я согласен на обработку";
        WebElement comment = driver.findElement(By.xpath("//textarea[@name='Comment']"));
        comment.sendKeys(stringComment);

        // Проверить, что все поля заполнены
        //введенными значениями
        wait.until(ExpectedConditions.attributeContains(lastName, "value", stringLastName));
        wait.until(ExpectedConditions.attributeContains(firstName, "value", stringFirstName));
        wait.until(ExpectedConditions.attributeContains(middleName, "value", stringMiddleName));
        wait.until(ExpectedConditions.attributeContains(regoinSelect, "value", "77"));
        wait.until(ExpectedConditions.attributeContains(phone, "value", "+7 (999) 999-99-99"));
        wait.until(ExpectedConditions.attributeContains(email, "value", stringEmail));
        wait.until(ExpectedConditions.attributeContains(comment, "value", stringComment));

        // Нажать Отправить
        WebElement sendButton = driver.findElement(By.xpath("//*[@id=\"button-m\"]"));
        sendButton.click();
        //Проверить, что у Поля - Эл. почта
        //присутствует сообщение об ошибке -
        //Введите корректный email
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Введите адрес электронной почты')]")));

    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
