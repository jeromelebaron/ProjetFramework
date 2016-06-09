/*
 * Créé le 9 juin 2016 par Jérome LE BARON
 */
package fr.afcepf.atod26.framework.test;

import org.apache.log4j.Logger;

public class Test4j {

    private static Logger logger = Logger.getLogger(Test4j.class);

    public static void main(String[] args) {
        
        try {
            Integer.parseInt("a");
        } catch (Exception localE) {
            logger.error("Erreur de conversion", localE);
        }

    }

}
