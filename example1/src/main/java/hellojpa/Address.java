package hellojpa;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Embeddable;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Address {

    private String city;
    private String street;
    private String zipcode;
}
