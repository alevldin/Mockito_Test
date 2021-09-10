package ru.netology.geo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

class GeoServiceImplTest {
    @Test
    void NULL_byIp_Test() {
        GeoService geoService = new GeoServiceImpl();
        String ipTest = "0.0.0.0";
        Location expectedLocation = null;
        Location resultLocation = geoService.byIp(ipTest);
        Assertions.assertEquals(expectedLocation, resultLocation);
    }
    @Test
    void NEW_YORK_byIp_Test() {
        GeoService geoService = new GeoServiceImpl();
        String ipUsa = GeoServiceImpl.NEW_YORK_IP;
        Location expectedLocation = new Location("New York", Country.USA, " 10th Avenue", 32);
        Location resultLocation = geoService.byIp(ipUsa);
        Assertions.assertEquals(expectedLocation, resultLocation);
    }

    @Test
    void RUSSIA_byIp_Test() {
        Location expectedLocation = new Location("Moscow", Country.RUSSIA, null, 0);
        GeoService geoService = new GeoServiceImpl();
        String ipUsa = "172.0.0.0";
        Location resultLocation = geoService.byIp(ipUsa);
        Assertions.assertEquals(expectedLocation, resultLocation);
    }
}