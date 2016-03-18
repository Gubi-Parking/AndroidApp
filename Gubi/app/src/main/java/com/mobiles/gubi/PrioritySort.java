package com.mobiles.gubi;
import java.util.ArrayList;
import java.util.Collections;
//This class is used as a way to sort the array,
//all the methods are static
//use this class as a way to sort based on the priority desire
//both, methods, setPriority and sortP shall be called whenever the database is updated
public class PrioritySort {

    public static void sortP(ArrayList<Zone> zones){ //sorts the array that receives based on its compareTo method

        Collections.sort(zones);
    }

    public static void setPriority(ArrayList<Zone> zones, PriorityType hello){ //sets the priority on each individual object based on the enum received

        for(int i=0;i<zones.size();i++){
            zones.get(i).setPriority(hello);
        }
    }

    public static void printArray(ArrayList<Zone> zones){ //prints the array
        for(int i=0;i<zones.size();i++){
            System.out.println(zones.get(i));
        }

    }

}
