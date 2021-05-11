package ru.itis.romanchuk.transportcompany.api.dto.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PassageForm {
    private Long cityFrom;

    private Long cityTo;

    private int cost;

    private String start;
}
