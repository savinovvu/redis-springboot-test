package ru.inbox.savinov_vu.app.dto;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;



class LinkTest {

    @ParameterizedTest
    @MethodSource("getArguments")
    void getLink(String link, String expected) {
        LinkDto linkDto = new LinkDto(link);
        String result = linkDto.getDomain();
        assertEquals(expected, result);
    }

    public static Stream<Arguments> getArguments() {
        return Stream.of(
                Arguments.of("https://ya.ru", "ya.ru"),
                Arguments.of("https://ya.ru?q=123", "ya.ru"),
                Arguments.of("funbox.ru", "funbox.ru"),
                Arguments.of("https://stackoverflow.com/questions/11828270/how-to-exit-the-vim-editor", "stackoverflow.com")
        );
    }
}