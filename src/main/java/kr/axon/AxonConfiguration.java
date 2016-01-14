package kr.axon;

import kr.axon.post.command.domain.PostAggregateRoot;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.SimpleCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.contextsupport.spring.AnnotationDriven;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.fs.FileSystemEventStore;
import org.axonframework.eventstore.fs.SimpleEventFileResolver;
import org.axonframework.unitofwork.DefaultUnitOfWorkFactory;
import org.axonframework.unitofwork.TransactionManager;
import org.axonframework.unitofwork.UnitOfWorkFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.io.File;

@Configuration
@AnnotationDriven
@SuppressWarnings("unused")
public class AxonConfiguration {

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Bean
    public CommandBus commandBus() {
        return new SimpleCommandBus();
    }

    @Bean
    public CommandGateway commandGateway() {
        return new DefaultCommandGateway(commandBus());
    }

    @Bean
    public EventBus eventBus() {
        return new SimpleEventBus();
    }

    @Bean
    public EventStore eventStore() {
        return new FileSystemEventStore(
                new SimpleEventFileResolver(new File("./events"))
        );
    }

    @Bean
    public EventSourcingRepository eventSourcingRepository() {
        EventSourcingRepository eventSourcingRepository =
                new EventSourcingRepository(PostAggregateRoot.class, eventStore());
        eventSourcingRepository.setEventBus(eventBus());
        return eventSourcingRepository;
    }
}