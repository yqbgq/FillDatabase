package types;

public abstract class Type {
    private String name;
    private int length;
    private boolean allowEmpty;
    private String type;
    private boolean hasForeignKey;
    private String foreignKeyDatabase;
    private String foreignKeyTable;
    private String foreignKeyColumn;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHasForeignKey() {
        return hasForeignKey;
    }

    public void setHasForeignKey(boolean hasForeignKey) {
        this.hasForeignKey = hasForeignKey;
    }

    public String getForeignKeyDatabase() {
        return foreignKeyDatabase;
    }

    public void setForeignKeyDatabase(String foreignKeyDatabase) {
        this.foreignKeyDatabase = foreignKeyDatabase;
    }

    public String getForeignKeyTable() {
        return foreignKeyTable;
    }

    public void setForeignKeyTable(String foreignKeyTable) {
        this.foreignKeyTable = foreignKeyTable;
    }

    public String getForeignKeyColumn() {
        return foreignKeyColumn;
    }

    public void setForeignKeyColumn(String foreignKeyColumn) {
        this.foreignKeyColumn = foreignKeyColumn;
    }

    public String toString(){
        return this.getName();
    }
}
