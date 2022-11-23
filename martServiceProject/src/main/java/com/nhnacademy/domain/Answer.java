package com.nhnacademy.domain;

import java.time.LocalDateTime;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Answer {

    private Long id;
    private String csName;

    @Size(min = 1, max = 40000)
    private String comment;
    private LocalDateTime dateTime;

}
