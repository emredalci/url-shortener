package com.example.url.usecase;

import com.example.common.model.UseCase;

public record Redirect(String shortened) implements UseCase {
}
