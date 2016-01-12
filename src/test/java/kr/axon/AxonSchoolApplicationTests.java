package kr.axon;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = AxonSchoolApplication.class)
@WebAppConfiguration
public class AxonSchoolApplicationTests {

    @Autowired
    private CommandGateway commandGateWay;

    @Test
    public void contextLoads() {
    }
}
