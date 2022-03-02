package sg.edu.nus.iss.app.visaWorkshop13;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static sg.edu.nus.iss.app.visaWorkshop13.util.IOUtil.*;

@SpringBootApplication
public class VisaWorkshop13Application {
	private static final Logger logger 
		= Logger.getLogger(VisaWorkshop13Application.class.getName());
	private static final String DATA_DIR = "dataDir";

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(VisaWorkshop13Application.class);
		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		List<String> optsVal = appArgs.getOptionValues(DATA_DIR);
		if(optsVal != null){
			createDir((String)optsVal.get(0));
		}else{
			logger.log(Level.WARNING, "No data directory is provided !");
			System.exit(1);
		}
		app.run(args);
	}

}
