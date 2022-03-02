package sg.edu.nus.iss.app.visaWorkshop13;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

import sg.edu.nus.iss.app.visaWorkshop13.controller.AddressbookController;

@SpringBootTest
class VisaWorkshop13ApplicationTests {
	@Autowired
	private AddressbookController controller;
	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
