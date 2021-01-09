package com.slavik;

import com.slavik.configuration.RestConfig;
import com.slavik.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(RestConfig.class);
        Communication communication = context.getBean("communication", Communication.class);

        System.out.println(communication.getAllUsers());

        User user = new User(3L,"James", "Brown", (byte)23);
        communication.postUser(user);
        user.setName("Thomas");
        user.setLastName("Shelby");
        communication.putUser(user);
        communication.deleteUser(3L);


    }
}
