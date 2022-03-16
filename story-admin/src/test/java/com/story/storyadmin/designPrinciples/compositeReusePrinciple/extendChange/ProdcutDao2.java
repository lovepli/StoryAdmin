package com.story.storyadmin.designPrinciples.compositeReusePrinciple.extendChange;


/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 */
public class ProdcutDao2 {

    private MySQLConnection mySQLConnection;

    private OracleConnection oracleConnection;

    public void setDBConnection(MySQLConnection mySQLConnection,OracleConnection oracleConnection){
        this.mySQLConnection=mySQLConnection;
        this.oracleConnection=oracleConnection;
    }
    public void addProduct(){
        String conn1=mySQLConnection.getConnection();
        String conn2=oracleConnection.getConnection();
        System.out.println("使用" + conn1 + "连接数据库");
        System.out.println("使用" + conn2 + "连接数据库");
    }
}
