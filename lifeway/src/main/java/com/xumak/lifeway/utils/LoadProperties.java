package com.xumak.lifeway.utils;

import com.xumak.lifeway.exception.MessagesProperties;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public final class LoadProperties {

    /**
     * Message when the property is not found
     */
    private final static String NOT_FOUND = "The property was not found.";
    /**
     * Empty String.
     */
    private final static String EMPTY = "";

    /**
     * Bundle of the configuration generals.
     */
    private static final ResourceBundle RES_BUND_GEN = ResourceBundle.getBundle(
            UtilProperties.RESOURCE_GENERAL);
    /**
     *
     */
    private static final ResourceBundle RES_BUND_EXC = ResourceBundle.getBundle(
            MessagesProperties.RESOURCE_EXCEPTION);

    /**
     * Value in the AEMBTN_SIGIN property.
     */
    public final static String TITLE_SIGIN = LoadProperties.getGeneralsProperties(UtilProperties.AEMTITLE_SIGIN);
    /**
     * Value in the ANALYSISURL property.
     */
    public final static String ANALYSIS_URL = LoadProperties.getGeneralsProperties(UtilProperties.ANALYSISURL);
    /**
     * Value in the AEMTXT_USERNAME property.
     */
    public final static String TXT_USER = LoadProperties.getGeneralsProperties(UtilProperties.AEMTXT_USERNAME);
    /**
     * Value in the AEMTXT_PASSWORD property.
     */
    public final static String TXT_PASSWORD = LoadProperties.getGeneralsProperties(UtilProperties.AEMTXT_PASSWORD);
    /**
     * Value in the AEMTXT_ID_USERNAME property.
     */
    public final static String TXT_ID_USER = LoadProperties.getGeneralsProperties(UtilProperties.AEMTXT_ID_USERNAME);
    /**
     * Value in the AEMTXT_ID_PASSWORD property.
     */
    public final static String TXT_ID_PASSWORD = LoadProperties.getGeneralsProperties(UtilProperties.AEMTXT_ID_PASSWORD);
    /**
     * Value in the AEMBTN_XPATH_SIGIN property.
     */
    public final static String BTN_ID_SIGIN = LoadProperties.getGeneralsProperties(UtilProperties.AEMBTN_ID_SIGIN);
    /**
     * Value in the AEMTXT_XPATH_ALERT_LOGIN property.
     */
    public final static String TXT_XPATH_ALERT_LOGIN = LoadProperties.getGeneralsProperties(UtilProperties.AEMTXT_XPATH_ALERT_LOGIN);
    /**
     * Value in the AEMTIME_WAIT property.
     */
    public final static Integer TIME_WAIT = Integer.parseInt(LoadProperties.getGeneralsProperties(UtilProperties.AEMTIME_WAIT));
    /**
     * Value in the AEMBTN_ID_DESIGN_MODE property.
     */
    public final static String DESIGN_MODE = LoadProperties.getGeneralsProperties(UtilProperties.AEMBTN_ID_DESIGN_MODE);
    /**
     * Value in the AEMIFR_ID_CQ property.
     */
    public final static String IFR_ID_CQ = LoadProperties.getGeneralsProperties(UtilProperties.AEMIFR_ID_CQ);
    /**
     * Value in the AEMLOAD_READY_STATE property.
     */
    public final static String LOAD_READY_STATE = LoadProperties.getGeneralsProperties(UtilProperties.AEMLOAD_READY_STATE);
    /**
     * Value in the BROWSERWINDOWS_MAXIMIZE property.
     */
    public final static Boolean BROWSER_WINDOWS_MAXIMIZE = Boolean.parseBoolean(LoadProperties.getGeneralsProperties(UtilProperties.BROWSERWINDOWS_MAXIMIZE));
    /**
     * Value in the FOLDERGOOGLE_DRIVE property.
     */
    public final static String FOLDER_GOOGLE_DRIVE = LoadProperties.getGeneralsProperties(UtilProperties.FOLDERGOOGLE_DRIVE);
    /**
     * Value in the FOLDERGOOGLE_DRIVE_TEMPORAL property.
     */
    public final static String FOLDER_GOOGLE_DRIVE_TEMPORAL = LoadProperties.getGeneralsProperties(UtilProperties.FOLDERGOOGLE_DRIVE_TEMPORAL);

    /**
     * Value in the EXCEPTIONLOGIN_FAILED property.
     */
    public final static String MESSAGE_LOGIN_FAILD = LoadProperties.getExceptionProperties(MessagesProperties.EXCEPTIONLOGIN_FAILED);
    /**
     * Value in the EXCEPTION_NOT_FOUND_ELEMENT property.
     */
    public final static String MESSAGE_NOT_FOUND_ELEMENT = LoadProperties.getExceptionProperties(MessagesProperties.EXCEPTION_NOT_FOUND_ELEMENT);
    /**
     * Value in the EXCEPTION_NOT_COULD_OPEN_WD property.
     */
    public final static String MESSAGE_NOT_COULD_OPEN_WD = LoadProperties.getExceptionProperties(MessagesProperties.EXCEPTION_NOT_COULD_OPEN_WD);

    /**
     * Return the value for the name of the key.
     *
     * @param name of the key
     * @return the value found, {@link #EMPTY} when the key is not found
     */
    private static String getGeneralsProperties(final String name) {
        try {
            return RES_BUND_GEN.getString(name);
        } catch (NullPointerException | MissingResourceException | ClassCastException exception) {
            System.out.println(LoadProperties.NOT_FOUND);
            return LoadProperties.EMPTY;
        }
    }

    /**
     * Return the value for the name of the key.
     *
     * @param name of the key
     * @return the value found, {@link #EMPTY} when the key is not found
     */
    private static String getExceptionProperties(final String name) {
        try {
            return RES_BUND_EXC.getString(name);
        } catch (NullPointerException | MissingResourceException | ClassCastException exception) {
            System.out.println(LoadProperties.NOT_FOUND);
            return LoadProperties.EMPTY;
        }
    }

    /**
     * Default constructor.
     */
    private LoadProperties() {
        //Utility class
    }
}
