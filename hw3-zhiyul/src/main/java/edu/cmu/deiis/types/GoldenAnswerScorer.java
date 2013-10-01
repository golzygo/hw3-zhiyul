package edu.cmu.deiis.types;

import java.util.Iterator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.cas.FSIndex;

import edu.cmu.deiis.types.AnswerScore;
import edu.cmu.deiis.types.Question;
import edu.cmu.deiis.types.Answer;

public class GoldenAnswerScorer extends JCasAnnotator_ImplBase{
  public void process(JCas aJCas){
    // peek at the isCorrect feature of the answer, assign 1.0 to the answer if true, 0 otherwise
    FSIndex answerIndex=aJCas.getAnnotationIndex(edu.cmu.deiis.types.Answer.type);
    Iterator answerIter=answerIndex.iterator();
    while(answerIter.hasNext()){
      Answer answer=(Answer)answerIter.next();
      AnswerScore annotation=new AnswerScore(aJCas);
      annotation.setBegin(answer.getBegin());
      annotation.setEnd(answer.getEnd());
      annotation.setCasProcessorId("edu.cmu.deiis.types.GoldenAnswerScorer");
      annotation.setConfidence(1.0);
      annotation.setAnswer(answer);
      if(answer.getIsCorrect()){
        annotation.setScore(1.0);
      }
      else{
        annotation.setScore(0.0);
      }
      annotation.addToIndexes();
    }
  }
}

