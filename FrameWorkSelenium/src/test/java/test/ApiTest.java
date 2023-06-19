package test;

import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsIterableContaining.hasItems;

public class ApiTest {

    @Test
    public void testGetAllGroups() {
        RestAssured.baseURI = "https://schedule.kpi.ua/api/schedule/groups";
        given().body("").
                when().get().then().body("data.name",hasItems("КП-12"));
    }

    @Test(dataProvider = "dpT")
    public void testGetTeachersContainsTeacher(String teacher) {
        RestAssured.baseURI = "https://schedule.kpi.ua/api/schedule/lecturer/list";
        given().body("").
                when().get().then().body("data.name",hasItems(teacher));
    }

    @DataProvider(name = "dpT")
    public Object[][] dataProviderTeachers() {
        return new Object[][] {
            {"Легеза Віктор Петрович"},
                {"Олещенко Любов Михайлівна"},
                {"Нещадим Олександр Михайлович"}
        };
    }
}
