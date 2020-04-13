package ru.inbox.savinov_vu.app.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.inbox.savinov_vu.app.dto.LinkContainerDto;
import ru.inbox.savinov_vu.app.dto.LinkFilterDto;
import ru.inbox.savinov_vu.test_helpers.config.AbstractSpringBootTest;
import ru.inbox.savinov_vu.test_helpers.factories.FilterFactory;
import ru.inbox.savinov_vu.test_helpers.factories.link.LinkContainerDtoFactory;
import ru.inbox.savinov_vu.test_helpers.init.InitLinkService;

import javax.annotation.Resource;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;



class LinkServiceTest extends AbstractSpringBootTest {

    @Resource
    private LinkService linkService;

    @Resource
    private InitLinkService initLinkService;


    @BeforeEach
    public void before() {
        initLinkService.deleteAll();
    }


    @Test
    void getByFilter() {
        LinkContainerDto linkContainerDto = LinkContainerDtoFactory.of();
        linkService.save(linkContainerDto);
        Set<String> byFilter = linkService.getByFilter(FilterFactory.of());
        Set<String> byWrongFilter = linkService.getByFilter(FilterFactory.ofHours(-3, -2));
        assertEquals(byFilter.size(), linkContainerDto.getDomains().size(), "must be equal");
        assertEquals(byWrongFilter.size(), 0, "set must be empty");
    }


    @Test
    void pieceOfData() throws InterruptedException {
        LinkFilterDto filter1 = initLinkService.saveDataAngGetFilter(LinkContainerDtoFactory.of());
        Thread.sleep(5);
        LinkFilterDto filter2 = initLinkService.saveDataAngGetFilter(LinkContainerDtoFactory.ofSecondLinkContainerDto());

        Set<String> byFilter1 = linkService.getByFilter(filter1);
        Set<String> byFilter2 = linkService.getByFilter(filter2);
        Set<String> all = linkService.getByFilter(FilterFactory.of());
        assertEquals(byFilter1.size(), 3, "first part");
        assertEquals(byFilter2.size(), 5, "second part");
        assertEquals(all.size(), 7, "all data");
    }


    @Test
    void save() {
        linkService.save(LinkContainerDtoFactory.of());
        LinkFilterDto filter = FilterFactory.of();
        Set<String> byFilter = linkService.getByFilter(filter);
        linkService.save(LinkContainerDtoFactory.ofSecondLinkContainerDto());
        Set<String> byFilter2 = linkService.getByFilter(filter);
        assertEquals(byFilter.size(), 3, "must be equal");
        assertEquals(byFilter2.size(), 7, "set must be empty");
    }


}