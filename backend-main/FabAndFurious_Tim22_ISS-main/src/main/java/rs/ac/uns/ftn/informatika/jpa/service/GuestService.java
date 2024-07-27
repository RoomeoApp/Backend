package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.jpa.model.Guest;
import rs.ac.uns.ftn.informatika.jpa.repository.GuestRepository;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IGuestService;

import java.util.List;
import java.util.Optional;

@Service
public class GuestService  implements IGuestService {

    private GuestRepository guestRepository;

    @Autowired
    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> getAll() {
        return this.guestRepository.findAll();
    }

    @Override
    public Optional<Guest> getGuest(String id) {
        return this.guestRepository.findById(Long.parseLong(id));
    }

    public void add(Guest guest) {
        this.guestRepository.save(guest);
    }

    public Guest updateGuest(Long id, Guest updatedGuest) {
        Optional<Guest> existingGuestOpt = guestRepository.findById(id);
        if (existingGuestOpt.isPresent()) {
            Guest existingGuest = existingGuestOpt.get();
            existingGuest.setFirstName(updatedGuest.getFirstName());
            existingGuest.setLastName(updatedGuest.getLastName());
            existingGuest.setEmail(updatedGuest.getEmail());
            existingGuest.setPhoneNumber(updatedGuest.getPhoneNumber());
            existingGuest.setAddress(updatedGuest.getAddress());

            return guestRepository.save(existingGuest);
        }
        return updatedGuest;
    }
}