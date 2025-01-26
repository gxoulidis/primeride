package gr.uom.primeride.service;

import gr.uom.primeride.exception.FormException;
import gr.uom.primeride.exception.ResourceNotFoundException;
import gr.uom.primeride.model.Citizen;
import gr.uom.primeride.model.Dealership;
import gr.uom.primeride.model.LoginRequest;
import gr.uom.primeride.repository.CitizenRepository;
import gr.uom.primeride.repository.DealershipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final CitizenRepository citizenRepository;
    private final DealershipRepository dealershipRepository;

    @Autowired
    public UserService(CitizenRepository citizenRepository,
                       DealershipRepository dealershipRepository) {
        this.citizenRepository = citizenRepository;
        this.dealershipRepository = dealershipRepository;
    }

    public ResponseEntity<Dealership> saveDealership(Dealership dealership) {
        validateAfmUniqueness(dealership.getAfm());
        validateEmailUniqueness(dealership.getEmail());
        validateDealershipFieldCorrectness(dealership);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dealershipRepository.save(dealership));
    }

    public ResponseEntity<Citizen> saveCitizen(Citizen citizen) {
        validateAfmUniqueness(citizen.getAfm());
        validateEmailUniqueness(citizen.getEmail());
        validateCitizenFieldCorrectness(citizen);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(citizenRepository.save(citizen));
    }

    public ResponseEntity<String> login(LoginRequest loginRequest) {
        String afm = loginRequest.getAfm().trim();
        String password = loginRequest.getPassword();

        Optional<Citizen> citizen = citizenRepository.findById(afm);
        if (citizen.isPresent()) {
            if ( citizen.get().getPassword().equals(password))
                return ResponseEntity.status(HttpStatus.OK)
                        .header("user_type", "citizen")
                        .header("user_name", citizen.get().getFname())
                        .body(citizen.get().getAfm());

            throwNotFoundException("Invalid Password", "password", "The password is wrong");
        }

        Optional<Dealership> dealership = dealershipRepository.findById(afm);
        if (dealership.isPresent()) {
            if( dealership.get().getPassword().equals(password))
                return ResponseEntity.status(HttpStatus.OK)
                        .header("user_type", "dealership")
                        .header("user_name", dealership.get().getOwner())
                        .body(dealership.get().getAfm());

            throwNotFoundException("Invalid Password", "password", "The password is wrong");
        }

        throwNotFoundException("User Not Found", "afm", "No user with this AFM found");

        //this will never be reached
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unexpected Error");
    }

    private void validateAfmUniqueness(String afm){
        var exceptionBuilder = new FormException.Builder("User Creation Failed");
        if (dealershipRepository.existsById(afm)) {
            exceptionBuilder.addDetail("afm", "A dealership with this afm already exists");
        } else if (citizenRepository.existsById(afm)) {
            exceptionBuilder.addDetail("afm", "A citizen with this afm already exists");
        }
        if (!exceptionBuilder.getDetails().isEmpty())
            throw exceptionBuilder.build();
    }

    private void validateEmailUniqueness(String email){
        var exceptionBuilder = new FormException.Builder("User Creation Failed");
        if (citizenRepository.existsByEmail(email))
            exceptionBuilder.addDetail("email","A citizen with this email already exists");
        else if (dealershipRepository.existsByEmail(email))
            exceptionBuilder.addDetail("email","A dealership with this email already exists");

        if (!exceptionBuilder.getDetails().isEmpty())
            throw exceptionBuilder.build();
    }

    private void validateDealershipFieldCorrectness(Dealership dealership) {
        var exceptionBuilder = new FormException.Builder("User Creation Failed");
        String dname = dealership.getDname().trim();
        String owner = dealership.getOwner().trim();
        String email = dealership.getEmail().trim();
        String afm = dealership.getAfm().trim();

        if (!afm.matches("\\d{9}")) {
            exceptionBuilder.addDetail("afm", "AFM must be exactly 9 digits");
        }
        if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"))
            exceptionBuilder.addDetail("email","Invalid email format");

        if(dname.length() < 2)
            exceptionBuilder.addDetail("dname","Dealership's name must be at least 2 characters");
        else if (!dname.matches("[a-zA-Z0-9\\s.]+"))
            exceptionBuilder.addDetail("dname","Dealership's name can contain only letters and numbers");

        if(owner.length() < 3)
            exceptionBuilder.addDetail("owner","Owner's name must be at least 3 characters");
        else if (!owner.matches("[a-zA-Z\\s.]+"))
            exceptionBuilder.addDetail("owner","Owner's name must be letters");

        // Dont trim password because citizen may think that it is valid
        if(dealership.getPassword().length() < 6)
            exceptionBuilder.addDetail("password","Password must be at least 6 characters");
        else if (dealership.getPassword().contains(" "))
            exceptionBuilder.addDetail("password", "Password must not contain any spaces");

        if (!exceptionBuilder.getDetails().isEmpty())
            throw exceptionBuilder.build();
    }

    private void validateCitizenFieldCorrectness(Citizen citizen) {
        var exceptionBuilder = new FormException.Builder("User Creation Failed");
        String email = citizen.getEmail().trim();
        String fname = citizen.getFname().trim();
        String lname = citizen.getLname().trim();
        String afm = citizen.getAfm().trim();

        if (!afm.matches("\\d{9}")) {
            exceptionBuilder.addDetail("afm", "AFM must be exactly 9 digits");
        }
        if (!email.matches("^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"))
            exceptionBuilder.addDetail("email","Invalid email format");

        if(fname.length() < 3)
            exceptionBuilder.addDetail("fname","First name must be at least 3 characters");
        else if (!fname.matches("[a-zA-Z\\s.]+"))
            exceptionBuilder.addDetail("fname","First name must be letters");

        if(lname.length() < 3)
            exceptionBuilder.addDetail("lname","Last name must be at least 3 characters");
        else if (!lname.matches("[a-zA-Z\\s.]+"))
            exceptionBuilder.addDetail("lname","Last name must be letters");

        // Dont trim password because citizen may think that it is valid
        if(citizen.getPassword().length() < 6)
            exceptionBuilder.addDetail("password","Password must be at least 6 characters");
        else if (citizen.getPassword().contains(" "))
            exceptionBuilder.addDetail("password", "Password must not contain any spaces");

        if (!exceptionBuilder.getDetails().isEmpty())
            throw exceptionBuilder.build();
    }

    private void throwNotFoundException(String message, String field, String reason) {
        throw new ResourceNotFoundException.Builder(message)
                .addDetail(field, message)
                .build();
    }

}
