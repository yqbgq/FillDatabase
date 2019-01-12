package Process;

import builder.ConcreteBuilder;
import org.junit.Test;
import process.TaskClient;

public class TestBuildTask {
    @Test
    public void testBuildTask() throws Exception{
        ConcreteBuilder builder = new ConcreteBuilder();
        TaskClient client = builder
                .setUsername("root")
                .setPassword("root")
                .addTask("fill","test1",100)
                .build();
        client.start();
    }
}
