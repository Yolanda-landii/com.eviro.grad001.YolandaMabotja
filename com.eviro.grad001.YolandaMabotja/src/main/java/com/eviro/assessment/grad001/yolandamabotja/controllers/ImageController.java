package com.eviro.assessment.grad001.yolandamabotja.controllers;
import com.eviro.assessment.grad001.yolandamabotja.domains.AccountProfile;
//import com.eviro.assessment.grad001.yolandamabotja.domains.FileParserImplement;
import com.eviro.assessment.grad001.yolandamabotja.domains.AccountProfileRepository;
import com.eviro.assessment.grad001.yolandamabotja.domains.FileParserImplement;
import com.eviro.assessment.grad001.yolandamabotja.interfaces.FileParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.*;

import java.io.File;


@RestController
@RequestMapping("/v1/api/image")
public class ImageController {
    private final AccountProfileRepository accountProfileRepository;
    private final FileParser fileParser;

    @Autowired
    public ImageController(AccountProfileRepository accountProfileRepository, FileParser fileParser) {
        this.accountProfileRepository = accountProfileRepository;
        this.fileParser = fileParser;
    }


    @GetMapping(value = "/{name}/{surname}/{filename:.+}")
    public FileSystemResource gethttpImageLink(@PathVariable String name,@PathVariable String surname, @PathVariable String filename) {
        AccountProfile accountProfile = new AccountProfile( name, surname, filename);
        accountProfile.setAccountHolderName(name);
        accountProfile.setAccountHolderSurname(surname);
        accountProfile.setHttpImageLink(filename);
//
        String updatedName = accountProfile.getAccountHolderName();

        String updatedSurname = accountProfile.getAccountHolderSurname();

        String httpImageLink = accountProfile.getHttpImageLink();

        // Construct the file path using the provided parameters
        String filePath = "/path/to/images/" + updatedName + "/" + updatedSurname + "/" + httpImageLink;
        System.out.println(filePath);
        FileParserImplement fileParserImplement = new FileParserImplement(accountProfileRepository, fileParser);
        File csvFile = new File("path/to/csv/file.csv");

        // Call the parseCSV method to start parsing the CSV file
        fileParserImplement.parseCSV(csvFile);
        // Create a FileSystemResource with the file path
        FileSystemResource resource = new FileSystemResource(filePath);

        // Check if the file exists
        if (resource.exists()) {
            // Return the resource

            return resource;
        } else {
            // Handle the case when the file does not exist
            try {
                throw new ResourceNotFoundException("File not found");
            } catch (ResourceNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
