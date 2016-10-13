package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

/**
 * Created by aleksandr.petrov on 12.10.16.
 */
public class ContactPhoneTests extends TestBase {

    @Test
    public void testContactPhones(){
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFormEditForm = app.contact().infoFormEditForm(contact);

        assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFormEditForm)));

        assertThat(contact.getAddress(), equalTo(contactInfoFormEditForm.getAddress()));
        assertThat(contact.getEmail(), equalTo(contactInfoFormEditForm.getEmail()));

    }

   private String mergePhones(ContactData contact){
       return Arrays.asList(contact.getHomePhone(), contact.getMobilePhone(), contact.getWorkPhone())
               .stream().filter((s) -> ! s.equals(""))
               .map(ContactPhoneTests :: cleanedPhone)
               .collect(Collectors.joining("\n"));
   }


    public static String cleanedPhone (String phone) {
        return phone.replaceAll("\\s", "").replaceAll("[-()]" , "");
    }
}
