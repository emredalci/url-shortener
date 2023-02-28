package url.usecase;

import common.model.UseCase;
import lombok.Builder;

@Builder
public record CreateShortenedUrl(long userId, String url) implements UseCase {

}
