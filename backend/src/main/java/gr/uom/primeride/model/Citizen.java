package gr.uom.primeride.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
public class Citizen extends User{
    @JsonProperty("fname")
    @Column(name = "first_name")
    @NotNull(message = "First name cannot be null")
    @NotEmpty(message = "First name cannot be empty")
    private String fname;

    @JsonProperty("lname")
    @Column(name = "last_name")
    @NotNull(message = "Last name cannot be null")
    @NotEmpty(message = "Last name cannot be empty")
    private String lname;

    public Citizen() {super();}

    public Citizen(String afm, String password, String fname, String lname, String email) {
        super(afm,password,email);
        this.fname = fname;
        this.lname = lname;
    }

    public String getFname() {
        return this.fname;
    }

    public void setFirstName(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return this.lname;
    }

    public void setLastName(String lname) {
        this.lname = lname;
    }

}
