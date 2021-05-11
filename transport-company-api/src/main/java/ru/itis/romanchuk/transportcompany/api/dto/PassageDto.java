package ru.itis.romanchuk.transportcompany.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.romanchuk.transportcompany.db.models.City;
import ru.itis.romanchuk.transportcompany.db.models.Passage;

import java.nio.file.Path;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassageDto {
    private Long id;

    private Long cityFrom;

    private String cityFromName;

    private Long cityTo;

    private String cityToName;

    private int cost;

    private String start;

    private List<CityDto> path;

    public PassageDto(Long id) {
        this.id = id;
    }

    public PassageDto(Long cityFrom, Long cityTo) {
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
    }

    public static PassageDto from(Passage passage){
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return PassageDto.builder()
                .id(passage.getId())
                .cityFrom(passage.getFrom().getId())
                .cityFromName(passage.getTo().getName())
                .cityTo(passage.getTo().getId())
                .cityToName(passage.getFrom().getName())
                .cost(passage.getCost())
                .start(df.format(passage.getStart()))
                .build();
    }

    public static List<PassageDto> from(List<Passage> passages){
        return passages.stream().map(PassageDto::from).collect(Collectors.toList());
    }
}
