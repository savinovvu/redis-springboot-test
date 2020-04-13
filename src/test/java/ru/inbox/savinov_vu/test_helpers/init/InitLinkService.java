package ru.inbox.savinov_vu.test_helpers.init;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.inbox.savinov_vu.app.dto.LinkContainerDto;
import ru.inbox.savinov_vu.app.dto.LinkFilterDto;
import ru.inbox.savinov_vu.app.service.LinkService;

import javax.annotation.Resource;
import java.util.List;

import static ru.inbox.savinov_vu.app.constants.StringConstant.LINKS;



@Service
public class InitLinkService {

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private LinkService linkService;


    public void deleteAll() {
        redisTemplate.delete(List.of(LINKS));
    }


    public LinkFilterDto saveDataAngGetFilter(LinkContainerDto containerDto) {
        long from = System.currentTimeMillis();
        linkService.save(containerDto);
        long to = System.currentTimeMillis();
        return new LinkFilterDto(from, to);
    }

}
