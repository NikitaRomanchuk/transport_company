package ru.itis.romanchuk.transportcompany.impl.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.romanchuk.transportcompany.api.dto.CityDto;
import ru.itis.romanchuk.transportcompany.api.dto.PassageDto;
import ru.itis.romanchuk.transportcompany.api.dto.forms.PassageForm;
import ru.itis.romanchuk.transportcompany.api.services.PassageService;
import ru.itis.romanchuk.transportcompany.db.models.City;
import ru.itis.romanchuk.transportcompany.db.models.Passage;
import ru.itis.romanchuk.transportcompany.db.models.User;
import ru.itis.romanchuk.transportcompany.db.repositories.CityRepository;
import ru.itis.romanchuk.transportcompany.db.repositories.PassageRepository;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PassageServiceImpl implements PassageService {

    private final PassageRepository passageRepository;

    private final CityRepository cityRepository;

    public PassageServiceImpl(PassageRepository passageRepository, CityRepository cityRepository) {
        this.passageRepository = passageRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public PassageDto addPassage(PassageForm passage) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date start;
        try {
            start = dateFormat.parse(passage.getStart());
        }catch (ParseException e){
            throw new IllegalArgumentException(e);
        }

        Passage newPassage = Passage.builder()
                .from(cityRepository.getOne(passage.getCityFrom()))
                .to(cityRepository.getOne(passage.getCityTo()))
                .cost(passage.getCost())
                .start(new Timestamp(start.getTime()))
                .build();
        passageRepository.save(newPassage);
        return PassageDto.from(newPassage);
    }

    @Override
    public List<CityDto> getOptimalPath(Long passageId) {
        Passage passage = passageRepository.getOne(passageId);
        String id;
        try {
            id = cityRepository.optimalPath(passage.getFrom().getId(), passage.getTo().getId()).get(0);
        } catch (IndexOutOfBoundsException e){
            throw new IllegalArgumentException();
        }
        String[] idArray = id.split(",");
        List<CityDto> cities = new ArrayList<>();
        for(String i : idArray){
            cities.add(CityDto.from(cityRepository.getOne(Long.parseLong(i))));
        }
        return cities;
    }

    @Override
    public List<PassageDto> getPassages() {
        return PassageDto.from(passageRepository.findAll());
    }

    @Override
    public List<PassageDto> getByFromAndTo(Long cityIdFrom, Long cityIdTo) {
        return PassageDto.from(passageRepository.findByFromAndTo(
                City.builder().id(cityIdFrom).build(),
                City.builder().id(cityIdTo).build()));
    }

    @Override
    public List<CityDto> availableCities() {
        return CityDto.from(cityRepository.findAll());
    }

    @Override
    public void removePassage(Long id) {
        passageRepository.deleteById(id);
    }

    @Override
    public PassageDto getById(Long id) {
        return PassageDto.from(passageRepository.getOne(id));
    }
}
