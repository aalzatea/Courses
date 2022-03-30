package com.oportun.poc.testingstrategy.entrypoints.rest.handlers.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

@RequiredArgsConstructor
@Getter
public class ErrorMessage {

    private final String status;

    private final Date date;

    private final String message;

    private final String description;

}
