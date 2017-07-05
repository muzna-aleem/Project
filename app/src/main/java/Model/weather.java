package Model;

/**
 * Created by Maham on 5/28/2017.
 */

public class weather {
    public String location;
    public String max;
    public String min;
    public weather() {
    }
    public weather(String s,String ma,String mi){
        location = s;
        max = ma;
        min = mi;
    }

    public String getLocation() {
        return location;
    }
    public String getmax() {
        return max;
    }
    public String getmin() {
        return min;
    }

    public void setLocation(String s) {
        location = s;
    }
    public void setmin(String i) {
        min = i;
    }
    public void setmax(String i) {
        max = i;
    }

}
