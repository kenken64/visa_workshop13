package sg.edu.nus.iss.app.visaWorkshop13.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;

import org.springframework.boot.ApplicationArguments;
import org.springframework.ui.Model;

import sg.edu.nus.iss.app.visaWorkshop13.model.Contact;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;

public class Contacts {
    private Logger logger = Logger.getLogger(Contacts.class.getName());

    public void saveContact(Contact contact, Model model, 
            ApplicationArguments applnArgs){
        String dataFilename = contact.getId();
        PrintWriter printWriter = null;
        FileWriter fileWriter = null;
        String dataDirPath = "/home/kenneth/data";
        String[] optValuesArr = null;
        try{
            if(applnArgs != null){
                Set<String> optnames = applnArgs.getOptionNames();
                if(optnames !=null && optnames.size() > 0){
                    String[] optnamesArr = optnames.toArray(new String[optnames.size()]);
                    if(optnamesArr != null && optnamesArr.length > 0){
                        List<String> optValues = applnArgs.getOptionValues(optnamesArr[0]);
                        if(optValues != null && optValues.size() > 0){
                            optValuesArr = optValues.toArray(new String[optValues.size()]);
                        }
                    }  
                }
            }
           
            if(optValuesArr != null){
                dataDirPath = optValuesArr[0];
                logger.log(Level.INFO, " optValuesArr[0] >>> " + optValuesArr[0]);
                logger.log(Level.INFO, " dataDirPath>>> " + dataDirPath);
                
            }
            
            fileWriter 
                = new FileWriter(dataDirPath + "/" + dataFilename
                    , Charset.forName("UTF-8"));
            printWriter = new PrintWriter(fileWriter);
            printWriter.println(contact.getName());
            printWriter.println(contact.getEmail());
            printWriter.println(contact.getPhoneNumber());  
        }catch(IOException e){
            logger.log(Level.WARNING, e.getMessage());
        }finally{
            if(printWriter != null)
                printWriter.close();
            try {
                if(fileWriter != null)
                    fileWriter.close();
            }catch(IOException e){
                logger.log(Level.WARNING, e.getMessage());
            }   
        }
        model.addAttribute("contact", new Contact(contact.getId(), contact.getName(),
                    contact.getEmail(), contact.getPhoneNumber()));
    }

    public void getContactById(Model model, 
        String contactId, ApplicationArguments applnArgs)
    {
        Set<String> optnames = applnArgs.getOptionNames();
        String[] optnamesArr = optnames.toArray(new String[optnames.size()]);
        List<String> optValues = applnArgs.getOptionValues(optnamesArr[0]);
        String[] optValuesArr = optValues.toArray(new String[optValues.size()]);
        Contact cResp = new Contact();
        try{
            Path filePath = new File(optValuesArr[0] + "/" + contactId).toPath();
            Charset charset = Charset.forName("utf-8");
            List<String> stringListVal = Files.readAllLines(filePath, charset);
            cResp.setName(stringListVal.get(0));
            cResp.setEmail(stringListVal.get(1));
            cResp.setPhoneNumber(stringListVal.get(2));
            cResp.setId(contactId);

        }catch(IOException e){
            logger.log(Level.WARNING, e.getMessage());
        }

        model.addAttribute("contact", cResp);
    }
    
}
