package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserInCar(String model, int series) {
       Session session = sessionFactory.openSession();
       Query query = session.createQuery("from Car where model = :model and series = :series");
       query.setParameter("model", model);
       query.setParameter("series", series);
       List<Car> cars = query.list();
       Car car = cars.get(0);
       List<User> users = session.createQuery("from User where car_id = " + car.getId()).list();
       session.close();
       return users.get(0);
   }


}
