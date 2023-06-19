package test;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsIterableContaining.hasItems;

public class ApiTest {

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://schedule.kpi.ua/api/";
    }

    @Test
    public void testGetAllGroups() {
        given().basePath("schedule/groups").body("").
                when().get().then().body("data.name",hasItems("КП-12", "КП-11","КП-13"));
    }

    @Test(dataProvider = "dpT")
    public void testGetTeachersContainsTeacher(String teacher) {
        given().basePath("schedule/lecturer/list").body("").
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
