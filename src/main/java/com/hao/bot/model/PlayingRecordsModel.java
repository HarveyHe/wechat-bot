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
 * @author Harvey.He
 * Model class for playing_records
 * 牌局记录
 */
@Entity
@Table(name = "playing_records")
@DynamicInsert
@DynamicUpdate
public class PlayingRecordsModel extends BaseModelClass implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
  
    /** playing_records_id
    *标识
    */ 
    private java.lang.Integer playingRecordsId;
    
    @Column(name = "playing_records_id")
    @Id @GeneratedValue(strategy = GenerationType.AUTO)   
    public java.lang.Integer getPlayingRecordsId(){
        return this.playingRecordsId;
    }
    
    public void setPlayingRecordsId(java.lang.Integer playingRecordsId){
        this.playingRecordsId = playingRecordsId;
        super.addValidField("playingRecordsId");
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
    
  
    /** playing_no
    *牌局期数
    */ 
    private java.lang.String playingNo;
    
    @Column(name = "playing_no")
    public java.lang.String getPlayingNo(){
        return this.playingNo;
    }
    
    public void setPlayingNo(java.lang.String playingNo){
        this.playingNo = playingNo;
        super.addValidField("playingNo");
    }
    
  
    /** record1
    *注1
    */ 
    private java.lang.Integer record1;
    
    @Column(name = "record1")
    public java.lang.Integer getRecord1(){
        return this.record1;
    }
    
    public void setRecord1(java.lang.Integer record1){
        this.record1 = record1;
        super.addValidField("record1");
    }
    
  
    /** record2
    *注2
    */ 
    private java.lang.Integer record2;
    
    @Column(name = "record2")
    public java.lang.Integer getRecord2(){
        return this.record2;
    }
    
    public void setRecord2(java.lang.Integer record2){
        this.record2 = record2;
        super.addValidField("record2");
    }
    
  
    /** record3
    *注3
    */ 
    private java.lang.Integer record3;
    
    @Column(name = "record3")
    public java.lang.Integer getRecord3(){
        return this.record3;
    }
    
    public void setRecord3(java.lang.Integer record3){
        this.record3 = record3;
        super.addValidField("record3");
    }
    
  
    /** record4
    *注4
    */ 
    private java.lang.Integer record4;
    
    @Column(name = "record4")
    public java.lang.Integer getRecord4(){
        return this.record4;
    }
    
    public void setRecord4(java.lang.Integer record4){
        this.record4 = record4;
        super.addValidField("record4");
    }
    
  
    /** record5
    *注5
    */ 
    private java.lang.Integer record5;
    
    @Column(name = "record5")
    public java.lang.Integer getRecord5(){
        return this.record5;
    }
    
    public void setRecord5(java.lang.Integer record5){
        this.record5 = record5;
        super.addValidField("record5");
    }
    
  
    /** record6
    *注6
    */ 
    private java.lang.Integer record6;
    
    @Column(name = "record6")
    public java.lang.Integer getRecord6(){
        return this.record6;
    }
    
    public void setRecord6(java.lang.Integer record6){
        this.record6 = record6;
        super.addValidField("record6");
    }
    
  
    /** record7
    *注7
    */ 
    private java.lang.Integer record7;
    
    @Column(name = "record7")
    public java.lang.Integer getRecord7(){
        return this.record7;
    }
    
    public void setRecord7(java.lang.Integer record7){
        this.record7 = record7;
        super.addValidField("record7");
    }
    
  
    /** record8
    *注8
    */ 
    private java.lang.Integer record8;
    
    @Column(name = "record8")
    public java.lang.Integer getRecord8(){
        return this.record8;
    }
    
    public void setRecord8(java.lang.Integer record8){
        this.record8 = record8;
        super.addValidField("record8");
    }
    
  
    /** record9
    *注9
    */ 
    private java.lang.Integer record9;
    
    @Column(name = "record9")
    public java.lang.Integer getRecord9(){
        return this.record9;
    }
    
    public void setRecord9(java.lang.Integer record9){
        this.record9 = record9;
        super.addValidField("record9");
    }
    
  
    /** record10
    *注10
    */ 
    private java.lang.Integer record10;
    
    @Column(name = "record10")
    public java.lang.Integer getRecord10(){
        return this.record10;
    }
    
    public void setRecord10(java.lang.Integer record10){
        this.record10 = record10;
        super.addValidField("record10");
    }
    
}