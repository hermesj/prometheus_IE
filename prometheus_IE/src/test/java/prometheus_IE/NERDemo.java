package prometheus_IE;
import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.*;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.sequences.DocumentReaderAndWriter;
import edu.stanford.nlp.util.Triple;

import java.util.List;


/** This is a demo of calling CRFClassifier programmatically.
 *  <p>
 *  Usage: {@code java -mx400m -cp "*" NERDemo [serializedClassifier [fileName]] }
 *  <p>
 *  If arguments aren't specified, they default to
 *  classifiers/english.all.3class.distsim.crf.ser.gz and some hardcoded sample text.
 *  If run with arguments, it shows some of the ways to get k-best labelings and
 *  probabilities out with CRFClassifier. If run without arguments, it shows some of
 *  the alternative output formats that you can get.
 *  <p>
 *  To use CRFClassifier from the command line:
 *  </p><blockquote>
 *  {@code java -mx400m edu.stanford.nlp.ie.crf.CRFClassifier -loadClassifier [classifier] -textFile [file] }
 *  </blockquote><p>
 *  Or if the file is already tokenized and one word per line, perhaps in
 *  a tab-separated value format with extra columns for part-of-speech tag,
 *  etc., use the version below (note the 's' instead of the 'x'):
 *  </p><blockquote>
 *  {@code java -mx400m edu.stanford.nlp.ie.crf.CRFClassifier -loadClassifier [classifier] -testFile [file] }
 *  </blockquote>
 *
 *  @author Jenny Finkel
 *  @author Christopher Manning
 */

public class NERDemo {

