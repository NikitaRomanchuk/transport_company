package ru.itis.romanchuk.transportcompany.impl.services;

import org.springframework.stereotype.Service;
import ru.itis.romanchuk.transportcompany.api.dto.CityDto;
import ru.itis.romanchuk.transportcompany.api.dto.PathDto;
import ru.itis.romanchuk.transportcompany.api.dto.forms.PathForm;
import ru.itis.romanchuk.transportcompany.api.services.PathService;
import ru.itis.romanchuk.transportcompany.db.models.City;
import ru.itis.romanchuk.transportcompany.db.models.Path;
import ru.itis.romanchuk.transportcompany.db.repositories.CityRepository;
import ru.itis.romanchuk.transportcompany.db.repositories.PathRepository;

import java.util.List;

@Service
public class PathServiceImpl implements PathService {

    private PathRepository pathRepository;
    private CityRepository cityRepository;

    public PathServiceImpl(PathRepository pathRepository, CityRepository cityRepository) {
        this.pathRepository = pathRepository;
        this.cityRepository = cityRepository;
    }

    @Override
    public PathDto addPath(PathForm path) {
        Path newPath = Path.builder()
                .from(cityRepository.getOne(path.getCityFrom()))
                .to(cityRepository.getOne(path.getCityTo()))
                .distance(path.getDistance())
                .build();
        newPath = pathRepository.save(newPath);
        return PathDto.from(newPath);
    }

    @Override
    public void removePath(Long pathId) {
        pathRepository.deleteById(pathId);
    }

    @Override
    public List<PathDto> getPaths() {
        return PathDto.from(pathRepository.findAll());
    }

    @Override
    public PathDto getPathById(Long id) {
        return PathDto.from(pathRepository.findById(id).orElseThrow(IllegalArgumentException::new));
    }

    @Override
    public List<CityDto> getAvailableCities() {
        return CityDto.from(cityRepository.findAll());
    }
}
