package ru.inbox.savinov_vu.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;



@Data
@NoArgsConstructor
@AllArgsConstructor
public class LinkFilterDto {

    @NotNull
    private Long from;

    @NotNull
    private Long to;
}
