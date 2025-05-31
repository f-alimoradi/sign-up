package org.example;

import org.example.framework.SingletonSessionFactory;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Optional;

public class UserService {
    public static void signUp(String firstName, String lastName, int age, String email, String password) {
        if (password.length() < 8) {
            throw new IllegalArgumentException("Weak password");
        }

        if (findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("An account with this email already exists");
        }

        User newUser = new User(firstName, lastName, age, email, password);
        SingletonSessionFactory.get().inTransaction(session -> {
            session.persist(newUser);
        });
    }

    public static User login(String email, String password) {
        Optional<User> userOptional = findByEmail(email);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("User with this email does not exist");
        }

        User user = userOptional.get();
        if (!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Incorrect password");
        }

        return user;
    }

    private static Optional<User> findByEmail(String email) {
        return SingletonSessionFactory.get().fromTransaction(session -> {
            Query<User> query = session.createQuery("FROM User WHERE email = :email", User.class);
            query.setParameter("email", email);
            return query.uniqueResultOptional();
        });
    }

    public static List<User> getAllUsers() {
        return SingletonSessionFactory.get().fromTransaction(session -> {
            return session.createQuery("FROM User", User.class).getResultList();
        });
    }
}