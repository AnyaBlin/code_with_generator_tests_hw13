package blin.tests;

import blin.config.ProjectConfig;
import blin.helpers.Attach;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.lang.String.format;

public class TestBase {

    String nameObject1 = "ВТБ Капитал";
    String nameObject2 = "ВТБ Страхование";
    String nameObject3 = "ВТБ Факторинг";
    String checkedElement1 = "Онлайн-сервисы";
    String checkedElement2 = "Отделения и Банкоматы";
    String checkedElement3 = "English";
    String checkedText1 = "ВТБ Капитал предлагает полный спектр инвестиционно-банковских продуктов и услуг как российским";
    String checkedText2 = "Почему именно ВТБ Страхование";
    String checkedText3 = "ВТБ Факторинг";
    String checkedText4 = "Careers";

    String URL = "https://www.vtb.ru/";

    public static ProjectConfig conf =
            ConfigFactory.create(ProjectConfig.class);
    @BeforeAll
    static void setup() {

        //ProjectConfig conf = ConfigFactory.create(ProjectConfig.class, System.getProperties());
/*        Configuration.browser = conf.browserName();
        Configuration.browserVersion = conf.browserVersion();
        Configuration.browserSize = conf.browserSize();*/
        Configuration.remote = format("https://%s:%s@%s",  conf.login(),
                conf.password(), System.getProperty("remoteBrowser"));


        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
                SelenideLogger.addListener("Allure", new AllureSelenide());



    }

    @AfterEach
    public void addAttach() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
    }
}

