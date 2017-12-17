package com.petrushin.main;

import com.petrushin.builder.UserBuilder;
import com.petrushin.dao.GenericDAO;
import com.petrushin.dao.exception.AbstractDAOException;
import com.petrushin.dao.exception.UserDAOException;
import com.petrushin.dao.impl.UserDAOImpl;
import com.petrushin.domain.*;

import java.util.List;

public class Main {

    public static void main(String[] args) {
            GenericDAO<User> userDAO = new UserDAOImpl(new UserBuilder());
        try {
            System.out.println(userDAO.getAll());
        } catch (AbstractDAOException e) {
            e.printStackTrace();
        }
//
////        UserShippingAddress address = new UserShippingAddress(
////                0, 5, "Егор", "Евгеньевич", "Иванов",
////                "Belarus", "Жодино", "ул. Темирязево 20 д 40",
////                "375291113252", "222160", true);
//
////        UserCard card = new UserCard(0,4,"1634163264332344", "Hello");
//
////        UserFlowerLot lot = new UserFlowerLot(0,5, "луговые", "Цветок", "Нашел на балконе",
////                40.50, null);
//
////        UserBet userBet = new UserBet(7,7,100.205);
//        User user = new User(0,2,"ZhorikSuper", "4totoTam", "privet@mail.ru");
//
//
//        try {
////            entityDAO.add(user);
//            List<User> userList = entityDAO.getAll();
//            for(User u: userList){
//                System.out.println(u);
//            }
//            System.out.println(entityDAO.ifLoginExist("senias"));
//        } catch (AbstractDAOException e) {
//            e.printStackTrace();
//        }
    }
}
