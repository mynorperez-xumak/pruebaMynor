package com.xumak.lifeway;

import com.xumak.lifeway.exception.AEMException;
import com.xumak.lifeway.utils.LoadProperties;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

//<editor-fold defaultstate="collapsed" desc="Functionality">
public class AEMConnector {

    /**
     * This is for write the analysis result with log4j.
     */
    private static final Logger LOG = Logger.getLogger(AEMConnector.class.getName());

    /**
     * The instance for the webdriver.
     */
    private WebDriver driver;
    /**
     * Have the status for search each element more one time.
     */
    private boolean aemElementSafe;
    /**
     *
     */
    private String readyIframe = "return document.readyState";

    /**
     * Constructor Default.
     */
    public AEMConnector() {
        aemElementSafe = true;

        //DesiredCapabilities capabilities = new DesiredCapabilities();
//</editor-fold>
        //capabilities.setBrowserName(BrowserType.FIREFOX);
        //capabilities.setPlatform(Platform.MAC);
//<editor-fold defaultstate="collapsed" desc="Functionality">

            //driver = new RemoteWebDriver(new URL("http://qaqc-deb.xumak.gt:4444/wd/hub"), capabilities);
        //driver = new RemoteWebDriver(new URL("http://127.0.0.1:4444/wd/hub"), capabilities);
        driver = new FirefoxDriver();

        if (LoadProperties.BROWSER_WINDOWS_MAXIMIZE) {
            driver.manage().window().maximize();
        }
    }

    /**
     * Open the url for analysis.
     *
     * @param url to analysis
     * @throws AEMException
     */
    public void openSite(final String url) throws AEMException {
        openSpecificSite(url);
    }

    /**
     * Open the url for analysis.
     *
     * @param url to analysis
     * @throws AEMException
     */
    public void openSiteAutomaticDesignMode(final String url) throws AEMException {
        openSpecificSite(url);
        changeIFrame();
    }

    /**
     *
     * @param url
     * @throws AEMException
     */
    public void openSiteInDesignMode(final String url) throws AEMException {
        openSpecificSite(url);
        changeDesignMode();
    }

    public void finalizeAemConector() {
        driver.close();
        driver.quit();
    }

    /**
     *
     * @param url
     * @throws AEMException
     */
    private void openSpecificSite(final String url) throws AEMException {
        try {
            driver.get(url);
            waitWhileLoad();
        } catch (Exception e) {
            System.out.println("Error exception: " + e.getMessage());
            System.out.println("Error exception: " + e.getLocalizedMessage());
            LOG.info("Not open the ulr: " + url + ".  " + LoadProperties.MESSAGE_NOT_COULD_OPEN_WD);
            throw new AEMException(LoadProperties.MESSAGE_NOT_COULD_OPEN_WD);
        }

        if (driver.getTitle().equals(LoadProperties.TITLE_SIGIN)) {
            logging(LoadProperties.TXT_USER, LoadProperties.TXT_PASSWORD);
        }

        if (!driver.getCurrentUrl().equals(url)) {
            LOG.info("Error unexpected.  " + LoadProperties.MESSAGE_LOGIN_FAILD);
            throw new AEMException(LoadProperties.MESSAGE_LOGIN_FAILD);
        }
    }

    /**
     * Method for logging when the site need authentication.
     *
     * @param username username for the site
     * @param password password for the site
     * @throws AEMException
     */
    private void logging(final String username, final String password) throws AEMException {
        writeByID(LoadProperties.TXT_ID_USER, username);
        writeByID(LoadProperties.TXT_ID_PASSWORD, password);
        clickByID(LoadProperties.BTN_ID_SIGIN);

        waitWhileLoad();

        if (driver.getTitle().equals(LoadProperties.TITLE_SIGIN)) {
            String messageRead = readByClass(LoadProperties.TXT_XPATH_ALERT_LOGIN);

            if (messageRead.isEmpty()) {
                LOG.info("Message is empty.  " + LoadProperties.MESSAGE_LOGIN_FAILD);
                throw new AEMException(LoadProperties.MESSAGE_LOGIN_FAILD);
            }

            LOG.info(messageRead);
            throw new AEMException(messageRead);
        }

        //readyIframe = "return document.getElementById(\"" + LoadProperties.IFR_ID_CQ
        //        + "\").contentWindow.document.readyState";
        //waitWhileLoad();
    }

