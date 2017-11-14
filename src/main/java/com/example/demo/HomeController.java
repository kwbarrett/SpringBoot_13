package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;


@Controller
public class HomeController {

    @Autowired
    ActorRepository actorRepository;

    @Autowired
    MovieRepository movieRepository;

    @RequestMapping("/")
    public String loadData(Model model){

        Actor a1 = new Actor();

        Movie m1 = new Movie();
        m1.setTitle("My Movie");
        m1.setDescription("My Movie is a great movie.");
        m1.setYear(2017);

        Movie m2 = new Movie();
        m2.setTitle("My Movie2");
        m2.setDescription("My Movie2 is a great movie.");
        m2.setYear(2017);

        Set<Movie> movies = new HashSet<>();

        a1.setName("Some Actor");
        a1.setRealName("Actor Realname");

        movies.add(m1);
        movies.add(m2);
        a1.setMovies(movies);
        actorRepository.save(a1);

        Set<Actor> cast = new HashSet<Actor>();
        cast.add(a1);

        m1.setCast(cast);
        m2.setCast(cast);
        movieRepository.save(m1);
        movieRepository.save(m2);

        model.addAttribute("actors", actorRepository.findAll());
        model.addAttribute("movies", movieRepository.findAll());


        return "index";
    }


}
