package types;

public class Char extends Type{
    public Char(String name, int length, boolean allowEmpty){
        this.setName(name);
        this.setLength(length);
        this.setAllowEmpty(allowEmpty);
        this.setType("char");
    }
}
