package kr.axon.post.controller;

import kr.axon.post.command.domain.PostContent;
import kr.axon.post.command.domain.PostIdentifier;
import kr.axon.post.controller.hateoas.PostResourceAssembler;
import kr.axon.post.controller.hateoas.PostResourceLinks;
import kr.axon.post.exception.PostNotFoundException;
import kr.axon.post.query.model.Post;
import kr.axon.post.query.repository.PostQueryRepository;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static kr.axon.post.command.api.PostCommand.*;
import static kr.axon.post.controller.hateoas.PostResourceAssembler.*;

@RestController
@RequestMapping("/api/posts")
public class PostRestController {

    @Autowired
    private CommandGateway commandGateway;

    @Autowired
    private PostQueryRepository repository;

    @Autowired
    private PostResourceAssembler resourceAssembler;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity save(@RequestBody PostContent content) {
        PostIdentifier id = new PostIdentifier();
        CreatePostCommand command = new CreatePostCommand(id, content);
        commandGateway.sendAndWait(command);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity modify(@PathVariable PostIdentifier id,
                                 @RequestBody PostContent content) {
        ModifyPostCommand command = new ModifyPostCommand(id, content);
        commandGateway.sendAndWait(command);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable PostIdentifier id) {
        DeletePostCommand command = new DeletePostCommand(id);
        commandGateway.sendAndWait(command);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public PostResource getOne(@PathVariable PostIdentifier id) {
        Post post = repository.findOne(id);
        if (post == null) {
            throw new PostNotFoundException(id + " not exist.");
        }
        return resourceAssembler.toResource(post);
    }

    @RequestMapping(method = RequestMethod.GET)
    public Resources<PostResourceAssembler.PostResource> findAll() {
        List<Post> posts = repository.findAll();
        return resourceAssembler.toResources(posts,
                PostResourceLinks.findAll().withSelfRel());
    }
}
