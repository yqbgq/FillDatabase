package Builder;

import process.TaskClient;
import builder.ConcreteBuilder;
import exception.BuildingException;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class TestBuilder {
    @Test
    public void testSuccess() throws BuildingException {
        ConcreteBuilder builder = new ConcreteBuilder();

        TaskClient client = builder
                .setUsername("root")
                .setPassword("root")
                .build();

        assertNotNull(client);
    }

    @Test
    public void testFail() throws BuildingException{
        ConcreteBuilder builder = new ConcreteBuilder();

        TaskClient client = builder
                .build();

        assertNotNull(client);
    }
}
