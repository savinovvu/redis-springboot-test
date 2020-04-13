package ru.inbox.savinov_vu.app.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.app.dto.LinkContainerDto;
import ru.inbox.savinov_vu.app.dto.LinkFilterDto;

import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import static ru.inbox.savinov_vu.app.constants.StringConstant.LINKS;



@Service
@RequiredArgsConstructor
public class LinkService {

    private final RedisTemplate redisTemplate;


    public Boolean save(LinkContainerDto containerDto) {
        long time = new Date().getTime();
        Set<String> links = containerDto.getDomains();
        Boolean status = redisTemplate.opsForZSet().add(LINKS, links, Double.valueOf(time));
        return status;
    }


    public Set<String> getByFilter(LinkFilterDto filter) {
        Set<Set<String>> rateSet = redisTemplate.opsForZSet().rangeByScore(LINKS, Double.valueOf(filter.getFrom()), Double.valueOf(filter.getTo()));
        Set<String> result = rateSet.stream().flatMap(v -> v.stream()).collect(Collectors.toSet());
        return result;
    }

}
