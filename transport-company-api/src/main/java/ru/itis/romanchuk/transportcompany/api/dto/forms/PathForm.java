package ru.itis.romanchuk.transportcompany.api.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PathForm {
    private Long cityFrom;

    private Long cityTo;

    private int distance;
}
