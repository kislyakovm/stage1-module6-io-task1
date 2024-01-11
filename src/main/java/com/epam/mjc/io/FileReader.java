package com.epam.mjc.io;

import java.io.File;
import java.io.FileInputStream;

public class FileReader {

    public Profile getDataFromFile(File file) {
        StringBuilder profileData = new StringBuilder();

        String name = null;
        Integer age = null;
        String email = null;
        Long phone = null;


        try(FileInputStream stream = new FileInputStream(file)) {

            int i;

            while ((i = stream.read()) != -1) {
                profileData.append((char) i);
            }

            String string = profileData.toString();

            if(string != null) {

                String[] splitStrings = string.split("\r\n");

                if (splitStrings.length == 4) {

                    String[] profileFields = new String[splitStrings.length];

                    for (i = 0; i < splitStrings.length; i++) {

                        String[] tempArray = splitStrings[i].split(" ");

                        if (tempArray.length == 2) {
                            profileFields[i] = tempArray[1];
                        }
                    }

                    if (profileFields.length == 4) {
                        name = profileFields[0];
                        age = Integer.parseInt(profileFields[1]);
                        email = profileFields[2];
                        phone = Long.parseLong(profileFields[3]);
                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Profile(name, age, email,  phone);

    }
}

