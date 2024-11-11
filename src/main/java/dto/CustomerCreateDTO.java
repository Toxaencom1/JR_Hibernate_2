package dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerCreateDTO {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String address;
    private final String address2;
    private final String district;
    private final String city;
    private final String postalCode;
    private final String phone;
}