  public static void main(String[] args) throws Exception {
	  //"models/nemgp_stanford_01.gz";//
    String serializedClassifier = "edu/stanford/nlp/models/ner/german.dewac_175m_600.crf.ser.gz";
    //String serializedClassifier = "classifiers/english.all.3class.distsim.crf.ser.gz";
    if (args.length > 0) {
      serializedClassifier = args[0];
    }

    AbstractSequenceClassifier<CoreLabel> classifier = CRFClassifier.getClassifier(serializedClassifier);

    /* For either a file to annotate or for the hardcoded text example, this
       demo file shows several ways to process the input, for teaching purposes.
    */

    if (args.length > 1) {

      /* For the file, it shows (1) how to run NER on a String, (2) how
         to get the entities in the String with character offsets, and
         (3) how to run NER on a whole file (without loading it into a String).
      */

      String fileContents = IOUtils.slurpFile(args[1]);
      List<List<CoreLabel>> out = classifier.classify(fileContents);
      for (List<CoreLabel> sentence : out) {
        for (CoreLabel word : sentence) {
          System.out.print(word.word() + '/' + word.get(CoreAnnotations.AnswerAnnotation.class) + ' ');
        }
        System.out.println();
      }

      System.out.println("---");
      out = classifier.classifyFile(args[1]);
      for (List<CoreLabel> sentence : out) {
        for (CoreLabel word : sentence) {
          System.out.print(word.word() + '/' + word.get(CoreAnnotations.AnswerAnnotation.class) + ' ');
        }
        System.out.println();
      }

      System.out.println("---");
      List<Triple<String, Integer, Integer>> list = classifier.classifyToCharacterOffsets(fileContents);
      for (Triple<String, Integer, Integer> item : list) {
        System.out.println(item.first() + ": " + fileContents.substring(item.second(), item.third()));
      }
//      System.out.println("---");
//      System.out.println("Ten best entity labelings");
//      DocumentReaderAndWriter<CoreLabel> readerAndWriter = classifier.makePlainTextReaderAndWriter();
//      classifier.classifyAndWriteAnswersKBest(args[1], 10, readerAndWriter);
//
//      System.out.println("---");
//      System.out.println("Per-token marginalized probabilities");
//      classifier.printProbs(args[1], readerAndWriter);

      // -- This code prints out the first order (token pair) clique probabilities.
      // -- But that output is a bit overwhelming, so we leave it commented out by default.
      // System.out.println("---");
      // System.out.println("First Order Clique Probabilities");
      // ((CRFClassifier) classifier).printFirstOrderProbs(args[1], readerAndWriter);

    } else {

      /* For the hard-coded String, it shows how to run it on a single
         sentence, and how to do this and produce several formats, including
         slash tags and an inline XML output format. It also shows the full
         contents of the {@code CoreLabel}s that are constructed by the
         classifier. And it shows getting out the probabilities of different
         assignments and an n-best list of classifications with probabilities.
      */

      String[] example = {"Silber vergoldet, getrieben und gegossen, ziseliert und punziert; die Krone von unten her auf dem Deckel verschraubt.Über dem breiten, geschwungenen Rand erhebt sich nach mehrfacher Abtreppung und einer Hohlkehle der Fuß des Ziboriums. Seine Oberseite zeigt in reicher Treibarbeit vier Blumengebinde in Muschelwerkkartuschen, sowie in vier kleinen Zwischenfeldern Blumen,Kornähren und Weintrauben.Über einem wulstigen Zwischenstück setzt der Schaft an, der mit dem Nodus zur Balusterform verschmolzen ist.Die schalenförmige, sehr breite Cuppa besitzt einen nicht durchbrochenen Überfang, der bis zur halben Höhe reicht. Er zeigt wiederum in Treibarbeit Blumengebinde in Muschelwerkkartuschen; in den kleineren Zwischenflächen wiederholen sich unterSpiegelfeldern, die mit gravierten Kreuzlagen gefüllt und von Volutenstegen gerahmt sind, die Blumenmotive der Fußoberseite. Die Cuppa ist oben leicht nach außen gewölbt.Der verhältnismäßig flache Deckel ist mit getriebenen Blumen und Kartuschenfelder bedeckt; er wölbt sich in der Mitte zu einem von einer getriebenen Sonne umgebenen Knauf aus, dessen Spitze ein Kreuz bildet. Aufgeschraubt ist auf dem Deckel eine großeKrone. Sie besteht aus einem glatten, von zwei Perlstäben gerahmten Reif, über dem sich frei geformte durchbrochene Rocailleformen nach oben hin entwickeln. Sie rahmen getriebene Gebinde aus Blumen, Früchten und Weintrauben, sowie leere Kartuschenfelder.Gestiftet hat dieses pompöse Ziborium der Domdekan und Weihbischof Adam Ernst Joseph Freiherr von Bernklau, der 1762 Domkapitular geworden war, 1767 seine Bischofsweihe erhielt und 1771 Domdekan wurde. Er starb 1779 im Alter von 66 Jahren. Zu der 1777erfolgten Schenkung gehörten noch zwei Kelche und eine Meßkännchengarnitur (vgl. Katalog Hubel S.35); davon hat sich möglicherweise noch ein Kelch erhalten (Inv.Nr. D 1974/17).Vom selben Augsburger Goldschmied Johann Ignaz Caspar Berthold, der 1755-1794 als Meister tätig war, stammen in Regensburg noch ein Kelch in der Kollegiatskirche St. Johann, der 1779 von Fürstbischof Anton Ignaz von Fugger gestiftet worden war (Anm. 419Kat. Hubel), sowie ein um 1781/83 entstandenes Rauchfaß, ursprünglich im Obermünster, heute im bischöflichen Studienseminar Westmünster (Anm. 420 Kat. Hubel). Rosenberg hatte das Monogramm ICB auf den Goldschmied Johann Karl Burger gedeutet, wasSchröder richtigstellen konnte." };
    		  // {"Good afternoon Rajat Raina, how are you today?", "I go to school at Stanford University, which is located in California." };
      for (String str : example) {
        System.out.println(classifier.classifyToString(str));
      }
      System.out.println("---");

      for (String str : example) {
        // This one puts in spaces and newlines between tokens, so just print not println.
        System.out.print(classifier.classifyToString(str, "slashTags", false));
      }
      System.out.println("---");

      for (String str : example) {
        // This one is best for dealing with the output as a TSV (tab-separated column) file.
        // The first column gives entities, the second their classes, and the third the remaining text in a document
        System.out.print(classifier.classifyToString(str, "tabbedEntities", false));
      }
      System.out.println("---");

      for (String str : example) {
        System.out.println(classifier.classifyWithInlineXML(str));
      }
      System.out.println("---");

      for (String str : example) {
        System.out.println(classifier.classifyToString(str, "xml", true));
      }
      System.out.println("---");

      for (String str : example) {
        System.out.print(classifier.classifyToString(str, "tsv", false));
      }
      System.out.println("---");

      // This gets out entities with character offsets
      int j = 0;
      for (String str : example) {
        j++;
        List<Triple<String,Integer,Integer>> triples = classifier.classifyToCharacterOffsets(str);
        for (Triple<String,Integer,Integer> trip : triples) {
          System.out.printf("%s over character offsets [%d, %d) in sentence %d.%n",
                  trip.first(), trip.second(), trip.third, j);
        }
      }
      System.out.println("---");

      // This prints out all the details of what is stored for each token
      int i=0;
      for (String str : example) {
        for (List<CoreLabel> lcl : classifier.classify(str)) {
          for (CoreLabel cl : lcl) {
            System.out.print(i++ + ": ");
            System.out.println(cl.toShorterString());
          }
        }
      }

      System.out.println("---");

    }
  }

}
