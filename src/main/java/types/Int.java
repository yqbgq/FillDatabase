package types;

public class Int extends Type{
    public Int(String name, int length, boolean allowEmpty, boolean autoIncrease){
        this.setName(name);
        this.setLength(length);
        this.setAllowEmpty(allowEmpty);
        this.setAutoIncrease(autoIncrease);
        this.setType("int");
    }

    private boolean autoIncrease;

    public boolean isAutoIncrease() {
        return autoIncrease;
    }

    public void setAutoIncrease(boolean autoIncrease) {
        this.autoIncrease = autoIncrease;
    }
}
