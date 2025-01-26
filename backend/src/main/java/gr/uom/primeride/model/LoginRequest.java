package gr.uom.primeride.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class LoginRequest {
    @NotNull
    @JsonProperty("afm")
    @NotEmpty(message = "AFM cannot be empty")
    private String afm;

    @NotNull
    @JsonProperty("password")
    @NotEmpty(message = "Password cannot be empty")
    private String password;

    public LoginRequest(String afm, String password) {
        this.afm = afm;
        this.password = password;
    }

    public String getAfm() {
        return afm;
    }

    public void setAfm(String afm) {
        this.afm = afm;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}