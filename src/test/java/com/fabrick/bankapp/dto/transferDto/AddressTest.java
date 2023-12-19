package com.fabrick.bankapp.dto.transferDto;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    public void shouldCreateAddressCorrectly() {
        //given
        String addressValue = "Via xxx";
        String cityValue = "Milan";
        String countryCodeValue = "MI";
        //when
        Address newAddress = new Address(addressValue, cityValue,countryCodeValue);
        //then
        assertThat(newAddress).isNotNull();
        assertThat(newAddress.getAddress()).isEqualTo(addressValue);
        assertThat(newAddress.getCity()).isEqualTo(cityValue);
        assertThat(newAddress.getCountryCode()).isEqualTo(countryCodeValue);
    }

}