package com.example.url.usecase;

import com.example.common.model.UseCase;
import lombok.Builder;

@Builder
public record Redirect(String shortened) implements UseCase {
}
