package ru.itis.romanchuk.transportcompany.impl.services;

import org.springframework.stereotype.Service;
import ru.itis.romanchuk.transportcompany.api.dto.CityDto;
import ru.itis.romanchuk.transportcompany.api.dto.forms.CityForm;
import ru.itis.romanchuk.transportcompany.api.services.CityService;
import ru.itis.romanchuk.transportcompany.db.models.City;
import ru.itis.romanchuk.transportcompany.db.repositories.CityRepository;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    private final CityRepository repository;

    public CityServiceImpl(CityRepository repository) {
        this.repository = repository;
    }


    @Override
    public CityDto addCity(CityForm cityForm) {
        City newCity = City.builder()
                .name(cityForm.getName())
                .build();
        repository.save(newCity);
        return CityDto.from(newCity);
    }

    @Override
    public void removeCity(Long cityId) {
        repository.deleteById(cityId);
    }

    @Override
    public List<CityDto> getCities() {
        return CityDto.from(repository.findAll());
    }

    @Override
    public CityDto getCityById(Long id) {
        return CityDto.from(repository.findById(id).orElseThrow(IllegalArgumentException::new));
    }
}
