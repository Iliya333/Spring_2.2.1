package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        userService.add(new User("Vasia", "Popov", "Vasia@mail.ru", new Car("VAZ_2101", 1)));
        userService.add(new User("Bob", "Pit", "Bob@mail.ru", new Car("Mazda", 2)));
        userService.add(new User("Viktor", "Volf", "Viktor@mail.ru", new Car("Volvo", 3)));
        userService.add(new User("Misha", "Minin", "Misha@mail.ru", new Car("BMW", 4)));

        List<User> users = userService.listUsers();
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car " + user.getCar().getId());
            System.out.println();
        }

        List<User> users1 = Collections.singletonList(userService.getUserInCar("VAZ_2101", 1));
        for (User user1 : users1) {
            System.out.println("Id = " + user1.getId());
            System.out.println("First Name = " + user1.getFirstName());
            System.out.println("Last Name = " + user1.getLastName());
            System.out.println("Email = " + user1.getEmail());
            System.out.println("Car " + user1.getCar().getId());
            System.out.println();
        }

        context.close();
    }
}
