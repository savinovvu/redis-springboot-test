package ru.inbox.savinov_vu.test_helpers.factories;

import ru.inbox.savinov_vu.app.dto.LinkFilterDto;

import java.util.Date;



public class FilterFactory {

    private static long hourMillisecond = 3_600_000;


    public static LinkFilterDto of() {
        long from = new Date().getTime() - hourMillisecond;
        long to = new Date().getTime() + hourMillisecond;
        return new LinkFilterDto(from, to);
    }


    public static LinkFilterDto ofHours(int fromHours, int toHours) {
        long from = new Date().getTime() + hourMillisecond * fromHours;
        long to = new Date().getTime() + hourMillisecond * toHours;
        return new LinkFilterDto(from, to);
    }


    public static LinkFilterDto ofMilliseconds(int fromMilliseconds, int toMilliseconds) {
        long from = new Date().getTime() + fromMilliseconds;
        long to = new Date().getTime() + toMilliseconds;
        return new LinkFilterDto(from, to);
    }

}
