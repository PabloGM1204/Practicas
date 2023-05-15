package com.serbatic.facturas.service;

import org.apache.velocity.exception.ResourceNotFoundException;
import com.serbatic.facturas.accessingData.User;

public interface UserServiceInterface {

  User addNewUser(String name, String firstSurname, String secondSurname, String email);

  User updateUserPartially(Long userId, User userDetails) throws ResourceNotFoundException;

  User findUser(Long userId) throws ResourceNotFoundException;

  void deleteEmployee(Long id);

  Iterable<User> getAllUsers();
}
