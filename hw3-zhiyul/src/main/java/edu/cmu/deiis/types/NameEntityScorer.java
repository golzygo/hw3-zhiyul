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
import org.cleartk.ne.type.NamedEntityMention;

public class NameEntityScorer extends JCasAnnotator_ImplBase{
  public void process(JCas aJCas){
    FSIndex neIndex=aJCas.getAnnotationIndex(org.cleartk.ne.type.NamedEntityMention.type);
    Iterator neIter=neIndex.iterator();
    NamedEntityMention ne = null;
    
    List<NamedEntityMention> questionNamedEntityMentions=new ArrayList<NamedEntityMention>();
    FSIndex questionIndex=aJCas.getAnnotationIndex(edu.cmu.deiis.types.Question.type);
    Iterator questionIter=questionIndex.iterator();
    while(questionIter.hasNext()){
      Question question=(Question)questionIter.next();
      // get question's nes, store them to the List questionNamedEntityMentions
      if(ne!=null){
        questionNamedEntityMentions.add(ne);
      }
      while(neIter.hasNext()){
        ne = (NamedEntityMention)neIter.next();
        if(ne.getEnd()<=question.getEnd()){
          questionNamedEntityMentions.add(ne);
        }
        else{
          // ne should belong to next sentence, so we need to break;
          break;
        }
      }
    }
    
    // for every answer, compare the ne overlap with the question,
    // and assign the answer a score according to that.
    FSIndex answerIndex=aJCas.getAnnotationIndex(edu.cmu.deiis.types.Answer.type);
    Iterator answerIter=answerIndex.iterator();
    while(answerIter.hasNext()){
      Answer answer = (Answer)answerIter.next();
      // get answer's nes, store them to the List answerNamedEntityMentions
      List<NamedEntityMention> answerNamedEntityMentions=new ArrayList<NamedEntityMention>();
      if(ne!=null){
        answerNamedEntityMentions.add(ne);
      }
      while(neIter.hasNext()){
        ne = (NamedEntityMention)neIter.next();
        if(ne.getEnd()<=answer.getEnd()){
          answerNamedEntityMentions.add(ne);
        }
        else{
          // ne should belong to next sentence, so we need to break;
          break;
        }
      }
      // compare answer's ne list with the question's ne list and assign a score
      int questionNamedEntityMentionFound = 0;
      int totalAnswerNamedEntityMention = answerNamedEntityMentions.size();

      for(NamedEntityMention neQ:questionNamedEntityMentions){
        for(int i=0;i<answerNamedEntityMentions.size();i++){
          if(neQ.getCoveredText().equals(answerNamedEntityMentions.get(i).getCoveredText())){
            questionNamedEntityMentionFound++;
            answerNamedEntityMentions.remove(i);
          }
        }
      }
      double score = questionNamedEntityMentionFound*1.0/totalAnswerNamedEntityMention;
      AnswerScore annotation=new AnswerScore(aJCas);
      annotation.setBegin(answer.getBegin());
      annotation.setEnd(answer.getEnd());
      annotation.setCasProcessorId("edu.cmu.deiis.types.NamedEntityScorer");
      annotation.setConfidence(1.0);
      annotation.setAnswer(answer);
      annotation.setScore(score);
      annotation.addToIndexes();
    }
  }

}
