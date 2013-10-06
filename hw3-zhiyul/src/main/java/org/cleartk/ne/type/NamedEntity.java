

/* First created by JCasGen Sat Oct 05 20:59:09 EDT 2013 */
package org.cleartk.ne.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.cas.FSArray;
import org.cleartk.score.type.ScoredTOP;


/** 
 * Updated by JCasGen Sat Oct 05 20:59:09 EDT 2013
 * XML source: /Users/golzygo/git/hw3-zhiyul/hw3-zhiyul/src/main/resources/descriptors/deiis_types.xml
 * @generated */
public class NamedEntity extends ScoredTOP {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(NamedEntity.class);
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int type = typeIndexID;
  /** @generated  */
  @Override
  public              int getTypeIndexID() {return typeIndexID;}
 
  /** Never called.  Disable default constructor
   * @generated */
  protected NamedEntity() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public NamedEntity(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public NamedEntity(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: entityType

  /** getter for entityType - gets 
   * @generated */
  public String getEntityType() {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_entityType == null)
      jcasType.jcas.throwFeatMissing("entityType", "org.cleartk.ne.type.NamedEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_entityType);}
    
  /** setter for entityType - sets  
   * @generated */
  public void setEntityType(String v) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_entityType == null)
      jcasType.jcas.throwFeatMissing("entityType", "org.cleartk.ne.type.NamedEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_entityType, v);}    
   
    
  //*--------------*
  //* Feature: entitySubtype

  /** getter for entitySubtype - gets 
   * @generated */
  public String getEntitySubtype() {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_entitySubtype == null)
      jcasType.jcas.throwFeatMissing("entitySubtype", "org.cleartk.ne.type.NamedEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_entitySubtype);}
    
  /** setter for entitySubtype - sets  
   * @generated */
  public void setEntitySubtype(String v) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_entitySubtype == null)
      jcasType.jcas.throwFeatMissing("entitySubtype", "org.cleartk.ne.type.NamedEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_entitySubtype, v);}    
   
    
  //*--------------*
  //* Feature: entityClass

  /** getter for entityClass - gets 
   * @generated */
  public String getEntityClass() {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_entityClass == null)
      jcasType.jcas.throwFeatMissing("entityClass", "org.cleartk.ne.type.NamedEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_entityClass);}
    
  /** setter for entityClass - sets  
   * @generated */
  public void setEntityClass(String v) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_entityClass == null)
      jcasType.jcas.throwFeatMissing("entityClass", "org.cleartk.ne.type.NamedEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_entityClass, v);}    
   
    
  //*--------------*
  //* Feature: entityId

  /** getter for entityId - gets 
   * @generated */
  public String getEntityId() {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_entityId == null)
      jcasType.jcas.throwFeatMissing("entityId", "org.cleartk.ne.type.NamedEntity");
    return jcasType.ll_cas.ll_getStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_entityId);}
    
  /** setter for entityId - sets  
   * @generated */
  public void setEntityId(String v) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_entityId == null)
      jcasType.jcas.throwFeatMissing("entityId", "org.cleartk.ne.type.NamedEntity");
    jcasType.ll_cas.ll_setStringValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_entityId, v);}    
   
    
  //*--------------*
  //* Feature: mentions

  /** getter for mentions - gets 
   * @generated */
  public FSArray getMentions() {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_mentions == null)
      jcasType.jcas.throwFeatMissing("mentions", "org.cleartk.ne.type.NamedEntity");
    return (FSArray)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_mentions)));}
    
  /** setter for mentions - sets  
   * @generated */
  public void setMentions(FSArray v) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_mentions == null)
      jcasType.jcas.throwFeatMissing("mentions", "org.cleartk.ne.type.NamedEntity");
    jcasType.ll_cas.ll_setRefValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_mentions, jcasType.ll_cas.ll_getFSRef(v));}    
    
  /** indexed getter for mentions - gets an indexed value - 
   * @generated */
  public NamedEntityMention getMentions(int i) {
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_mentions == null)
      jcasType.jcas.throwFeatMissing("mentions", "org.cleartk.ne.type.NamedEntity");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_mentions), i);
    return (NamedEntityMention)(jcasType.ll_cas.ll_getFSForRef(jcasType.ll_cas.ll_getRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_mentions), i)));}

  /** indexed setter for mentions - sets an indexed value - 
   * @generated */
  public void setMentions(int i, NamedEntityMention v) { 
    if (NamedEntity_Type.featOkTst && ((NamedEntity_Type)jcasType).casFeat_mentions == null)
      jcasType.jcas.throwFeatMissing("mentions", "org.cleartk.ne.type.NamedEntity");
    jcasType.jcas.checkArrayBounds(jcasType.ll_cas.ll_getRefValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_mentions), i);
    jcasType.ll_cas.ll_setRefArrayValue(jcasType.ll_cas.ll_getRefValue(addr, ((NamedEntity_Type)jcasType).casFeatCode_mentions), i, jcasType.ll_cas.ll_getFSRef(v));}
  }

    