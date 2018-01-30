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
 * Model class for bot_integral
 * 积分
 */
@Entity
@Table(name = "bot_integral")
@DynamicInsert
@DynamicUpdate
public class BotIntegralModel extends BaseModelClass implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
  
    /** uintegral_id
    *标识
    */ 
    private java.lang.Integer uintegralId;
    
    @Column(name = "uintegral_id")
    @Id @GeneratedValue(strategy = GenerationType.AUTO)   
    public java.lang.Integer getUintegralId(){
        return this.uintegralId;
    }
    
    public void setUintegralId(java.lang.Integer uintegralId){
        this.uintegralId = uintegralId;
        super.addValidField("uintegralId");
    }
    
  
    /** to_user_name
    *微信会员id
    */ 
    private java.lang.String toUserName;
    
    @Column(name = "to_user_name")
    public java.lang.String getToUserName(){
        return this.toUserName;
    }
    
    public void setToUserName(java.lang.String toUserName){
        this.toUserName = toUserName;
        super.addValidField("toUserName");
    }
    
  
    /** remaining_points
    *剩余积分
    */ 
    private java.lang.Double remainingPoints;
    
    @Column(name = "remaining_points")
    public java.lang.Double getRemainingPoints(){
        return this.remainingPoints;
    }
    
    public void setRemainingPoints(java.lang.Double remainingPoints){
        this.remainingPoints = remainingPoints;
        super.addValidField("remainingPoints");
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