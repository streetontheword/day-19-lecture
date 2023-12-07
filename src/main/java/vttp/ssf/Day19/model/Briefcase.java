package vttp.ssf.Day19.model;

import org.springframework.stereotype.Component;

@Component("Briefcase")
public class Briefcase extends Bag {

    @Override
    public void showBagType() {
       System.out.println("you are carrying a briefcase.");
    }
   
    
}
