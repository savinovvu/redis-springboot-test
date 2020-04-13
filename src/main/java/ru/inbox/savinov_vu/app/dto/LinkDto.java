package ru.inbox.savinov_vu.app.dto;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import ru.inbox.savinov_vu.app.exception.hierarchy.FlowException;

import java.net.MalformedURLException;
import java.net.URL;

import static java.util.Objects.isNull;



@Data
@AllArgsConstructor
public class LinkDto {

    @JsonValue
    private String link;


    public String getDomain() {
        if (isNull(link) || link.isBlank()) {
            return "";
        }

        if (notStartWithProtocol()) {
            link = "https://" + link;
        }
        return getHost();
    }


    private String getHost() {
        try {
            return new URL(link).getHost();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        throw new FlowException("this link format is not provided");
    }


    private boolean notStartWithProtocol() {
        return !(link.startsWith("https") || link.startsWith("http"));
    }

}
