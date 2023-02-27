package url.usecase;

import common.model.UseCase;

public record CreateShortenedUrl(String email, String url) implements UseCase {
}
