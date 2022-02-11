import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.TimeUnit;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Math.min;


public class FirstPage {

    WebDriver driver;

    public FirstPage() throws ParseException {
    }

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() {

         driver = new ChromeDriver();
    }

    @AfterEach
    void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Current date + 5
    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    Date date = new Date();
    String currentDate = dateFormat.format(date);
    Date day = dateFormat.parse(currentDate);
    Date addFive = new Date(day.getTime() + TimeUnit.DAYS.toMillis(5));
    String plusFive = dateFormat.format(addFive);

    //Current month
    String[] monthName = {"January", "February",
            "March", "April", "May", "June", "July",
            "August", "September", "October", "November",
            "December"};

    Calendar cal = Calendar.getInstance();
    String currentMonth = monthName[cal.get(Calendar.MONTH)];


    private List<String> workWithList() {
        // List of favourite films
        List<String> movies = new ArrayList<>(Arrays.asList("Soul", "Avengers", "Taxi 3", "The Irish Man", "A Hero", "Spider Man", "The Godfather", "Pulp Fiction"));
        Collections.shuffle(movies);
        return movies;
    }

    @Test
    void test() throws InterruptedException {
        driver.get("https://docs.google.com/forms/d/e/1FAIpQLScNx9xK2LM-G3Z3fJXOQapiSK1IAoNXc_67MyS-soTfhDXotA/viewform");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#i6")));

        // First Checkbox
        driver.findElement(By.cssSelector("#i6")).click();

        //Second CheckBox
        driver.findElement(By.cssSelector("#i15")).click();

        //Date + 5
        driver.findElement(By.cssSelector("input[type*='date']")).sendKeys(plusFive);
        Thread.sleep(2000);

        // Click on button Next
        driver.findElement(By.cssSelector(".quantumWizButtonPaperbuttonLabel")).click();
        Thread.sleep(2000);

        // Verification of the error
        driver.findElement(By.cssSelector(".freebirdFormviewerComponentsQuestionBaseRoot.hasError")).isDisplayed();
        Thread.sleep(2000);

        // Fill name of current month
        driver.findElement(By.cssSelector(".freebirdFormviewerComponentsQuestionTextTextInput .exportInput, .freebirdFormviewerComponentsQuestionTextTextInput .exportTextarea")).sendKeys(currentMonth);
        Thread.sleep(2000);

        // Click on next to the second page
        driver.findElement(By.cssSelector(".quantumWizButtonPaperbuttonLabel")).click();
        Thread.sleep(2000);

        //Second Page - fill in random 3 movies
        driver.findElement(By.className("quantumWizTextinputPapertextareaInput")).sendKeys((workWithList().get(0) + "\n" + workWithList().get(1) + "\n" + workWithList().get(2)));

        Thread.sleep(20000);


    }
}

