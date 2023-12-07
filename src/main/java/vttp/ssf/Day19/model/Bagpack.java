package vttp.ssf.Day19.model;

import org.springframework.stereotype.Component;

@Component ("Bagpack")
public class Bagpack extends Bag{

    @Override
    public void showBagType() {
       System.out.println("you are carrying a bagpack.");
    }

    
    
}
