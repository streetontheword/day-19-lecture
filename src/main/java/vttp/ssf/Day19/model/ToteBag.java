package vttp.ssf.Day19.model;

import org.springframework.stereotype.Component;

@Component("totebag")
public class ToteBag extends Bag{

    @Override
    public void showBagType() {
       System.out.println("you are carrying a tote bag.");
    }
    
    
}
