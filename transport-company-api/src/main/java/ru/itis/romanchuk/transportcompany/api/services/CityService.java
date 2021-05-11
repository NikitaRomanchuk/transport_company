package ru.itis.romanchuk.transportcompany.api.services;

import ru.itis.romanchuk.transportcompany.api.dto.CityDto;
import ru.itis.romanchuk.transportcompany.api.dto.forms.CityForm;
import ru.itis.romanchuk.transportcompany.db.models.City;

import java.util.List;

public interface CityService {
    CityDto addCity(CityForm cityForm);

    void removeCity(Long cityId);

    List<CityDto> getCities();

    CityDto getCityById(Long id);
}
