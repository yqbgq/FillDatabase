package util;

import types.Type;

import java.util.ArrayList;

public class FindType {
    public static int find(ArrayList<Type> typeList , String name){
        for(Type type : typeList){
            if(type.getName().equals(name)){
                return typeList.indexOf(type);
            }
        }
        return -1;
    }
}
