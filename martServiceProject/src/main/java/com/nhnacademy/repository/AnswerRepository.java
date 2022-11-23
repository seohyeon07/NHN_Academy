package com.nhnacademy.repository;

import com.nhnacademy.domain.Answer;

public interface AnswerRepository {

    Answer registerAnswer(Answer answer);

    Answer getAnswer(long id);

}
