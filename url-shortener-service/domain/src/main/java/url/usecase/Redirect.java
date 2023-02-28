package url.usecase;

import common.model.UseCase;

public record Redirect(String shortened) implements UseCase {
}
