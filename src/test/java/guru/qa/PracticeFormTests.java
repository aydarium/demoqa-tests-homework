package guru.qa;


import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class PracticeFormTests {

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = true;
        Configuration.timeout = 5000; // default 4000
    }

    @Test
    void fillPracticeFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Vasya");
        $("#lastName").setValue("Pupkin");
        $("#userEmail").setValue("vasiliy@pupkins.ru");
        $("[value=Male]").parent().click();
        $("#userNumber").setValue("5551235678");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("February");
        $(".react-datepicker__year-select").selectOption("2004");
        $("[aria-label$='February 29th, 2004']").click();
        $("#subjectsInput").setValue("a"); $("#subjectsWrapper").find(byText("Arts")).click();
        $("#subjectsInput").setValue("u"); $("#subjectsWrapper").find(byText("Social Studies")).click();
        $("#subjectsInput").setValue("h"); $("#subjectsWrapper").find(byText("History")).click();
        $("#hobbiesWrapper").find(byText("Reading")).click();
        $("#hobbiesWrapper").find(byText("Music")).click();
        $("#uploadPicture").uploadFromClasspath("image.jpg");
        $("#currentAddress").setValue("Pushkin Street, Kolotushkin's House");
        $("#state").click(); $("#stateCity-wrapper").find(byText("Haryana")).click();
        $("#city").click(); $("#stateCity-wrapper").find(byText("Panipat")).click();
        $("#submit").click();

        $(".modal-header").shouldHave(text("Thanks for submitting the form"));
        $(".table").shouldHave(text("Vasya Pupkin"));
        $(".table").shouldHave(text("vasiliy@pupkins.ru"));
        $(".table").shouldHave(text("Male"));
        $(".table").shouldHave(text("5551235678"));
        $(".table").shouldHave(text("29 February,2004"));
        $(".table").shouldHave(text("Arts, Social Studies, History"));
        $(".table").shouldHave(text("Reading, Music"));
        $(".table").shouldHave(text("image.jpg"));
        $(".table").shouldHave(text("Pushkin Street, Kolotushkin's House"));
        $(".table").shouldHave(text("Haryana Panipat"));
    }
}
