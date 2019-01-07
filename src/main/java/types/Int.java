package types;

public class Int extends Type{
    public Int(String name, int length, boolean allowEmpty, boolean autoIncrease){
        this.setName(name);
        this.setLength(length);
        this.setAllowEmpty(allowEmpty);
        this.setAutoIncrease(allowEmpty);
    }

    private boolean autoIncrease;

    public boolean isAutoIncrease() {
        return autoIncrease;
    }

    public void setAutoIncrease(boolean autoIncrease) {
        this.autoIncrease = autoIncrease;
    }
}
