package kr.axon.post.command.domain;

import lombok.EqualsAndHashCode;
import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

import java.io.Serializable;

@EqualsAndHashCode
public class PostIdentifier implements Serializable {
    private static final long serialVersionUID = -7106184259025268531L;

    private String identifier;

    public PostIdentifier() {
        this.identifier = IdentifierFactory.getInstance().generateIdentifier();
    }

    public PostIdentifier(String identifier) {
        Assert.notNull(identifier, "Identifier may not be null");
        this.identifier = identifier;
    }

    public String toString() {
        return this.identifier;
    }
}
