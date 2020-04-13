package ru.inbox.savinov_vu.test_helpers.factories.link;

import ru.inbox.savinov_vu.app.dto.LinkDto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;



public class LinkDtoFactory {

    public static List<String> ofStrings() {
        return List.of(
                "https://ya.ru",
                "https://ya.ru?q=123",
                "funbox.ru",
                "https://stackoverflow.com/questions/11828270/how-to-exit-the-vim-editor");
    }

    public static List<String> ofDomain() {
        return List.of(
                "ya.ru",
                "ya.ru",
                "funbox.ru",
                "stackoverflow.com");
    }
    
    public static Set<String> ofSetDomain() {
        return ofDomain().stream().collect(Collectors.toSet());
    }

    public static List<String> ofSecondStrings() {
        return List.of(
                "https://google.com",
                "https://ya.ru",
                "https://mail.ru?q=123",
                "https://www.baeldung.com",
                "baeldung.com"
        );
    }

    public static List<LinkDto> of() {
        return of(ofStrings());
    }


    public static List<LinkDto> of(List<String> list) {
        return list
                .stream().map(v -> new LinkDto(v)).collect(Collectors.toList());
    }

}
