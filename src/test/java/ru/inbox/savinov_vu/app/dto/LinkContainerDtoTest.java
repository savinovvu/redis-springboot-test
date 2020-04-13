package ru.inbox.savinov_vu.app.dto;

import org.junit.jupiter.api.Test;
import ru.inbox.savinov_vu.test_helpers.factories.link.LinkContainerDtoFactory;
import ru.inbox.savinov_vu.test_helpers.factories.link.LinkDtoFactory;

import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;



class LinkContainerDtoTest {

    @Test
    void getLink() {
        Set<String> expected = LinkDtoFactory.ofDomain().stream().collect(Collectors.toSet());
        LinkContainerDto linkContainerDto = LinkContainerDtoFactory.of();
        Set<String> domains = linkContainerDto.getDomains();
        assertEquals(domains, expected);
    }
}

