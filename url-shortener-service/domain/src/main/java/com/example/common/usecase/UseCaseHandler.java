package com.example.common.usecase;

import com.example.common.model.UseCase;

public interface UseCaseHandler<E, T extends UseCase>{

    E handler(T useCase);
}
