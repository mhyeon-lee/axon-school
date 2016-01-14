package kr.axon.supporter;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.axonframework.common.Assert;
import org.axonframework.domain.IdentifierFactory;

import javax.persistence.Embeddable;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
@MappedSuperclass
@Embeddable     // for query JPA Model
public abstract class AbstractIdentifier implements Serializable {
    private static final long serialVersionUID = -756169764489352313L;

    protected String id;

    protected AbstractIdentifier() {
        this.id = IdentifierFactory.getInstance().generateIdentifier();
    }

    protected AbstractIdentifier(String id) {
        Assert.notNull(id, "id may not be null");
        this.id = id;
    }

    public String toString() {
        return this.id;
    }
}
