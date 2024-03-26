package com.onlinesareesshoppingdao;

import com.onlinesareesshopping.entities.Cashondelivery;

public interface Cashondeliverydao {

    void insert(Cashondelivery codobj);
    
    //Cashondelivery getcashondelivery(int codId, int mobNo, String status,String address,Double payment);

	Cashondelivery getcashondelivery(int codId, int mobNo, String status, String address);

	
    
    // Add other methods if needed
}
