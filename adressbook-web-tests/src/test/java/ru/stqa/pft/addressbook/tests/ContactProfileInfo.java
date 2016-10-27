package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        ContactData contactInfoFromDetailsPage = app.contact().infoProfileForm(contact);
        assertThat(contactInfoFromDetailsPage.getDetails(), equalTo(mergeUserInfo(contact)));
    }

    private String mergeUserInfo(ContactData contact) {
        return Stream.of(
                fullNameAndAddress(contact),
                allPhones(contact),
                allEmails(contact))
                .filter((s) -> s != null && !s.equals(""))
                .map(ContactProfileInfo::cleaned)
                .collect(Collectors.joining("\n\n"));
    }

    private String fullName(ContactData contact){
        ContactData contactInfoFromEditForm = app.contact().infoFormEditForm(contact);
        return Stream.of(contactInfoFromEditForm.getFirstname(),contactInfoFromEditForm.getLastname())
                .filter((s) -> s != null && !s.equals(""))
                .map(ContactProfileInfo::cleaned)
                .collect(Collectors.joining(" "));
    }

    private String fullNameAndAddress(ContactData contact){
        ContactData contactInfoFromEditForm = app.contact().infoFormEditForm(contact);
        String r = Stream.of(fullName(contact), contactInfoFromEditForm.getAddress())
                .filter((s) -> s != null && !s.equals(""))
                .map(ContactProfileInfo::cleaned)
                .collect(Collectors.joining("\n"));
        return r;
    }

    private String allPhones(ContactData contact){
        ContactData contactInfoFromEditForm = app.contact().infoFormEditForm(contact);
        return Stream.of(
                ("H: "+contactInfoFromEditForm.getHomePhone()),
                ("M: "+ contactInfoFromEditForm.getMobilePhone()),
                ("W: "+contactInfoFromEditForm.getWorkPhone()))
                .filter((s) -> s != null && !s.equals(""))
                .map(ContactProfileInfo::cleaned)
                .collect(Collectors.joining("\n"));
    }


    private String allEmails(ContactData contact){
        ContactData contactInfoFromEditForm = app.contact().infoFormEditForm(contact);
        return Stream.of(
                (contactInfoFromEditForm.getEmail()+" (www."+contactInfoFromEditForm.getEmail().substring(contactInfoFromEditForm.getEmail().lastIndexOf("@") +1)+")"),
                (contactInfoFromEditForm.getEmail2()+" (www."+contactInfoFromEditForm.getEmail2().substring(contactInfoFromEditForm.getEmail2().lastIndexOf("@") +1)+")"),
                (contactInfoFromEditForm.getEmail3()+" (www."+contactInfoFromEditForm.getEmail3().substring(contactInfoFromEditForm.getEmail3().lastIndexOf("@") +1)+")"))
                .filter((s) -> s != null && !s.equals(""))
                .map(ContactProfileInfo::cleaned)
                .collect(Collectors.joining("\n"));
    }

    public static String cleaned(String details) {
        return details
                .replaceAll("[-()]", "")
                .replaceAll("www.mail.ru", "")
//                .replaceAll("H:", "")
//                .replaceAll("M:", "")
//                .replaceAll("W:", "")
                .replaceAll("\n\n", "\n")
//                .replaceFirst(" ", "\n")
                .replaceAll("\n ", "\n")
                .replaceAll("ru ", "ru")
                .replaceAll("7 ", "7");
    }
}
