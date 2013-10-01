package edu.cmu.deiis.types;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.cas.FSIndex;

import edu.cmu.deiis.types.AnswerScore;
import edu.cmu.deiis.types.Question;
import edu.cmu.deiis.types.Answer;
import edu.cmu.deiis.types.Token;

public class TokenOverlapScorer extends JCasAnnotator_ImplBase{
  public void process(JCas aJCas){
    FSIndex tokenIndex=aJCas.getAnnotationIndex(edu.cmu.deiis.types.Token.type);
    Iterator tokenIter=tokenIndex.iterator();
    Token token = null;
    
    List<Token> questionTokens=new ArrayList<Token>();
    FSIndex questionIndex=aJCas.getAnnotationIndex(edu.cmu.deiis.types.Question.type);
    Iterator questionIter=questionIndex.iterator();
    while(questionIter.hasNext()){
      Question question=(Question)questionIter.next();
      // get question's tokens, store them to the List questionTokens
      if(token!=null){
        questionTokens.add(token);
      }
      while(tokenIter.hasNext()){
        token = (Token)tokenIter.next();
        if(token.getEnd()<=question.getEnd()){
          questionTokens.add(token);
        }
        else{
          // token should belong to next sentence, so we need to break;
          break;
        }
      }
    }
    
    // for every answer, compare the token overlap with the question,
    // and assign the answer a score according to that.
    FSIndex answerIndex=aJCas.getAnnotationIndex(edu.cmu.deiis.types.Answer.type);
    Iterator answerIter=answerIndex.iterator();
    while(answerIter.hasNext()){
      Answer answer = (Answer)answerIter.next();
      // get answer's tokens, store them to the List answerTokens
      List<Token> answerTokens=new ArrayList<Token>();
      if(token!=null){
        answerTokens.add(token);
      }
      while(tokenIter.hasNext()){
        token = (Token)tokenIter.next();
        if(token.getEnd()<=answer.getEnd()){
          answerTokens.add(token);
        }
        else{
          // token should belong to next sentence, so we need to break;
          break;
        }
      }
      // compare answer's token list with the question's token list and assign a score
      int questionTokenFound = 0;
      int totalAnswerToken = answerTokens.size();

      for(Token tokenQ:questionTokens){
        for(int i=0;i<answerTokens.size();i++){
          if(tokenQ.getCoveredText().equals(answerTokens.get(i).getCoveredText())){
            questionTokenFound++;
            answerTokens.remove(i);
          }
        }
      }
      double score = questionTokenFound*1.0/totalAnswerToken;
      AnswerScore annotation=new AnswerScore(aJCas);
      annotation.setBegin(answer.getBegin());
      annotation.setEnd(answer.getEnd());
      annotation.setCasProcessorId("edu.cmu.deiis.types.TokenOverlapScorer");
      annotation.setConfidence(1.0);
      annotation.setAnswer(answer);
      annotation.setScore(score);
      annotation.addToIndexes();
    }
  }

}
