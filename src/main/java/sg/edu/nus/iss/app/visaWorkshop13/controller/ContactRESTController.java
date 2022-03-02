package sg.edu.nus.iss.app.visaWorkshop13.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import sg.edu.nus.iss.app.visaWorkshop13.model.Contact;
import sg.edu.nus.iss.app.visaWorkshop13.util.Contacts;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ContactRESTController {
    private Logger logger = Logger.getLogger(ContactRESTController.class.getName());
    
    @Autowired
    private ApplicationArguments applicationArguments;
    

    @PostMapping("/contact2")
    public Contact contactSubmitRest(@RequestBody Contact contact, 
        Model model, HttpServletResponse httpResponse){
        logger.log(Level.INFO, "Id : " + contact.getId());
        logger.log(Level.INFO, "Name : " + contact.getName());
        logger.log(Level.INFO, "Email : " + contact.getEmail());
        logger.log(Level.INFO, "Phone Number : " + contact.getPhoneNumber());
        Contacts ct = new Contacts();
        ct.saveContact(contact, model, applicationArguments);
        httpResponse.setStatus(HttpStatus.CREATED.value());
        return contact;
    }

}
