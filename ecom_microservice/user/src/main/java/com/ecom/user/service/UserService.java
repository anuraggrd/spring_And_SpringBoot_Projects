package com.ecom.user.service;


import com.ecom.user.dto.AddressDto;
import com.ecom.user.dto.UserRequest;
import com.ecom.user.dto.UserResponse;
import com.ecom.user.entity.Address;
import com.ecom.user.entity.User;
import com.ecom.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userDb;

    public List<UserResponse> fetchAllUser() {
        return userDb.findAll().stream().map(UserService::entityToReponse).collect(Collectors.toList());
    }

    public boolean addUser(UserRequest user) {

        User save = (User) userDb.save(RequestToUserEntity(user));
        return save != null ? true : false;
    }

    public Optional<UserResponse> fetchUser(String id) {

        return userDb.findById(id).map(UserService::entityToReponse);
    }

    public Boolean updateUser(String id, UserRequest updateduser) {
        return userDb.findById(id).map(existinguser -> {
            updateUserFromRequest(existinguser, updateduser);
            userDb.save(existinguser);
            return true;
        }).orElse(false);

    }

    public Boolean removeUser(String id) {
        User o = (User) userDb.findById(id).get();
        userDb.delete(o);
        return o != null ? true : false;

    }

    private void updateUserFromRequest(User existinguser, UserRequest updateduser) {

        existinguser.setFirstName(updateduser.getFirstName());
        existinguser.setLastName(updateduser.getLastName());
        existinguser.setEmail(updateduser.getEmail());
        existinguser.setPhoneNum(updateduser.getPhoneNum());

        if (updateduser.getAddress() != null) {
            Address adddentity = new Address();
            adddentity.setCity(updateduser.getAddress().getCity());
            adddentity.setState(updateduser.getAddress().getState());
            adddentity.setStreet(updateduser.getAddress().getStreet());
            adddentity.setCountry(updateduser.getAddress().getCountry());
            adddentity.setZipcode(updateduser.getAddress().getZipcode());
            existinguser.setAddress(adddentity);
        }
    }

    private static UserResponse entityToReponse(User user) {
        UserResponse response = new UserResponse();
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhoneNum(user.getPhoneNum());

        if (user.getAddress() != null) {
            AddressDto adddto = new AddressDto();
            adddto.setCity(user.getAddress().getCity());
            adddto.setState(user.getAddress().getState());
            adddto.setStreet(user.getAddress().getStreet());
            adddto.setCountry(user.getAddress().getCountry());
            adddto.setZipcode(user.getAddress().getZipcode());
            response.setAddress(adddto);
        }

        return response;
    }

    private static User RequestToUserEntity(UserRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPhoneNum(request.getPhoneNum());

        if (request.getAddress() != null) {
            Address adddentity = new Address();
            adddentity.setCity(request.getAddress().getCity());
            adddentity.setState(request.getAddress().getState());
            adddentity.setStreet(request.getAddress().getStreet());
            adddentity.setCountry(request.getAddress().getCountry());
            adddentity.setZipcode(request.getAddress().getZipcode());
            user.setAddress(adddentity);
        }
        return user;
    }
}
