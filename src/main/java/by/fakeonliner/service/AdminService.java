package by.fakeonliner.service;

import by.fakeonliner.entity.user.User;
import by.fakeonliner.repository.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private UserDao userDao;

//    public List<User> performOperation(String operation, User user, List<User> users, String userNumber) {
//        switch(operation) {
//            case "addAdmin" : {
//                if (!user.getRoleUser().equals("admin")) {
//                    User modifyUser = addAdminStatus(user);
//                    int number = Integer.parseInt(userNumber);
//                    users.set(number, modifyUser);
//                }
//                return users;
//            }
//            case "removeAdmin" : {
//                if (user.getRoleUser().equals("admin")) {
//                    User modifyUser = removeAdminStatus(user);
//                    int number = Integer.parseInt(userNumber);
//                    users.set(number, modifyUser);
//                }
//                return users;
//            }
//
//            case "deleteUser" : {
//                deleteUser(user);
//                users.remove(user);
//                return users;
//            }
//        }
//        return null;
//    }

// Не будет других админов
//    private User addAdminStatus(User user) {
//        user.setRoleUser(RoleUser.ADMIN);
////        userDao.addAdminStatus(user.getId());
//        return user;
//    }

    // Не будет других админов
//    private User removeAdminStatus(User user) {
//        user.setRoleUser(RoleUser.USER);
////        userDao.removeAdminStatus(user.getId());
//        return user;
//    }

    private void deleteUser(User user) {
//        userDao.delete(user.getId());
    }
}
