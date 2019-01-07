package types;

public class Int {
    private String name;
    private int length;
    private boolean allowEmpty;
    private boolean autoIncrease;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public boolean isAllowEmpty() {
        return allowEmpty;
    }

    public void setAllowEmpty(boolean allowEmpty) {
        this.allowEmpty = allowEmpty;
    }

    public boolean isAutoIncrease() {
        return autoIncrease;
    }

    public void setAutoIncrease(boolean autoIncrease) {
        this.autoIncrease = autoIncrease;
    }
}
