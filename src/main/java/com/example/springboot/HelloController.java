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

@RestController
public class HelloController {
    @Autowired
    private ApplicationConfigBeen appConfigBeen;

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
                "JNDI lookup: " + ((JNDIObj)ctx.lookup("com/example/jndiProp")).getJndiProp() + "\n" +
                "\n" +
                "\n";
    }

}
