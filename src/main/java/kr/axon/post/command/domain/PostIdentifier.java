package kr.axon.post.command.domain;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

import java.io.Serializable;

@EqualsAndHashCode
@ToString
public class PostIdentifier implements Serializable {

    private String identifier;

    public PostIdentifier() {
        this.identifier = IdentifierFactory.getInstance().generateIdentifier();
    }

    public PostIdentifier(String identifier) {
        Assert.notNull(identifier, "Identifier may not be null");
        this.identifier = identifier;
    }
}
