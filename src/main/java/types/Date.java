package types;


public class Date extends Type {
    public Date(String name, int length, boolean allowEmpty){
        this.setName(name);
        this.setLength(length);
        this.setAllowEmpty(allowEmpty);
        this.setType("date");
    }
}
