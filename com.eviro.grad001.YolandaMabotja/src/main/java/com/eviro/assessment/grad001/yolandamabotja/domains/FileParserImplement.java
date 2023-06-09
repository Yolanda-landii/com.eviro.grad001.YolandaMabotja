package com.eviro.assessment.grad001.yolandamabotja.domains;

import java.io.File;
import java.io.FileReader;
import java.net.URI;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;


import com.eviro.assessment.grad001.yolandamabotja.interfaces.FileParser;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;


public class FileParserImplement implements FileParser {
    private final AccountProfileRepository accountProfileRepository;
    private final FileParser fileParser;


    public FileParserImplement(AccountProfileRepository accountProfileRepository,FileParser fileParser) {
        this.accountProfileRepository = accountProfileRepository;
        this.fileParser = fileParser;
    }

    @Override
    public void parseCSV(File csvFile) {
        try (CSVReader reader = new CSVReader(new FileReader(csvFile))){
            String[] line;
            while ((line = reader.readNext()) != null){
                String accountHolderName = line[0];
                String accountHolderSurname = line[1];
                String imageFormat = line[2];
                String base64ImageData = line[3];

                //convert base64 image into physical file
                File imageFile = fileParser.convertCSVDataToImage(base64ImageData);

                //create the account profile with the extracted fields
                AccountProfile accountProfile = new AccountProfile(accountHolderName,accountHolderSurname,imageFile.getName());


                //save the account profile in the database
                accountProfileRepository.save(accountProfile);
                // Create the image link for the file
                URI imageLink = createImageLink(imageFile);
                // Retrieve all account profiles

                // Use or store the image link as required
                System.out.println("Image Link: " + imageLink);
            }

        }catch (IOException | CsvValidationException e){
            e.printStackTrace();
        }

    }

    @Override
    public File convertCSVDataToImage(String base64ImageData) {
        try {
            //convert base64 image data to bytes
            byte[] imageData = Base64.getDecoder().decode(base64ImageData);
            //Generate a unique filename based on the current timestamp
            String filename = System.currentTimeMillis() + ".png";
            //save the image file
            File imageFile = new File(filename);
            FileOutputStream outputStream = new FileOutputStream(imageFile);
            outputStream.write(imageData);
            outputStream.close();

            return imageFile;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public URI createImageLink(File fileImage) {
        try {
            return fileImage.toURI();
        }catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
