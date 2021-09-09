package ru.netology.i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

class LocalizationServiceImplTest {

    @Test
    void test_local_USA() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        Country testCountry = Country.USA;
        String expectedWelcome = "Welcome";
        String resultWelcome = localizationService.locale(testCountry);
        Assertions.assertEquals(expectedWelcome, resultWelcome);
    }

    @Test
    void test_local_RUSSIA() {
        LocalizationService localizationService = new LocalizationServiceImpl();
        Country testCountry = Country.RUSSIA;
        String expectedWelcome = "Добро пожаловать";
        String resultWelcome = localizationService.locale(testCountry);
        Assertions.assertEquals(expectedWelcome, resultWelcome);

    }


}