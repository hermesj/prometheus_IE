package de.uni_koeln.spinfo.prometheus_IE.components;

import java.util.List;
import java.util.Properties;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.AnnotationPipeline;
import edu.stanford.nlp.pipeline.POSTaggerAnnotator;
import edu.stanford.nlp.pipeline.TokenizerAnnotator;
import edu.stanford.nlp.pipeline.WordsToSentencesAnnotator;
import edu.stanford.nlp.pipeline.TokenizerAnnotator.TokenizerType;
import edu.stanford.nlp.tagger.maxent.MaxentTagger;
import edu.stanford.nlp.time.TimeAnnotations;
import edu.stanford.nlp.time.TimeAnnotator;
import edu.stanford.nlp.time.TimeExpression;
import edu.stanford.nlp.util.CoreMap;

public class TimeExParser {
	
	
	private AnnotationPipeline pipeline;
	public TimeExParser(){
		Properties props = new Properties();
	    //props.put("pos.model","edu/stanford/nlp/models/pos-tagger/german/german-hgc.tagger");
	    //props.put("ner.model","edu/stanford/nlp/models/ner/german.hgc_175m_600.crf.ser.gz");
	    pipeline = new AnnotationPipeline();
	    pipeline.addAnnotator(new TokenizerAnnotator(false, TokenizerType.German));
	    pipeline.addAnnotator(new WordsToSentencesAnnotator(false));
	    
	    MaxentTagger mt = new MaxentTagger( "edu/stanford/nlp/models/pos-tagger/german/german-dewac.tagger");
	    pipeline.addAnnotator(new POSTaggerAnnotator(mt));
	    pipeline.addAnnotator(new TimeAnnotator("sutime", props));
	}
	
	public String deTimeEx(String toProcess){
		String toReturn = null;
		Annotation annotation = new Annotation(toProcess);
	      annotation.set(CoreAnnotations.DocDateAnnotation.class, "2016-02-12");
	      pipeline.annotate(annotation);
	      //toReturn = annotation.get(CoreAnnotations.CalendarAnnotation.class);
	      System.out.println(annotation.get(CoreAnnotations.TextAnnotation.class));
	      List<CoreMap> timexAnnsAll = annotation.get(TimeAnnotations.TimexAnnotations.class);
	      for (CoreMap cm : timexAnnsAll) {
	        List<CoreLabel> tokens = cm.get(CoreAnnotations.TokensAnnotation.class);
	        System.out.println(cm + " [from char offset " +
	            tokens.get(0).get(CoreAnnotations.CharacterOffsetBeginAnnotation.class) +
	            " to " + tokens.get(tokens.size() - 1).get(CoreAnnotations.CharacterOffsetEndAnnotation.class) + ']' +
	            " --> " + cm.get(TimeExpression.Annotation.class).getTemporal());
	      }
	      System.out.println("--");
		return toReturn;
	}
	


}
