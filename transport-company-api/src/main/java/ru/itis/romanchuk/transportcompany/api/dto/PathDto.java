package ru.itis.romanchuk.transportcompany.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.romanchuk.transportcompany.db.models.Passage;
import ru.itis.romanchuk.transportcompany.db.models.Path;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PathDto {
    private Long id;

    private Long cityFrom;

    private String cityFromName;

    private Long cityTo;

    private String cityToName;

    private int distance;

    public PathDto(Long id) {
        this.id = id;
    }

    public PathDto(Long cityFrom, Long cityTo, int distance) {
        this.cityFrom = cityFrom;
        this.cityTo = cityTo;
        this.distance = distance;
    }

    public static PathDto from(Path path){
        return PathDto.builder()
                .id(path.getId())
                .cityFrom(path.getFrom().getId())
                .cityFromName(path.getFrom().getName())
                .cityTo(path.getTo().getId())
                .cityToName(path.getTo().getName())
                .distance(path.getDistance())
                .build();
    }

    public static List<PathDto> from(List<Path> paths){
        return paths.stream().map(PathDto::from).collect(Collectors.toList());
    }
}

