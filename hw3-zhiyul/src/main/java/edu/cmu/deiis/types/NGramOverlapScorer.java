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
import edu.cmu.deiis.types.NGram;

public class NGramOverlapScorer extends JCasAnnotator_ImplBase{
  public void process(JCas aJCas){
    FSIndex nGramIndex=aJCas.getAnnotationIndex(edu.cmu.deiis.types.NGram.type);
    Iterator nGramIter=nGramIndex.iterator();
    NGram nGram = null;
    
    List<NGram> questionNGrams=new ArrayList<NGram>();
    FSIndex questionIndex=aJCas.getAnnotationIndex(edu.cmu.deiis.types.Question.type);
    Iterator questionIter=questionIndex.iterator();
    while(questionIter.hasNext()){
      Question question=(Question)questionIter.next();
      // get question's nGrams, store them to the List questionNGrams
      if(nGram!=null){
        questionNGrams.add(nGram);
      }
      while(nGramIter.hasNext()){
        nGram = (NGram)nGramIter.next();
        if(nGram.getEnd()<=question.getEnd()){
          questionNGrams.add(nGram);
        }
        else{
          // nGram should belong to next sentence, so we need to break;
          break;
        }
      }
    }
    
    // for every answer, compare the nGram overlap with the question,
    // and assign the answer a score according to that.
    FSIndex answerIndex=aJCas.getAnnotationIndex(edu.cmu.deiis.types.Answer.type);
    Iterator answerIter=answerIndex.iterator();
    while(answerIter.hasNext()){
      Answer answer = (Answer)answerIter.next();
      // get answer's nGrams, store them to the List answerNGrams
      List<NGram> answerNGrams=new ArrayList<NGram>();
      if(nGram!=null){
        answerNGrams.add(nGram);
      }
      while(nGramIter.hasNext()){
        nGram = (NGram)nGramIter.next();
        if(nGram.getEnd()<=answer.getEnd()){
          answerNGrams.add(nGram);
        }
        else{
          // nGram should belong to next sentence, so we need to break;
          break;
        }
      }
      // compare answer's nGram list with the question's nGram list and assign a score
      int questionNGramFound = 0;
      int totalAnswerNGram = answerNGrams.size();

      for(NGram nGramQ:questionNGrams){
        for(int i=0;i<answerNGrams.size();i++){
          if(nGramQ.getCoveredText().equals(answerNGrams.get(i).getCoveredText())){
            questionNGramFound++;
            answerNGrams.remove(i);
          }
        }
      }
      double score = questionNGramFound*1.0/totalAnswerNGram;
      AnswerScore annotation=new AnswerScore(aJCas);
      annotation.setBegin(answer.getBegin());
      annotation.setEnd(answer.getEnd());
      annotation.setCasProcessorId("edu.cmu.deiis.types.NGramOverlapScorer");
      annotation.setConfidence(1.0);
      annotation.setAnswer(answer);
      annotation.setScore(score);
      annotation.addToIndexes();
    }
  }

}
