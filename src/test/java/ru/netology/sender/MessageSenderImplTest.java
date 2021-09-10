package ru.netology.sender;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;

import java.util.HashMap;
import java.util.Map;

class MessageSenderImplTest {
    @Test
    void messageSenderRussiaTest() {
        String ip = "172.";
        String expected = "Добро пожаловать ";
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ip)).thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать ");
        MessageSender MessageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String actual = MessageSender.send(headers);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void messageSenderUSATest() {
        String ip = "96.";
        String expected = "Welcome";
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ip)).thenReturn(new Location("New York", Country.USA, null, 0));
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA)).thenReturn("Welcome");
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        String actual = messageSender.send(headers);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void sendThrowsExceptionTest() {
        Map<String, String> headers = new HashMap<>();
        headers.put(null, null);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Assertions.assertThrows(NullPointerException.class, () -> messageSender.send(headers));
    }

    @ParameterizedTest

    @CsvSource({"172., Добро пожаловать "})
     void MessageSenderTest(String location, String welcomeMessage) {
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");
        Location loc = new Location("Moscow", Country.RUSSIA, "Lenina", 16);
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp("172.")).thenReturn(loc);
        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, location);
        String actual = messageSender.send(headers);
        Assertions.assertEquals(welcomeMessage,actual);
    }
}