package cloud.autotests.tests.VTB;

import com.codeborne.pdftest.PDF;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;

import java.io.File;
import java.io.IOException;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class VTBPages {
    String VTBCapital = "ВТБ Капитал";
    String VTBInsurance = "ВТБ Страхование";
    String VTBFactoring = "ВТБ Факторинг";
    String OnlineService = "Онлайн-сервисы";
    String OfficesAndATMs = "Отделения и Банкоматы";
    String English = "English";
    String corporateBusiness = "Крупный бизнес";
    String paymentsAndTransfers = "Платежи и переводы";
    String currencyExchange = "Обмен валюты";
    String cash ="В офисе (наличные)";
    String nonCash ="В офисе (безналично)";
    String calculate = "Рассчитать";
    String preliminaryCalculation = "Предварительный расчет по текущему курсу";
    String symbolDollar = "?";
    String searchOffice = "Найти отделение";
    String VTBCapitalText = "ВТБ Капитал предлагает полный спектр инвестиционно-банковских продуктов и услуг как российским";
    String VTBInsuranceText = "Почему именно ВТБ Страхование";
    String Careers = "Careers";
    String PDFDocumentText = "Раскрытие информации профессиональным участником рынка ценных бумаг";
    String VTBMainPage = "https://www.vtb.ru/";

    public VTBPages checkResultsVTBMainPage() {

        $(".header-bottom-menu").shouldHave(text(OnlineService));

        return this;
    }

    public VTBPages checkResultsVTBCapitalTab() {

        $(".tab-panel__wrap").shouldHave(text(VTBCapitalText));

        return this;
    }

    public VTBPages checkResultsVTBInsurance() {

        $(".tab-panel__wrap").shouldHave(text(VTBInsuranceText));

        return this;
    }

    public VTBPages checkResultsVTBFactoring() {

        $(".tab-panel__wrap").shouldHave(text(VTBFactoring));

        return this;
    }
    public VTBPages checkResultsAfterClickingOnButtonCalculate() {

        $(".groupstyles__Box-section-calculator__sc-1qusdsd-0.iDREvM").shouldHave(text(preliminaryCalculation));

        return this;
    }

    public VTBPages checkResultsAfterClickingOnButtonSearchOffice() {

        $(".ng-scope").shouldHave(text(OfficesAndATMs));

        return this;
    }

    public VTBPages checkResultsChangeLanguage() {

        $(".navigation__second__menu").shouldHave(text(Careers));

        return this;
    }

    public VTBPages goToTheCorporateBusinessTabAndGoToTheVTBCapitalTab() {

        $$(".link__text.text.color-white.size-small-medium.weight-medium").find(text(corporateBusiness)).click();
        $(".tab-panel__image").scrollTo();
        $$(".tab-panel__row li").find(text(VTBCapital)).click();

        return this;
    }

    public VTBPages goToTheCorporateBusinessTabAndGoToTheVTBInsurance() {

        $$(".tab-panel__row li").find(text(VTBInsurance)).click();

        return this;
    }

    public VTBPages goToTheCorporateBusinessTabAndGoToTheVTBFactoring() {

        $$(".tab-panel__row li").find(text(VTBFactoring)).click();

        return this;
    }

    public VTBPages goToThePaymentPage() {

        open(VTBMainPage);
        $$(".header-bottom-menu-item").findBy(text(paymentsAndTransfers)).click();

        return this;
    }

    public VTBPages goToTheCurrencyExchangePage() {

        $$(".header-menu-panel-group-item").findBy(text(currencyExchange)).click();

        return this;
    }

    public VTBPages chooseNonCashMethodOfExchange() {

        $(".parametersstyles__SelectBox-currency-converter__sc-8kybyp-1.cXEdli").scrollTo();
        $$(".selectstyles__Box-currency-converter__sc-30iz8h-0.hVxMvd").findBy(text(cash)).click();
        $$(".dropdownstyles__OptionsList-currency-converter__sc-9kquo6-1.elNYdH li").findBy(text(nonCash)).click();

        return this;
    }

    public VTBPages fillCurrencyConverterAndClickButtonCalculate() {

        $$(".tabsstyles__TabsLayout-currency-converter__sc-yft9ju-0.jQlDBj li").find(text(symbolDollar)).click();
        $(".base-inputstyles__Input-currency-converter__sc-1h5el2c-4.jVzcqp").click();
        $(".base-inputstyles__Input-currency-converter__sc-1h5el2c-4.jVzcqp").sendKeys("100");
        $$(".big-buttonstyles__Wrapper-currency-converter__sc-4w7jjq-5.jFzfnT").find(text(calculate)).click();

        return this;
    }

    public VTBPages clickButtonSearchOffice() {

        $$(".big-buttonstyles__Wrapper-currency-converter__sc-4w7jjq-5.jFzfnT").find(text(searchOffice)).click();

        return this;
    }

    public VTBPages downloadPDFFileAndCheckNumberOfPages() throws IOException {

        open(VTBMainPage);
        $(".link__text.text.color-dark-gray.size-small-medium.weight-normal").scrollTo();
        $(By.linkText(PDFDocumentText)).click();
        File pdf = $(".docs-items__doc.docs-items__doc_pdf.docs-items__doc-br").scrollTo().download();
        PDF parsedPdf = new PDF(pdf);
        Assertions.assertEquals(2, parsedPdf.numberOfPages);

        return this;
    }

    public VTBPages scrollToChangeLanguageAndClickEnglish() {

        $(".location").scrollTo().find(By.linkText(English)).click();

        return this;
    }


}
