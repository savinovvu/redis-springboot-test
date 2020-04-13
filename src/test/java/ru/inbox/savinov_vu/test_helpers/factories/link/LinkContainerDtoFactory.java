package ru.inbox.savinov_vu.test_helpers.factories.link;

import ru.inbox.savinov_vu.app.dto.LinkContainerDto;
import ru.inbox.savinov_vu.app.dto.LinkDto;

import java.util.List;

import static ru.inbox.savinov_vu.test_helpers.factories.link.LinkDtoFactory.ofSecondStrings;



public class LinkContainerDtoFactory {

    public static LinkContainerDto of() {
        return of(LinkDtoFactory.of());
    }


    public static LinkContainerDto of(List<LinkDto> links) {
        return new LinkContainerDto(links);
    }


    public static LinkContainerDto ofSecondLinkContainerDto() {
        return of(LinkDtoFactory.of(ofSecondStrings()));
    }


}
