package edu.cmu.deiis.types;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.cas.FSIndex;
import org.apache.uima.jcas.cas.FSArray;

import edu.cmu.deiis.types.Token;
import edu.cmu.deiis.types.NGram;

public class TokenBiGramAnnotator extends JCasAnnotator_ImplBase{
  public void process(JCas aJCas){
    // because BiGram cannot cross sentences, we need to do the BiGram sentence by sentence
    FSIndex tokenIndex=aJCas.getAnnotationIndex(edu.cmu.deiis.types.Token.type);
    Iterator tokenIter=tokenIndex.iterator();
    Token token = null;
    Token token2 = null; // the Token next to the "token" Token
    
    // first do it in question sentence
    FSIndex questionIndex=aJCas.getAnnotationIndex(edu.cmu.deiis.types.Question.type);
    Iterator questionIter=questionIndex.iterator();
    while(questionIter.hasNext()){
      Question question=(Question)questionIter.next();
      while(tokenIter.hasNext()){
        if(token==null){
          token = (Token)tokenIter.next();
        }
        if(tokenIter.hasNext()){
          token2=(Token)tokenIter.next();
          if(token2.getEnd()<=question.getEnd()){
            // found one BiGram, save it to an annotation
            NGram annotation=new NGram(aJCas);
            annotation.setBegin(token.getBegin());
            annotation.setEnd(token2.getEnd());
            annotation.setCasProcessorId("edu.cmu.deiis.types.TokenBiGramAnnotator");
            annotation.setConfidence(1.0);
            annotation.setElementType("edu.cmu.deiis.types.Token");
            FSArray tokens=new FSArray(aJCas, 2);
            tokens.set(0, token);
            tokens.set(1, token2);
            annotation.setElements(tokens);
            annotation.addToIndexes();
            // move token forward
            token=token2;
          }
          else{
            // token2 should belong to next sentence, so we need to break;
            token=token2;
            break;
          }
        }
      }
    }
    
    // then do it in answer sentences
    FSIndex answerIndex=aJCas.getAnnotationIndex(edu.cmu.deiis.types.Answer.type);
    Iterator answerIter=answerIndex.iterator();
    while(answerIter.hasNext()){
      Answer answer = (Answer)answerIter.next();
      while(tokenIter.hasNext()){
        if(token2==null){
          token = (Token)tokenIter.next();
        }
        if(tokenIter.hasNext()){
          token2=(Token)tokenIter.next();
          if(token2.getEnd()<=answer.getEnd()){
            // found one BiGram, save it to an annotation
            NGram annotation=new NGram(aJCas);
            annotation.setBegin(token.getBegin());
            annotation.setEnd(token2.getEnd());
            annotation.setCasProcessorId("edu.cmu.deiis.types.TokenBiGramAnnotator");
            annotation.setConfidence(1.0);
            annotation.setElementType("edu.cmu.deiis.types.Token");
            FSArray tokens=new FSArray(aJCas, 2);
            tokens.set(0, token);
            tokens.set(1, token2);
            annotation.setElements(tokens);
            annotation.addToIndexes();
            // move token forward
            token=token2;
          }
          else{
            // token2 should belong to next sentence, so we need to break;
            token = token2;
            break;
          }
        }
      }
    }
    
  }
}
