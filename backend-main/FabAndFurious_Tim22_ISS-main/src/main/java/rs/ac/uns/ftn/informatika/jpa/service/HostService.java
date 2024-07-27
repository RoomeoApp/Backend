package rs.ac.uns.ftn.informatika.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.informatika.jpa.model.Guest;
import rs.ac.uns.ftn.informatika.jpa.model.Host;
import rs.ac.uns.ftn.informatika.jpa.repository.HostRepository;
import rs.ac.uns.ftn.informatika.jpa.service.interfaces.IHostService;

import java.util.List;
import java.util.Optional;

@Service
public class HostService implements IHostService {

    private HostRepository hostRepository;

    @Autowired
    public HostService(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    public List<Host> getAll() {
        return this.hostRepository.findAll();
    }

    @Override
    public Optional<Host> getHost(String id) {
        return  this.hostRepository.findById(Long.parseLong(id));
    }

    public void add(Host host) {
        this.hostRepository.save(host);
    }

    public Host updateHost(Long id, Host updatedGuest) {
        Optional<Host> existingGuestOpt = hostRepository.findById(id);
        if (existingGuestOpt.isPresent()) {
            Host existingGuest = existingGuestOpt.get();
            existingGuest.setFirstName(updatedGuest.getFirstName());
            existingGuest.setLastName(updatedGuest.getLastName());
            existingGuest.setEmail(updatedGuest.getEmail());
            existingGuest.setPhoneNumber(updatedGuest.getPhoneNumber());
            existingGuest.setAddress(updatedGuest.getAddress());

            return hostRepository.save(existingGuest);
        }
        return updatedGuest;
    }
}
