package com.example.springboot;

import com.example.springboot.domain.Todo;
import com.example.springboot.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.jndi.JndiTemplate;
import org.springframework.mock.jndi.SimpleNamingContextBuilder;
import org.springframework.web.bind.annotation.*;

import javax.naming.CompositeName;
import javax.naming.Context;
import javax.naming.InvalidNameException;
import javax.naming.Name;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {
    @Autowired
    private ApplicationConfigBeen appConfigBeen;

    @Autowired
    private TodoRepository todoRepository;

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
        File f = new File("/yolo/azure_test");
/*        FileWriter fw = new FileWriter(f);
        fw.write("Hello world");
        fw.flush();*/

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

    private Runnable execute(String id) {
        return new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                    System.out.println("---->" + id);
                }
            }
        };
    }

    @GetMapping("/list")
    public List<Todo> getTodoList() {
        ExecutorService executor = Executors.newFixedThreadPool(10);
        new Thread(execute("1")).start();
        new Thread(execute("2")).start();
        new Thread(execute("3")).start();
        new Thread(execute("4")).start();
        new Thread(execute("5")).start();
        new Thread(execute("6")).start();
        new Thread(execute("7")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        new Thread(execute("8")).start();
        boolean x= true;
        while (x){}
        return Arrays.asList(new Todo(),new Todo());
    }

    @PostMapping("/todo")
    @ResponseStatus(HttpStatus.CREATED)
    public Todo createTodo(@RequestBody Todo todo) {
        return todoRepository.save(todo);
    }
}
