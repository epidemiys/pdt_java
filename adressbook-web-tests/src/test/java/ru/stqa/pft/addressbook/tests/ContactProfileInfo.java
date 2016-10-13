package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by aleksandr.petrov on 13.10.16.
 */
public class ContactProfileInfo extends TestBase {

    @Test
    public void testContactDetails() {
        app.goTo().homePage();
        ContactData contact = app.contact().all().iterator().next();
        ContactData contactInfoFromDetailsForm = app.contact().infoProfileForm(contact);

        assertThat((mergeUserInfo(contact)), equalTo((cleaned(contactInfoFromDetailsForm.getAllInfo()))));
    }

    private String mergeUserInfo(ContactData contact) {
        return Arrays.asList(contact.getFirstname(), contact.getLastname(), contact.getAddress(),
                contact.getAllPhones(), contact.getAllEmail())
                .stream().filter(s -> !s.equals(""))
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String details) {
        return details.replaceAll("[-()]", "")
                .replaceAll("www.mail.ru", "")
                .replaceAll("H:", "")
                .replaceAll("M:", "")
                .replaceAll("W:", "")
                .replaceAll("\n\n", "\n")
                .replaceFirst(" ", "\n")
                .replaceAll("\n ", "\n")
                .replaceAll("ru ", "ru")
                .replaceAll("7 ", "7");
    }
}
