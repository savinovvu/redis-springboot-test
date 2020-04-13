package ru.inbox.savinov_vu.app.controller;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.inbox.savinov_vu.app.dto.LinkContainerDto;
import ru.inbox.savinov_vu.app.dto.LinkFilterDto;
import ru.inbox.savinov_vu.app.service.LinkService;
import ru.inbox.savinov_vu.test_helpers.config.AbstractSpringBootTest;
import ru.inbox.savinov_vu.test_helpers.factories.FilterFactory;
import ru.inbox.savinov_vu.test_helpers.factories.link.LinkContainerDtoFactory;
import ru.inbox.savinov_vu.test_helpers.factories.link.LinkDtoFactory;

import javax.annotation.Resource;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.inbox.savinov_vu.app.constants.StringConstant.FROM;
import static ru.inbox.savinov_vu.app.constants.StringConstant.OK;
import static ru.inbox.savinov_vu.app.constants.StringConstant.TO;



class LinkControllerTest extends AbstractSpringBootTest {

    @Resource
    private MockMvc mockMvc;

    @Resource
    private JacksonTester<LinkContainerDto> jacksonTester;

    @MockBean
    private LinkService linkService;

    private LinkContainerDto linkContainerDto;

    private LinkFilterDto filterDto;


    @BeforeEach
    public void before() {
        linkContainerDto = LinkContainerDtoFactory.of();
        filterDto = FilterFactory.of();
        when(linkService.getByFilter(filterDto)).thenReturn(LinkDtoFactory.ofSetDomain());
        when(linkService.save(linkContainerDto)).thenReturn(Boolean.TRUE);
    }


    @Test
    void saveVisitedLinks_success() throws Exception {
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.post("/visited_links")
                        .content(jacksonTester.write(linkContainerDto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        );
        result
                .andExpect(jsonPath("$.status", Matchers.equalTo(OK)))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void saveVisitedLinks_whenNoBody_thenBadRequest() throws Exception {
        mockMvc.perform(post("/visited_links"))
                .andExpect(status().isBadRequest());
    }


    @Test
    void getVisitedDomains() throws Exception {
        ResultActions result = mockMvc.perform(
                MockMvcRequestBuilders.get("/visited_domains")
                        .param(FROM, String.valueOf(filterDto.getFrom()))
                        .param(TO, String.valueOf(filterDto.getTo()))
                        .content(jacksonTester.write(linkContainerDto).getJson())
                        .contentType(MediaType.APPLICATION_JSON)
        );
        result
                .andExpect(jsonPath("$.domains", hasSize(3)))
                .andExpect(jsonPath("$.status", Matchers.equalTo(OK)))
                .andExpect(status().isOk());

    }


}