package kr.axon.post.command.domain;

import lombok.*;
import lombok.experimental.Wither;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class PostContent {
    @Wither
    private String title;
    @Wither
    private String content;
}
