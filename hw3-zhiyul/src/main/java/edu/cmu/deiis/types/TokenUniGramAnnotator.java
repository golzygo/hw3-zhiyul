package edu.cmu.deiis.types;

import java.util.Iterator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.cas.FSArray;

import edu.cmu.deiis.types.Token;
import edu.cmu.deiis.types.NGram;

public class TokenUniGramAnnotator extends JCasAnnotator_ImplBase{
  public void process(JCas aJCas){
    FSIndex tokenIndex=aJCas.getAnnotationIndex(edu.cmu.deiis.types.Token.type);
    Iterator tokenIter=tokenIndex.iterator();
    while(tokenIter.hasNext()){
      // turn this token into a UniGram
      Token token=(Token)tokenIter.next();
      NGram annotation=new NGram(aJCas);
      annotation.setBegin(token.getBegin());
      annotation.setEnd(token.getEnd());
      annotation.setCasProcessorId("edu.cmu.deiis.types.TokenUniGramAnnotator");
      annotation.setConfidence(1.0);
      annotation.setElementType("edu.cmu.deiis.types.Token");
      FSArray tokens=new FSArray(aJCas, 1);
      tokens.set(0, token);
      annotation.setElements(tokens);
      annotation.addToIndexes();
    }
  }
}
