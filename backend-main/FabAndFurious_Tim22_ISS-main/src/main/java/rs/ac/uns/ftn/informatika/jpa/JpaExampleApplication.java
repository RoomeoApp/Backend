package rs.ac.uns.ftn.informatika.jpa;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import rs.ac.uns.ftn.informatika.jpa.model.*;
import rs.ac.uns.ftn.informatika.jpa.model.enums.*;
import rs.ac.uns.ftn.informatika.jpa.repository.AccommodationRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.RatingRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.ReportRepository;
import rs.ac.uns.ftn.informatika.jpa.repository.ReservationRepository;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class JpaExampleApplication {

	@Autowired
	AccommodationRepository accommodationRepository;
	@Autowired
	ReservationRepository reservationRepository;
	@Autowired
	ReportRepository reportRepository;
	@Autowired
	RatingRepository ratingRepository;

	public static void main(String[] args) {

		SpringApplication.run(JpaExampleApplication.class, args);

	}

	@Bean
	protected InitializingBean sendDatabase() {
		return this::databaseInitialization;
	}

	private void databaseInitialization() {

		EcoFriendlyAmenity solarPanels = new EcoFriendlyAmenity(EcoAmenity.SolarPanels);
		EcoFriendlyAmenity smartThermostats = new EcoFriendlyAmenity(EcoAmenity.SmartThermostats);
		EcoFriendlyAmenity energyEfficientDevices = new EcoFriendlyAmenity(EcoAmenity.EnergyEfficientDevices);
		EcoFriendlyAmenity zeroWastePolicy = new EcoFriendlyAmenity(EcoAmenity.ZeroWastePolicy);
		EcoFriendlyAmenity recyclingBins = new EcoFriendlyAmenity(EcoAmenity.RecyclingBins);
		EcoFriendlyAmenity limitedUseOfSingleUsePlastics = new EcoFriendlyAmenity(EcoAmenity.LimitedUseOfSingleUsePlastics);
		EcoFriendlyAmenity waterSavingFaucetsShowersToilets = new EcoFriendlyAmenity(EcoAmenity.WaterSavingFaucetsShowersToilets);
		EcoFriendlyAmenity naturalNonToxicMaterials = new EcoFriendlyAmenity(EcoAmenity.NaturalNonToxicMaterials);
		EcoFriendlyAmenity greenSpacesAndGardens = new EcoFriendlyAmenity(EcoAmenity.GreenSpacesAndGardens);
		EcoFriendlyAmenity freeBicyclesForGuests = new EcoFriendlyAmenity(EcoAmenity.FreeBicyclesForGuests);
		EcoFriendlyAmenity electricVehicleChargers = new EcoFriendlyAmenity(EcoAmenity.ElectricVehicleChargers);

		List<String> availability1 = Arrays.asList("07/15/2024", "07/16/2024","07/17/2024", "07/18/2024","07/19/2024", "07/20/2024","07/21/2024", "07/22/2024", "07/23/2024","07/24/2024", "07/25/2024", "07/26/2024");
		List<String> availability2 = Arrays.asList("07/24/2024", "07/25/2024", "07/26/2024","07/27/2024", "07/28/2024", "07/29/2024"); //24-26, 3
		List<String> availability3 = Arrays.asList("07/25/2024", "07/26/2024","07/27/2024", "07/28/2024","07/29/2024", "07/30/2024");
		List<String> availability4 = Arrays.asList("07/26/2024", "07/27/2024");
		List<String> availability5 = Arrays.asList("07/15/2024", "07/16/2024","07/17/2024", "07/18/2024","07/19/2024", "07/20/2024");
		List<String> availability6 = Arrays.asList("07/25/2024", "07/26/2024","07/27/2024", "07/28/2024","07/29/2024", "07/30/2024");

		Accommodation a1 = new Accommodation("Hotel Centar 1", "Comfortable apartment located in a quiet part of the city.", "Uspenska 1, Novi Sad", AccommodationType.ROOM, true, true, false, true, availability1, Payment.PerPerson, 150, BookingMethod.AUTOMATIC, 2, 5, AccommodationRequestStatus.ACCEPTED, Long.parseLong("3"), 10, 5, "image1;image2;image3");
		Accommodation a3 = new Accommodation("Hotel Centar 2", "Comfortable apartment located in a quiet part of the city.", "Uspenska 15, Novi Sad", AccommodationType.ROOM, true, true, false, true, availability2, Payment.PerPerson, 200, BookingMethod.NON_AUTOMATIC, 2, 3, AccommodationRequestStatus.ACCEPTED, Long.parseLong("3"), 10, 5, "image3;image2");
		Accommodation a2 = new Accommodation("Hotel Moskva 1", "Comfortable apartment located in city center.", "Balkanska 1, Beograd", AccommodationType.ROOM, true, true, true, false, availability3, Payment.PerAccommodation, 250, BookingMethod.AUTOMATIC, 4, 6, AccommodationRequestStatus.ACCEPTED, Long.parseLong("3"), 20, 7, "image2");
		Accommodation a4 = new Accommodation("Hotel Moskva 2", "Comfortable apartment located in city center.", "Balkanska 1, Beograd", AccommodationType.ROOM, true, true, true, false, availability4, Payment.PerAccommodation, 235, BookingMethod.AUTOMATIC, 4, 8, AccommodationRequestStatus.ACCEPTED, Long.parseLong("3"), 50, 7, "image1");
		Accommodation a5 = new Accommodation("Studio 11", "Spacious studio located in city center.", "Jevrejska 14, Novi Sad", AccommodationType.STUDIO, true, false, true, true, availability5, Payment.PerPerson, 200, BookingMethod.NON_AUTOMATIC, 2, 3, AccommodationRequestStatus.PENDING, Long.parseLong("3"), 0, 2, "image3");
		Accommodation a6 = new Accommodation("Studio 12", "Spacious studio located in city center.", "Jevrejska 15, Novi Sad", AccommodationType.STUDIO, true, false, true, true, availability6, Payment.PerPerson, 150, BookingMethod.NON_AUTOMATIC, 2, 3, AccommodationRequestStatus.ACCEPTED, Long.parseLong("3"), 5, 2, "image2");

		a1.getEcoFriendlyAmenities().add(solarPanels);
		a1.getEcoFriendlyAmenities().add(waterSavingFaucetsShowersToilets);
		a1.getEcoFriendlyAmenities().add(recyclingBins);
		a1.getEcoFriendlyAmenities().add(freeBicyclesForGuests);

        a2.getEcoFriendlyAmenities().add(electricVehicleChargers);
        a2.getEcoFriendlyAmenities().add(naturalNonToxicMaterials);

        a3.getEcoFriendlyAmenities().add(limitedUseOfSingleUsePlastics);
		a3.getEcoFriendlyAmenities().add(energyEfficientDevices);

        a4.getEcoFriendlyAmenities().add(smartThermostats);
        a4.getEcoFriendlyAmenities().add(greenSpacesAndGardens);
        a4.getEcoFriendlyAmenities().add(zeroWastePolicy);

		a1.setRate(4.3);
		a2.setRate(3.8);
		a3.setRate(4.7);
		a4.setRate(5.0);
		a5.setRate(3.3);
        a6.setRate(4.9);
		accommodationRepository.save(a1);
		accommodationRepository.save(a2);
		accommodationRepository.save(a3);
		accommodationRepository.save(a4);
		accommodationRepository.save(a5);
		accommodationRepository.save(a6);

		Rating r1 = new Rating(4.3,  RatingStatus.ACCEPTED, 1);
		Rating r2 = new Rating(3.8,  RatingStatus.ACCEPTED, 2);
		Rating r3 = new Rating(4.7,  RatingStatus.ACCEPTED, 3);
        Rating r4 = new Rating(5.0,  RatingStatus.ACCEPTED, 4);
        Rating r5 = new Rating(3.3,  RatingStatus.ACCEPTED, 5);
		Rating r6 = new Rating(4.9,  RatingStatus.ACCEPTED, 6);
		ratingRepository.save(r1);
		ratingRepository.save(r2);
		ratingRepository.save(r3);
		ratingRepository.save(r4);
		ratingRepository.save(r5);
		ratingRepository.save(r6);


		Reservation reservation1 = new Reservation(1L, "07/16/2024","07/17/2024", ReservationRequestStatus.ACCEPTED, Long.valueOf(2), 150);
		Reservation reservation2 = new Reservation(1L, "07/18/2024","07/20/2024", ReservationRequestStatus.ACCEPTED, Long.valueOf(2), 300);
		Reservation reservation3 = new Reservation(3L, "07/27/2024","07/31/2024", ReservationRequestStatus.ACCEPTED, Long.valueOf(2), 350);
		Reservation reservation4 = new Reservation(2L, "08/04/2024","08/11/2024", ReservationRequestStatus.ACCEPTED, Long.valueOf(2), 200);
		Reservation reservation5 = new Reservation(4L, "06/05/2024","06/06/2024", ReservationRequestStatus.REJECTED, Long.valueOf(4), 200);
		Reservation reservation6 = new Reservation(4L, "03/27/2024","03/29/2024", ReservationRequestStatus.REJECTED, Long.valueOf(4), 200);
		Reservation reservation7 = new Reservation(4L, "03/06/2024","03/09/2024", ReservationRequestStatus.REJECTED, Long.valueOf(4), 200);
		Reservation reservation8 = new Reservation(4L, "02/19/2024","02/29/2024", ReservationRequestStatus.REJECTED, Long.valueOf(4), 500);
		Reservation reservation9 = new Reservation(4L, "01/19/2024","01/19/2024", ReservationRequestStatus.REJECTED, Long.valueOf(4), 600);
        Reservation reservation10 = new Reservation(3L, "08/01/2024","08/03/2024", ReservationRequestStatus.REJECTED, Long.valueOf(2), 100);
		Reservation reservation11 = new Reservation(3L, "07/11/2024","07/16/2024", ReservationRequestStatus.ACCEPTED, Long.valueOf(2), 250);
		reservationRepository.save(reservation1);
		reservationRepository.save(reservation2);
		reservationRepository.save(reservation3);
		reservationRepository.save(reservation4);
		reservationRepository.save(reservation5);
		reservationRepository.save(reservation6);
		reservationRepository.save(reservation7);
		reservationRepository.save(reservation8);
		reservationRepository.save(reservation9);
        reservationRepository.save(reservation10);
		reservationRepository.save(reservation11);
	}
}