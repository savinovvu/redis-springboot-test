package ru.inbox.savinov_vu.app.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.inbox.savinov_vu.app.dto.DomainResponse;
import ru.inbox.savinov_vu.app.dto.LinkContainerDto;
import ru.inbox.savinov_vu.app.dto.LinkFilterDto;
import ru.inbox.savinov_vu.app.dto.StatusResponse;
import ru.inbox.savinov_vu.app.service.LinkService;

import javax.validation.Valid;
import java.util.Set;

import static ru.inbox.savinov_vu.app.constants.StringConstant.OK;



@RestController
@RequiredArgsConstructor
public class LinkController {


    private final LinkService linkService;


    @PostMapping(value = "/visited_links", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> saveVisitedLinks(@RequestBody LinkContainerDto linkContainer) {
        linkService.save(linkContainer);
        return ResponseEntity.ok(StatusResponse.of(OK));
    }


    @GetMapping(value = "/visited_domains", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getVisitedDomains(@Valid LinkFilterDto filterDto) {
        Set<String> result = linkService.getByFilter(filterDto);
        return ResponseEntity.ok(DomainResponse.of(result, OK));
    }
}
