package com.chengkun.practice.dto;

public record CustomerRes(
        Long id,
        String firstName,
        String lastName,
        String email
) {
}
