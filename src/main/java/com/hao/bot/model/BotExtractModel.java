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
 * Model class for bot_extract
 * 提取记录
 */
@Entity
@Table(name = "bot_extract")
@DynamicInsert
@DynamicUpdate
public class BotExtractModel extends BaseModelClass implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
  
    /** extract_id
    *标识
    */ 
    private java.lang.Integer extractId;
    
    @Column(name = "extract_id")
    @Id @GeneratedValue(strategy = GenerationType.AUTO)   
    public java.lang.Integer getExtractId(){
        return this.extractId;
    }
    
    public void setExtractId(java.lang.Integer extractId){
        this.extractId = extractId;
        super.addValidField("extractId");
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
    
  
    /** points
    *数量
    */ 
    private java.lang.Double points;
    
    @Column(name = "points")
    public java.lang.Double getPoints(){
        return this.points;
    }
    
    public void setPoints(java.lang.Double points){
        this.points = points;
        super.addValidField("points");
    }
    
  
    /** to_user_name
    *微信会员
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
    
  
    /** user_name
    *微信会员名称
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
    
  
    /** status
    *审核状态：0待审核，1审核通过，-1审核不通过
    */ 
    private java.lang.Integer status;
    
    @Column(name = "status")
    public java.lang.Integer getStatus(){
        return this.status;
    }
    
    public void setStatus(java.lang.Integer status){
        this.status = status;
        super.addValidField("status");
    }
    
}