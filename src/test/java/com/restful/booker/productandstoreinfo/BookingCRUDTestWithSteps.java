package com.restful.booker.productandstoreinfo;

import com.restful.booker.bookinginfo.AuthSteps;
import com.restful.booker.bookinginfo.BookingSteps;
import com.restful.booker.testbase.TestBase;
import com.restful.booker.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.annotations.Steps;
import net.serenitybdd.annotations.Title;
import net.serenitybdd.junit.runners.SerenityRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.equalTo;
@RunWith(SerenityRunner.class)
public class BookingCRUDTestWithSteps extends TestBase {
    static String userName = "admin";
    static String password = "password123";

    public static String token;

    static String firstName = TestUtils.getRandomValue() + "Tom";
    static String lastname = TestUtils.getRandomValue() + "Jerry";
    static int totalprice = 456;
    static boolean depositpaid = true;
    static String additionalneed = "Dinner";

    static int id;

    @Steps
    BookingSteps steps;
    @Steps
    AuthSteps step;

    @Title("This will create Token")
    @Test
    public void test001() {

        ValidatableResponse response = step.createToken(userName, password);
        token = response.extract().path("token");
        System.out.println(token);


    }

    @Title("Getting all booking Id")
    @Test
    public void test002() {
        steps.getAllBookingIds().statusCode(200);
    }

    @Title("This will create new booking")
    @Test
    public void test003() {
        ValidatableResponse response = steps.createBooking(firstName, lastname, totalprice, depositpaid, additionalneed).statusCode(200);
        id = response.extract().path("bookingid");
        System.out.println(id);
    }

    @Title("Verify the booking added to the application")
    @Test
    public void test004() {
        ValidatableResponse response = steps.getBookingById(id).statusCode(200);
        response.body("firstname", equalTo(firstName), "lastname", equalTo(lastname), "totalprice", equalTo(totalprice));
    }

    @Title("Update and verify booking information")
    @Test
    public void test005() {
        firstName = firstName + "_updated";
        lastname = lastname + "_updated";
        totalprice = 852;
        depositpaid = false;
        additionalneed = "breakfast";
        ValidatableResponse response = steps.updateBookingWithId(firstName, lastname, totalprice, depositpaid, additionalneed, token, id).statusCode(200);
        response.body("firstname", equalTo(firstName), "lastname", equalTo(lastname), "totalprice", equalTo(totalprice));

    }

    @Title("Partially update and verify booking information")
    @Test
    public void test006() {
        firstName = firstName + "kanan";
        lastname = lastname + "kanan";
        ValidatableResponse response = steps.partiallyUpdateBooking(firstName, lastname, totalprice, depositpaid, additionalneed, token, id).statusCode(200);
        response.body("firstname", equalTo(firstName), "lastname", equalTo(lastname));
    }

    @Title("Delete the booking and verify if the booking deleted")
    @Test
    public void test007() {
        steps.deleteBooking(token, id).statusCode(201);
        steps.getBookingById(id).statusCode(404);
    }

}
