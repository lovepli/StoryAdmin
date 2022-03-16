package com.story.storyadmin.designPrinciples.compositeReusePrinciple;

/**
 * @author: lipan
 * @date: 2021/8/19
 * @description:
 * 这是一种非常典型的合成复用原则应用场景。但是，对于目前的设计来说，DBConnection还不是一种抽象，不便于系统扩展。目前的系统支持MySQL数据库连接，假设业务发生变化，数据库操作层要支持Oracle数据库。
 *
 * 当然，我们可以在DBConnection中增加对Oracle数据库支持的方法，但是这违背了开闭原则。其实，可以不必修改Dao的代码，将DBConnection修改为abstract
 */
public class ProdcutDao{

    private DBConnection dbConnection;

    public void setDBConnection(DBConnection dbConnection){
        this.dbConnection=dbConnection;
    }
    public void addProduct(){
        String conn=dbConnection.getConnection();
        System.out.println("使用" + conn + "连接数据库");
    }
}
