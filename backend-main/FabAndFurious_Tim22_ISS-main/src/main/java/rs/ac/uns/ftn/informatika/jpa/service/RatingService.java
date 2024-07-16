package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.jpa.model.Accommodation;
import rs.ac.uns.ftn.informatika.jpa.model.Rating;
import rs.ac.uns.ftn.informatika.jpa.model.Report;
import rs.ac.uns.ftn.informatika.jpa.model.enums.AccommodationRequestStatus;
import rs.ac.uns.ftn.informatika.jpa.model.enums.RatingStatus;
import rs.ac.uns.ftn.informatika.jpa.model.enums.RatingType;
import rs.ac.uns.ftn.informatika.jpa.repository.AccommodationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.RatingRepository;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IRatingService;

import javax.el.PropertyNotFoundException;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RatingService implements IRatingService {
    private RatingRepository ratingRepository;
    private AccommodationRepository accommodationRepository;

    public RatingService(RatingRepository ratingRepository, AccommodationRepository accommodationRepository) {
        this.ratingRepository = ratingRepository;
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public List<Rating> getAll() {
        return this.ratingRepository.findAll();
    }

    @Override
    public Rating saveRating(Rating rating) {
        ratingRepository.save(rating);

        Accommodation accommodation = accommodationRepository.findById((long) rating.getAccommodationId()).orElseThrow(() -> new PropertyNotFoundException("Accommodation with ID " + rating.getAccommodationId() + " not found."));

        int counter=0;
        double average = 0;

        for (Rating r : ratingRepository.findAll()){
            if (r.getAccommodationId() == accommodation.getId().intValue()){
                counter++;
                average += r.getRating();

                System.out.println("r: " + r.getRating());
            }
        }
        double final_rate = average / counter;
        final_rate = Double.parseDouble(String.format("%.1f", final_rate));
        accommodation.setRate(final_rate);

        accommodationRepository.save(accommodation);

        System.out.println("acc" + accommodation);

        return rating;
    }

    @Override
    public Rating acceptRatingRequest(Long ratingId) {
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(() -> new PropertyNotFoundException("Rating with ID " + ratingId + " not found."));

        rating.setStatus(RatingStatus.ACCEPTED);

        return ratingRepository.save(rating);
    }

    @Override
    public Rating rejectRatingRequest(Long ratingId) {
        Rating rating = ratingRepository.findById(ratingId).orElseThrow(() -> new PropertyNotFoundException("Rating with ID " + ratingId + " not found."));

        rating.setStatus(RatingStatus.REJECTED);

        return ratingRepository.save(rating);
    }


    @Override
    public List<Rating> getAllHostRatings(Long hostId) {
        List<Rating> ratings = ratingRepository.findAll();
        List<Rating> hostRatings = new ArrayList<>();
        for (Rating r: ratings) {
            if (r.getType() == RatingType.HOST){
                Accommodation accommodation = accommodationRepository.findById(Long.valueOf(r.getAccommodationId())).orElseThrow(() -> new PropertyNotFoundException("Accommodation not found."));;
                if (accommodation.getHostId() == hostId)
                    {hostRatings.add(r);}
            }
        }
        return hostRatings;
    }
    @Override
    public List<Rating> getAllAccommodationRatings(Long accommodationId) {
        List<Rating> ratings = ratingRepository.findAll();
        List<Rating> accommodationRatings = new ArrayList<>();
        for (Rating r: ratings) {
            if (r.getType() == RatingType.ACCOMMODATION){
                if(r.getAccommodationId()==accommodationId.intValue())
                    {accommodationRatings.add(r);}
            }
        }
        return accommodationRatings;
    }
    @Override
    public Optional<Rating> getRating(String id) {
        return  this.ratingRepository.findById(Long.parseLong(id));
    }

    @Override
    public void deleteRating(Long id) {
        Rating existingRating = ratingRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rating with ID " + id + " not found"));

        ratingRepository.delete(existingRating);
    }
}
