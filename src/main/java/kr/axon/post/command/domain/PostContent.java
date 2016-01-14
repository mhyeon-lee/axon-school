package kr.axon.post.command.domain;

import lombok.*;
import lombok.experimental.Wither;

import javax.persistence.Embeddable;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable
public class PostContent {
    @Wither
    private String title;
    @Wither
    private String content;
}
