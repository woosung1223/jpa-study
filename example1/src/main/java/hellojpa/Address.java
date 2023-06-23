package hellojpa;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;

    public Address(Address other) {
        this.city = other.getCity();
        this.street = other.getStreet();
        this.zipcode = other.getZipcode();
    }
}
