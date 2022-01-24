package cloud.autotests.tests.BookStore;

import com.codeborne.selenide.Configuration;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class BookStoreTests {
    @BeforeAll
    static void setup() {

        RestAssured.baseURI = "https://demoqa.com";
        Configuration.baseUrl = "https://demoqa.com";

    }


    @Test
    void withAllLogsTest() {

        given()
                .get("/BookStore/v1/Books")
                .then()
                .log().all()
                .body("books", hasSize(greaterThan(0)));

    }

    @Test
    void authorizeTestsWithAllureSucces() {
        String data = "{ \"userName\": \"Ann\", \"password\": \"1234567890Aa!\"}";
        given()
                .filter(new AllureRestAssured())
                .contentType("application/json")
                .body(data)
                .when()
                .log().all()
                .post("Account/v1/GenerateToken")
                .then()
                .log().body()
                .body("status", is("Success"))
                .body("result", is("User authorized successfully."));

    }
    @Test
    void authorizeTestsWithAllureFailed() {
        String data = "{ \"userName\": \"Ann\", \"password\": \"1234567890Aa\"}";
        given()
                .filter(new AllureRestAssured())
                .contentType("application/json")
                .body(data)
                .when()
                .log().all()
                .post("Account/v1/GenerateToken")
                .then()
                .log().body()
                .body("status", is("Failed"))
                .body("result", is("User authorization failed."));

    }

    @Test
    void bookInfo() {
        given()
                .filter(new AllureRestAssured())
                .contentType("application/json")
                .when()
                .log().all()
                .get("BookStore/v1/Book?ISBN=9781449331818")
                .then()
                .log().body()
                .statusCode(200)
                .body("author", is("Addy Osmani"))
                .body("title", is("Learning JavaScript Design Patterns"));

    }


}

