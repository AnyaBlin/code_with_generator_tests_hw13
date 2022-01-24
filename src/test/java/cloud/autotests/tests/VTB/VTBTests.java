package cloud.autotests.tests.VTB;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;


public class VTBTests extends TestBase {
    @Test
    @DisplayName("Открываем страницы ВТБ и проверяем контент")
    public void testsForVTBOpenPagesAndCheckContent() {

        step("Открываем главную страницу ВТБ", () -> {
            open(VTBPages.VTBMainPage);
            step("Проверяем, что шапка содержит элемент" + VTBPages.OnlineService, () -> {
                VTBPages.checkResultsVTBMainPage();
            });
            step("Переходим на вкладку `Крупный бизнес` -> " + VTBPages.VTBCapital, () -> {
                VTBPages.goToTheCorporateBusinessTabAndGoToTheVTBCapitalTab();
                step("Проверяем вкладку на содержание текста " + VTBPages.VTBCapitalText, () -> {
                    VTBPages.checkResultsVTBCapitalTab();
                });
            });
            step("Переходим на вкладку" + VTBPages.VTBInsurance, () -> {
                VTBPages.goToTheCorporateBusinessTabAndGoToTheVTBInsurance();
                step("Проверяем вкладку на содержание текста " + VTBPages.VTBInsuranceText, () -> {
                    VTBPages.checkResultsVTBInsurance();
                });
            });
            step("Переходим на вкладку" + VTBPages.VTBFactoring, () -> {
                VTBPages.goToTheCorporateBusinessTabAndGoToTheVTBFactoring();
                step("Проверяем вкладку на содержание текста " + VTBPages.VTBFactoring, () -> {
                    VTBPages.checkResultsVTBFactoring();
                });
            });
        });
    }

    @Test
    @DisplayName("Открываем главную страницу ВТБ, проверяем содержимое при нажатии кнопки `Найти отделение` ")
    public void openPaymentPageFillСurrencyConverterAndCheckButtonFindOffice() {

        step("Открываем главную страницу и переходим в `Платежи и Переводы`", () -> {
            VTBPages.goToThePaymentPage();
        });
        step("Переходим на страницу `Обмен валюты`", () ->
                VTBPages.goToTheCurrencyExchangePage());
        step("Выбираем способ обмена", () -> {
            VTBPages.chooseNonCashMethodOfExchange();
        });
        step("Заполняем конвертер валют и жмём кнопку 'Рассчитать' ", () -> {
            VTBPages.fillCurrencyConverterAndClickButtonCalculate();
        });
        step("Проверяем содержимое кнопки "+ VTBPages.calculate, () -> {
            VTBPages.checkResultsAfterClickingOnButtonCalculate();
        });


        step("Нажимаем на кнопку `Найти отделение`", () ->
                VTBPages.clickButtonSearchOffice());
        step("Проверяем, что страница содержит элемент" + VTBPages.OfficesAndATMs, () ->
                VTBPages.checkResultsAfterClickingOnButtonSearchOffice());
    }

    @Test
    @DisplayName("Скачиваем PDF документ и проверяем на количество страниц")
    public void downloadFiles() throws IOException {
        VTBPages.downloadPDFFileAndCheckNumberOfPages();
    }

    @Test
    @DisplayName("Меняем язык на сайте и проверяем содержимое")
    public void changeLanguage() {
        step("Выбираем английский язык", () -> {
            open(VTBPages.VTBMainPage);
            VTBPages.scrollToChangeLanguageAndClickEnglish();
            step("Проверяем содержимое", () ->
                    VTBPages.checkResultsChangeLanguage());
        });
    }

}