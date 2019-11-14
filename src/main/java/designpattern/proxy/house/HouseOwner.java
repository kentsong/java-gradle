package designpattern.proxy.house;

public class HouseOwner implements RentHouse {

    public void rent() {
        System.out.println("I want to rent my house");
    }

    public void charge(String str) {
        System.out.println("You get : " + str + " RMB HouseCharge.");
    }
}
