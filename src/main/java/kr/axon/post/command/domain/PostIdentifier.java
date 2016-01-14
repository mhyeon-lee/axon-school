package kr.axon.post.command.domain;

import kr.axon.supporter.AbstractIdentifier;

public class PostIdentifier extends AbstractIdentifier {
    private static final long serialVersionUID = -7106184259025268531L;

    public PostIdentifier() {
        super();
    }

    public PostIdentifier(String identifier) {
        super(identifier);
    }
}
