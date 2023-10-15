package dev.diegoczajka.conversionpurchase.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.diegoczajka.conversionpurchase.model.exchanges.ExchangeDetailData;
import dev.diegoczajka.conversionpurchase.utils.ValidCountryString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
public class ExchangeServiceImpl implements ExchangeService {
    @Value("${base.url}")
    private String baseUrl;
    @Autowired
    private RestTemplate restTemplate;


    @Override
    public ExchangeDetailData getByCountry(String country,String dateOfPurchase) {
        try {
            StringBuilder stringBuilder = new StringBuilder(baseUrl);
            stringBuilder.append("?filter=country:in:");
            stringBuilder.append(ValidCountryString.transform(country));
            stringBuilder.append(",record_date:lte:");
            stringBuilder.append(dateOfPurchase);
            stringBuilder.append("&sort=-record_date&format=json&page[number]=1&page[size]=1");
            ResponseEntity<String> result = restTemplate.exchange(stringBuilder.toString(), HttpMethod.GET, null, String.class);
            int firstInex = result.getBody().indexOf("[") + 1;
            int lastIndex = result.getBody().indexOf("]");
            String json = result.getBody().substring(firstInex, lastIndex);

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(json, ExchangeDetailData.class);


        } catch (Exception e) {

            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Exception while calling treasury endpoint", e);
        }

    }

}
