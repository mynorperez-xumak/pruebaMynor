package feature;

import com.xumak.lifeway.AEMConnector;
import com.xumak.lifeway.exception.AEMException;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/"})
public class LifeWay {

    public static final String EMPTY = "";
    public static final String CHECK_OFF = "off";

    public static final AEMConnector AEM = new AEMConnector();
    public static final String UR_PAGE_BASE = "http://qaqc-deb.xumak.gt:4502/editor.html/content/Testing1.html";
    public static final String URL_PAGE_TEST = "http://qaqc-deb.xumak.gt:4502/editor.html/content/Testing.html";
    public static final String URL_SITES = "http://qaqc-deb.xumak.gt:4502/sites.html/content";

    public static final String UR_PRODUCT = "http://10.0.12.155:4503/content/lifeway/ProductQA.html";
    public static final String URL_PRODUCT_QA = "http://10.0.12.155:4503/content/lifeway/ProductQA.005678143.2016-WIN.html";
    public static final String URL_COMPANION_PRODUCTS = "http://10.0.12.155:4503/content/lifeway/ProductQA.html";
    public static final String URL_PRODUCT_QA_OK = "http://10.0.12.155:4503/content/lifeway/ProductQA.001148411.html";
    public static final String URL_PRODUCT_QA_UNAVALIABLE = "http://10.0.12.155:4503/content/lifeway/ProductQA.001148412.html";
    public static final String URL_PRODUCT_QA_STORE = "http://10.0.12.155:4503/content/lifeway/ProductQA.001148413.html";
    public static final String URL_PRODUCT_QA_NO_PRICE = "http://10.0.12.155:4503/content/lifeway/ProductQA.001148414.html";
    public static final String URL_BANNER_AD = "http://10.0.12.155:4503/content/lifeway/QABannerAdd.html";
    public static final String URL_ARTICLE_PAGE = "http://10.0.12.155:4503/content/lifeway/QAArticlePage.html";
    public static final String URL_MESSAGE_BOX = "http://10.0.12.155:4503/content/lifeway/QAMessageBox.html";
    public static final String URL_HOME_PAGE = "http://10.0.12.155:4503/content/lifeway/HomePageQA.html";
    public static final String UR_TEXT_IMAGE_WIDE = "http://10.0.12.155:4503/content/lifeway/lw-203.html";
    public static final String URL_ARTICLE_SUMMARY = "http://10.0.12.155:4503/content/lifeway/article-summary.html";
    public static final String URL_SIMPLE_BUTTON = "http://10.0.12.155:4503/content/lifeway/QAONLY-TWOCOLUMN.html";
    public static final String URL_ARTICLE_TEMPLATE = "http://10.0.12.155:4503/content/lifeway/ArticleTemplate-QAONLY.html";
    public static final String URL_ARTICLE_RELATED = "http://10.0.12.155:4503/content/lifeway/QAArticlePage1.html";
    public static final String URL_HOMEPAGE_QA = "http://10.0.12.155:4503/content/lifeway/HOMEPAGE-QAONLY.html";
    public static final String URL_HOMEPAGE_QAONLY = "http://qa.xms.lifeway.com:4503/content/lifeway/HOMEPAGE-QAONLY.html";
    public static final String URL_HOME_PAGE_QA_XMS = "http://qa.xms.lifeway.com:4503/content/lifeway/HomePageQA.html";
    public static final String URL_CHECKOUT_QA = "http://qa.xms.lifeway.com:4503/content/lifeway/CHECKOUT_QAONLY.html"; 
    public static final String URL_CHECKOUT_QAONLY ="http://qa.xms.lifeway.com/content/lifeway/CHECKOUT_QAONLY.html";    
    //aemConnector.finalizeAemConector();

    //<editor-fold defaultstate="collapsed" desc="Generic functions">
    public static void openUrl(final String url) throws Exception {
        try {
            AEM.openSite(url);
        } catch (AEMException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void selectComponent(String component) throws AEMException {
        AEM.clickByXpath(component);
    }

    public static void clickConfiguration() throws AEMException {
        AEM.clickByXpath("//*[@id=\"EditableToolbar\"]/button[1]");
    }

    public static void clickOk() throws InterruptedException, AEMException {
        Thread.sleep(3000);
        AEM.clickByXpath("/html/body/form/nav/div/button[4]");
        Thread.sleep(3000);
    }
    //</editor-fold>
}
