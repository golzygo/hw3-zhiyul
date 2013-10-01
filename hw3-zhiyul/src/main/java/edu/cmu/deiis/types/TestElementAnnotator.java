package edu.cmu.deiis.types;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import edu.cmu.deiis.types.Question;
import edu.cmu.deiis.types.Answer;

public class TestElementAnnotator extends JCasAnnotator_ImplBase{
  public void process(JCas aJCas){
    // get document text
    String docText=aJCas.getDocumentText();
    String lines[]=docText.split("\n");
    int linePosition=0; // record current line's beginning position
    for(String line:lines){
      if(line.startsWith("Q")){
        // found one question - create annotation
        Question annotation=new Question(aJCas);
        annotation.setBegin(linePosition+2);
        annotation.setEnd(linePosition+line.length());
        linePosition+=(line.length()+1); // +1 to the beginning of next line
        annotation.setCasProcessorId("edu.cmu.deiis.types.TestElementAnnotator");
        annotation.setConfidence(1.0);
        annotation.addToIndexes();
      }
      else if(line.startsWith("A")){
        // found one answer - create annotation
        Answer annotation=new Answer(aJCas);
        annotation.setBegin(linePosition+4);
        annotation.setEnd(linePosition+line.length());
        linePosition+=(line.length()+1); // +1 to the beginning of next line
        annotation.setCasProcessorId("edu.cmu.deiis.types.TestElementAnnotator");
        annotation.setConfidence(1.0);
        if(line.charAt(2)=='1'){
          annotation.setIsCorrect(true);
        }
        else{
          annotation.setIsCorrect(false);
        }
        annotation.addToIndexes();
      }
    }
  }

}
