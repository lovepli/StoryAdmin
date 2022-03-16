package com.story.storyadmin.framework.springTransaction;

import javax.sql.DataSource;

/**
 * @author: 59688
 * @date: 2021/9/27
 * @description:
 */
public class UserService {

    private UserAccountDao userAccountDao;
    private UserOrderDao userOrderDao;
    private TransactionManager transactionManager;

    public UserService(DataSource dataSource){
        userAccountDao = new UserAccountDao(dataSource);
        userOrderDao = new UserOrderDao(dataSource);
        transactionManager = new TransactionManager(dataSource);
    }

    public void action(){
        try {
            transactionManager.start();
            userAccountDao.buy();
            userOrderDao.order();
            transactionManager.close();
        }catch (Exception e){
            e.printStackTrace();
            transactionManager.rollback();
        }


    }
}
