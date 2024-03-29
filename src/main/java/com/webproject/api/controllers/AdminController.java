package com.webproject.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webproject.api.repository.UserRepository;
import com.webproject.api.analysis.AnalysisDetailResponse;
import com.webproject.api.analysis.AnalysisService;
import com.webproject.api.movie.MovieDetailRequest;
import com.webproject.api.movie.MovieDetailsResponse;
import com.webproject.api.movie.MovieDto;
import com.webproject.api.movie.MovieService;
import com.webproject.api.user.*;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AnalysisService analysisService;

    @Autowired
    MovieService movieService;

    @GetMapping("/users")
    public List<UserDetailResponse> getUsers() {

        List<UserDetailResponse> users = new ArrayList<>();

        List<UserDto> userResponse = userService.getAllUsers();

        for (UserDto userDto : userResponse) {
            UserDetailResponse user = new UserDetailResponse();
            BeanUtils.copyProperties(userDto, user);
            users.add(user);
        }

        return users;
    }


    @PutMapping(path = "/edit/{id}")
    public MovieDetailsResponse movieDetailUpdate(@PathVariable String id, @RequestBody MovieDetailRequest movieDetail) throws Exception {

        MovieDetailsResponse returnMovie = new MovieDetailsResponse();

        MovieDto movieCopy = new MovieDto();

        BeanUtils.copyProperties(movieDetail, movieCopy);

        MovieDto updateMovie = movieService.updateMovie(movieCopy, id);

        BeanUtils.copyProperties(updateMovie, returnMovie);


        return returnMovie;

    }

    @GetMapping(path = "/analytics")
    public AnalysisDetailResponse getAnalysis() {

        return analysisService.getData();

    }

}
