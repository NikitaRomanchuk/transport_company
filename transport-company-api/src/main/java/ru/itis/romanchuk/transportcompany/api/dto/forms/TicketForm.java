package ru.itis.romanchuk.transportcompany.api.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketForm {
    private Long passageId;

    private Long userId;
}
