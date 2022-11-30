package de.neuefische;

import lombok.Data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Data
public class GuestList {

    List<String> guests;


    public void createGuestListFile() {
        try {
            File myGuestList = new File("guests.txt");
            if (myGuestList.createNewFile()) {
                System.out.println("File created: " + myGuestList.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public String writeToGuestList() throws IOException {

        Path path = Paths.get("guests.txt");
        createGuestListFile();

        try {
            FileWriter myWriter = new FileWriter("guests.txt");
            for (String guest : guests) {
                myWriter.write(guest);
                if (!guest.equals(guests.get(guests.size() - 1))) {
                    myWriter.write(System.getProperty("line.separator"));
                }
            }
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Files.readString(path);
    }

    public String readFromGuestList() throws IOException {
        return Files.readString(Paths.get("guests.txt"));
    }

    public String addGuest(String newGuest) throws IOException {
        guests.add(newGuest);
        return writeToGuestList();
    }
}
