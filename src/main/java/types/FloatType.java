package types;

public class FloatType extends Type{
    public FloatType(String name, int length, boolean allowEmpty){
        this.setName(name);
        this.setLength(length);
        this.setAllowEmpty(allowEmpty);
        this.setType("float");
    }
}
