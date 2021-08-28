package com.expenseSharing;

import com.expenseSharing.model.ExpenseType;
import com.expenseSharing.repository.UserBalanceDAO;
import com.expenseSharing.service.SplitService;
import com.expenseSharing.service.SplitServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
	// write your code here





        SplitServiceImpl splitService = new SplitServiceImpl();
        List<String> payeeUser = new ArrayList<String>();
        payeeUser.add("2");
        payeeUser.add("3");
        payeeUser.add("4");

        List<Double> metaData = new ArrayList<>();
        metaData.add(500.0);
        splitService.enrichUserBalance(1000.0,"1",4, payeeUser, ExpenseType.EQUAL, metaData);

        metaData = new ArrayList<>();
        metaData.add(100.0);
        payeeUser = new ArrayList<>();
        payeeUser.add("1");
        splitService.enrichUserBalance(1000.0,"5",1, payeeUser, ExpenseType.EXACT, metaData);


        metaData = new ArrayList<>();
        metaData.add(50.0);
        payeeUser = new ArrayList<>();
        payeeUser.add("5");
        splitService.enrichUserBalance(1000.0,"1",1, payeeUser, ExpenseType.EXACT, metaData);

        metaData = new ArrayList<>();
        metaData.add(500.0);
        payeeUser = new ArrayList<>();
        payeeUser.add("1");
        splitService.enrichUserBalance(1000.0,"2",1, payeeUser, ExpenseType.EXACT, metaData);


        System.out.println(UserBalanceDAO.UserBalance.toString());
        for(Map.Entry<String,Double> data : UserBalanceDAO.UserBalance.entrySet())
        {
            String key = data.getKey();
            String userId[] = key.split("#");
            if(key.contains("2"))
            {
                if(data.getValue()>0)
                {

                    System.out.println(userId[1] + "  will pay  "+ " "+ userId[0] + " "+ data.getValue() );


                }
//                else
//                {
//
//
//                    System.out.println(userId[1] +"has to  pay"+ data.getValue()+ userId[0] );
//
//                }



            }

        }



    }



}
