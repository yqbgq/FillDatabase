package Process;

import builder.ConcreteBuilder;
import process.TaskClient;

public class TestDemo {
    public static void main(String[] args) throws Exception{
        TestDemo t = new TestDemo();
        t.testBuildTask();
    }

    private void testBuildTask() throws Exception{
        ConcreteBuilder builder = new ConcreteBuilder();
        TaskClient client = builder
                .setUsername("root")
                .setPassword("root")
                .addTask("foreign","test1",100000)
                .build();
        client.start();
    }
}
