package com.example.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.jndi.JndiTemplate;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import java.util.Map;

@RestController
public class HelloController {
    @Autowired
    private ApplicationConfigBeen appConfigBeen;

    private final MessageProperties properties;

    public HelloController(MessageProperties properties) {
        this.properties = properties;
    }

    private String mkStrEnv() {
        Map<String, String> envs = System.getenv();
        String all = "";
        for (String s : envs.keySet()) {
            all += s + " : " + envs.get(s);
        }
        return all;
    }

    private String readConnStr(String key) {
        return System.getenv("CONNSTR_" + key);
    }

    private String readEnvar(String key) {
        return System.getenv("APPSETTING_" + key);
    }

    @GetMapping("/")
    public String index() throws Exception {
        //JNDI mock provider
        SimpleNamingContextBuilder builder = new SimpleNamingContextBuilder();
        builder.activate();
        JndiTemplate jndiTemplate = new JndiTemplate();
        Context ctx = jndiTemplate.getContext();

        ctx.bind("com/example/jndiProp", new JNDIObj());
        return "Greetings from Spring Boot! ..........\n" +
                "Application config property Name:" + appConfigBeen.getName() + "\n" +
                "Application config property Age:" + appConfigBeen.getAge() + "\n" +
                "JNDI lookup: " + ((JNDIObj) ctx.lookup("com/example/jndiProp")).getJndiProp() + "\n" +
                "Envar : APP_S1" + readEnvar("APP_S1") + " \n" +
                "....................................\n" +
                "\n" +
                "\n" +
                "" + mkStrEnv() + "\n" +
                "\n" +
                "\n" +
                "............................\n" +
                "Azure configuration server : " + properties.getMessage() + "\n";

    }

}
