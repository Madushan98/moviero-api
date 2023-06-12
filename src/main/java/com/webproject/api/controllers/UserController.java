package com.webproject.api.controllers;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webproject.api.cart.CartDetailResponse;
import com.webproject.api.cart.CartService;
import com.webproject.api.purches.PurchesMoviesRequest;
import com.webproject.api.purches.StreamPurchesDetailResponse;
import com.webproject.api.purches.StreamPurchesService;

import com.webproject.api.repository.*;
import com.webproject.api.user.*;


@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    MovieRepository movieRepository;

    @Autowired
    CartRepository cartRepository;


    @Autowired
    StreamPurchesRepository streamRepository;

    @Autowired
    StreamPurchesService streamService;

    @Autowired
    CartService cartService;

    @GetMapping
    public String GetUser() {

        return "users";
    }


    @PostMapping
    public UserDetailResponse CreateUser(@RequestBody UserDetailRequestModel userDetail) throws Exception {

        UserDetailResponse userResponse = new UserDetailResponse();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetail, userDto);

        if (userRepository.findByEmail(userDetail.getEmail()) != null) {

        }

        UserDto returnUser = userService.createUser(userDto);


        BeanUtils.copyProperties(returnUser, userResponse);

        return userResponse;
    }


    @GetMapping(path = "/{id}")
    public UserDetailResponse GetUser(@PathVariable String id) {

        UserDetailResponse returnUser = new UserDetailResponse();
        UserDto user = userService.getUserById(id);


        BeanUtils.copyProperties(user, returnUser);

        return returnUser;

    }

    @PutMapping(path = "/{id}")
    public UserDetailResponse UserDetailUpdate(@PathVariable String id, @RequestBody UserDetailRequestModel userDetail) {

        UserDetailResponse returnUser = new UserDetailResponse();
        UserDto userCopy = new UserDto();

        BeanUtils.copyProperties(userDetail, userCopy);

        UserDto updateUser = userService.updateUser(userCopy, id);

        BeanUtils.copyProperties(updateUser, returnUser);


        return returnUser;

    }


    @PutMapping(path = "/{userId}/cart/{movieId}")
    public String AddToCart(@PathVariable(value = "userId") String userId, @PathVariable(value = "movieId") String movieId) throws Exception {

        String returnMessage = cartService.AddToCart(movieId, userId);

        return returnMessage;

    }


    @DeleteMapping(path = "/{userId}/cart/{movieId}")
    public CartDetailResponse RemoveFromCart(@PathVariable(value = "userId") String userId, @PathVariable(value = "movieId") String movieId) throws Exception {

        String returnMessage = cartService.removeFromCart(movieId, userId);

        CartDetailResponse returnCart = cartService.getCart(userId);

        return returnCart;

    }


    @GetMapping(path = "/{userId}/cart")
    public CartDetailResponse GetUserCartDetails(@PathVariable String userId) {


        CartDetailResponse returnCart = cartService.getCart(userId);


        return returnCart;

    }


    @GetMapping(path = "/{userId}/purches")
    public StreamPurchesDetailResponse GetUserPurchesDetails(@PathVariable String userId) {


        StreamPurchesDetailResponse returnStreamDetails = streamService.getStreamDetails(userId);


        return returnStreamDetails;

    }


    @PutMapping(path = "/{userId}/purches")
    public StreamPurchesDetailResponse BuyMovies(@PathVariable String userId, @RequestBody PurchesMoviesRequest purchesMovies) {


        List<String> movies = purchesMovies.getMovies();


        StreamPurchesDetailResponse returnStreamDetails = streamService.buyMovies(movies, userId);


        return returnStreamDetails;

    }


}
