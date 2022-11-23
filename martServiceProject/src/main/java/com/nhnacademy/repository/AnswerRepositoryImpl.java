package com.nhnacademy.repository;

import com.nhnacademy.domain.Answer;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class AnswerRepositoryImpl implements AnswerRepository {

    private final Map<Long, Answer> answerMap = new HashMap<>();

    @Override
    public Answer registerAnswer(Answer answer) {

        long id = answer.getId();
        answer.setDateTime(LocalDateTime.now());

        answerMap.put(id, answer);

        return answer;
    }

    @Override
    public Answer getAnswer(long id) {
        return answerMap.get(id);
    }

}
