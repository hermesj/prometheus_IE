package de.uni_koeln.spinfo.prometheus_IE.components;

import java.io.IOException;
import java.util.List;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.util.Triple;

public class NERParser {
	
	public void doNER(String toProcess){
		String serializedClassifier = "edu/stanford/nlp/models/ner/german.dewac_175m_600.crf.ser.gz";
	    //String serializedClassifier = "classifiers/english.all.3class.distsim.crf.ser.gz";
	    try {	    	
			AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(serializedClassifier);
			//System.out.println(classifier.classifyToString(toProcess));
			// This one puts in spaces and newlines between tokens, so just print not println.
			System.out.print(classifier.classifyToString(toProcess, "tsv", false));
		      
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

}
