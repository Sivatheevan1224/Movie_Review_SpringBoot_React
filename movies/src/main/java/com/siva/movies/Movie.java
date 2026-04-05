package com.siva.movies;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "movies")
@Data // it handle all getter setter and toString method
@AllArgsConstructor // it handle all args constructor
@NoArgsConstructor // it handle no args constructor
public class Movie {
    @Id
    private ObjectId id;
    private String imdbId;
    private String title;
    private String releaseDate;
    private String trailerLink;
    private String poster;
    private List<String> genres;
    private List<String> backdrops;
    //it will store the review ids of the reviews related to this movie
    @DocumentReference // it create a reference to the reviews collection and store the review ids in this field
    private List<Review> reviewsIds;
}