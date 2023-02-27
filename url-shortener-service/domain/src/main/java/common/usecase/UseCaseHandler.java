package common.usecase;

import common.model.UseCase;

public interface UseCaseHandler<E, T extends UseCase>{

    E handler(T useCase);
}
