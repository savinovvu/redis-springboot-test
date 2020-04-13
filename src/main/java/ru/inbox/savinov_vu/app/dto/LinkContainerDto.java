package ru.inbox.savinov_vu.app.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinkContainerDto {

    @NotBlank
    private List<LinkDto> links;

    public Set<String> getDomains() {
        return links.stream().map(LinkDto::getDomain).collect(Collectors.toSet());
    }
}
