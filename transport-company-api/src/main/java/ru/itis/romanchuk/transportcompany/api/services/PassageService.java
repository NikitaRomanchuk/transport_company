package ru.itis.romanchuk.transportcompany.api.services;

import ru.itis.romanchuk.transportcompany.api.dto.CityDto;
import ru.itis.romanchuk.transportcompany.api.dto.PassageDto;
import ru.itis.romanchuk.transportcompany.api.dto.forms.PassageForm;
import ru.itis.romanchuk.transportcompany.db.models.City;
import ru.itis.romanchuk.transportcompany.db.models.Passage;
import ru.itis.romanchuk.transportcompany.db.models.User;

import java.nio.file.Path;
import java.util.List;

public interface PassageService {
    PassageDto addPassage(PassageForm passage);

    List<CityDto> getOptimalPath(Long passageId);

    List<PassageDto> getPassages();

    List<PassageDto> getByFromAndTo(Long cityIdFrom, Long cityIdTo);

    List<CityDto> availableCities();

    void removePassage(Long id);

    PassageDto getById(Long id);
}
