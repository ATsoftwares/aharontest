package com.bluerbn.flightapp;

import com.bluerbn.flightapp.cache.Cache;
import com.bluerbn.flightapp.models.CacheModel;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = FlightAppApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FlightAppApplicationTests {

    @Autowired
    Cache<String, CacheModel<?>> ticketCache;

    @LocalServerPort
    private int randomServerPort;

    private String baseUrl;

    @Before
    public void init() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        baseUrl = String.format("http://localhost:%s", randomServerPort);
    }

    @Test
    public void ticketExistTest() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<Object> res = restTemplate.exchange(baseUrl + "/tickets/1", HttpMethod.GET,
                    null, Object.class);
            assertThat(HttpStatus.OK).isEqualTo(res.getStatusCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cacheTestFirstIsOut() throws InterruptedException {
        Thread.sleep(5200);
        Assert.assertNull(ticketCache.get("1"));
    }

    @Test
    public void cacheTestMapIsEmpty() throws InterruptedException {
        Thread.sleep(12000);
        Assert.assertEquals(ticketCache.size(), 0);
    }
}
