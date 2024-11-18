package com.parking.parkinglot.ejb;

import com.parking.parkinglot.common.CarDto;
import com.parking.parkinglot.common.UserDto;
import com.parking.parkinglot.entities.Car;
import com.parking.parkinglot.entities.User;
import com.parking.parkinglot.servlet.Users;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Stateless
public class UserBean {
    private static final Logger LOG = Logger.getLogger(CarsBean.class.getName());

    @PersistenceContext
    EntityManager entityManager;

    public List<UserDto> findAllUsers(){
        LOG.info("findAllUsers");
        try{
            TypedQuery<User> typedQuery = entityManager.createQuery("SELECT c FROM User c", User.class);
            List<User> users = typedQuery.getResultList();
            return copyUsersToDto(users);
        }
        catch(Exception ex){
            throw new EJBException(ex);
        }
    }

    List<UserDto> copyUsersToDto(List<User> users){
        List<UserDto> dtos = new ArrayList<UserDto>();
        for(User user : users){
            dtos.add(new UserDto(user.getId(), user.getEmail(), user.getPassword(), user.getUsername()));
        }
        return dtos;
    }



}
