package com.mobiles.gubi;

public class Zone implements Comparable<Zone>{

    //busy- occupied places
    //total- total places
    //WChair - number of wheel chair places in the zone
    //CPool- number of car pool places in the zone
    //priority- priority of the object based on a certain attribute

    private int busy,total,WChair,CPool,priority; //the attributes of the class

    public String getLocalization() {
        return localization;
    }



    private String localization;
    private String name; //the name of the class

    public Zone(int busy,int total,int WChair,int CPool,String name, String localization){ //constructor
        this.busy = busy;
        this.total=total;
        this.WChair = WChair;
        this.CPool = CPool;
        this.name = name;
        this.localization = localization;
    }

    //setters and getters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBusy() {
        return busy;
    }

    public void setBusy(int busy) {
        this.busy = busy;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getWChair() {
        return WChair;
    }

    public void setWChair(int WChair) {
        this.WChair = WChair;
    }

    public int getCPool() {
        return CPool;
    }

    public void setCPool(int CPool) {
        this.CPool = CPool;
    }

    public int getPriority() {
        return priority;
    }

    //setPriority is different from the other setters
    //it receives a PriorityType enum, which uses to determine the value of priority

    public void setPriority(PriorityType hi) {
        if(hi==PriorityType.CARPOOL){
            priority=CPool;
        }

        else if(hi==PriorityType.AVAILABLE){
            priority=total-busy;
        }
        else if(hi==PriorityType.WHEELCHAIR){
            priority=WChair;
        }
    }


    //compareTo used to compare a zone with another
    //returns 0 if both object priority attribute has the same value
    //returns 1 if the priority of the received object is bigger than the priority of this object
    //returns -1 otherwise
    @Override
    public int compareTo(Zone other) {

        if(this.priority==other.getPriority()){
            return 0;
        }
        else if(this.priority<other.getPriority()){
            return 1;

        }
        else{
            return -1;
        }
    }

    //used to print the object
    public String toString(){
        return name+" "+priority+"";
    }




}