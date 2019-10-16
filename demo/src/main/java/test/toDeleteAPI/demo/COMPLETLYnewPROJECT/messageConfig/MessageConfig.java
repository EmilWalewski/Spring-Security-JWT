package test.toDeleteAPI.demo.COMPLETLYnewPROJECT.messageConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
public class MessageConfig {

    @Autowired
    Environment environment;

    public String getMessage(String propertyName){

        return environment.getProperty(propertyName);
    }
}
