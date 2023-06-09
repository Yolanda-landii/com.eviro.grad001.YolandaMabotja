package com.eviro.assessment.grad001.yolandamabotja;

import com.eviro.assessment.grad001.yolandamabotja.controllers.ImageController;
import com.eviro.assessment.grad001.yolandamabotja.domains.AccountProfileRepository;
import com.eviro.assessment.grad001.yolandamabotja.interfaces.FileParser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.FileSystemResource;


@SpringBootApplication
public class GradAssessmentApplication {



	public static void main(String[] args) {
			SpringApplication.run(GradAssessmentApplication.class, args);

		ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		// Retrieve the required beans from the application context
		AccountProfileRepository accountProfileRepository = context.getBean(AccountProfileRepository.class);
		FileParser fileParser = context.getBean(FileParser.class);

		// Create an instance of ImageController and autowire its dependencies
		//

		ImageController imageController = new ImageController(accountProfileRepository, fileParser);
		String name = "John";
		String surname = "Doe";
		String filename = "image.jpg";

		FileSystemResource resource = imageController.gethttpImageLink(name, surname, filename);
		System.out.println(resource);
//			ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);


	}

}
