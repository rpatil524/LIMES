/*
 * LIMES Core Library - LIMES – Link Discovery Framework for Metric Spaces.
 * Copyright © 2011 Data Science Group (DICE) (ngonga@uni-paderborn.de)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.aksw.limes.core.io.preprocessing;

import org.aksw.limes.core.exceptions.InvalidPreprocessingFunctionException;
import org.aksw.limes.core.io.preprocessing.functions.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;


public class PreprocessingFunctionFactory {
    static Logger logger = LoggerFactory.getLogger(PreprocessingFunctionFactory.class);

    public static final String CLEAN_IRI = "cleaniri";
    public static final String CLEAN_NUMBER = "number";
    public static final String CONCAT = "concat";
    public static final String REGEX_REPLACE = "regexreplace";
    public static final String REMOVE_LANGUAGE_TAG = "nolang";
    public static final String RENAME_PROPERTY = "rename";
    public static final String REPLACE = "replace";
    public static final String TO_CELSIUS = "celsius";
    public static final String TO_FAHRENHEIT = "fahrenheit";
    public static final String TO_UPPERCASE = "uppercase";
    public static final String TO_LOWERCASE = "lowercase";
    public static final String REMOVE_BRACES = "removebraces";
    public static final String REMOVE_NON_ALPHANUMERIC = "regularalphabet";
    public static final String URI_AS_STRING = "uriasstring";
    public static final String SPLIT = "split";
    public static final String TO_WKT_POINT = "towktpoint"; //toWktPoint

    public static PreprocessingFunctionType getPreprocessingType(String expression){
        switch(expression.trim().toLowerCase()){
            case(CLEAN_IRI):
                return PreprocessingFunctionType.CLEAN_IRI;
            case(CLEAN_NUMBER):
                return PreprocessingFunctionType.CLEAN_NUMBER;
            case(CONCAT):
                return PreprocessingFunctionType.CONCAT;
            case(REGEX_REPLACE):
                return PreprocessingFunctionType.REGEX_REPLACE;
            case(REMOVE_LANGUAGE_TAG):
                return PreprocessingFunctionType.REMOVE_LANGUAGE_TAG;
            case(RENAME_PROPERTY):
                return PreprocessingFunctionType.RENAME_PROPERTY;
            case(REPLACE):
                return PreprocessingFunctionType.REPLACE;
            case(TO_CELSIUS):
                return PreprocessingFunctionType.TO_CELSIUS;
            case(TO_FAHRENHEIT):
                return PreprocessingFunctionType.TO_FAHRENHEIT;
            case(TO_UPPERCASE):
                return PreprocessingFunctionType.TO_UPPERCASE;
            case(TO_LOWERCASE):
                return PreprocessingFunctionType.TO_LOWERCASE;
            case(REMOVE_BRACES):
                return PreprocessingFunctionType.REMOVE_BRACES;
            case(REMOVE_NON_ALPHANUMERIC):
                return PreprocessingFunctionType.REMOVE_NON_ALPHANUMERIC;
            case(URI_AS_STRING):
                return PreprocessingFunctionType.URI_AS_STRING;
            case(SPLIT):
                return PreprocessingFunctionType.SPLIT;
            case(TO_WKT_POINT):
                return PreprocessingFunctionType.TO_WKT_POINT;
            default:
                throw new InvalidPreprocessingFunctionException(expression);
        }
    }

    public static APreprocessingFunction getPreprocessingFunction(PreprocessingFunctionType type){
        switch(type){
            case CLEAN_IRI:
                return new CleanIri();
            case CLEAN_NUMBER:
                return new CleanNumber();
            case CONCAT:
                return new Concat();
            case REGEX_REPLACE:
                return new RegexReplace();
            case REMOVE_LANGUAGE_TAG:
                return new RemoveLanguageTag();
            case RENAME_PROPERTY:
                return new RenameProperty();
            case REPLACE:
                return new Replace();
            case TO_CELSIUS:
                return new ToCelsius();
            case TO_FAHRENHEIT:
                return new ToFahrenheit();
            case TO_UPPERCASE:
                return new ToUppercase();
            case TO_LOWERCASE:
                return new ToLowercase();
            case REMOVE_BRACES:
                return new RemoveBraces();
            case REMOVE_NON_ALPHANUMERIC:
                return new RemoveNonAlphanumeric();
            case URI_AS_STRING:
                return new UriAsString();
            case SPLIT:
                return new Split();
            case TO_WKT_POINT:
                return new ToWktPoint();
            default:
                throw new InvalidPreprocessingFunctionException(type.toString());
        }
    }

    public static List<String> listTypes() {
        return Arrays.asList(
                CLEAN_IRI,
                CLEAN_NUMBER,
                CONCAT,
                REGEX_REPLACE,
                REMOVE_LANGUAGE_TAG,
                RENAME_PROPERTY,
                REPLACE,
                TO_CELSIUS,
                TO_FAHRENHEIT,
                TO_UPPERCASE,
                TO_LOWERCASE,
                REMOVE_BRACES,
                REMOVE_NON_ALPHANUMERIC,
                URI_AS_STRING,
                SPLIT,
                TO_WKT_POINT
        );
    }

}
