package gr.uom.primeride.controller;

import gr.uom.primeride.model.Citizen;
import gr.uom.primeride.model.Dealership;
import gr.uom.primeride.model.LoginRequest;
import gr.uom.primeride.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/citizen")
    public ResponseEntity<Citizen> createCitizen(@Valid @RequestBody Citizen citizen) {
        return userService.saveCitizen(citizen);
    }

    @PostMapping("/dealership")
    public ResponseEntity<Dealership> createDealership(@Valid @RequestBody Dealership dealership) {
        return userService.saveDealership(dealership);
    }

    @PostMapping("/login")
    public ResponseEntity<String> userLogin(@Valid @RequestBody LoginRequest loginRequest) {
        return userService.login(loginRequest);
    }
}
