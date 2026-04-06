import React from "react";
import "./Hero.css";
import { Carousel } from "react-bootstrap";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faCirclePlay } from "@fortawesome/free-solid-svg-icons";
import { Link , useNavigate} from "react-router-dom";
import { Button } from "react-bootstrap";

const Hero = ({ movies }) => {
    const navigate = useNavigate();

    function reviews(movieId){
        navigate(`/Reviews/${movieId}`);
    }

  if (!movies || movies.length === 0) {
    return <div>Loading...</div>;
  }

  return (
    <div>
      <Carousel>
        {movies.map((movie, index) => (
          <Carousel.Item key={index}>
            <div className="movie-card-container">
              <div
                className="movie-card"
                style={{ "--img": `url(${movie.backdrops[0]})` }}
              >
                <div className="movie-detail">
                  <div className="movie-poster">
                    <img src={movie.poster} alt={movie.title} />
                  </div>
                  <div className="movie-title">
                    <h4>{movie.title}</h4>
                  </div>
                  <div className="movie-button-container">
                    <Link
                      to={`/Trailer/${movie.trailerLink.substring(movie.trailerLink.length - 11)}`}
                      className="play-button-link"
                    >
                      <div className="play-button-icon-container">
                        <FontAwesomeIcon
                          icon={faCirclePlay}
                          className="play-button-icon"
                        />
                      </div>
                    </Link>

                    <div className="movie-review-button-container">
                        <Button varient="info" onClick={()=> reviews(movie.imdbId)}>
                            Reviews
                        </Button>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </Carousel.Item>
        ))}
      </Carousel>
    </div>
  );
};

export default Hero;
