package edu.cmu.deiis.types;

import java.util.Iterator;

import org.apache.uima.analysis_component.JCasAnnotator_ImplBase;
import org.apache.uima.jcas.JCas;
import org.apache.uima.cas.FSIndex;

import edu.cmu.deiis.types.Token;
import edu.cmu.deiis.types.Question;
import edu.cmu.deiis.types.Answer;

//import java.io.StringReader;
//import edu.stanford.nlp.ling.Word;
//import edu.stanford.nlp.objectbank.TokenizerFactory;
//import edu.stanford.nlp.process.PTBTokenizer.PTBTokenizerFactory;
//import edu.stanford.nlp.process.Tokenizer;

public class TokenAnnotator extends JCasAnnotator_ImplBase{
  public void process(JCas aJCas){
    FSIndex questionIndex=aJCas.getAnnotationIndex(edu.cmu.deiis.types.Question.type);
    Iterator questionIter=questionIndex.iterator();
    while(questionIter.hasNext()){
      Question question=(Question)questionIter.next();
      // get question text
      String sentence=question.getCoveredText();
      String words[]=sentence.split(" ");
      int wordPosition=question.getBegin(); // save the current word's position
      for (String word : words){
        while(!Character.isLetterOrDigit(word.charAt(word.length()-1))){
          word=word.substring(0, word.length()-1);
        } // discard the punctuation(s)
        Token annotation=new Token(aJCas);
        annotation.setBegin(wordPosition);
        annotation.setEnd(wordPosition+word.length());
        wordPosition+=(word.length()+1); // assume there is only one space between tokens
        // not accurate, better to use a nlp tool, but stanford-nlp has a bug on my computer
        annotation.setCasProcessorId("edu.cmu.deiis.types.TokenAnnotator");
        annotation.setConfidence(1.0);
        annotation.addToIndexes();
      }
    }
    
    FSIndex answerIndex=aJCas.getAnnotationIndex(edu.cmu.deiis.types.Answer.type);
    Iterator answerIter=answerIndex.iterator();
    while(answerIter.hasNext()){
      Answer answer=(Answer)answerIter.next();
      // get answer text
      String sentence=answer.getCoveredText();
      String words[]=sentence.split(" ");
      int wordPosition=answer.getBegin(); // save the current word's position
      for (String word : words){
        while(!Character.isLetterOrDigit(word.charAt(word.length()-1))){
          word=word.substring(0, word.length()-1);
        } // discard the punctuation(s)
        Token annotation=new Token(aJCas);
        annotation.setBegin(wordPosition);
        annotation.setEnd(wordPosition+word.length());
        wordPosition+=(word.length()+1); // assume there is only one space between tokens
        // not accurate, better to use a nlp tool, but stanford-nlp has a bug on my computer
        annotation.setCasProcessorId("edu.cmu.deiis.types.TokenAnnotator");
        annotation.setConfidence(1.0);
        annotation.addToIndexes();
      }
    }
    
    // stanford-nlp tokenizer, doesn't work here because the document analyzer encounters
    // an error: "java.lang.NoClassDefFoundError: 
    // edu/stanford/nlp/process/PTBTokenizer$PTBTokenizerFactory"
//    String docText=aJCas.getDocumentText();
//    TokenizerFactory<Word> factory =
//            PTBTokenizerFactory.newTokenizerFactory();
//    Tokenizer<Word> tokenizer =
//            factory.getTokenizer(new StringReader(docText));
//    for(Word token : tokenizer.tokenize()){
//      // found one token - create annotation
//      Token annotation=new Token(aJCas);
//      annotation.setBegin(token.beginPosition());
//      annotation.setEnd(token.endPosition());
//      annotation.setCasProcessorId("edu.cmu.deiis.types.TokenAnnotator");
//      annotation.setConfidence(1.0);
//      annotation.addToIndexes();
//    }
    
  }
}
