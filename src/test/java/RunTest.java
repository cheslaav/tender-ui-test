
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.WebDriverRunner;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.HashMap;


@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"html:target/cucumber-html-reports", "json:target/cucumber.json",
                "pretty:target/cucumber-pretty.txt", "junit:target/cucumber-results.xml"}, //отвечает за создание отчетов о тесте
        features = "src/test/java/features", //путь до фич
        glue = "steps", //путь до степов
        tags = "@prodTest", //фичам и даже отдельным сценариям можно присваивать тэги; данный параметр указывает какие именно тесты будут запущены. Тэги записываются в строчку через запятую: “@smoketest, @alltests, @special”
        dryRun = false, //dryRun – если true, то сразу после запуска теста фреймворк проверяет, все ли шаги теста разработаны, если нет, то выдает предупреждение. При false предупреждение будет выдаваться по достижении неразработанного шага. По умолчанию false
        strict = false, //strict – если true, то при встрече неразработанного шага тест остановится с ошибкой. False — неразработанные шаги пропускаются. По умолчанию false.
        snippets = SnippetType.UNDERSCORE
)
public class RunTest {
    @BeforeClass
    public static void setupClass() throws Exception {
        WebDriverManager.chromedriver().setup();
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--no-sandbox");
        //option.setHeadless(true);
        option.addArguments("--start-maximized");
        DesiredCapabilities chrome = DesiredCapabilities.chrome();
        chrome.setJavascriptEnabled(true);
        option.setCapability(ChromeOptions.CAPABILITY, option);
        HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
        chromePrefs.put("download.default_directory", System.getProperty("user.dir"));
        option.setExperimentalOption("prefs", chromePrefs);
        WebDriverRunner.setWebDriver(new ChromeDriver(option));
    }

    @AfterClass
    public static void quite() {
        WebDriverRunner.getWebDriver().quit();
    }
}