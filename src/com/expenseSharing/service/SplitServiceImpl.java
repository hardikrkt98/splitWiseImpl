package com.expenseSharing.service;

import com.expenseSharing.model.ExpenseType;
import com.expenseSharing.repository.UserBalanceDAO;
import javafx.util.Pair;

import java.util.List;

public class SplitServiceImpl implements  SplitService{





    public void enrichUserBalance(Double expense,
                                  String payerUserId,
                                  Integer numberOfPayeeUsers,
                                  List<String> payeeUserIds,
                                  ExpenseType expenseType,
                                  List<Double> expenseMetaData){
        switch(expenseType){
            case EXACT:
                enrchUserExactExpenseType(expense, payerUserId, numberOfPayeeUsers, payeeUserIds, expenseMetaData);
                break;
            case EQUAL:
                enrchUserEqualExpenseType(expense,payerUserId, numberOfPayeeUsers, payeeUserIds);
                break;
        }




    }

    private void enrchUserEqualExpenseType(Double expense, String payerUserId, Integer numberOfPayeeUsers, List<String> payeeUserIds) {
          Double payeeIndividualExpense = expense/numberOfPayeeUsers;
       // payerUserid---> payee

          for(String payee : payeeUserIds)
          {
                 String key = payerUserId + "#" + payee;
                 String reverseKey = payee + "#" + payerUserId;

                 if(UserBalanceDAO.UserBalance.containsKey(reverseKey))
                 {
                     Double updateValue = UserBalanceDAO.UserBalance.get(reverseKey);
                     updateValue += -1*payeeIndividualExpense;
                     UserBalanceDAO.UserBalance.put(reverseKey, updateValue);
                     UserBalanceDAO.UserBalance.put(key, -1*updateValue);

                 }
                 UserBalanceDAO.UserBalance.putIfAbsent(key, payeeIndividualExpense);
                 UserBalanceDAO.UserBalance.putIfAbsent(reverseKey, -1*payeeIndividualExpense);

          }

    }

      private void enrchUserExactExpenseType(Double expense, String payerUserId, Integer numberOfPayeeUsers, List<String> payeeUserIds, List<Double> expenseMetaData) {
            for (int i =0 ;i<numberOfPayeeUsers;i++)
            {
                String payee = payeeUserIds.get(i);
                Double payeeIndividualExpense = expenseMetaData.get(i);

                String key = payerUserId + "#" + payee;
                String reverseKey = payee + "#" + payerUserId;

                if(UserBalanceDAO.UserBalance.containsKey(reverseKey))
                {
                    Double updateValue = UserBalanceDAO.UserBalance.get(reverseKey);
                    updateValue += -1*payeeIndividualExpense;
                    UserBalanceDAO.UserBalance.put(reverseKey, updateValue);
                    UserBalanceDAO.UserBalance.put(key, -1*updateValue);

                }
                UserBalanceDAO.UserBalance.putIfAbsent(key, payeeIndividualExpense);
                UserBalanceDAO.UserBalance.putIfAbsent(reverseKey, -1*payeeIndividualExpense);


            }

    }


}
