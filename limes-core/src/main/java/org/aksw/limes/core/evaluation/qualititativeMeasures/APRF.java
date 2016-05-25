/**
 * 
 */
package org.aksw.limes.core.evaluation.qualititativeMeasures;

import org.aksw.limes.core.datastrutures.GoldStandard;
import org.aksw.limes.core.io.mapping.Mapping;


/**
 * This class contains three methods that calculate such values:
 * true-positive: the results classified as positive and the classification is correct.
 * false-positive: the results classified as positive and the classification is incorrect.
 * true-negative:	the results classified as negative and the classification is correct.
 * false-positive:	the results classified as negative and the classification is incorrect.
 * These values are used by Measures classes to evaluate the mappings results
 * @author mofeed
 * @author Klaus Lyko <lyko@informatik.uni-leipzig.de>
 * @version 1.0
 */
public abstract class APRF implements IQualitativeMeasure{
    public abstract double calculate(Mapping predictions, GoldStandard goldStandard);

    /**
     * @param predictions : the mapping generated by the algorithm
     * @param goldStandard : the gold standard to measure the accuracy compared to it
     * @param truePositive if it is true you calc. true positive otherwise it is false positive
     * @return based on the flag it returns either True positive or False positive
     */
    public static double trueFalsePositive(final Mapping predictions, final Mapping goldStandard, boolean truePositive)
    {
        double TPCounter = 0,FPCounter=0;
        for(String sUri : predictions.getMap().keySet())
            for(String tUri : predictions.getMap().get(sUri).keySet())
                if(goldStandard.contains(sUri, tUri))
                    TPCounter++;
                else 
                    FPCounter++;
        if(truePositive)
            return TPCounter;
        return FPCounter;
    }
    /**
     * @param predictions : the mapping generated by the algorithm
     * @param goldStandard : the gold standard to measure the accuracy comapred to it
     * @return number of true links in gold standard that are not considered in the predicttion
     */
    public static double falseNegative(final Mapping predictions, final Mapping goldStandard)
    {
        double FNCounter = 0;
        for(String sUri : goldStandard.getMap().keySet())
            for(String tUri : goldStandard.getMap().get(sUri).keySet())
                if(!predictions.contains(sUri, tUri))
                    FNCounter++;
        return FNCounter;
    }

    /**
     * @param goldStandardSize : the size of the gold standard mapping
     * @param sourceDatasetSize: the size of the source dataset
     * @param targetDatasetSize: the size of the target dataset
     * @return the number of truly classified negative
     */
    public static double trueNegative(final long goldStandardSize, final long sourceDatasetSize, final long targetDatasetSize)
    {
        return (sourceDatasetSize*targetDatasetSize) -goldStandardSize;
    }
}
