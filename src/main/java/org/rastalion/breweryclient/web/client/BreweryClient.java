package org.rastalion.breweryclient.web.client;

import org.rastalion.breweryclient.web.model.BeerDto;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.UUID;

@Component
public class BreweryClient {

    private final String API_HOST = "http://localhost:8080";
    private final String BEER_PATH_V1 = "/api/v1/beer/";

    private final RestTemplate restTemplate;

    public BreweryClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public BeerDto  getBeerById(UUID uuid) throws MalformedURLException {
        URL base = new URL(API_HOST + BEER_PATH_V1);
        return restTemplate.getForObject(base + uuid.toString(), BeerDto.class);
    }

    public URI saveNewBeer(BeerDto beerDto) {
        return restTemplate.postForLocation(this.API_HOST + BEER_PATH_V1, beerDto);
    }

    public void updateBeer(UUID uuid, BeerDto beerDto) {
        restTemplate.put(API_HOST + BEER_PATH_V1 + "/" + uuid.toString(), beerDto);
    }

    public void deleteBeerById(UUID uuid) {
        restTemplate.delete(API_HOST + BEER_PATH_V1 + "/" + uuid);
    }

}
