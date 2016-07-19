package com.xumak.lifeway;

import com.xumak.lifeway.exception.AEMException;
import com.xumak.lifeway.utils.LoadProperties;
import java.util.Calendar;
import org.apache.log4j.Logger;

public class Main {

    /**
     * This is for write the analysis result with log4j.
     */
    private static final Logger LOG = Logger.getLogger(Main.class.getName());
    /**
     * Id for control the test
     */
    public static Long idTest = Calendar.getInstance().getTimeInMillis();
    /**
     * Millisencods for minute.
     */
    private final static int MINUTE = 6000;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Calendar inicio = Calendar.getInstance();

        LOG.info("Start with test, the id for this test is: " + idTest.toString());

        try {
            AEMConnector aemConnector = new AEMConnector();

            aemConnector.openSite(LoadProperties.ANALYSIS_URL);
            //aemConnector.openSiteAutomaticDesignMode(LoadProperties.ANALYSIS_URL);
            //aemConnector.openSiteInDesignMode(LoadProperties.ANALYSIS_URL);
            
            aemConnector.clickByID("cq-gen22");
            aemConnector.writeByID("ext-comp-1078", "/content/lifeway");
            
            Thread.sleep(3000);
            
            aemConnector.clickByID("cq-gen192");
            aemConnector.clickByID("cq-gen179");

            aemConnector.finalizeAemConector();
        } catch (AEMException | InterruptedException ex) {
            System.out.println(ex.getMessage());
        }

        Calendar fin = Calendar.getInstance();
        Long timeInMillis = fin.getTimeInMillis() - inicio.getTimeInMillis();

        LOG.info("End the test " + idTest.toString());
        LOG.info("Duration of test: " + (timeInMillis / Main.MINUTE) + "minutes");
    }
}
