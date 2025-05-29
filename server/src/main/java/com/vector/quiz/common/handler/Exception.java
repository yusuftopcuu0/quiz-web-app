package com.vector.quiz.common.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Exception<E> {

    private String path;

    private Date createdAt;

    private String hostName;

    private E message;
}