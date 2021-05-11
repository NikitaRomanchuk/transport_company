package ru.itis.romanchuk.transportcompany.api.services;

import ru.itis.romanchuk.transportcompany.api.dto.CityDto;
import ru.itis.romanchuk.transportcompany.api.dto.PathDto;
import ru.itis.romanchuk.transportcompany.api.dto.forms.PathForm;

import java.util.List;

public interface PathService {
    PathDto addPath(PathForm path);

    void removePath(Long pathId);

    List<PathDto> getPaths();

    PathDto getPathById(Long id);

    List<CityDto> getAvailableCities();
}
