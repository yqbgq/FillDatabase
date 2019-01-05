package Builder;

import application.TaskClient;
import builder.ConcreteBuilder;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestBuilder {
    @Test
    public void testSuccess(){
        ConcreteBuilder builder = new ConcreteBuilder();

        TaskClient client = builder
                .setUsername("root")
                .setPassword("root")
                .build();

        assertNotNull(client);
    }

    @Test
    public void testFail(){
        ConcreteBuilder builder = new ConcreteBuilder();

        TaskClient client = builder
                .build();

        assertNotNull(client);
    }
}
