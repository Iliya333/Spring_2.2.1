package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("Vasia", "Popov", "Vasia@mail.ru", new Car("VAZ_2101", 1)));
      userService.add(new User("Bob", "Pit", "Bob@mail.ru", new Car("Mazda",  2)));
      userService.add(new User("Viktor", "Volf", "Viktor@mail.ru", new Car("Volvo", 3)));
      userService.add(new User("Misha", "Minin", "Misha@mail.ru", new Car("BMW", 4)));



      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car " +  user.getCar().getId());
         System.out.println();
      }

      User user1 = userService.getUserInCar("VAZ_2101", 1);
      User user2 = userService.getUserInCar("Mazda",  2);
      User user3 = userService.getUserInCar("Volvo", 3);
      User user4 = userService.getUserInCar("BMW", 4);

      System.out.println(user1.getFirstName());
      System.out.println(user2.getFirstName());
      System.out.println(user3.getFirstName());
      System.out.println(user4.getFirstName());


      context.close();
   }
}
