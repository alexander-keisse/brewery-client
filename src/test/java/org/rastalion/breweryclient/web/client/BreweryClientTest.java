package org.rastalion.breweryclient.web.client;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.rastalion.breweryclient.web.model.BeerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.net.MalformedURLException;
import java.net.URI;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class BreweryClientTest {

    /*
    These are stub implementations, for you to learn how to work with the RestTemplate interface

    These do not have a deep logic in them because we are not working with persistence.
    We don't need to at this point.

    And it is better to keep pushing that decision forward until the very last point.

    We do not want our application being defined by something like persistence [which is only an I/O device]
     */

    @Autowired
    BreweryClient client;

    private BeerDto validBeerDto;

    @BeforeEach
    void setUp() {
        validBeerDto = BeerDto.builder()
                        .beerName("Nice Ale")
                        .beerStyle("ALE")
                        .upc(123123123123L)
                        .build();
    }

    @Test
    void getBeerById() throws MalformedURLException {
        BeerDto beerDto = client.getBeerById(UUID.randomUUID());

        assertNotNull(beerDto);
    }

    @Test
    void saveNewBeer() {
        URI uri = client.saveNewBeer(validBeerDto);
        assertNotNull(uri);

        System.out.println(uri.toString());

    }

    @Test
    void updateBeer() {
        client.updateBeer(UUID.randomUUID(), validBeerDto);
    }

    @Test
    void deleteBeerById() {
        client.deleteBeerById(UUID.randomUUID());

    }

    @AfterEach
    void tearDown() {
        validBeerDto = null;
    }
}