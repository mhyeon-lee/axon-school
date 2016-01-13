package kr.axon.post.command.domain;

import lombok.Value;
import lombok.experimental.Wither;

@Value
public class PostContent {
    @Wither
    private final String title;
    @Wither
    private final String content;
}
