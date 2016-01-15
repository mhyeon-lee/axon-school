package kr.axon.post.command.domain;

import lombok.*;
import lombok.experimental.Wither;

import javax.persistence.Embeddable;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
@Embeddable       // for query JPA Model
public class PostContent {
    @Wither
    private String title;
    @Wither
    private String body;
}
