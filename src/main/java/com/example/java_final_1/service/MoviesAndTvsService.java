package com.example.java_final_1.service;

import com.example.java_final_1.models.MoviesAndTvs;
import com.example.java_final_1.repositories.MoviesAndTvsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
public class MoviesAndTvsService {

    @Autowired
    public MoviesAndTvsRepository moviesAndTvsRepository;

    public ArrayList<MoviesAndTvs> getAllMovies() {
        ArrayList<MoviesAndTvs> moviesOnly = new ArrayList<>();
        int count = moviesAndTvsRepository.findAll().size();
        for (int i = 0; i < count; i++) {
            if (moviesAndTvsRepository.findAll().get(i).getType().equalsIgnoreCase("movies")) {
                moviesOnly.add(moviesAndTvsRepository.findAll().get(i));
            }
        }
        return moviesOnly;
    }

    public List<MoviesAndTvs> getAllTvs() {
        ArrayList<MoviesAndTvs> tvOnly = new ArrayList<>();
        int count = moviesAndTvsRepository.findAll().size();
        for (int i = 0; i < count; i++) {
            if (moviesAndTvsRepository.findAll().get(i).getType().equalsIgnoreCase("tvs")) {
                tvOnly.add(moviesAndTvsRepository.findAll().get(i));
            }
        }
        return tvOnly;

    }

    public List<MoviesAndTvs> getSearchContent(String searchString) {
        ArrayList<MoviesAndTvs> searchlist = new ArrayList<>();
        int count = moviesAndTvsRepository.findAll().size();
        System.out.println(count);
        for (int i = 0; i < count; i++) {
            if (moviesAndTvsRepository.findAll().get(i).getTitle().toLowerCase().contains(searchString)) {
                searchlist.add(moviesAndTvsRepository.findAll().get(i));
            }
        }
        return searchlist;

    }

    public List<MoviesAndTvs> getFeaturedMovies(boolean isFeatured) {
        ArrayList<MoviesAndTvs> Movieslist = new ArrayList<>();
        ArrayList<MoviesAndTvs> featuredMovieslist = new ArrayList<>();
        int count = moviesAndTvsRepository.findAll().size();
        for (int i = 0; i < count; i++) {
            if (moviesAndTvsRepository.findAll().get(i).getType().equalsIgnoreCase("movies")) {
                Movieslist.add(moviesAndTvsRepository.findAll().get(i));
                int count_1 = Movieslist.size();
                for (int j = 0; i < count_1; j++) {
                    if (moviesAndTvsRepository.findAll().get(i).isFeatured()==isFeatured) {
                        featuredMovieslist.add(moviesAndTvsRepository.findAll().get(i));
                    }
                }

            }
        }
        return featuredMovieslist;

    }

    public List<MoviesAndTvs> getFeaturedTvs(boolean isFeatured) {
        ArrayList<MoviesAndTvs> Tvslist = new ArrayList<>();
        ArrayList<MoviesAndTvs> featuredTvslist = new ArrayList<>();
        int count = moviesAndTvsRepository.findAll().size();
        for (int i = 0; i < count; i++) {
            if (moviesAndTvsRepository.findAll().get(i).getType().equalsIgnoreCase("tvs")) {
                Tvslist.add(moviesAndTvsRepository.findAll().get(i));
                int count_1 = Tvslist.size();
                for (int j = 0; i < count_1; j++) {
                    if (moviesAndTvsRepository.findAll().get(i).isFeatured()==isFeatured) {
                        featuredTvslist.add(moviesAndTvsRepository.findAll().get(i));
                    }
                }

            }
        }
        return featuredTvslist;
    }

    public Optional<MoviesAndTvs> updateMoviesOrTvs(String id, MoviesAndTvs moviesAndTvs) {

          Optional <MoviesAndTvs> moviesAndTvs1=  moviesAndTvsRepository.findById(id);
        if (moviesAndTvs1.get().getId().equals(id)) {

            moviesAndTvs1.stream().forEach(e -> {
                e.setTitle(moviesAndTvs.getTitle());
                e.setSynopsis(moviesAndTvs.getSynopsis());
                e.setPrice(moviesAndTvs.getPrice());
                e.setSmallPosterUrl(moviesAndTvs.getSmallPosterUrl());
                e.setBigPosterUrl(moviesAndTvs.getBigPosterUrl());
                e.setType(moviesAndTvs.getType());
                e.setBuyPrice(moviesAndTvs.getBuyPrice());
                e.setFeatured(moviesAndTvs.isFeatured());
                e.setRentPrice(moviesAndTvs.getRentPrice());});
        }
        System.out.println(moviesAndTvs1);
        moviesAndTvsRepository.save(moviesAndTvs1.get());
        return moviesAndTvs1;
        }





    public void delete(String id) {
        moviesAndTvsRepository.deleteById(id);

    }
}