    //<editor-fold defaultstate="collapsed" desc="Section for write in element">
    /**
     * Write text over one element by id.
     *
     * @param id the element
     * @param value to write in the element id
     * @throws AEMException
     */
    public void writeByID(final String id, final String value) throws AEMException {
        write(By.id(id), value);
    }

    /* Write text over one element by id.
     *
     * @param id the element
     * @param value to write in the element id
     * @throws AEMException
     */
    public void writeByXpath(final String id, final String value) throws AEMException {
        write(By.xpath(id), value);
    }

    /**
     * Generic function for write in the elements by a object By.
     *
     * @param elementBy Object when the resource id
     * @param value to write in the element
     * @throws AEMException
     */
    private void write(final By elementBy, final String value) throws AEMException {
        try {
            LOG.info("Write Elemento=" + elementBy);
            WebElement inpUserName = driver.findElement(elementBy);
            inpUserName.sendKeys(value);
        } catch (Exception e) {
            if (aemElementSafe) {
                LOG.info("Research Element.  Elemento=" + elementBy);
                pause();
                write(elementBy, value);
            } else {
                LOG.info(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT + ".  Element=" + elementBy);
                throw new AEMException(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT);
            }
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Section for clean element">
    public void cleanByID(final String id) throws AEMException {
        clean(By.id(id));
    }

    public void cleanByXpath(final String xpath) throws AEMException {
        clean(By.xpath(xpath));
    }

    private void clean(final By elementBy) throws AEMException {
        try {
            LOG.info("Clean Elemento=" + elementBy);
            driver.findElement(elementBy).clear();
        } catch (Exception e) {
            if (aemElementSafe) {
                LOG.info("Research Element.  Elemento=" + elementBy);
                pause();
                clean(elementBy);
            } else {
                LOG.info(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT + ".  Element=" + elementBy);
                throw new AEMException(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT);
            }
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Section for get value">
    public String getValueById(final String id) throws AEMException {
        return getValue(By.id(id));
    }

    public String getValueByXpath(final String xpath) throws AEMException {
        return getValue(By.xpath(xpath));
    }

    private String getValue(final By elementBy) throws AEMException {
        String returnText = "";
        try {
            returnText = driver.findElement(elementBy).getAttribute("value");
            LOG.info("Read Elemento=" + elementBy);
        } catch (Exception e) {
            if (aemElementSafe) {
                LOG.info("Research Element.  Elemento=" + elementBy);
                pause();
                getValue(elementBy);
            } else {
                LOG.info(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT + ".  Element=" + elementBy);
                throw new AEMException(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT);
            }
        }

        return returnText;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Section for get select status element">
    public boolean isSelectedById(final String id) throws AEMException {
        return isSelected(By.id(id));
    }

    private boolean isSelected(final By elementBy) throws AEMException {
        boolean retorno = false;

        try {
            retorno = driver.findElement(elementBy).isSelected();
            LOG.info("Read Elemento=" + elementBy);
        } catch (Exception e) {
            if (aemElementSafe) {
                LOG.info("Research Element.  Elemento=" + elementBy);
                pause();
                retorno = isSelected(elementBy);
            } else {
                LOG.info(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT + ".  Element=" + elementBy);
                throw new AEMException(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT);
            }
        }

        return retorno;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Section for read in element">
    public String readById(final String id) throws AEMException {
        return read(By.id(id));
    }

    /**
     * Read text over one element by classname.
     *
     * @param className the element
     * @return the text of the element
     * @throws AEMException
     */
    public String readByClass(final String className) throws AEMException {
        return read(By.className(className));
    }

    public String readByXpath(final String xPath) throws AEMException {
        return read(By.xpath(xPath));
    }

    /**
     * Generic function for read in the element by a object By
     *
     * @param elementBy Object when the resource id
     * @return the text of the element
     * @throws AEMException
     */
    private String read(final By elementBy) throws AEMException {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            LOG.info(ex.getMessage());
        }

        String returnText = "";
        try {
            returnText = driver.findElement(elementBy).getText();
            LOG.info("Read Elemento=" + elementBy);
        } catch (Exception e) {
            if (aemElementSafe) {
                LOG.info("Research Element.  Elemento=" + elementBy);
                pause();
                read(elementBy);
            } else {
                LOG.info(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT + ".  Element=" + elementBy);
                throw new AEMException(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT);
            }
        }

        return returnText;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Section for click in element">
    /**
     * Click over one element by id.
     *
     * @param id id the element
     * @throws AEMException
     */
    public void clickByID(final String id) throws AEMException {
        click(By.id(id));
    }

    /**
     * Click over one element by xpath.
     *
     * @param xpath xpath the element
     * @throws AEMException
     */
    public void clickByXpath(final String xpath) throws AEMException {
        click(By.xpath(xpath));
    }

    /**
     * Generic function for click in the element by a object By
     *
     * @param elementBy Object when the resource id
     * @throws AEMException
     */
    private void click(final By elementBy) throws AEMException {
        try {
            LOG.info("Click Elemento=" + elementBy);
            driver.findElement(elementBy).click();
            //waitWhileLoad();
        } catch (Exception e) {
            if (aemElementSafe) {
                LOG.info("Research Element.  Elemento=" + elementBy);
                pause();
                click(elementBy);
            } else {
                LOG.info(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT + ".  Element=" + elementBy);
                throw new AEMException(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT);
            }
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Section fro select dropdown">
    public void selectDropDownById(final String id, final String visibleText) throws AEMException {
        selectDropDown(By.id(id), visibleText);
    }

    public void selectDropDownByXpath(final String xpath, final String visibleText) throws AEMException {
        selectDropDown(By.xpath(xpath), visibleText);
    }

    private void selectDropDown(final By elementBy, final String visibleText) throws AEMException {
        try {
            LOG.info("Click Elemento=" + elementBy);
            Select dropdown = new Select(driver.findElement(elementBy));
            dropdown.selectByVisibleText(visibleText);
            //waitWhileLoad();
        } catch (Exception e) {
            if (aemElementSafe) {
                LOG.info("Research Element.  Elemento=" + elementBy);
                pause();
                selectDropDown(elementBy, visibleText);
            } else {
                LOG.info(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT + ".  Element=" + elementBy);
                throw new AEMException(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT);
            }
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Section for Tab in element">
    public void tabById(final String id) throws AEMException {
        tab(By.id(id));
    }

    public void tabByXpath(final String xPath) throws AEMException {
        tab(By.xpath(xPath));
    }

    private void tab(final By elementBy) throws AEMException {
        try {
            LOG.info("Tab Elemento=" + elementBy);
            driver.findElement(elementBy).sendKeys(Keys.TAB);
            //waitWhileLoad();
        } catch (Exception e) {
            if (aemElementSafe) {
                LOG.info("Research Element.  Elemento=" + elementBy);
                pause();
                tab(elementBy);
            } else {
                LOG.info(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT + ".  Element=" + elementBy);
                throw new AEMException(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT);
            }
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Section for Enter in element">
    public void enterById(final String id) throws AEMException {
        enter(By.id(id));
    }

    public void enterByXpath(final String xPath) throws AEMException {
        enter(By.xpath(xPath));
    }

    private void enter(final By elementBy) throws AEMException {
        try {
            LOG.info("Enter Elemento=" + elementBy);
            driver.findElement(elementBy).sendKeys(Keys.ENTER);
            //waitWhileLoad();
        } catch (Exception e) {
            if (aemElementSafe) {
                LOG.info("Research Element.  Elemento=" + elementBy);
                pause();
                enter(elementBy);
            } else {
                LOG.info(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT + ".  Element=" + elementBy);
                throw new AEMException(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT);
            }
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Section for Drag&Drop in element">
    public void dragDropById(final String source, final String target) throws AEMException {
        dragDrop(By.id(source), By.id(target));
    }

    public void dragDropByXpath(final String source, final String target) throws AEMException {
        dragDrop(By.xpath(source), By.xpath(target));
    }

    private void dragDrop(final By source, final By target) throws AEMException {
        try {
            LOG.info("DragDrop Elemento=" + source);

            executeScript("document.querySelector(\"#coral-92 > div > div:nth-child(1) > span.cq-droptarget.coral-FileUpload.coral-Form-field.coral-FileUpload--dropSupport.is-disabled.cq-FileUpload\").className+=\" is-active\"");

            (new Actions(driver)).dragAndDrop(driver.findElement(source),
                    driver.findElement(target)).build().perform();

        } catch (Exception e) {
            if (aemElementSafe) {
                LOG.info("Research Element.  Elemento=" + source);
                pause();
                dragDrop(source, target);
            } else {
                LOG.info(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT + ".  Element=" + source);
                throw new AEMException(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT);
            }
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Section for Exist element">
    public boolean existsElementByID(final String id) throws AEMException {
        return existsElement(By.id(id));
    }

    public boolean existsElementByXpath(final String xPath) throws AEMException {
        return existsElement(By.xpath(xPath));
    }

    private boolean existsElement(final By byElement) throws AEMException {

        try {
            LOG.info("existsElement Elemento=" + byElement);

            WebElement webElement = driver.findElement(byElement);

            if (webElement != null) {
                return true;
            }
        } catch (Exception e) {
            if (aemElementSafe) {
                LOG.info("Research Element.  Elemento=" + byElement);
                pause();
                existsElement(byElement);
            } else {
                LOG.info(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT + ".  Element=" + byElement);
                throw new AEMException(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT);
            }
        }

        return false;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Section for IsPropertie in element">
    public boolean isPropertieID(final String id, final String propertie) throws AEMException {
        return isPropertie(By.id(id), propertie);
    }

    public boolean isPropertieXpath(final String xPath, final String propertie) throws AEMException {
        return isPropertie(By.xpath(xPath), propertie);
    }

    private boolean isPropertie(final By byElement, final String propertie) throws AEMException {
        try {
            LOG.info("IsPropertie Elemento=" + byElement);

            WebElement webElement = driver.findElement(byElement);

            if (webElement != null) {
                webElement.getAttribute(propertie);
                return true;
            }
        } catch (Exception e) {
            if (aemElementSafe) {
                LOG.info("Research Element.  Elemento=" + byElement);
                pause();
                isPropertie(byElement, propertie);
            } else {
                LOG.info(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT + ".  Element=" + byElement);
                throw new AEMException(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT);
            }
        }

        return false;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Section for GetPropertie in element">
    public String getPropertieID(final String id, final String propertie) throws AEMException {
        return getPropertie(By.id(id), propertie);
    }

    public String getPropertieXpath(final String xPath, final String propertie) throws AEMException {
        return getPropertie(By.xpath(xPath), propertie);
    }

    private String getPropertie(final By byElement, final String propertie) throws AEMException {
        try {
            LOG.info("GetPropertie Elemento=" + byElement);

            WebElement webElement = driver.findElement(byElement);

            if (webElement != null) {
                return webElement.getAttribute(propertie);
            }
        } catch (Exception e) {
            if (aemElementSafe) {
                LOG.info("Research Element.  Elemento=" + byElement);
                pause();
                getPropertie(byElement, propertie);
            } else {
                LOG.info(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT + ".  Element=" + byElement);
                throw new AEMException(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT);
            }
        }

        return "";
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Section for GetStyleValue in element">
    public String getCssValueID(final String id, final String css) throws AEMException {
        return getCssValue(By.id(id), css);
    }

    public String getCssValueXpath(final String xPath, final String css) throws AEMException {
        return getCssValue(By.xpath(xPath), css);
    }

    private String getCssValue(final By byElement, final String css) throws AEMException {
        try {
            LOG.info("GetPropertie Elemento=" + byElement);

            WebElement webElement = driver.findElement(byElement);

            if (webElement != null) {
                return webElement.getCssValue(css);
            }
        } catch (Exception e) {
            if (aemElementSafe) {
                LOG.info("Research Element.  Elemento=" + byElement);
                pause();
                getCssValue(byElement, css);
            } else {
                LOG.info(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT + ".  Element=" + byElement);
                throw new AEMException(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT);
            }
        }

        return "";
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Section for mode preview">
    public void inPreviewMode() throws AEMException {
        clickByXpath("//*[@id=\"Content\"]/div[1]/nav/div[3]/button[2]");
        WebElement frame = driver.findElement(By.id("ContentFrame"));
        driver.switchTo().frame(frame);
    }

    public void inEditMode() throws AEMException, InterruptedException {
        //driver.switchTo().parentFrame();
        driver.switchTo().window(driver.getWindowHandle());
        clickByXpath("//*[@id=\"Content\"]/div[1]/nav/div[3]/button[1]");
        Thread.sleep(3000);
        clickByXpath("//*[@id=\"Content\"]/div[1]/nav/div[1]/button[1]");
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Section for links">
    public boolean compareUpperFooter(final String tittle, final String link) throws AEMException {
        By byElement = By.xpath("//div[contains(@class, 'upperfooter')]/div/ul");

        try {
            List<WebElement> especific = driver.findElements(byElement);
            for (WebElement each : especific) {
                if (each.getText().contains(tittle) && each.getText().contains(link)) {
                    return true;
                }
            }

        } catch (Exception e) {
            LOG.info(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT + ".  Element=" + e.getMessage());
            throw new AEMException(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT);
        }

        return false;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Section for compare if the element is visible">
    public boolean isVisibleById(final String id) throws AEMException {
        return isVisible(By.id(id));
    }

    public boolean isVisibleXpath(final String xpath) throws AEMException {
        return isVisible(By.xpath(xpath));
    }

    private boolean isVisible(final By elementBy) throws AEMException {
        try {
            LOG.info("Enter Elemento=" + elementBy);
            return driver.findElement(elementBy).isDisplayed();
        } catch (Exception e) {
            LOG.info(LoadProperties.MESSAGE_NOT_FOUND_ELEMENT + ".  Element=" + elementBy);
            return false;
        }
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Click in recaptcha">
    public void clickRecaptchaXpath(final String xpath) throws AEMException {
        WebElement frame = driver.findElement(By.xpath(xpath));
        driver.switchTo().frame(frame);

        clickByID("recaptcha-anchor");

        driver.switchTo().window(driver.getWindowHandle());
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Section get title">
    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public String getUrl(final int indice) {
        Object[] tabs = driver.getWindowHandles().toArray();

        driver.switchTo().window(tabs[indice].toString());

        String url = driver.getCurrentUrl();
        driver.switchTo().window(tabs[indice - 1].toString());

        return url;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Section GetTabs">
    public Integer getTabs() {
        return driver.getWindowHandles().size();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Section to get before content">
    public String getBeforeContent(String selector) {
        return getPseudoContent(selector, "::before");
    }

    private String getPseudoContent(String selector, String pseudo) {
        String content = "";
        String script = "return window.getComputedStyle(document.querySelector(\'" + selector + "\'), \'" + pseudo + "\').getPropertyValue(\'content\')";

        try {
            content = ((JavascriptExecutor) driver).executeScript(script).toString();
        } catch (Exception e) {
            LOG.info("Cath: " + e.getMessage());
        }

        return content;
    }
    //</editor-fold>

    /**
     * Set {@link #aemElementSafe}.
     *
     * @param aemElementSafe The {@link #aemElementSafe} to set
     */
    public void setAemElementSafe(boolean aemElementSafe) {
        this.aemElementSafe = aemElementSafe;
    }

    /**
     * Making the pause.
     */
    private void pause() {
        try {
            Thread.sleep(LoadProperties.TIME_WAIT);
        } catch (InterruptedException e) {
            LOG.info("Error in pause: " + e.getMessage());
        }
    }

    /**
     *
     */
    private void waitWhileLoad() {
        LOG.info("-----------------------------------------------------------");
        LOG.info("Expression: " + readyIframe);
        String status = "";

        try {
            Thread.sleep(LoadProperties.TIME_WAIT);
            status = ((JavascriptExecutor) driver).executeScript(readyIframe).toString();
        } catch (Exception e) {
            LOG.info("Cath: " + e.getMessage());
        }

        LOG.info("DocumentState: " + status);
        if (status.equals(LoadProperties.LOAD_READY_STATE)) {
            return;
        }

        waitWhileLoad();
    }

    private void executeScript(String script) {
        try {
            ((JavascriptExecutor) driver).executeScript(script).toString();
        } catch (Exception e) {
            LOG.info("Cath: " + e.getMessage());
        }
    }

    /**
     *
     * @throws AEMException
     */
    private void changeDesignMode() throws AEMException {
        clickByID(LoadProperties.DESIGN_MODE);

        waitWhileLoad();
        changeIFrame();
    }

    /**
     *
     */
    private void changeIFrame() {
        setAemElementSafe(true);
        WebElement frame = driver.findElement(By.id(LoadProperties.IFR_ID_CQ));
        driver.switchTo().frame(frame);

        readyIframe = "return parent.document.getElementById(\"" + LoadProperties.IFR_ID_CQ
                + "\").contentWindow.document.readyState";

        waitWhileLoad();
    }
//</editor-fold>
}
