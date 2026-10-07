package dto;

import entities.Review;
import entities.User;

import java.util.List;

public record UserAndReviewsDTO(User user, List<Review> reviews) {
}
