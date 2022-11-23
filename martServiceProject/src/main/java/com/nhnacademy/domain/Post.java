package com.nhnacademy.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    private long id;

    private String userName;

    @Size(min = 2, max = 200)
    private String title;

    private String type;

    @NotBlank
    @Size(max = 40000)
    private String content;

    private List<String> fileName;

    private LocalDateTime dateTime;

    private boolean replyCheck;

    private Answer answer;

}
