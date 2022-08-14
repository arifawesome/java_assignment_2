package com.example.java_final_1.controller;

import com.example.java_final_1.models.Customer;
import com.example.java_final_1.models.MoviesAndTvs;
import com.example.java_final_1.repositories.MoviesAndTvsRepository;
import com.example.java_final_1.service.MoviesAndTvsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.repository.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Validated
@RequestMapping("/MoviesAndTvs")
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class MoviesAndTvsController {
    @Autowired
    public MoviesAndTvsRepository moviesAndTvsRepository;
    @Autowired
    public MoviesAndTvsService moviesAndTvsService;

    @GetMapping("/movies")
    public ResponseEntity<List<MoviesAndTvs>> getAllMovies() {
        List<MoviesAndTvs> movies = moviesAndTvsService.getAllMovies();
        return new ResponseEntity(movies, HttpStatus.OK);
    }

    @GetMapping("/tvs")
    public ResponseEntity<List<MoviesAndTvs>> getAllTvs() {
        List<MoviesAndTvs> tvs = moviesAndTvsService.getAllTvs();
        return new ResponseEntity(tvs, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MoviesAndTvs>> getSearchContent(@RequestParam(value = "searchString") String searchString) {
        //System.out.println("hi this is working");
        List<MoviesAndTvs> searchlist = moviesAndTvsService.getSearchContent(searchString);
        if(searchlist.isEmpty()){
            return new ResponseEntity("No result found", HttpStatus.NOT_FOUND);
        }else{
            return new ResponseEntity(searchlist, HttpStatus.OK);
        }

    }

    @GetMapping("/movie")
    public ResponseEntity<List<MoviesAndTvs>> getFeaturedMovies(@RequestParam(value = "isFeatured") boolean isFeatured) {
        List<MoviesAndTvs> featuredMovies = moviesAndTvsService.getFeaturedMovies(isFeatured);
        if(featuredMovies.isEmpty()){
            return new ResponseEntity("No result found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(featuredMovies, HttpStatus.OK);
    }

    @GetMapping("/tv")
    public ResponseEntity<List<MoviesAndTvs>> getFeatureTvs(@RequestParam(value = "isFeatured") boolean isFeatured) {
        List<MoviesAndTvs> featuredTvs = moviesAndTvsService.getFeaturedTvs(isFeatured);
        if(featuredTvs.isEmpty()){
            return new ResponseEntity("No result found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(featuredTvs, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MoviesAndTvs> getAllMoviesAndTvs(@PathVariable("id") String id) {
        Optional<MoviesAndTvs> result = moviesAndTvsRepository.findById(id);
        ArrayList<MoviesAndTvs> items= new ArrayList<>();
        if (result.isPresent()) {
            return new ResponseEntity(result, HttpStatus.OK);
        } else {
            return new ResponseEntity("content is not exist", HttpStatus.NOT_FOUND);
        }

    }

    @PutMapping("/update/{id}")
    public  ResponseEntity<MoviesAndTvs> updateMoviesOrTvs(@PathVariable("id") String id, @RequestBody MoviesAndTvs moviesAndTvs) {
        Optional<MoviesAndTvs> result = moviesAndTvsRepository.findById(id);

        if(result.isPresent()){moviesAndTvsService.updateMoviesOrTvs(id, moviesAndTvs);
            return new ResponseEntity("Updated successfully", HttpStatus.OK);}
        else {
            return new ResponseEntity("content "+id+ " not exist", HttpStatus.NOT_FOUND);

        }



    }
    @DeleteMapping("/delete/{id}")
    public  ResponseEntity deleteMoviesOrTvs(@PathVariable("id") String id) {
        Optional<MoviesAndTvs> result = moviesAndTvsRepository.findById(id);
        System.out.println(result);
        if(result.isPresent()){
            //System.out.println("hi this is working too");
            moviesAndTvsService.delete(id);
            return new ResponseEntity("content deleted successfully", HttpStatus.OK);
        }
            return new ResponseEntity("content "+id+ " not exist for deletion", HttpStatus.NOT_FOUND);





    }
}
