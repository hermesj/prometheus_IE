package de.uni_koeln.spinfo.prometheus_IE.components;

import java.io.IOException;
import java.util.List;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.Triple;

public class NERParser {
	
	private AbstractSequenceClassifier<CoreLabel> classifier;
	
	public NERParser(){
		String serializedClassifier = "edu/stanford/nlp/models/ner/german.dewac_175m_600.crf.ser.gz";
		try {
			classifier  = CRFClassifier.getClassifier(serializedClassifier);
		} catch (ClassCastException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String doNER(String toProcess){
		
	    //String serializedClassifier = "classifiers/english.all.3class.distsim.crf.ser.gz";
	    try {	    	
			String toReturn = classifier.classifyWithInlineXML(toProcess);
	    	return toReturn;
		      
		} catch (ClassCastException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} 
	}

}
