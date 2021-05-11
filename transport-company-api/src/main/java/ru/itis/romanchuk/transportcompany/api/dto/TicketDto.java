package ru.itis.romanchuk.transportcompany.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.romanchuk.transportcompany.db.models.Path;
import ru.itis.romanchuk.transportcompany.db.models.Ticket;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDto {
    private Long id;

    private PassageDto passageDto;

    private Ticket.State state;

    public static TicketDto from(Ticket ticket){
        return TicketDto.builder()
                .id(ticket.getId())
                .passageDto(PassageDto.from(ticket.getPassage()))
                .build();
    }

    public static List<PathDto> from(List<Path> paths){
        return paths.stream().map(PathDto::from).collect(Collectors.toList());
    }
}
