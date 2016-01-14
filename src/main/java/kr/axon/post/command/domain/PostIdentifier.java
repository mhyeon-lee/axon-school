package kr.axon.post.command.domain;

import lombok.EqualsAndHashCode;
import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode
@Embeddable     // for query JPA Model
public class PostIdentifier implements Serializable {
    private static final long serialVersionUID = -7106184259025268531L;

    private String id;

    public PostIdentifier() {
        this.id = IdentifierFactory.getInstance().generateIdentifier();
    }

    public PostIdentifier(String identifier) {
        Assert.notNull(identifier, "Identifier may not be null");
        this.id = identifier;
    }

    public String toString() {
        return this.id;
    }
}
