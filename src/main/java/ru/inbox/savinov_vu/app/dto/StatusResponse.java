package ru.inbox.savinov_vu.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;



@Data
@AllArgsConstructor(staticName = "of")
public class StatusResponse {

    private String status;

}
