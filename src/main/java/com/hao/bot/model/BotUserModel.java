package com.hao.bot.model;    

import java.io.Serializable;
import com.gsst.eaf.core.model.BaseModelClass;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Model class for bot_user
 * 
 */
@Entity
@Table(name = "bot_user")
@DynamicInsert
@DynamicUpdate
public class BotUserModel extends BaseModelClass implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
  
    /** user_id
    *用户标识
    */ 
    private java.lang.Integer userId;
    
    @Column(name = "user_id")
    @Id @GeneratedValue(strategy = GenerationType.AUTO)   
    public java.lang.Integer getUserId(){
        return this.userId;
    }
    
    public void setUserId(java.lang.Integer userId){
        this.userId = userId;
        super.addValidField("userId");
    }
    
  
    /** user_account
    *登录账号
    */ 
    private java.lang.String userAccount;
    
    @Column(name = "user_account")
    public java.lang.String getUserAccount(){
        return this.userAccount;
    }
    
    public void setUserAccount(java.lang.String userAccount){
        this.userAccount = userAccount;
        super.addValidField("userAccount");
    }
    
  
    /** user_name
    *用户名称
    */ 
    private java.lang.String userName;
    
    @Column(name = "user_name")
    public java.lang.String getUserName(){
        return this.userName;
    }
    
    public void setUserName(java.lang.String userName){
        this.userName = userName;
        super.addValidField("userName");
    }
    
  
    /** password
    *登录密码
    */ 
    private java.lang.String password;
    
    @Column(name = "password")
    public java.lang.String getPassword(){
        return this.password;
    }
    
    public void setPassword(java.lang.String password){
        this.password = password;
        super.addValidField("password");
    }
    
  
    /** user_type
    *用户类型,0 系统管理用户
    */ 
    private java.lang.Byte userType;
    
    @Column(name = "user_type")
    public java.lang.Byte getUserType(){
        return this.userType;
    }
    
    public void setUserType(java.lang.Byte userType){
        this.userType = userType;
        super.addValidField("userType");
    }
    
  
    /** user_status
    *用户状态 0正常,-1已删除,1禁用,只有0的状态才能正常登录
    */ 
    private java.lang.Byte userStatus;
    
    @Column(name = "user_status")
    public java.lang.Byte getUserStatus(){
        return this.userStatus;
    }
    
    public void setUserStatus(java.lang.Byte userStatus){
        this.userStatus = userStatus;
        super.addValidField("userStatus");
    }
    
  
    /** create_time
    *创建日期
    */ 
    private java.util.Date createTime;
    
    @Column(name = "create_time")
    public java.util.Date getCreateTime(){
        return this.createTime;
    }
    
    public void setCreateTime(java.util.Date createTime){
        this.createTime = createTime;
        super.addValidField("createTime");
    }
    
  
    /** creator
    *创建人
    */ 
    private java.lang.Integer creator;
    
    @Column(name = "creator")
    public java.lang.Integer getCreator(){
        return this.creator;
    }
    
    public void setCreator(java.lang.Integer creator){
        this.creator = creator;
        super.addValidField("creator");
    }
    
  
    /** modify_time
    *修改时间
    */ 
    private java.util.Date modifyTime;
    
    @Column(name = "modify_time")
    public java.util.Date getModifyTime(){
        return this.modifyTime;
    }
    
    public void setModifyTime(java.util.Date modifyTime){
        this.modifyTime = modifyTime;
        super.addValidField("modifyTime");
    }
    
  
    /** modifier
    *修改人
    */ 
    private java.lang.Integer modifier;
    
    @Column(name = "modifier")
    public java.lang.Integer getModifier(){
        return this.modifier;
    }
    
    public void setModifier(java.lang.Integer modifier){
        this.modifier = modifier;
        super.addValidField("modifier");
    }
    
}