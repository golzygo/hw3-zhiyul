

/* First created by JCasGen Sat Oct 05 20:59:09 EDT 2013 */
package org.cleartk.score.type;

import org.apache.uima.jcas.JCas; 
import org.apache.uima.jcas.JCasRegistry;
import org.apache.uima.jcas.cas.TOP_Type;

import org.apache.uima.jcas.tcas.Annotation;


/** 
 * Updated by JCasGen Sat Oct 05 20:59:09 EDT 2013
 * XML source: /Users/golzygo/git/hw3-zhiyul/hw3-zhiyul/src/main/resources/descriptors/deiis_types.xml
 * @generated */
public class ScoredAnnotation extends Annotation {
  /** @generated
   * @ordered 
   */
  @SuppressWarnings ("hiding")
  public final static int typeIndexID = JCasRegistry.register(ScoredAnnotation.class);
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
  protected ScoredAnnotation() {/* intentionally empty block */}
    
  /** Internal - constructor used by generator 
   * @generated */
  public ScoredAnnotation(int addr, TOP_Type type) {
    super(addr, type);
    readObject();
  }
  
  /** @generated */
  public ScoredAnnotation(JCas jcas) {
    super(jcas);
    readObject();   
  } 

  /** @generated */  
  public ScoredAnnotation(JCas jcas, int begin, int end) {
    super(jcas);
    setBegin(begin);
    setEnd(end);
    readObject();
  }   

  /** <!-- begin-user-doc -->
    * Write your own initialization here
    * <!-- end-user-doc -->
  @generated modifiable */
  private void readObject() {/*default - does nothing empty block */}
     
 
    
  //*--------------*
  //* Feature: score

  /** getter for score - gets 
   * @generated */
  public double getScore() {
    if (ScoredAnnotation_Type.featOkTst && ((ScoredAnnotation_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "org.cleartk.score.type.ScoredAnnotation");
    return jcasType.ll_cas.ll_getDoubleValue(addr, ((ScoredAnnotation_Type)jcasType).casFeatCode_score);}
    
  /** setter for score - sets  
   * @generated */
  public void setScore(double v) {
    if (ScoredAnnotation_Type.featOkTst && ((ScoredAnnotation_Type)jcasType).casFeat_score == null)
      jcasType.jcas.throwFeatMissing("score", "org.cleartk.score.type.ScoredAnnotation");
    jcasType.ll_cas.ll_setDoubleValue(addr, ((ScoredAnnotation_Type)jcasType).casFeatCode_score, v);}    
  }

    