package types;

public class Bit extends Type {
    public Bit(String name, int length, boolean allowEmpty){
        this.setName(name);
        this.setLength(length);
        this.setAllowEmpty(allowEmpty);
        this.setType("bit");
    }
}
