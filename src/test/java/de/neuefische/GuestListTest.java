package de.neuefische;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

class GuestListTest {

    @Test
    void shouldBeEmptyInitially() {
        GuestList guestList = new GuestList();
        guestList.setGuests(new ArrayList<>());

        List<String> actual = guestList.getGuests();

        Assertions.assertEquals(new ArrayList<String>(), actual);
    }

    @Test
    void shouldReadSameGuestsAsWrittenBefore() {
        GuestList guestList = new GuestList();

        List<String> guests = new ArrayList<>();
        guests.add("Karl");
        guests.add("Ute");

        guestList.setGuests(guests);

        List<String> actual = guestList.getGuests();

        Assertions.assertEquals(guests, actual);
    }

    @Test
    void shouldWriteToFileSystem() throws IOException {
        // given
        GuestList guestList = new GuestList();

        List<String> guests = new ArrayList<>();
        guests.add("Theodor");
        guests.add("Anette");

        guestList.setGuests(guests);

        String expected = """
                Theodor
                Anette""";

        // when
        String actual = guestList.writeToGuestList();

        // then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReadFromFileSystem() throws IOException {
        // given
        GuestList guestList = new GuestList();

        List<String> guests = new ArrayList<>();
        guests.add("Stephan");
        guests.add("Max");

        guestList.setGuests(guests);
        guestList.writeToGuestList();

        String expected = """
                Stephan
                Max""";

        // when
        String actual = guestList.readFromGuestList();

        // then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void shouldReadUpdatedGuestList() throws IOException {
        // given
        GuestList guestList = new GuestList();

        List<String> guests = new ArrayList<>();
        guests.add("Stephan");
        guests.add("Max");

        guestList.setGuests(guests);
        guestList.writeToGuestList();

        // when
        String actual = guestList.addGuest("Petra");

        String expected = """
                Stephan
                Max
                Petra""";

        // then
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void checkForExceptionIfFileDoesNotExist() {

        GuestList guestList = new GuestList();

        File file = Paths.get("guests.txt").toFile();
        if (file.exists()) {
            file.delete();
        }

        try {
        String actual = guestList.readFromGuestList();
        Assertions.fail();

        } catch (IOException e) {
            Assertions.assertTrue(true);
        }
    }

}