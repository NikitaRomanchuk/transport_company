package ru.itis.romanchuk.transportcompany.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.romanchuk.transportcompany.db.models.City;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CityDto {
    private Long id;
    private String name;

    public CityDto(Long id) {
        this.id = id;
    }

    public CityDto(String name) {
        this.name = name;
    }

    public static CityDto from(City city){
        return CityDto.builder().id(city.getId()).name(city.getName()).build();
    }

    public static List<CityDto> from(List<City> cities){
        return cities.stream().map(CityDto::from).collect(Collectors.toList());
    }
}
