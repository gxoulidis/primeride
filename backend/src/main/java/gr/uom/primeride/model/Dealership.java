package gr.uom.primeride.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


@Entity
public class Dealership extends User{

    @JsonProperty("dname")
    @NotNull(message = "Dealership's name cannot be null")
    @NotEmpty(message = "Dealership's name cannot be empty")
    @Column(name = "dealership_name")
    private String dname;

    @JsonProperty("owner")
    @NotNull(message = "Owner's cannot be null")
    @NotEmpty(message = "Owner's name cannot be empty")
    @Column(name = "owner_name")
    private String owner;

    public Dealership() {super();}
    public Dealership(String afm, String dname, String owner, String password, String email) {
        super(afm,password,email);
        this.dname = dname;
        this.owner = owner;
    }

    public String getDname() {
        return this.dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getOwner() {
        return this.owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

}
