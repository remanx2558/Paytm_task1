package src.main.service;

import src.main.model.User;
import src.main.util.DataLoader;

import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    public User getUserById(int id) {
        return DataLoader.users.get(id);
    }

    public List<User> getAllUsers() {
        return DataLoader.users.values().stream().collect(Collectors.toList());
    }

    public List<User> getUsersByOccupation(String occupation) {
        return DataLoader.users.values().stream()
                .filter(user -> user.getOccupation().equalsIgnoreCase(occupation))
                .collect(Collectors.toList());
    }
}
