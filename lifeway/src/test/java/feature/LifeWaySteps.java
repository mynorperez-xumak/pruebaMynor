package feature;

import com.xumak.lifeway.exception.AEMException;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class LifeWaySteps {

    private static String originVariant;
    private static String variant;

    private static Integer tabOrigin;
    private static Integer tab;

    private static String hrefOrigin;

    private static String anchorTarget;

    private static String titleOrigin;

    private static final String IMAGE_PATTERN
            = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

    private static final Pattern pattern = Pattern.compile(IMAGE_PATTERN);

    private static Matcher matcher;

    private static void fillFormCorrect() throws AEMException {
        LifeWay.AEM.writeByID("inputTo", "example@qa.com");
        LifeWay.AEM.writeByID("inputMessage", "Testing message content");
        LifeWay.AEM.writeByID("inputYourName", "QA developer");
        LifeWay.AEM.writeByID("inputFrom", "test@qa.com");
    }

    private static boolean isImage(String image) {
        matcher = pattern.matcher(image);
        return matcher.matches();
    }

    //<editor-fold defaultstate="collapsed" desc="US 1193 Buy box to Cart Update">
    //<editor-fold defaultstate="collapsed" desc="Scenario: Visitor add product to cart">
    @Given("^a visitor wants to add an item to his cart$")
    public void a_visitor_wants_to_add_an_item_to_his_cart() throws Throwable {
        startProductQAOK();
    }

    @When("^he clicks on an add to cart button$")
    public void he_clicks_on_an_add_to_cart_button() throws Throwable {
        LifeWay.AEM.clickByID("addToCart");
        Thread.sleep(2000);
    }

    @Then("^an asynchronous call should be made to the API Gateway function to add an item to the current cart$")
    public void an_asynchronous_call_should_be_made_to_the_API_Gateway_function_to_add_an_item_to_the_current_cart() throws Throwable {

    }

    @Then("^the cart current state should be received as a response$")
    public void the_cart_current_state_should_be_received_as_a_response() throws Throwable {
    }

    @Then("^the cart summary modal window should fade in showing the cart summary$")
    public void the_cart_summary_modal_window_should_fade_in_showing_the_cart_summary() throws Throwable {
        String display = "block";
        String displayValue = LifeWay.AEM.getCssValueID("buyBoxModal", "display");

        assertEquals(display, displayValue);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: The product is only buyable in stores">
    @Given("^a product that is only buyable in stores$")
    public void a_product_that_is_only_buyable_in_stores() throws Throwable {
        LifeWay.openUrl(LifeWay.URL_PRODUCT_QA_STORE);
    }

    @When("^the page is loaded$")
    public void the_page_is_loaded() throws Throwable {
    }

    @Then("^the add to cart button should be disabled$")
    public void the_add_to_cart_button_should_be_disabled() throws Throwable {
        boolean disabled = LifeWay.AEM.isPropertieXpath("//*[@id=\"OrderItemAddForm\"]/button", "disabled");

        assertTrue(disabled);
    }

    @Then("^showing a gray color$")
    public void showing_a_gray_color() throws Throwable {
        String classBlock = "btn btn-default btn-block";
        String classValue = LifeWay.AEM.getPropertieXpath("//*[@id=\"OrderItemAddForm\"]/button", "class");
        assertEquals(classBlock, classValue);
    }

    @Then("^the availability message should be present saying that is only available on stores$")
    public void the_availability_message_should_be_present_saying_that_is_only_available_on_stores() throws Throwable {
        String message = "This product is available via stores only.";
        String messageValue = LifeWay.AEM.readByXpath("//*[@id=\"buy-box-mobile\"]/div/div[2]/p");

        assertTrue(messageValue.contains(message));
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: The product does not have a price">
    @Given("^a product without price$")
    public void a_product_without_price() throws Throwable {
        LifeWay.openUrl(LifeWay.URL_PRODUCT_QA_NO_PRICE);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: The product is unavailable">
    @Given("^an unavailable product$")
    public void an_unavailable_product() throws Throwable {
        LifeWay.openUrl(LifeWay.URL_PRODUCT_QA_UNAVALIABLE);
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 141 Product Media">
    //<editor-fold defaultstate="collapsed" desc="Scenario: Product media is obtained">
    @Given("^the visitor has opened a product page$")
    public void the_visitor_has_opened_a_product_page() throws Throwable {
        startProductQA();
    }

    @Given("^the product has media related to id$")
    public void the_product_has_media_related_to_id() throws Throwable {

    }

    @Given("^the videos URLs contained on the product data are from brightcove$")
    public void the_videos_URLs_contained_on_the_product_data_are_from_brightcove() throws Throwable {
    }

    @Given("^the audio URLs contained on the product data are from scene(\\d+)$")
    public void the_audio_URLs_contained_on_the_product_data_are_from_scene(int arg1) throws Throwable {
    }

    @When("^the video player renders the videos list$")
    public void the_video_player_renders_the_videos_list() throws Throwable {
    }

    @When("^the audio player renders the audio files list$")
    public void the_audio_player_renders_the_audio_files_list() throws Throwable {
    }

    @Then("^the visitor can play the videos on the video player$")
    public void the_visitor_can_play_the_videos_on_the_video_player() throws Throwable {
        LifeWay.AEM.clickByID("myExperience928199562001");
        Thread.sleep(6000);
        LifeWay.AEM.clickByID("myExperience928199562001");
    }

    @Then("^the visitor can play the audio files on the audio player$")
    public void the_visitor_can_play_the_audio_files_on_the_audio_player() throws Throwable {
        String initCurrentTime = LifeWay.AEM.readByXpath("//*[@id=\"mep_0\"]/div/div[3]/div[4]/span");
        LifeWay.AEM.clickByXpath("//*[@id=\"mep_0\"]/div/div[3]/div[2]/button");

        Thread.sleep(3000);

        String currentTime = LifeWay.AEM.readByXpath("//*[@id=\"mep_0\"]/div/div[3]/div[4]/span");
        LifeWay.AEM.clickByXpath("//*[@id=\"mep_0\"]/div/div[3]/div[2]/button");

        assertFalse(initCurrentTime.equals(currentTime));
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 162 XK Social Buttons">
    //<editor-fold defaultstate="collapsed" desc="Scenario: Visitor wants to see social buttons">
    @Given("^the visitor wants to see social buttons$")
    public void the_visitor_wants_to_see_social_buttons() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_ARTICLE_TEMPLATE);
    }

    @When("^he navigates to article template$")
    public void he_navigates_to_article_template() throws Throwable {
    }

    @Then("^the visitor will be able to the social buttons component displayed$")
    public void the_visitor_will_be_able_to_the_social_buttons_component_displayed() throws Throwable {
        boolean isVisible = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[3]/div[2]/div");
        assertTrue(isVisible);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: the visitor opens the email modal">
    @Given("^the visitor opens the email modal$")
    public void the_visitor_opens_the_email_modal() throws Throwable {
        titleOrigin = LifeWay.AEM.readByXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[1]/h1");
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[3]/div[2]/div/ul/li[4]/a");
    }

    @When("^the modal is rendered$")
    public void the_modal_is_rendered() throws Throwable {
        Thread.sleep(5000);
        boolean isVisible = LifeWay.AEM.isVisibleById("emailModal");
        assertTrue(isVisible);
    }

    @Then("^the page title should be shown instead the product name$")
    public void the_page_title_should_be_shown_instead_the_product_name() throws Throwable {
        String productName = LifeWay.AEM.readByXpath("//*[@id=\"emailModalLabel\"]/span");
        assertEquals(titleOrigin, productName);
    }
    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 166 Product Details">
    //<editor-fold defaultstate="collapsed" desc="Scenario: Visitor interacts with image/video main thumbnail section">

    @Given("^the visitor is on the product page$")
    public void the_visitor_is_on_the_product_page() throws Throwable {
    }

    @When("^the visitor clicks on the main image$")
    public void the_visitor_clicks_on_the_main_image() throws Throwable {
        LifeWay.AEM.clickByXpath("//*[@id=\"product-images\"]/div/div[2]/div[2]/div/div/img");
    }

    @Then("^if the thumbnail is a simple image, a modal section with the clicked image at full size is displayed$")
    public void if_the_thumbnail_is_a_simple_image_a_modal_section_with_the_clicked_image_at_full_size_is_displayed() throws Throwable {
        LifeWay.AEM.clickByXpath("//*[@id=\"product-images\"]/div/div[1]/a");

        String display = "block";
        Thread.sleep(2000);
        String displayValue = LifeWay.AEM.getCssValueID("enlarge-product-image", "display");

        LifeWay.AEM.clickByXpath("//*[@id=\"enlarge-product-image\"]/div/div/div[1]/button");

        assertEquals(display, displayValue);
    }

    @Then("^if the thumbnail is a video, the modal section displays the video player$")
    public void if_the_thumbnail_is_a_video_the_modal_section_displays_the_video_player() throws Throwable {
        LifeWay.AEM.clickByXpath("//*[@id=\"product-images\"]/div/div[2]/div[4]/div");

        String display = "block";
        Thread.sleep(2000);
        String displayValue = LifeWay.AEM.getCssValueID("product-video-1234", "display");

        LifeWay.AEM.clickByXpath("//*[@id=\"product-video-1234\"]/div/div/div[1]/button");

        assertEquals(display, displayValue);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: visitor clicks on an author/contributor link">
    @When("^the visitor clicks on the name of a contributor$")
    public void the_visitor_clicks_on_the_name_of_a_contributor() throws Throwable {
        LifeWay.AEM.clickByXpath("//*[@id=\"product-details\"]/p[1]/a[1]");
    }

    @Then("^is redirected to the contributor page$")
    public void is_redirected_to_the_contributor_page() throws Throwable {
        String url = "http://10.0.12.155:4503/content/lifeway/ProductQA.005678143.2016-WIN.html";
        String urlValue = LifeWay.AEM.getUrl();

        assertEquals(url, urlValue);
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 169 Editor note">
    //<editor-fold defaultstate="collapsed" desc="Scenario: The component Editors note is rendered">
    @When("^the component editors note is rendered$")
    public void the_component_editors_note_is_rendered() throws Throwable {
        String classEditorNoteOrigin = "editorsnote parbase section base xk-component xk-section-base ng-scope";
        String classEditorNote = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[3]", "class");

        assertEquals(classEditorNoteOrigin, classEditorNote);
    }

    @Then("^I can see the editor note content with the configured format in the Author instance$")
    public void i_can_see_the_editor_note_content_with_the_configured_format_in_the_Author_instance() throws Throwable {
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 1802 Page Slider">
    //<editor-fold defaultstate="collapsed" desc="Scenario: The component is rendered">
    @Given("^a pages slider already configured$")
    public void a_pages_slider_already_configured() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_ARTICLE_PAGE);
    }

    @When("^the component pages slide is rendered$")
    public void the_component_pages_slide_is_rendered() throws Throwable {
        boolean exist = LifeWay.AEM.existsElementByXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[4]");
        assertTrue(exist);
    }

    @Then("^the Page title, and description is obtained$")
    public void the_Page_title_and_description_is_obtained() throws Throwable {
    }

    @Then("^the page title should be rendered as each item title$")
    public void the_page_title_should_be_rendered_as_each_item_title() throws Throwable {
        String classOrigin = "tile__title";
        String classTitle = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[4]/div/div[1]/div/div[1]/div/div/a/p", "class");

        assertEquals(classOrigin, classTitle);
    }

    @Then("^the page description should be rendered as each item summary$")
    public void the_page_description_should_be_rendered_as_each_item_summary() throws Throwable {
        String classOrigin = "tile__desc";
        String classDescription = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[4]/div/div[1]/div/div[1]/div/div/p", "class");

        assertEquals(classOrigin, classDescription);
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 1904 XK Article Summary">
    //<editor-fold defaultstate="collapsed" desc="Scenario: rendering the component">
    @Given("^the visitor opens an article page with article summary component$")
    public void the_visitor_opens_an_article_page_with_article_summary_component() throws Throwable {
        LifeWay.openUrl(LifeWay.URL_ARTICLE_SUMMARY);
    }

    @When("^the article summary component is rendered$")
    public void the_article_summary_component_is_rendered() throws Throwable {
        boolean isRender = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[5]/div");
        assertTrue(isRender);
    }

    @When("^has obtained the page description data from the page properties$")
    public void has_obtained_the_page_description_data_from_the_page_properties() throws Throwable {
    }

    @Then("^the article summary is shown as the screenshot$")
    public void the_article_summary_is_shown_as_the_screenshot() throws Throwable {
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 190 Footnotes">
    //<editor-fold defaultstate="collapsed" desc="Scenario: Visitor hovers over anchor link">
    @Given("^the visitor opens an article page with footnotes$")
    public void the_visitor_opens_an_article_page_with_footnotes() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_ARTICLE_PAGE);
    }

    @Given("^at least one of the footnotes has anchorlink$")
    public void at_least_one_of_the_footnotes_has_anchorlink() throws Throwable {
        boolean exist = LifeWay.AEM.existsElementByXpath("//*[@id=\"footnotes\"]/ol/li[1]/p/a");

        assertTrue(exist);
    }

    @When("^the visitor hovers over the anchor link$")
    public void the_visitor_hovers_over_the_anchor_link() throws Throwable {
        String anchor = LifeWay.AEM.getPropertieXpath("//*[@id=\"footnotes\"]/ol/li[1]/p/a", "href");

        assertTrue(anchor.contains("#"));
    }

    @Then("^the tooltip should be visible$")
    public void the_tooltip_should_be_visible() throws Throwable {
        String toolTip = LifeWay.AEM.getPropertieXpath("//*[@id=\"footnotes\"]/ol/li[1]/p/a", "title");

        assertFalse(toolTip.isEmpty());
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 203 Text and Image wide">
    //<editor-fold defaultstate="collapsed" desc="Scenario: the visitor clicks on the image or the button">
    @Given("^The visitor has opened a page with a text and image wide component$")
    public void the_visitor_has_opened_a_page_with_a_text_and_image_wide_component() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.UR_TEXT_IMAGE_WIDE);
    }

    @When("^the visitor clicks on the button$")
    public void the_visitor_clicks_on_the_button() throws Throwable {
        hrefOrigin = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div/div[2]/p[2]/a", "href");
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div/div/div/div[2]/p[2]/a");
    }

    @Then("^The visitor should be redirected to \"([^\"]*)\" property$")
    public void the_visitor_should_be_redirected_to_property(String arg1) throws Throwable {
        String url = LifeWay.AEM.getUrl();
        assertEquals(hrefOrigin, url);
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 205 box with columns">
    //<editor-fold defaultstate="collapsed" desc="Scenario: The component is rendered in preview mode / publish">
    @Given("^the visitor navigates to page with a Box with columns components$")
    public void the_visitor_navigates_to_page_with_a_Box_with_columns_components() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_HOME_PAGE);
    }

    @Given("^the Box with columns component has three or more Box with columns items$")
    public void the_Box_with_columns_component_has_three_or_more_Box_with_columns_items() throws Throwable {
        boolean visib = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div[3]/div/div/div/div[5]");
        assertTrue(visib);
    }

    @When("^the componente box is rendered$")
    public void the_componente_box_is_rendered() throws Throwable {
        boolean visibl = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div[3]");

        assertTrue(visibl);
    }

    @Then("^each box with columns item should be rendered as a column$")
    public void each_box_with_columns_item_should_be_rendered_as_a_column() throws Throwable {
        String classOriginal = "col-sm-12 col-md-6 col-lg-3 p-a-md";
        String classco = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div[3]/div/div/div/div[2]", "class");
        assertEquals(classOriginal, classco);

        String classc = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div[3]/div/div/div/div[3]", "class");
        assertEquals(classOriginal, classc);

        String classcol = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div[3]/div/div/div/div[4]", "class");
        assertEquals(classOriginal, classcol);

    }

    @Then("^there can be shown up to four columns$")
    public void there_can_be_shown_up_to_four_columns() throws Throwable {
    }

    @Then("^should be rendered as the screenshots$")
    public void should_be_rendered_as_the_screenshots() throws Throwable {
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: The visitor clicks on a button">
    @Given("^the visitor navigates to page$")
    public void the_visitor_navigates_to_page() throws Throwable {
        boolean visible = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div[3]");
        assertTrue(visible);
    }

    @Given("^scrolls to the box with columns section$")
    public void scrolls_to_the_box_with_columns_section() throws Throwable {
        hrefOrigin = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div[3]/div/div/div/div[2]/a", "href");
    }

    @When("^the visitor clicks in the button$")
    public void the_visitor_clicks_in_the_button() throws Throwable {
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div[3]/div/div/div/div[2]/a");
    }

    @Then("^the vissitor is redirected to URL$")
    public void the_vissitor_is_redirected_to_URL() throws Throwable {
        String redi = LifeWay.AEM.getUrl();
        assertEquals(hrefOrigin, redi);
    }

    @Then("^the redirection occurs in Target specified tab$")
    public void the_redirection_occurs_in_Target_specified_tab() throws Throwable {
        /**
         * COMPROBAR REDIRECCIONAMIENTO CON ATRIBUTO TARGET.
         */

        LifeWay.AEM.finalizeAemConector();
    }
    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 225 Subtitle">
    //<editor-fold defaultstate="collapsed" desc="Scenario: the component is rendered">

    @When("^the Article subtitle component is rendered$")
    public void the_Article_subtitle_component_is_rendered() throws Throwable {
        boolean exist = LifeWay.AEM.existsElementByXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[5]/div");
        assertTrue(exist);
    }

    @Then("^the article subtitle should be shown under the headline of the article page$")
    public void the_article_subtitle_should_be_shown_under_the_headline_of_the_article_page() throws Throwable {
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 230 Author and Date">
    //<editor-fold defaultstate="collapsed" desc="Scenario: The component is rendered">
    @Given("^an article page is loaded$")
    public void an_article_page_is_loaded() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_ARTICLE_PAGE);
    }

    @When("^the component is rendered$")
    public void the_component_is_rendered() throws Throwable {
        String classAuthorOrigin = "clearfix authorline-share";
        String classAuthor = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[3]", "class");

        assertEquals(classAuthorOrigin, classAuthor);
    }

    @Then("^the name of the author is shown$")
    public void the_name_of_the_author_is_shown() throws Throwable {
        String classNameAuthorOrigin = "article__author";
        String classNameAuthor = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[3]/div[1]/p/a[1]", "class");

        assertEquals(classNameAuthorOrigin, classNameAuthor);
    }

    @Then("^the name has a link to the author's page$")
    public void the_name_has_a_link_to_the_author_s_page() throws Throwable {
        String href = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[3]/div[1]/p/a[1]", "href");

        try {
            new URL(href);
        } catch (Exception e) {
            assertTrue(false);
        }

    }

    @Then("^the created date is shown in the format: \"([^\"]*)\"$")
    public void the_created_date_is_shown_in_the_format(String arg1) throws Throwable {
        String format = arg1;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String date = LifeWay.AEM.readByXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[3]/div[1]/p");
        date = date.substring(date.indexOf("created on "), date.length());
        date = date.replaceAll("created on ", "");
        date = date.replaceAll("at ", "");
        date = date.trim();

        try {
            sdf.parse(date);
        } catch (Exception e) {
            assertTrue(false);
        }
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 233 Logo">
    //<editor-fold defaultstate="collapsed" desc="First Scenario">
    @Given("^an Author wants to configure the Logo component$")
    public void an_Author_wants_to_configure_the_Logo_component() throws Throwable {
        /*LifeWay.openUrl(LifeWay.UR_PAGE_BASE);
         Thread.sleep(1000);*/
    }

    @Given("^enters to the Persistent elements page$")
    public void enters_to_the_Persistent_elements_page() throws Throwable {
        /*LifeWay.AEM.inPreviewMode();
         Thread.sleep(1000);*/
    }

    @Given("^enters to the Header and Footer configuration page$")
    public void enters_to_the_Header_and_Footer_configuration_page() throws Throwable {
        //TO DO
    }

    @Then("^the author can identify the \"([^\"]*)\" Component$")
    public void the_author_can_identify_the_Logo_Component(String arg1) throws Throwable {
        /*LifeWay.AEM.exitsElementByXpath("//h1[contains(text(), \""+arg1+"\")]");

         LifeWay.AEM.inEditMode();
         Thread.sleep(1000);*/
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Second Scenario">
    @Given("^the author has opened the local dialog$")
    public void the_author_has_opened_the_local_dialog() throws Throwable {
        LifeWay.clickConfiguration();
    }

    @Given("^adds the logo image \"([^\"]*)\"$")
    public void adds_the_logo_image(String arg1) throws Throwable {
        Thread.sleep(3000);
        LifeWay.AEM.writeByID("assetsearch", arg1);
        LifeWay.AEM.enterById("assetsearch");
        Thread.sleep(3000);

        //LifeWay.AEM.dragDropByXpath("//*[@id=\"coral-9\"]/div[2]/div[1]/div[1]/article", "//*[@id=\"coral-92\"]/div/div[1]/span[1]/div/div[1]");
    }

    @Given("^enters the Logo URL: \"([^\"]*)\" property$")
    public void enters_the_Logo_URL_property(String arg1) throws Throwable {
        LifeWay.AEM.writeByID("coral-95", arg1);
    }

    @Given("^enters the Logo Alt Text: \"([^\"]*)\" optional property$")
    public void enters_the_Logo_Alt_Text_optional_property(String arg1) throws Throwable {
        LifeWay.AEM.writeByXpath("//*[@id=\"coral-92\"]/div/div[3]/input", arg1);
    }

    @When("^finishes the configuration and click OK$")
    public void finishes_the_configuration_and_click_OK() throws Throwable {
        LifeWay.clickOk();
    }

    @Then("^the logo: \"([^\"]*)\" is displayed with a link pointing to \"([^\"]*)\"$")
    public void the_logo_is_displayed_with_a_link_pointing_to(String arg1, String arg2) throws Throwable {
        LifeWay.AEM.inPreviewMode();
        Thread.sleep(3000);

        String link = LifeWay.AEM.getPropertieXpath("/html/body/div[2]/div/div/div/div/div/div[1]/div[1]/div[2]/div[1]/div[2]/a", "href");
        String logo = LifeWay.AEM.getPropertieXpath("/html/body/div[2]/div/div/div/div/div/div[1]/div[1]/div[2]/div[1]/div[2]/a/img", "src");

        URL urlLogo = new URL(logo);

        int separator = urlLogo.getFile().lastIndexOf("/");
        //int lastDot = urlLogo.getFile().lastIndexOf(".");

        /*if (lastDot < separator) {
         lastDot = urlLogo.getFile().length();
         }*/
        if (separator > 0) {
            //logo = urlLogo.getFile().substring(separator + 1, lastDot);
            logo = urlLogo.getFile().substring(separator + 1,
                    urlLogo.getFile().length());
        }

        assertEquals(arg1, logo);
        assertEquals(arg2, link);

        Thread.sleep(1000);
        LifeWay.AEM.inEditMode();
        Thread.sleep(3000);
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="comment">
    //<editor-fold defaultstate="collapsed" desc="comment">
    
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="comment">
    
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="comment">
    
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="comment">
    
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="comment">
    
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="comment">
    
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="comment">
    
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="comment">
    
//</editor-fold>
    
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 250 XK Most Recent">
    //<editor-fold defaultstate="collapsed" desc="Scenario: a visitor sees the component and clicks on a link">
    @Given("^the visitor have seen a Most recent component$")
    public void the_visitor_have_seen_a_Most_recent_component() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_ARTICLE_PAGE);

        boolean isVisible = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[2]/div/div/div/div[2]");
        assertTrue(isVisible);
    }

    @When("^he clicks on an article title link$")
    public void he_clicks_on_an_article_title_link() throws Throwable {
        hrefOrigin = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div[2]/div/div/div/div[2]/div/ul/li[1]/a", "href");
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div/div/div[2]/div/div/div/div[2]/div/ul/li[1]/a");
    }

    @Then("^the visitor is directed to that specific article page$")
    public void the_visitor_is_directed_to_that_specific_article_page() throws Throwable {
        String url = LifeWay.AEM.getUrl();
        assertEquals(hrefOrigin, url);
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 267 Article Content Highlight">
    //<editor-fold defaultstate="collapsed" desc="Scenario: The article page renders the Content Highlight like the configured by the Author">
    @Given("^the user navigates to the Article page$")
    public void the_user_navigates_to_the_Article_page() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_ARTICLE_PAGE);
    }

    @Given("^it contains a Highlight content$")
    public void it_contains_a_Highlight_content() throws Throwable {
        String classHighlightOrigin = "card card-block bg-faded highlights";
        String classHighlight = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div[2]/div/div/div/div/div", "class");

        assertEquals(classHighlightOrigin, classHighlight);
    }

    @Then("^I can see the highlight content with the configured format in the Author instance$")
    public void i_can_see_the_highlight_content_with_the_configured_format_in_the_Author_instance() throws Throwable {
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 273 Author Information">
    //<editor-fold defaultstate="collapsed" desc="Scenario: Automatic mode ON">
    @Given("^the Automatic mode checkbox is set$")
    public void the_Automatic_mode_checkbox_is_set() throws Throwable {

    }

    @When("^the component author information is rendered$")
    public void the_component_author_information_is_rendered() throws Throwable {
        boolean isRender = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[9]");
        assertTrue(isRender);
    }

    @Then("^the author image is loaded from authors contributor page$")
    public void the_author_image_is_loaded_from_authors_contributor_page() throws Throwable {
        String img = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[9]/div/div/div[1]/img", "src");
        assertTrue(img.contains("/content/lifeway/"));
    }

    @Then("^the biography is loaded from author's contributor page$")
    public void the_biography_is_loaded_from_author_s_contributor_page() throws Throwable {
    }

    @Then("^the name is loaded from authors contributor page$")
    public void the_name_is_loaded_from_authors_contributor_page() throws Throwable {
        String biography = LifeWay.AEM.readByXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[9]/div/div/div[2]/p[1]");
        assertTrue(biography.startsWith("Carol Pipes"));
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: Automatic mode OFF">
    @Given("^the Automatic mode checkbox is not set$")
    public void the_Automatic_mode_checkbox_is_not_set() throws Throwable {
    }

    @Then("^the author image comes from the local dialog$")
    public void the_author_image_comes_from_the_local_dialog() throws Throwable {
        String img = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[10]/div/div/div[1]/img", "src");
        assertTrue(img.contains("/content/dam/"));
    }

    @Then("^the biography comes from the local dialog$")
    public void the_biography_comes_from_the_local_dialog() throws Throwable {
    }

    @Then("^the author name comes from the local dialog$")
    public void the_author_name_comes_from_the_local_dialog() throws Throwable {
        String biography = LifeWay.AEM.readByXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[10]/div/div/div[2]/p[1]");
        assertFalse(biography.startsWith("Carol Pipes"));
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: No image found">
    @Given("^the image is not found$")
    public void the_image_is_not_found() throws Throwable {
        String img = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[11]/div/div/div[1]/img", "src");
        assertTrue(img.isEmpty());
        //
    }

    @Then("^the text should be aligned to the left$")
    public void the_text_should_be_aligned_to_the_left() throws Throwable {
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US-278 Product Slider">
    //<editor-fold defaultstate="collapsed" desc="Scenario: click on the name or image of the product">
    @Given("^A authored page, like articles$")
    public void a_authored_page_like_articles() throws Throwable {
    }

    @Given("^a product slider correctly configured$")
    public void a_product_slider_correctly_configured() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.UR_PRODUCT);
    }

    @When("^a visitor clicks on the title of an item on the slider$")
    public void a_visitor_clicks_on_the_title_of_an_item_on_the_slider() throws Throwable {
        hrefOrigin = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div/div[2]/div[6]/div/div[1]/div/div[1]/div/div/a", "href");
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div/div/div/div[2]/div[6]/div/div[1]/div/div[1]/div/div/a");

    }

    @Then("^the visitor should be redirected to that specific product detail page$")
    public void the_visitor_should_be_redirected_to_that_specific_product_detail_page() throws Throwable {
    }

    @Then("^the product page is open on the current tab$")
    public void the_product_page_is_open_on_the_current_tab() throws Throwable {
        Thread.sleep(3000);
        String url = LifeWay.AEM.getUrl();

        assertEquals(hrefOrigin, url);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: Adding a product that has variants">
    @Given("^A product that has been configured on the product slider$")
    public void a_product_that_has_been_configured_on_the_product_slider() throws Throwable {
    }

    @Given("^that product has variants$")
    public void that_product_has_variants() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.UR_PRODUCT);
    }

    @Given("^the sale format is not set$")
    public void the_sale_format_is_not_set() throws Throwable {
    }

    @When("^the product is displayed$")
    public void the_product_is_displayed() throws Throwable {
        boolean existValue = LifeWay.AEM.existsElementByXpath("/html/body/div[3]/div/div/div/div[2]/div[6]/div/div[1]/div/div[3]");
        assertTrue(existValue);
    }

    @Then("^the price shown should be the lowest variant price$")
    public void the_price_shown_should_be_the_lowest_variant_price() throws Throwable {

    }

    @Then("^the \"([^\"]*)\" price label should be shown before the price$")
    public void the_price_label_should_be_shown_before_the_price(String arg1) throws Throwable {
        String price = LifeWay.AEM.readByXpath("/html/body/div[3]/div/div/div/div[2]/div[6]/div/div[1]/div/div[3]/div/div/p[2]");
        assertTrue(price.trim().startsWith(arg1));
    }

    @Then("^the text color is the same as the actual price$")
    public void the_text_color_is_the_same_as_the_actual_price() throws Throwable {
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: A product image is not present or broken">
    @Given("^A configured product that does not have an image or the product image is broken$")
    public void a_configured_product_that_does_not_have_an_image_or_the_product_image_is_broken() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.UR_PRODUCT);
    }

    @When("^the item on the slider is rendered$")
    public void the_item_on_the_slider_is_rendered() throws Throwable {
        the_product_is_displayed();
    }

    @Then("^\"([^\"]*)\" should provide a placeholder image to display$")
    public void scene_should_provide_a_placeholder_image_to_display(String arg1) throws Throwable {
        String href = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div/div[2]/div[6]/div/div[1]/div/div[1]/div/div/a/div/div/img", "src");
        assertTrue(href.contains(href));
    }

    @Then("^the placeholder image should be used as the product image$")
    public void the_placeholder_image_should_be_used_as_the_product_image() throws Throwable {
        boolean exist = LifeWay.AEM.existsElementByXpath("/html/body/div[3]/div/div/div/div[2]/div[6]/div/div[1]/div/div[1]/div/div/a/div/div/img");
        assertTrue(exist);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: Alternative Text of the product images">
    @Given("^an item on the slider$")
    public void an_item_on_the_slider() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.UR_PRODUCT);
    }

    @When("^the item is displayed$")
    public void the_item_is_displayed() throws Throwable {
        boolean exist = LifeWay.AEM.existsElementByXpath("/html/body/div[3]/div/div/div/div[2]/div[6]/div/div[1]/div/div[3]");
        assertTrue(exist);
    }

    @Then("^the alternative text of the product image should be the product name$")
    public void the_alternative_text_of_the_product_image_should_be_the_product_name() throws Throwable {
        String alternativeText = LifeWay.AEM.readByXpath("/html/body/div[3]/div/div/div/div[2]/div[6]/div/div[1]/div/div[3]/div/div/p[1]");
        assertTrue(alternativeText.length() > 0);
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 286 Photo Gallery">
    //<editor-fold defaultstate="collapsed" desc="Scenario: The article page renders the photo gallery like the configured by the Author">
    @Given("^it contains a photo gallery$")
    public void it_contains_a_photo_gallery() throws Throwable {
        boolean exist = LifeWay.AEM.existsElementByXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[1]/div");
        assertTrue(exist);
    }

    @Then("^I can see the photo gallery with the configured format in the Author instance$")
    public void i_can_see_the_photo_gallery_with_the_configured_format_in_the_Author_instance() throws Throwable {
    }
    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 293 Top navigation">
    //<editor-fold defaultstate="collapsed" desc="First Scenario">

    @Given("^an Author wants to configure the Top Navigation component$")
    public void an_Author_wants_to_configure_the_Top_Navigation_component() throws Throwable {
        //TO DO
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Second Scenario">
    @Given("^adds an element of the Link List multifield$")
    public void adds_an_element_of_the_Link_List_multifield() throws Throwable {
        Thread.sleep(3000);
        LifeWay.AEM.clickByXpath("//*[@id=\"coral-91\"]/div/div/button");
    }

    @Given("^adds the LinkText: \"([^\"]*)\" property of the element$")
    public void adds_the_LinkText_property_of_the_element(String arg1) throws Throwable {
        LifeWay.AEM.writeByXpath("//*[@id=\"coral-91\"]/div/div/ol/li/section/div/div[1]/input", arg1);
    }

    @Given("^adds the LinkUrl: \"([^\"]*)\" property of the element$")
    public void adds_the_LinkUrl_property_of_the_element(String arg1) throws Throwable {
        LifeWay.AEM.writeByXpath("//*[@id=\"coral-91\"]/div/div/ol/li/section/div/div[3]/input", arg1);
    }

    @Then("^the top navigation should be displayed\\.$")
    public void the_top_navigation_should_be_displayed() throws Throwable {
        LifeWay.AEM.inPreviewMode();

        Thread.sleep(3000);
        assertTrue(LifeWay.AEM.existsElementByXpath("//div[contains(@class, 'topnavigationconfiguration')]"));

        Thread.sleep(1000);
        LifeWay.AEM.inEditMode();
        Thread.sleep(3000);
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 314 XK Category Grid">
    //<editor-fold defaultstate="collapsed" desc="Scenario: The button text is not specified on elements">
    @Given("^no text was specified on Button text field$")
    public void no_text_was_specified_on_Button_text_field() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_HOMEPAGE_QA);
        LifeWay.AEM.clickByXpath("//*[@id=\"categorygrid-9c98206a244675e609dcd5eb3c6c8689\"]/div/div/div[1]/div[3]/button[4]");
    }

    @When("^the element is rendered$")
    public void the_element_is_rendered() throws Throwable {
        boolean isRendered = LifeWay.AEM.isVisibleXpath("//*[@id=\"item-9c98206a244675e609dcd5eb3c6c86893\"]/div/div[1]/a/img");
        assertTrue(isRendered);
    }

    @Then("^No button should be shown$")
    public void no_button_should_be_shown() throws Throwable {
        boolean isShow = LifeWay.AEM.isVisibleXpath("//*[@id=\"item-9c98206a244675e609dcd5eb3c6c86893\"]/div/div[2]/p/a");
        assertFalse(isShow);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: The visitor clicks on an image or element title">
    @Given("^the visitor has clicked an element's image or has clicked an element's title$")
    public void the_visitor_has_clicked_an_element_s_image_or_has_clicked_an_element_s_title() throws Throwable {
        LifeWay.AEM.clickByXpath("//*[@id=\"categorygrid-9c98206a244675e609dcd5eb3c6c8689\"]/div/div/div[1]/div[3]/button[1]");
        LifeWay.AEM.clickByXpath("//*[@id=\"item-9c98206a244675e609dcd5eb3c6c86890\"]/div[1]/div[1]/a");
    }

    @When("^the action is called$")
    public void the_action_is_called() throws Throwable {

    }

    @Then("^the visitor is redirected to \"([^\"]*)\"$")
    public void the_visitor_is_redirected_to(String arg1) throws Throwable {
        String url = LifeWay.AEM.getUrl();
        boolean isRedirected = url.contains(arg1);
        assertTrue(isRedirected);
    }

    @Then("^the target should be as specified on \"([^\"]*)\"$")
    public void the_target_should_be_as_specified_on(String arg1) throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_HOMEPAGE_QA);
        String targetOrigin = LifeWay.AEM.getPropertieXpath("//*[@id=\"item-9c98206a244675e609dcd5eb3c6c86890\"]/div[2]/div[1]/a", "target");
        assertEquals(arg1, targetOrigin);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: visitor clicks on a blue button of categories">
    @Given("^the component has more than one category specified$")
    public void the_component_has_more_than_category_specified() throws Throwable {
        boolean moreOne = LifeWay.AEM.existsElementByXpath("//*[@id=\"categorygrid-9c98206a244675e609dcd5eb3c6c8689\"]/div/div/div[1]/div[3]/button[2]");
        assertTrue(moreOne);
    }

    @Given("^the categories are shown as blue buttons at te top of the section$")
    public void the_categories_are_shown_as_blue_buttons_at_te_top_of_the_section() throws Throwable {

    }

    @When("^the visitor clicks on a button$")
    public void the_visitor_clicks_on_a_button() throws Throwable {
        LifeWay.AEM.clickByXpath("//*[@id=\"categorygrid-9c98206a244675e609dcd5eb3c6c8689\"]/div/div/div[1]/div[3]/button[2]");
        anchorTarget = LifeWay.AEM.getPropertieXpath("//*[@id=\"categorygrid-9c98206a244675e609dcd5eb3c6c8689\"]/div/div/div[1]/div[3]/button[2]", "data-target");
        anchorTarget = anchorTarget.replaceAll("#", "");
    }

    @Then("^the elements of the clicked category should be shown at the bottom$")
    public void the_elements_of_the_clicked_category_should_be_shown_at_the_bottom() throws Throwable {
        boolean isVisible = LifeWay.AEM.isVisibleById(anchorTarget);
        assertTrue(isVisible);
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 315 Text">
    //<editor-fold defaultstate="collapsed" desc="Scenario: The component Text is render">
    @When("^the component Text is render$")
    public void the_component_Text_is_render() throws Throwable {
        String classTextOrigin = "section text";
        String classText = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[5]", "class");

        assertEquals(classTextOrigin, classText);
    }

    @Then("^id anchor satisfy the regular expression \"([^\"]*)\"$")
    public void id_anchor_satisfy_the_regular_expression(String arg1) throws Throwable {
        String id = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[5]/div[2]", "id");

        assertTrue(id.matches(arg1));
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 318 XK Article Blockquote">
    //<editor-fold defaultstate="collapsed" desc="Scenario: visitor opens an article page with a quote">
    @When("^he sees a quote$")
    public void he_sees_a_quote() throws Throwable {
        assertTrue(LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[2]"));
    }

    @Then("^the quote should show a thin color column$")
    public void the_quote_should_show_a_thin_color_column() throws Throwable {
        String color = "rgba(225, 178, 67, 1)";
        String colorQuote = LifeWay.AEM.getCssValueXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[2]/blockquote", "border-left-color");

        assertEquals(color, colorQuote);
    }

    @Then("^the text aligned to it$")
    public void the_text_aligned_to_it() throws Throwable {
    }

    @Then("^the author of the quote is shown at the bottom after a slash$")
    public void the_author_of_the_quote_is_shown_at_the_bottom_after_a_slash() throws Throwable {
        String footherOrigin = "\"— \"";
        String foother = LifeWay.AEM.getBeforeContent("div.section.articleblockquote > blockquote > footer");

        assertEquals(footherOrigin, foother);
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 324 xk bread crumb">
    //<editor-fold defaultstate="collapsed" desc="Scenario: the visitor clicks on a link">
    @Given("^the visitor sees a page with breadcrumbs$")
    public void the_visitor_sees_a_page_with_breadcrumbs() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_HOME_PAGE);
        boolean visible = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div[1]");
        assertTrue(visible);
    }

    @Given("^the breadcrumbs has \"([^\"]*)\" checkbox unchecked$")
    public void the_breadcrumbs_has_checkbox_unchecked(String arg1) throws Throwable {
    }

    @When("^the visitor clicks on a link$")
    public void the_visitor_clicks_on_a_link() throws Throwable {
        hrefOrigin = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div[1]/ol/li[2]/a", "href");
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div[1]/ol/li[2]/a");
    }

    @Then("^he should be redirected to \"([^\"]*)\" page$")
    public void he_should_be_redirected_to_page(String arg1) throws Throwable {
        Thread.sleep(3000);
        String dir = LifeWay.AEM.getUrl();

        assertEquals(hrefOrigin, dir);
    }

    @Then("^the page is opened as \"([^\"]*)\" field specifies$")
    public void the_page_is_opened_as_field_specifies(String arg1) throws Throwable {
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 332 Upper Footer">
    //<editor-fold defaultstate="collapsed" desc="First Scenario">
    @Given("^an Author wants to configure the Upper Footer component$")
    public void an_Author_wants_to_configure_the_Upper_Footer_component() throws Throwable {
        // TO DO
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Second Scenario">
    @Given("^The Author has opened the local dialog of the component$")
    public void the_Author_has_opened_the_local_dialog_of_the_component() throws Throwable {
        LifeWay.clickConfiguration();
    }

    @Given("^adds a new element on the Columns multifield$")
    public void adds_a_new_element_on_the_Columns_multifield() throws Throwable {
        Thread.sleep(1000);
        LifeWay.AEM.clickByXpath("//*[@id=\"coral-91\"]/div/div/button");
    }

    @Given("^adds the Column Title: \"([^\"]*)\" property$")
    public void adds_the_Column_Title_property(String arg1) throws Throwable {
        LifeWay.AEM.writeByXpath("//*[@id=\"coral-91\"]/div/div/ol/li/section/div/div[1]/input", arg1);
    }

    @Given("^adds a new element on the Links multifield, part of Columns multifield$")
    public void adds_a_new_element_on_the_Links_multifield_part_of_Columns_multifield() throws Throwable {
        LifeWay.AEM.clickByXpath("//*[@id=\"coral-91\"]/div/div/ol/li/section/div/div[2]/div/button");
    }

    @Given("^adds the link's \"([^\"]*)\" property$")
    public void adds_the_link_s_property(String arg1) throws Throwable {
        LifeWay.AEM.writeByXpath("//*[@id=\"coral-91\"]/div/div/ol/li/section/div/div[2]/div/ol/li/section/div/div[1]/input", arg1);
    }

    @Given("^adds an internal or external link on URL: \"([^\"]*)\" property$")
    public void adds_an_internal_or_external_link_on_URL_property(String arg1) throws Throwable {
        LifeWay.AEM.writeByXpath("//*[@id=\"coral-91\"]/div/div/ol/li/section/div/div[2]/div/ol/li/section/div/div[3]/input", arg1);
    }

    @Given("^checks the link's Is Special Link property if the link should be highlighted or not$")
    public void checks_the_link_s_Is_Special_Link_property_if_the_link_should_be_highlighted_or_not() throws Throwable {
        LifeWay.AEM.clickByXpath("//*[@id=\"coral-91\"]/div/div/ol/li/section/div/div[2]/div/ol/li/section/div/div[5]/label/input[1]");
    }

    @When("^the Author finishes the configuration and click on OK$")
    public void the_Author_finishes_the_configuration_and_click_on_OK() throws Throwable {
        LifeWay.clickOk();
    }

    @Then("^For each column added, it's list of links should be displayed under the column's Column Title: \"([^\"]*)\"$")
    public void for_each_column_added_it_s_list_of_links_should_be_displayed_under_the_column_s_Column_Title(String arg1) throws Throwable {
        LifeWay.AEM.inPreviewMode();
        Thread.sleep(3000);

        assertTrue(LifeWay.AEM.compareUpperFooter(arg1, "Link 1"));
    }

    @Then("^if a link's Is Special Link property is checked, the Link Text should be displayed with different color$")
    public void if_a_link_s_Is_Special_Link_property_is_checked_the_Link_Text_should_be_displayed_with_different_color() throws Throwable {
        Thread.sleep(1000);
        LifeWay.AEM.inEditMode();
        Thread.sleep(3000);
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 340 Social Media Share">
    //<editor-fold defaultstate="collapsed" desc="Scenario: the visitor enters incorrectly an email field">
    @Given("^the visitor is sending a sharing email$")
    public void the_visitor_is_sending_a_sharing_email() throws Throwable {
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div/div/div/div[1]/div/div[3]/div/div[3]/div/ul/li[3]/a");
    }

    @When("^he enters incorrectly the email, or does not sepparete with commas the lists of emails on the \"([^\"]*)\" field$")
    public void he_enters_incorrectly_the_email_or_does_not_sepparete_with_commas_the_lists_of_emails_on_the_field(String arg1) throws Throwable {
        LifeWay.AEM.writeByID("inputTo", arg1);
        LifeWay.AEM.tabById("inputMessage");
    }

    @Then("^\"([^\"]*)\" message under the field should come up$")
    public void message_under_the_field_should_come_up(String arg1) throws Throwable {
        Thread.sleep(3000);
        String message = LifeWay.AEM.readByXpath("//*[@id=\"formSendEmail\"]/div[2]/div[2]/div/fieldset[1]/div/div");
        assertEquals(arg1, message);
    }

    @Then("^the back ground color of the message section is \"([^\"]*)\"")
    public void the_back_ground_color_of_the_message_section_is_orange(String arg1) throws Throwable {
        String color = LifeWay.AEM.getCssValueXpath("//*[@id=\"formSendEmail\"]/div[2]/div[2]/div/fieldset[1]/div/div", "background-color");
        assertEquals(arg1, color);

        the_visitor_has_clicked_on_the_close_icon_of_the_modal_or_has_clicked_outside_the_modal_window();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: Visitor sends an email (Defined on LW-1217)">
    @Given("^the visitor has clicked on the email icon$")
    public void the_visitor_has_clicked_on_the_email_icon() throws Throwable {
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div/div/div/div[1]/div/div[3]/div/div[3]/div/ul/li[3]/a");
    }

    @Given("^has filled correctly the pop up form$")
    public void has_filled_correctly_the_pop_up_form() throws Throwable {
        fillFormCorrect();
    }

    @When("^the visitor passes captcha and clicks the submit button$")
    public void the_visitor_passes_captcha_and_clicks_the_submit_button() throws Throwable {
        //LifeWay.AEM.clickRecaptchaXpath("//*[@id=\"formSendEmail\"]/div[2]/div[2]/div/div[2]/div/div/div/iframe");
    }

    @Then("^an email is sended using the layout configured under etc/static/emaillayouts/$")
    public void an_email_is_sended_using_the_layout_configured_under_etc_static_emaillayouts() throws Throwable {
        LifeWay.AEM.clickByID("buttonSendMail");

        String message = LifeWay.AEM.readByXpath("//*[@id=\"formSendEmail\"]/div[2]/div[1]/div/div[1]");
        String message_ok = "Your message has been sent";
        LifeWay.AEM.clickByXpath("//*[@id=\"formSendEmail\"]/div[1]/button");

        assertEquals(message_ok, message);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: Visitor uses captcha functionality (Defined on LW-1222)">
    @Given("^The visitor needs to send an email$")
    public void the_visitor_needs_to_send_an_email() throws Throwable {
    }

    @When("^he wants to submit the email form$")
    public void he_wants_to_submit_the_email_form() throws Throwable {
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div/div/div/div[1]/div/div[3]/div/div[3]/div/ul/li[3]/a");
    }

    @When("^passes the captcha widget$")
    public void passes_the_captcha_widget() throws Throwable {
        the_visitor_passes_captcha_and_clicks_the_submit_button();
    }

    @Then("^he is able to submit the form$")
    public void he_is_able_to_submit_the_form() throws Throwable {
        fillFormCorrect();
    }

    @Then("^the email is sent$")
    public void the_email_is_sent() throws Throwable {
        an_email_is_sended_using_the_layout_configured_under_etc_static_emaillayouts();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: Visitor fills the email form incorrectly">
    @Given("^the visitor is entering the fields for the email form$")
    public void the_visitor_is_entering_the_fields_for_the_email_form() throws Throwable {
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div/div/div/div[1]/div/div[3]/div/div[3]/div/ul/li[3]/a");
    }

    @Given("^the email field is blank$")
    public void the_email_field_is_blank() throws Throwable {
        LifeWay.AEM.clickByID("inputTo");
    }

    @Given("^the personal message text área is blank$")
    public void the_personal_message_text_área_is_blank() throws Throwable {
        LifeWay.AEM.clickByID("inputMessage");
    }

    @Given("^your name field is blank$")
    public void your_name_field_is_blank() throws Throwable {
        LifeWay.AEM.clickByID("inputYourName");
    }

    @Given("^your email field is blank$")
    public void your_email_field_is_blank() throws Throwable {
        LifeWay.AEM.clickByID("inputFrom");
        LifeWay.AEM.clickByID("checkSendMeCopy");
        LifeWay.AEM.clickByID("checkSendMeCopy");
    }

    @When("^any of the required fields marked with the carácter \"([^\"]*)\" are not present$")
    public void any_of_the_required_fields_marked_with_the_carácter_are_not_present(String arg1) throws Throwable {
    }

    @Then("^Enter a valid email message under the field should come up$")
    public void enter_a_valid_email_message_under_the_field_should_come_up() throws Throwable {
        Thread.sleep(3000);
        String message = LifeWay.AEM.readByXpath("//*[@id=\"formSendEmail\"]/div[2]/div[2]/div/fieldset[1]/div/div");
        assertEquals("Enter a valid email", message);
        message = LifeWay.AEM.readByXpath("//*[@id=\"formSendEmail\"]/div[2]/div[2]/div/fieldset[2]/div/div");
        assertEquals("Please complete the required data", message);
        message = LifeWay.AEM.readByXpath("//*[@id=\"formSendEmail\"]/div[2]/div[2]/div/fieldset[3]/div/div");
        assertEquals("Please complete the required data", message);
        message = LifeWay.AEM.readByXpath("//*[@id=\"formSendEmail\"]/div[2]/div[2]/div/fieldset[4]/div/div");
        assertEquals("Enter a valid email", message);
    }

    @Then("^the back ground color of the message section is orange$")
    public void the_back_ground_color_of_the_message_section_is_orange() throws Throwable {
        String colorBackGround = "rgba(252, 236, 152, 1)";

        String color = LifeWay.AEM.getCssValueXpath("//*[@id=\"formSendEmail\"]/div[2]/div[2]/div/fieldset[1]/div/div", "background-color");
        assertEquals(colorBackGround, color);
        color = LifeWay.AEM.getCssValueXpath("//*[@id=\"formSendEmail\"]/div[2]/div[2]/div/fieldset[2]/div/div", "background-color");
        assertEquals(colorBackGround, color);
        color = LifeWay.AEM.getCssValueXpath("//*[@id=\"formSendEmail\"]/div[2]/div[2]/div/fieldset[3]/div/div", "background-color");
        assertEquals(colorBackGround, color);
        color = LifeWay.AEM.getCssValueXpath("//*[@id=\"formSendEmail\"]/div[2]/div[2]/div/fieldset[4]/div/div", "background-color");
        assertEquals(colorBackGround, color);

        the_visitor_has_clicked_on_the_close_icon_of_the_modal_or_has_clicked_outside_the_modal_window();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: visitor clicks on "send me a copy" check box">
    @Given("^the visitor has filled correctly the email form$")
    public void the_visitor_has_filled_correctly_the_email_form() throws Throwable {
        the_visitor_has_opened_the_email_share_modal();
        the_visitor_passes_captcha_and_clicks_the_submit_button();
    }

    @When("^the send me a copy check box is checked$")
    public void the_send_me_a_copy_check_box_is_checked() throws Throwable {
        LifeWay.AEM.clickByID("checkSendMeCopy");
    }

    @Then("^a copy is sent to your email field email data$")
    public void a_copy_is_sent_to_your_email_field_email_data() throws Throwable {
        LifeWay.AEM.clickByID("buttonSendMail");

        String message = LifeWay.AEM.readByXpath("//*[@id=\"formSendEmail\"]/div[2]/div[1]/div/div[1]");
        String message_ok = "Your message has been sent";
        LifeWay.AEM.clickByXpath("//*[@id=\"formSendEmail\"]/div[1]/button");

        assertEquals(message_ok, message);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: visitor clicks on the close icon of the modal or outside the modal">
    @Given("^the visitor has opened the email share modal$")
    public void the_visitor_has_opened_the_email_share_modal() throws Throwable {
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div/div/div/div[1]/div/div[3]/div/div[3]/div/ul/li[3]/a");
        fillFormCorrect();
    }

    @When("^the visitor has clicked on the close icon of the modal or has clicked outside the modal window$")
    public void the_visitor_has_clicked_on_the_close_icon_of_the_modal_or_has_clicked_outside_the_modal_window() throws Throwable {
        LifeWay.AEM.clickByXpath("//*[@id=\"formSendEmail\"]/div[1]/button");
    }

    @Then("^The modal is closed$")
    public void the_modal_is_closed() throws Throwable {
        boolean close = !LifeWay.AEM.isVisibleById("//*[@id=\"emailModal\"]");

        assertTrue(close);
    }

    @Then("^the information is not saved$")
    public void the_information_is_not_saved() throws Throwable {
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div/div/div/div[1]/div/div[3]/div/div[3]/div/ul/li[3]/a");

        String texto = LifeWay.AEM.getValueById("inputTo");
        assertEquals(texto, LifeWay.EMPTY);

        texto = LifeWay.AEM.getValueById("inputMessage");
        assertEquals(texto, LifeWay.EMPTY);

        texto = LifeWay.AEM.getValueById("inputYourName");
        assertEquals(texto, LifeWay.EMPTY);

        texto = LifeWay.AEM.getValueById("inputFrom");
        assertEquals(texto, LifeWay.EMPTY);

        boolean selected = LifeWay.AEM.isSelectedById("checkSendMeCopy");
        assertFalse(selected);

        LifeWay.AEM.finalizeAemConector();
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 357 Message Box">
    //<editor-fold defaultstate="collapsed" desc="Scenario: All the configured scenarios in Author must render as expected in all the following environments">
    @Given("^the user navigates to the Message Box$")
    public void the_user_navigates_to_the_Message_Box() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_MESSAGE_BOX);
    }

    @Given("^it contains a MessageBox content$")
    public void it_contains_a_MessageBox_content() throws Throwable {
        String classMessageBoxOrigin = "parbase section messagebox base xk-component xk-section-base ng-scope";
        String classMessageBox = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div", "class");

        assertEquals(classMessageBoxOrigin, classMessageBox);
    }

    @Then("^I can see the MessageBox content with the configured format in the Author instance$")
    public void i_can_see_the_MessageBox_content_with_the_configured_format_in_the_Author_instance() throws Throwable {
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 360 xk table">
    //<editor-fold defaultstate="collapsed" desc="Scenario: Visitor navigates to XK Table component">
    @Given("^The author has configured a XK Table component$")
    public void the_author_has_configured_a_XK_Table_component() throws Throwable {
    }

    @Given("^The visitor opens a page with a XK Table component$")
    public void the_visitor_opens_a_page_with_a_XK_Table_component() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_ARTICLE_PAGE);

    }

    @When("^the component xk table is rendered$")
    public void the_component_xk_table_is_rendered() throws Throwable {

        boolean visibe = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[13]");
        assertTrue(visibe);
    }

    @Then("^the Table should be shown, with a head and body$")
    public void the_Table_should_be_shown_with_a_head_and_body() throws Throwable {
        boolean visible = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[13]/div/table/tbody/tr[1]/th[1]");
        assertTrue(visible);

        boolean visiblee = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[13]/div/table/tbody/tr[1]/th[2]");
        assertTrue(visiblee);

        boolean isvisible = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[13]/div/table/tbody/tr[1]/th[3]");
        assertTrue(isvisible);

        boolean isvisiblee = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[13]/div/table/tbody/tr[1]/th[4]");
        assertTrue(isvisiblee);

        boolean visibl = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[13]/div/table/tbody/tr[1]/th[5]");
        assertTrue(visibl);

        boolean visib = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[13]/div/table/tbody/tr[1]/th[6]");
        assertTrue(visib);

    }

    @Then("^the table contains the rows and columns defined by the author$")
    public void the_table_contains_the_rows_and_columns_defined_by_the_author() throws Throwable {
        String dateOrigin = "Date";
        String dateValue = LifeWay.AEM.readByXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[13]/div/table/tbody/tr[1]/th[1]");
        assertEquals(dateOrigin, dateValue);

        String invoiceOrigin = "Invoice";
        String invoiceValue = LifeWay.AEM.readByXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[13]/div/table/tbody/tr[1]/th[2]");
        assertEquals(invoiceOrigin, invoiceValue);

        String trackOrigin = "Track Shipping";
        String trackValue = LifeWay.AEM.readByXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[13]/div/table/tbody/tr[1]/th[3]");
        assertEquals(trackOrigin, trackValue);

        String datOrigin = "date";
        String datValue = LifeWay.AEM.readByXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[13]/div/table/tbody/tr[1]/th[4]");
        assertEquals(datOrigin, datValue);

        String invoiOrigin = "Invoice";
        String invoiValue = LifeWay.AEM.readByXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[13]/div/table/tbody/tr[1]/th[5]");
        assertEquals(invoiOrigin, invoiValue);

        String shippingOrigin = "track Shipping";
        String shippingValue = LifeWay.AEM.readByXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[13]/div/table/tbody/tr[1]/th[6]");
        assertEquals(shippingOrigin, shippingValue);

        String usuarioOrigin = "3/15/10";
        String usuarioValue = LifeWay.AEM.readByXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[13]/div/table/tbody/tr[2]/td[1]");
        assertEquals(usuarioOrigin, usuarioValue);

        String usuarioInOrigin = "123456";
        String usuariInValue = LifeWay.AEM.readByXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[13]/div/table/tbody/tr[2]/td[2]/p[1]");
        assertEquals(usuarioInOrigin, usuariInValue);

        String usuarioTrackOrigin = "1234454545454";
        String usuarioTrackvalue = LifeWay.AEM.readByXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[13]/div/table/tbody/tr[2]/td[3]");
        assertEquals(usuarioTrackOrigin, usuarioTrackvalue);

        String usuarioDaTOrigin = "3/15/10";
        String usuarioDatValue = LifeWay.AEM.readByXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[13]/div/table/tbody/tr[2]/td[4]");
        assertEquals(usuarioDaTOrigin, usuarioDatValue);

        String usuarioInvoOrigin = "121212122";
        String usuarioInvoValue = LifeWay.AEM.readByXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[13]/div/table/tbody/tr[2]/td[5]");
        assertEquals(usuarioInvoOrigin, usuarioInvoValue);

        String usuarioTraOrigin = "54545454545454";
        String usuarioTraValue = LifeWay.AEM.readByXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[13]/div/table/tbody/tr[2]/td[6]");
        assertEquals(usuarioTraOrigin, usuarioTraValue);
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 366 Embed Content">
    //<editor-fold defaultstate="collapsed" desc="Scenario: Visitor is able to see the video">
    @Given("^The visitor opens an specific page$")
    public void the_visitor_opens_an_specific_page() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_ARTICLE_PAGE);
    }

    @When("^he scrolls down$")
    public void he_scrolls_down() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_ARTICLE_PAGE + "#footnotes");
    }

    @Then("^he will be able to see the embed content$")
    public void he_will_be_able_to_see_the_embed_content() throws Throwable {
        boolean isVisible = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[8]");
        assertTrue(isVisible);
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 383 Simple button">
    //<editor-fold defaultstate="collapsed" desc="Scenario: The visitor clicks the button">
    @Given("^the visitor sees a button$")
    public void the_visitor_sees_a_button() throws Throwable {
        boolean isVisible = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div/div[1]/a");
        assertTrue(isVisible);
    }

    @When("^the visitor clicks the button$")
    public void the_visitor_clicks_the_button() throws Throwable {
        hrefOrigin = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div[1]/div/div/div[1]/a", "href");
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div/div/div[1]/div/div/div[1]/a");
    }

    @Then("^the visitor should redirect to \"([^\"]*)\"$")
    public void the_visitor_should_redirect_to(String arg1) throws Throwable {
        String url = LifeWay.AEM.getUrl();
        assertEquals(hrefOrigin, url);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: Button Color (Added by QA) Primary">
    @Given("^Button: \"([^\"]*)\", Color field has been configured in Author with \"([^\"]*)\" option$")
    public void button_Color_field_has_been_configured_in_Author_with_option(String arg1, String arg2) throws Throwable {
        String textButton = LifeWay.AEM.readByXpath(arg1);
        assertTrue(textButton.contains(arg2));
    }

    @When("^the visitor navigate to Two Column Page$")
    public void the_visitor_navigate_to_Two_Column_Page() throws Throwable {
    }

    @Then("^the button should: \"([^\"]*)\" display in \"([^\"]*)\"$")
    public void the_button_should_display_in(String arg1, String arg2) throws Throwable {
        String colorButton = LifeWay.AEM.getCssValueXpath(arg1, "background-color");
        assertEquals(arg2, colorButton);
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 386 XK Concact Resources">
    //<editor-fold defaultstate="collapsed" desc="Scenario: The visitor wants to see the component displayed">
    @Given("^the author adds Contact Us & Helpful Resources in Author$")
    public void the_author_adds_Contact_Us_Helpful_Resources_in_Author() throws Throwable {
    }

    @When("^the visitor navigates Publish$")
    public void the_visitor_navigates_Publish() throws Throwable {
        LifeWay.openUrl(LifeWay.URL_HOME_PAGE);
    }

    @Then("^he will see the component displayed$")
    public void he_will_see_the_component_displayed() throws Throwable {
        boolean isDisplayed = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div[9]");
        assertTrue(isDisplayed);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: the visitor clicks on the icon">
    @Given("^the visitor navigates to a page with XK Contact & Resources component$")
    public void the_visitor_navigates_to_a_page_with_XK_Contact_Resources_component() throws Throwable {
    }

    @When("^the visitor clicks on an icon$")
    public void the_visitor_clicks_on_an_icon() throws Throwable {
        hrefOrigin = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div[9]/div/div/div[1]/div[3]/div[1]/a", "href");
        anchorTarget = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div[9]/div/div/div[1]/div[3]/div[1]/a", "target");
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div[9]/div/div/div[1]/div[3]/div[1]/a");
    }

    @Then("^he is redirected to Icon URL$")
    public void he_is_redirected_to_Icon_URL() throws Throwable {
        Thread.sleep(3000);
        hrefOrigin = hrefOrigin.replace("http://", "https://");
        String url = LifeWay.AEM.getUrl(1);
        assertEquals(hrefOrigin, url);
    }

    @Then("^the new page is displayed as \"([^\"]*)\" specifies$")
    public void the_new_page_is_displayed_as_specifies(String arg1) throws Throwable {
        assertEquals(arg1, anchorTarget);
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 391 XK Quick Item Add">
    //<editor-fold defaultstate="collapsed" desc="Scenario: the visitor enters productID and qty and click add">
    @Given("^a visitor has entered a product id$")
    public void a_visitor_has_entered_a_product_id() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_CHECKOUT_QAONLY);
    }

    @Given("^the product ID is valid$")
    public void the_product_ID_is_valid() throws Throwable {
        LifeWay.AEM.writeByID("quantity", "001054710");
        LifeWay.AEM.writeByID("quick-item-add__qty", "1");//001054710
    }

    @Given("^sets the qty equal more  the minimum of the product$")
    public void sets_the_qty_equal_more_the_minimum_of_the_product() throws Throwable {
    }

    @Given("^less equal than the maximum of the product$")
    public void less_equal_than_the_maximum_of_the_product() throws Throwable {
    }

    @When("^the visitor clicks add$")
    public void the_visitor_clicks_add() throws Throwable {
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div/div/div[2]/div/div/div/div[1]/div/div/div[3]/a");
    }

    @Then("^The item should be added to the cart$")
    public void the_item_should_be_added_to_the_cart() throws Throwable {
        boolean visible = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div/div[2]/div/div[1]/div/div[2]/div/div[1]/a");
        assertTrue(visible);

    }

    @Then("^the totals should be updated$")
    public void the_totals_should_be_updated() throws Throwable {

    }

    @Then("^the items in the cart should be updated$")
    public void the_items_in_the_cart_should_be_updated() throws Throwable {
        String valuee = "cartsummary.totalItem > 0";
        String valueCarritoo = LifeWay.AEM.getPropertieXpath("//*[@id=\"head-nav\"]/div/ul/li[2]/a/span", "ng-show");

        assertEquals(valuee, valueCarritoo);
    }

    @Then("^the cart item should be updated$")
    public void the_cart_item_should_be_updated() throws Throwable {

    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: the product ID is already on the cart">
    @Given("^an item is added to the cart$")
    public void an_item_is_added_to_the_cart() throws Throwable {
        Thread.sleep(3000);
        LifeWay.AEM.writeByID("quantity", "005129632");
        LifeWay.AEM.writeByID("quick-item-add__qty", "1");
    }

    @Given("^the item is already in the cart$")
    public void the_item_is_already_in_the_cart() throws Throwable {

    }

    @When("^the add button is clicked$")
    public void the_add_button_is_clicked() throws Throwable {
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div/div/div[2]/div/div/div/div[1]/div/div/div[3]/a");
    }

    @Then("^the quantities of both items should be added$")
    public void the_quantities_of_both_items_should_be_added() throws Throwable {
        //boolean visib = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div");
        //assertTrue(visib);
    }

    @Then("^the cart items should be updated$")
    public void the_cart_items_should_be_updated() throws Throwable {
        String value = "cartsummary.totalItem > 0";
        String valueCarito = LifeWay.AEM.getPropertieXpath("//*[@id=\"head-nav\"]/div/ul/li[2]/a/span", "ng-show");

        assertEquals(value, valueCarito);
    }

    @Then("^the cart totals should be updated$")
    public void the_cart_totals_should_be_updated() throws Throwable {
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: Field validation">
    @Given("^the existence of two fields$")
    public void the_existence_of_two_fields() throws Throwable {

    }

    @When("^data is entered$")
    public void data_is_entered() throws Throwable {
        LifeWay.AEM.cleanByID("quantity");
        LifeWay.AEM.cleanByID("quick-item-add__qty");
        LifeWay.AEM.writeByID("quantity", "005129632");
        LifeWay.AEM.writeByID("quick-item-add__qty", "0");
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div/div/div[2]/div/div/div/div[1]/div/div/div[3]/a");
        Thread.sleep(3000);
    }

    @Then("^the error message is \"([^\"]*)\"$")
    public void the_error_message_is(String arg1) throws Throwable {
        //arg1 = "alert alert-info xk-quick-alert";
        String valid = LifeWay.AEM.readByXpath("/html/body/div[3]/div/div[2]");
        assertEquals(arg1, valid);

        LifeWay.AEM.finalizeAemConector();
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 393 Companion Product">
    //<editor-fold defaultstate="collapsed" desc="Scenario: Visitor sees the component">
    @Given("^a product with companion products$")
    public void a_product_with_companion_products() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_COMPANION_PRODUCTS);
    }

    @Then("^the component should be rendered as the screenshot$")
    public void the_component_should_be_rendered_as_the_screenshot() throws Throwable {
    }

    @Then("^for each item, if has variants, the default SKU option should be preselected$")
    public void for_each_item_if_has_variants_the_default_SKU_option_should_be_preselected() throws Throwable {
        String value = "005678143.2016-WIN-default";
        String valueSelected = LifeWay.AEM.getValueByXpath("//*[@id=\"companion-products\"]/div[2]/div[2]/div/div/div[2]/div/div[1]/div/select");

        assertEquals(value, valueSelected);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: Visitor selects variant">
    @Given("^a companion product with variants$")
    public void a_companion_product_with_variants() throws Throwable {
        a_product_with_companion_products();
        originVariant = LifeWay.AEM.readByXpath("//*[@id=\"companion-products\"]/div[2]/div[2]/div/div/div[2]/div/div[3]/span");
    }

    @When("^the visitor selects a variant$")
    public void the_visitor_selects_a_variant() throws Throwable {
        LifeWay.AEM.selectDropDownByXpath("//*[@id=\"companion-products\"]/div[2]/div[2]/div/div/div[2]/div/div[1]/div/select", "Small");
    }

    @Then("^the variant info and pricing is updated$")
    public void the_variant_info_and_pricing_is_updated() throws Throwable {
        variant = LifeWay.AEM.readByXpath("//*[@id=\"companion-products\"]/div[2]/div[2]/div/div/div[2]/div/div[3]/span");

        assertNotEquals(originVariant, variant);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: Visitor clicks on add to cart">
    @Given("^a visitor wants to add a companion product to his cart$")
    public void a_visitor_wants_to_add_a_companion_product_to_his_cart() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_COMPANION_PRODUCTS);
    }

    @When("^the visitor clicks on the cart button$")
    public void the_visitor_clicks_on_the_cart_button() throws Throwable {
        LifeWay.AEM.clickByXpath("//*[@id=\"companion-products\"]/div[2]/div[1]/div/div/div[2]/div/div[4]/button");
    }

    @Then("^the default sky option is added to the cart$")
    public void the_default_sky_option_is_added_to_the_cart() throws Throwable {
        Thread.sleep(3000);
    }

    @Then("^the cart summary pop up modal is displayed$")
    public void the_cart_summary_pop_up_modal_is_displayed() throws Throwable {
        String visible = "block";
        String visibleValue = LifeWay.AEM.getCssValueID("buyBoxModal", "display");

        assertEquals(visible, visibleValue);

        he_clicks_on_continue_shopping_button();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: Visitor clicks on add to cart b">
    @When("^the quantity is <= (\\d+)$")
    public void the_quantity_is(int arg1) throws Throwable {
        LifeWay.AEM.cleanByXpath("//*[@id=\"companion-products\"]/div[2]/div[1]/div/div/div[2]/div/div[2]/input");
        LifeWay.AEM.writeByXpath("//*[@id=\"companion-products\"]/div[2]/div[1]/div/div/div[2]/div/div[2]/input", String.valueOf(arg1));
    }

    @Then("^The Max Quantity message is shown$")
    public void the_Max_Quantity_message_is_shown() throws Throwable {
        boolean exist = LifeWay.AEM.existsElementByXpath("//*[@id=\"companion-products\"]/div[2]/div[1]/div/div/div[2]/div/div[2]/div");
        assertTrue(exist);
    }

    @Then("^the item is not added to the cart$")
    public void the_item_is_not_added_to_the_cart() throws Throwable {
        String disabled = "true";
        String disabledValue = LifeWay.AEM.getPropertieXpath("//*[@id=\"companion-products\"]/div[2]/div[1]/div/div/div[2]/div/div[4]/button", "disabled");
        assertEquals(disabled, disabledValue);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: Visitor clicks on a companion product">
    @Given("^a product page has been loaded$")
    public void a_product_page_has_been_loaded() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_COMPANION_PRODUCTS);
    }

    @When("^the visitor clicks on a companion product name$")
    public void the_visitor_clicks_on_a_companion_product_name() throws Throwable {
        tabOrigin = LifeWay.AEM.getTabs();
        LifeWay.AEM.clickByXpath("//*[@id=\"companion-products\"]/div[2]/div[1]/div/div/div[1]/a");
    }

    @Then("^the product's page should be loaded on a new tab$")
    public void the_product_s_page_should_be_loaded_on_a_new_tab() throws Throwable {
        tab = LifeWay.AEM.getTabs();

        assertNotEquals(tabOrigin, tab);
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 395 Cart Summary">
    //<editor-fold defaultstate="collapsed" desc="Scenario: Visitor sees the cart summary">
    @Given("^an item has been added to the cart$")
    public void an_item_has_been_added_to_the_cart() throws Throwable {
        he_clicks_on_an_add_to_cart_button();
    }

    @When("^the cart summary is displayed$")
    public void the_cart_summary_is_displayed() throws Throwable {
    }

    @Then("^the total of the items in the cart, the product default image, product name and price should be displayed as the screenshot$")
    public void the_total_of_the_items_in_the_cart_the_product_default_image_product_name_and_price_should_be_displayed_as_the_screenshot() throws Throwable {

        boolean exist = LifeWay.AEM.existsElementByXpath("//*[@id=\"buyBoxModal\"]/div/div/div/div[2]/div/div[2]/table/tbody/tr[1]/td[1]");
        assertTrue(exist);

        exist = LifeWay.AEM.existsElementByID("modalImage");
        assertTrue(exist);

        exist = LifeWay.AEM.existsElementByID("modalPrdTitle");
        assertTrue(exist);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: Visitor clicks on continue shopping">
    @Given("^the visitor decided to keep shopping after adding an item to his cart$")
    public void the_visitor_decided_to_keep_shopping_after_adding_an_item_to_his_cart() throws Throwable {
        he_clicks_on_an_add_to_cart_button();
    }

    @When("^he clicks on continue shopping button$")
    public void he_clicks_on_continue_shopping_button() throws Throwable {
        //TMP
        //LifeWay.AEM.clickByXpath("//*[@id=\"buyBoxModal\"]/div/div/div/div[3]/button[1]");
    }

    @Then("^the modal window should fade$")
    public void the_modal_window_should_fade() throws Throwable {
        Thread.sleep(1000);
        String visible = "none";
        String visibleValue = LifeWay.AEM.getCssValueID("buyBoxModal", "display");

        assertEquals(visible, visibleValue);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: Visitor clicks on checkout">
    @Given("^the visitor decided to checkout after adding an item to his cart$")
    public void the_visitor_decided_to_checkout_after_adding_an_item_to_his_cart() throws Throwable {
        he_clicks_on_an_add_to_cart_button();
    }

    @When("^he clicks on checkout button$")
    public void he_clicks_on_checkout_button() throws Throwable {
        //TMP
        //LifeWay.AEM.clickByXpath("//*[@id=\"buyBoxModal\"]/div/div/div/div[3]/button[2]");
    }

    @Then("^the modal window should fade. The functionality will be develop later on$")
    public void the_modal_window_should_fade_The_functionality_will_be_develop_later_on() throws Throwable {
        //TODO
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 397 Buy Box">
    //<editor-fold defaultstate="collapsed" desc="Displaying a product with multiple SKU options">
    @Given("^a product with multiple SKU options$")
    public void a_product_with_multiple_SKU_options() throws Throwable {
    }

    @When("^is displayed on the product page$")
    public void is_displayed_on_the_product_page() throws Throwable {
    }

    @Then("^the first price line is displayed as a range from the cheapest sku to the most expensive one$")
    public void the_first_price_line_is_displayed_as_a_range_from_the_cheapest_sku_to_the_most_expensive_one() throws Throwable {
        String range = LifeWay.AEM.readByXpath("//*[@id=\"buy-box-mobile\"]/div/div[1]/p[1]/span");
        assertNotEquals("", range);
    }

    @Then("^is followed by the list price$")
    public void is_followed_by_the_list_price() throws Throwable {
        String price = LifeWay.AEM.readByXpath("//*[@id=\"buy-box-mobile\"]/div/div[1]/p[2]/span[1]");
        assertNotEquals("", price);
    }

    @Then("^a dropdown list is displayed with links to the product pages of each of the SKU options available$")
    public void a_dropdown_list_is_displayed_with_links_to_the_product_pages_of_each_of_the_SKU_options_available() throws Throwable {
        boolean visible = LifeWay.AEM.isVisibleById("xk-sku");
        assertTrue(visible);
    }

    @Then("^the shown text is obtained from the product data$")
    public void the_shown_text_is_obtained_from_the_product_data() throws Throwable {
        LifeWay.AEM.clickByID("xk-sku");
        LifeWay.AEM.tabById("xk-sku");
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: Displaying a product with discount">
    @Given("^a product with Sale Price on the product data$")
    public void a_product_with_Sale_Price_on_the_product_data() throws Throwable {
        LifeWay.AEM.selectDropDownById("xk-sku", "Small");
    }

    @Then("^the Sale Price Label is followed by the Sale price amount$")
    public void the_Sale_Price_Label_is_followed_by_the_Sale_price_amount() throws Throwable {
        String price = LifeWay.AEM.readByXpath("//*[@id=\"buy-box-mobile\"]/div/div[1]/p[2]/span[1]");
        assertNotEquals("", price);

        price = LifeWay.AEM.readByXpath("//*[@id=\"buy-box-mobile\"]/div/div[1]/p[3]");
        assertNotEquals("", price);
    }

    @Then("^the Regular Price Label is displayed with \"([^\"]*)\" color, followed by the List price from PIM product data$")
    public void the_Regular_Price_Label_is_displayed_with_color_followed_by_the_List_price_from_PIM_product_data(String arg1) throws Throwable {
        String color = LifeWay.AEM.getCssValueXpath("//*[@id=\"buy-box-mobile\"]/div/div[1]/p[3]", "color");
        assertEquals(arg1, color);
    }

    @Then("^the You Save Label is displayed with \"([^\"]*)\" color, followed by the percentage value of the difference between the Regular price and sale price$")
    public void the_You_Save_Label_is_displayed_with_color_followed_by_the_percentage_value_of_the_difference_between_the_Regular_price_and_sale_price(String arg1) throws Throwable {
        String color = LifeWay.AEM.getCssValueXpath("//*[@id=\"buy-box-mobile\"]/div/div[1]/p[4]", "color");
        assertEquals(arg1, color);

        String price = LifeWay.AEM.readByXpath("//*[@id=\"buy-box-mobile\"]/div/div[1]/p[4]");
        boolean save = price.matches("You Save \\d*%.*");
        assertTrue(save);
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: Displaying common pricing and shipping details from a product">

    @Given("^a product page is being requested$")
    public void a_product_page_is_being_requested() throws Throwable {
        a_product_with_Sale_Price_on_the_product_data();
    }

    @When("^the Buy box is rendered$")
    public void the_Buy_box_is_rendered() throws Throwable {
        boolean isRender = LifeWay.AEM.isVisibleById("buy-box-mobile");
        assertTrue(isRender);
    }

    @Then("^if there are bulk discounts available for this component, the Bulk discounts label is displayed with a link that opens a tooltip with the list of discounts$")
    public void if_there_are_bulk_discounts_available_for_this_component_the_Bulk_discounts_label_is_displayed_with_a_link_that_opens_a_tooltip_with_the_list_of_discounts() throws Throwable {
        String label = "Bulk Discount";
        String labelDisplayed = LifeWay.AEM.readByXpath("//*[@id=\"buy-box-mobile\"]/div/div[1]/p[5]/a");
        assertEquals(label, labelDisplayed);
    }

    @Then("^under Bulk discounts label the availability message is displayed$")
    public void under_Bulk_discounts_label_the_availability_message_is_displayed() throws Throwable {
        boolean isDisplayed = LifeWay.AEM.isVisibleById("product-quantity");
        assertTrue(isDisplayed);
    }

    @Then("^the Add to cart button is displayed with \"([^\"]*)\" button text as a caption$")
    public void the_Add_to_cart_button_is_displayed_with_as_a_caption(String arg1) throws Throwable {
        String caption = LifeWay.AEM.readById("addToCart");
        assertEquals(arg1, caption);
    }

    @Then("^if the product is not buyable, this information comes from the product data, the button is rendered with a grey color and disabled$")
    public void if_the_product_is_not_buyable_this_information_comes_from_the_product_data_the_button_is_rendered_with_a_grey_color_and_disabled() throws Throwable {
        LifeWay.AEM.cleanByID("product-quantity");

        String valid = "true";
        String validValue = LifeWay.AEM.getPropertieID("addToCart", "data-qtyvalid");
        assertEquals(valid, validValue);
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 403 Text Banners">
    //<editor-fold defaultstate="collapsed" desc="Scenario: The visitor clicks on the button or image of a banner">
    @Given("^the visitor sees the banners$")
    public void the_visitor_sees_the_banners() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_HOME_PAGE);
    }

    @When("^the visitor clicks on the image or the button of one$")
    public void the_visitor_clicks_on_the_image_or_the_button_of_one() throws Throwable {
        hrefOrigin = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div/div[2]/a[1]", "href");
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div/div/div/div[2]/a[1]");
    }

    @Then("^the visitor is redirected to URL$")
    public void the_visitor_is_redirected_to_URL() throws Throwable {
        String url = LifeWay.AEM.getUrl();

        assertEquals(hrefOrigin, url);
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 405 Article Headline">
    @Given("^an article page is opened$")
    public void an_article_page_is_opened() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_ARTICLE_PAGE);
    }

    @When("^the Article Headline component is rendered$")
    public void the_Article_Headline_component_is_rendered() throws Throwable {
        String classHeadLineOrigin = "articleheadline parbase articleHeadline base xk-component xk-section-base ng-scope";
        String classHeadLine = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[1]", "class");

        assertEquals(classHeadLineOrigin, classHeadLine);
    }

    @When("^the data is obtained from the page properties$")
    public void the_data_is_obtained_from_the_page_properties() throws Throwable {
    }

    @Then("^the article headline should be shown at the top of the article page$")
    public void the_article_headline_should_be_shown_at_the_top_of_the_article_page() throws Throwable {
        String classNextElementOrigin = "articlesubtitle atricleSubtitle parbase base xk-component xk-section-base ng-scope";
        String classNextElement = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[2]", "class");

        assertEquals(classNextElementOrigin, classNextElement);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 414 Banner Ad">
    //<editor-fold defaultstate="collapsed" desc="Scenario: A visitor clicks on the banner image">
    @Given("^a visitor navigates to a page with a banner$")
    public void a_visitor_navigates_to_a_page_with_a_banner() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_BANNER_AD);
    }

    @When("^the visitor clicks on the banner$")
    public void the_visitor_clicks_on_the_banner() throws Throwable {
        tabOrigin = LifeWay.AEM.getTabs();
        hrefOrigin = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div[1]/div/div/div/a", "href");
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div[1]/div/div/div/a");
    }

    @Then("^he should be redirected to the configured URL\\.$")
    public void he_should_be_redirected_to_the_configured_URL() throws Throwable {
        tab = LifeWay.AEM.getTabs();
        String url = LifeWay.AEM.getUrl(tab - 1);

        Thread.sleep(3000);

        assertNotEquals(tabOrigin, tab);
        assertEquals(hrefOrigin, url);
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 432 XK Image With Caption">
    //<editor-fold defaultstate="collapsed" desc="Scenario: Caption mode set">
    @Given("^the author has checked the author mode$")
    public void the_author_has_checked_the_author_mode() throws Throwable {
    }

    @When("^the component image with caption is rendered$")
    public void the_component_image_with_caption_is_rendered() throws Throwable {
        boolean isRender = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[1]");
        assertTrue(isRender);
    }

    @Then("^a box should be rendered containing the image$")
    public void a_box_should_be_rendered_containing_the_image() throws Throwable {
        boolean isRender = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[1]/figure");
        assertTrue(isRender);
    }

    @Then("^at the bottom of the box should be displayed Credit, Caption, Cite vertically from top to bottom in this order$")
    public void at_the_bottom_of_the_box_should_be_displayed_Credit_Caption_Cite_vertically_from_top_to_bottom_in_this_order() throws Throwable {
        boolean displayCredit = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[1]/figure/p");
        boolean displayCaption = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[1]/figure/figcaption");
        boolean displayCite = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[1]/figure/figcaption/cite");

        assertTrue(displayCredit);
        assertTrue(displayCaption);
        assertTrue(displayCite);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: caption mode disabled">
    @Given("^an image with the Caption Mode checkbox unchecked$")
    public void an_image_with_the_Caption_Mode_checkbox_unchecked() throws Throwable {
    }

    @Then("^the image is rendered without any box$")
    public void the_image_is_rendered_without_any_box() throws Throwable {
        boolean isRender = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[4]");
        assertTrue(isRender);

        isRender = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[4]/img");
        assertTrue(isRender);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: Large images">
    @Given("^the size selected is Large$")
    public void the_size_selected_is_Large() throws Throwable {
    }

    @Then("^the image is centered in the container$")
    public void the_image_is_centered_in_the_container() throws Throwable {
        String classOrigin = "figure large left";
        String classValue = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[3]/figure", "class");

        assertEquals(classOrigin, classValue);
    }

    @Then("^the alignment is moot$")
    public void the_alignment_is_moot() throws Throwable {
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: Alignment and Size (added by QA)">
    @Given("^the size selected is small$")
    public void the_size_selected_is_small() throws Throwable {
    }

    @Given("^the alignment selected is Right$")
    public void the_alignment_selected_is_Right() throws Throwable {
    }

    @Then("^the image will be aligned to the right in a small size$")
    public void the_image_will_be_aligned_to_the_right_in_a_small_size() throws Throwable {
        String classOrigin = "figure small right";
        String classValue = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[2]/figure", "class");

        assertEquals(classOrigin, classValue);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: Alignment and Size (Added by QA)">
    @Given("^the size selected is medium$")
    public void the_size_selected_is_medium() throws Throwable {
    }

    @Given("^the alignment selected is left$")
    public void the_alignment_selected_is_left() throws Throwable {
    }

    @Then("^the image will be aligned to the left in a medium size$")
    public void the_image_will_be_aligned_to_the_left_in_a_medium_size() throws Throwable {
        String classOrigin = "figure medium left";
        String classValue = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[1]/figure", "class");

        assertEquals(classOrigin, classValue);
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 436 xk testimonials">
    //<editor-fold defaultstate="collapsed" desc="Scenario: the visitor clicks on load more button">
    @Given("^the visitor is on the testimonials section$")
    public void the_visitor_is_on_the_testimonials_section() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_HOME_PAGE);

        boolean visible = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div[6]");
        assertTrue(visible);
    }

    @Given("^the \"([^\"]*)\" number is greater than nine$")
    public void the_number_is_greater_than_nine(String arg1) throws Throwable {

        LifeWay.AEM.clickByXpath("/html/body/div[3]/div[6]/div/div/div[2]/div[2]/a");
        Thread.sleep(3000);
        boolean visib = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div[6]/div/div/div[2]/div[1]/div[10]");
        assertTrue(visib);
    }

    @When("^the visitor clicks on the load more button$")
    public void the_visitor_clicks_on_the_load_more_button() throws Throwable {
    }

    @Then("^six more items should be loaded asynchronously if its possible$")
    public void six_more_items_should_be_loaded_asynchronously_if_its_possible() throws Throwable {

        boolean visiblee = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div[6]/div/div/div[2]/div[1]/div[6]");
        assertTrue(visiblee);

    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: there are no items to load">

    @Given("^the component has loaded all the configured \"([^\"]*)\"$")
    public void the_component_has_loaded_all_the_configured(String arg1) throws Throwable {
    }

    @When("^the visitor clicks in the load more button$")
    public void the_visitor_clicks_in_the_load_more_button() throws Throwable {
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div[6]/div/div/div[2]/div[2]/a");
        Thread.sleep(3000);

    }

    @Then("^the button should disappear$")
    public void the_button_should_disappear() throws Throwable {
        boolean visible = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div[6]/div/div/div[2]/div[2]/a");
        assertFalse(visible);
    }

    @Then("^no new items are displayed$")
    public void no_new_items_are_displayed() throws Throwable {
    }
    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 437 XK Related Articles">
    //<editor-fold defaultstate="collapsed" desc="Scenario: Manual mode enabled">

    @Given("^the Manual Mode checkbox is set$")
    public void the_Manual_Mode_checkbox_is_set() throws Throwable {
    }

    @When("^the component related articles is rendered$")
    public void the_component_related_articles_is_rendered() throws Throwable {
        boolean isRendered = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div[1]/div[6]/div[12]");
        assertTrue(isRendered);
    }

    @Then("^the configuration should override design configuration$")
    public void the_configuration_should_override_design_configuration() throws Throwable {
    }

    @Then("^the Image should be shown regarding if the specified article has or not thumbnail image configured$")
    public void the_Image_should_be_shown_regarding_if_the_specified_article_has_or_not_thumbnail_image_configured() throws Throwable {
        boolean existImage = LifeWay.AEM.existsElementByXpath("//*[@id=\"related-articles\"]/div[1]/img");
        assertTrue(existImage);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: The component is rendered">
    @Given("^a Related Articles component$")
    public void a_Related_Articles_component() throws Throwable {
        the_component_related_articles_is_rendered();
    }

    @Then("^it will show a list of Amount articles$")
    public void it_will_show_a_list_of_Amount_articles() throws Throwable {
        boolean listRealated = LifeWay.AEM.isVisibleById("related-articles");
        assertTrue(listRealated);
    }

    @Then("^if the article has a thumbnail, it will be shown$")
    public void if_the_article_has_a_thumbnail_it_will_be_shown() throws Throwable {
        the_Image_should_be_shown_regarding_if_the_specified_article_has_or_not_thumbnail_image_configured();
    }

    @Then("^the article title will be shown$")
    public void the_article_title_will_be_shown() throws Throwable {
        boolean exist = LifeWay.AEM.existsElementByXpath("//*[@id=\"related-articles\"]/div[1]/h4");
        assertTrue(exist);
    }

    @Then("^the title of the article will be a link to the article's page$")
    public void the_title_of_the_article_will_be_a_link_to_the_article_s_page() throws Throwable {
        boolean exist = LifeWay.AEM.existsElementByXpath("//*[@id=\"related-articles\"]/div[1]/h4/a");
        assertTrue(exist);
    }

    @Then("^the summary of the article will be shown under the article title$")
    public void the_summary_of_the_article_will_be_shown_under_the_article_title() throws Throwable {
        boolean exist = LifeWay.AEM.existsElementByXpath("//*[@id=\"related-articles\"]/div[1]/p");
        assertTrue(exist);
        LifeWay.AEM.finalizeAemConector();
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 439 XK Shopping Cart">
    //<editor-fold defaultstate="collapsed" desc="Scenario: the visitor clicks on the icon">
    @Given("^a page opened by an anonymous or logged user$")
    public void a_page_opened_by_an_anonymous_or_logged_user() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_CHECKOUT_QA);
    }

    @When("^the visitor clicks on the shopping cart icon$")
    public void the_visitor_clicks_on_the_shopping_cart_icon() throws Throwable {
        LifeWay.AEM.clickByXpath("//*[@id=\"head-nav\"]/div/ul/li[2]/a");
    }

    @Then("^should be redirected to the shopping cart page$")
    public void should_be_redirected_to_the_shopping_cart_page() throws Throwable {
        boolean visible = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div/div");
        assertTrue(visible);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: an item is added/removed to the cart">
    @Given("^an anonymous or logged user$")
    public void an_anonymous_or_logged_user() throws Throwable {

    }

    @When("^he adds an item to the cart$")
    public void he_adds_an_item_to_the_cart() throws Throwable {
        LifeWay.AEM.writeByID("quantity", "001054710");
        LifeWay.AEM.writeByID("quick-item-add__qty", "2");
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div/div/div[2]/div/div/div/div[1]/div/div/div[3]/a");
    }

    @Then("^the cart should show a red circle with the total number of items on the current cart$")
    public void the_cart_should_show_a_red_circle_with_the_total_number_of_items_on_the_current_cart() throws Throwable {

        String value = "cartsummary.totalItem > 0";
        String valueCarrito = LifeWay.AEM.getPropertieXpath("//*[@id=\"head-nav\"]/div/ul/li[2]/a/span", "ng-show");

        assertEquals(value, valueCarrito);
    }

    @Then("^is updated when the page is loaded$")
    public void is_updated_when_the_page_is_loaded() throws Throwable {

    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: there is no items on the cart">

    @Given("^a visitor with no items on his cart$")
    public void a_visitor_with_no_items_on_his_cart() throws Throwable {

        //boolean visi = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div/div/div[1]/div/div/div/div[2]");
        //assertTrue(visi);
    }

    @When("^the shopping cart icon is shown$")
    public void the_shopping_cart_icon_is_shown() throws Throwable {
        boolean visib = LifeWay.AEM.isVisibleXpath("//*[@id=\"head-nav\"]/div/ul/li[2]/a");
        assertTrue(visib);
    }

    @Then("^it should not have the red circle$")
    public void it_should_not_have_the_red_circle() throws Throwable {
        boolean notvisi = LifeWay.AEM.isVisibleXpath("//*[@id=\"head-nav\"]/div/ul/li[2]/a/span");
        assertFalse(notvisi);

    }
    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 443 XK Sponsors & Brands">
    //<editor-fold defaultstate="collapsed" desc="Scenario: the main image is not configured">

    @Given("^the author doesn't configured the \"([^\"]*)\" field$")
    public void the_author_doesn_t_configured_the_field(String arg1) throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_HOME_PAGE);
    }

    @When("^the component xk Sponsors is rendered$")
    public void the_component_xk_Sponsors_is_rendered() throws Throwable {
        boolean visibl = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div[7]");
        assertTrue(visibl);
    }

    @Then("^the sponsors images should be aligned at the left$")
    public void the_sponsors_images_should_be_aligned_at_the_left() throws Throwable {
        String alignOrigin = "block";
        String alignValue = LifeWay.AEM.getCssValueXpath("/html/body/div[3]/div[7]/div/div/div[1]", "display");
        assertEquals(alignOrigin, alignValue);
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: a visitor clicks on a sponsor image">
    @Given("^the visitor sees a sponsor image$")
    public void the_visitor_sees_a_sponsor_image() throws Throwable {
        boolean visiblee = LifeWay.AEM.isVisibleXpath("/html/body/div[3]/div[7]/div/div/div[2]/div[1]/div[1]/a/img");
        assertTrue(visiblee);
    }

    @Given("^the image has the \"([^\"]*)\" field configured$")
    public void the_image_has_the_field_configured(String arg1) throws Throwable {
        hrefOrigin = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div[7]/div/div/div[2]/div[1]/div[1]/a", "href");
        assertEquals(hrefOrigin, arg1);
    }

    @When("^the visitor clicks on one sponsor image$")
    public void the_visitor_clicks_on_one_sponsor_image() throws Throwable {
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div[7]/div/div/div[2]/div[1]/div[1]/a");
    }

    @Then("^the visitor should be redirected to \"([^\"]*)\" page$")
    public void the_visitor_should_be_redirected_to_page(String arg1) throws Throwable {
        Thread.sleep(3000);
        String dire = LifeWay.AEM.getUrl();
        assertEquals(arg1, dire);

    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 702 Configuration template">
    @Given("^the author is configuring header and footer$")
    public void the_author_is_configuring_header_and_footer() throws Throwable {
    }

    @Given("^has opened the dialog to create a page$")
    public void has_opened_the_dialog_to_create_a_page() throws Throwable {
        LifeWay.AEM.clickByXpath("/html/body/div[2]/div[2]/div[3]/div[2]/div/div[1]/nav[1]/div[1]/a");
    }

    @When("^he selects the template Configuration template$")
    public void he_selects_the_template_Configuration_template() throws Throwable {
        LifeWay.AEM.clickByXpath("//*[@id=\"coral-50\"]/div/a[1]");
        Thread.sleep(4000);
        LifeWay.AEM.clickByXpath("/html/body/form/div/div/div[1]/div/div[1]/article/a");
        LifeWay.AEM.clickByXpath("/html/body/form/nav/button");
        Thread.sleep(2000);

        LifeWay.AEM.writeByXpath("//*[@id=\"coral-1\"]/div/section[1]/div[1]/input", "LifeWay_US702");
        LifeWay.AEM.writeByXpath("//*[@id=\"coral-1\"]/div/section[1]/div[2]/div/input", "US 702 Configurate Template");

        LifeWay.AEM.clickByXpath("/html/body/form/nav/button[2]");
        Thread.sleep(1000);
        LifeWay.AEM.clickByXpath("/html/body/div[3]/div[3]/button[1]");
    }

    @Then("^a page is created with only one parsys to insert configuration components$")
    public void a_page_is_created_with_only_one_parsys_to_insert_configuration_components() throws Throwable {
        LifeWay.openUrl("http://qaqc-deb.xumak.gt:4502/editor.html/content/LifeWay_US702.html");
        //Thread.sleep(3000);
        //LifeWay.AEM.inEditMode();
        //Thread.sleep(3000);
        // String title = LifeWay.AEM.getPropertieXpath("//div[contains(@class, 'cq-Overlay cq-Overlay--component cq-droptarget cq-Overlay--placeholder')]", "title");
        //assertEquals(title, "Drag components here");

        LifeWay.AEM.finalizeAemConector();
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 2263 alignment social share and buttons">
    //<editor-fold defaultstate="collapsed" desc="Scenario: Visitor clicks on main call to action">
    @Given("^the author has set \"([^\"]*)\"$")
    public void the_author_has_set(String arg1) throws Throwable {

    }

    @Given("^the authorr hass sset \"([^\"]*)\"$")
    public void the_authorr_hass_sset(String arg1) throws Throwable {

    }

    @When("^the visitor clicks on the blue button$")
    public void the_visitor_clicks_on_the_blue_button() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_HOMEPAGE_QAONLY);
        hrefOrigin = LifeWay.AEM.getPropertieXpath("/html/body/div[3]/div[1]/div/div/div[1]/p[4]/a", "href");

        LifeWay.AEM.clickByXpath("/html/body/div[3]/div[1]/div/div/div[1]/p[4]/a");
    }

    @Then("^the visitor is redirected to Main Call to Action URL$")
    public void the_visitor_is_redirected_to_Main_Call_to_Action_URL() throws Throwable {
        Thread.sleep(3000);
        hrefOrigin = hrefOrigin.replace("http", "https").concat("?gws_rd=ssl");
        String diree = LifeWay.AEM.getUrl(1);
        assertEquals(hrefOrigin, diree);
    }

    @Then("^the new page opens as specified in \"([^\"]*)\"$")
    public void the_new_page_opens_as_specified_in(String arg1) throws Throwable {
        /**
         * COMPROBAR REDIRECCIONAMIENTO CON ATRIBUTO TARGET.
         */

    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="">
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: visitor does not define main call to action">
    @Given("^the authors has not set a \"([^\"]*)\"$")
    public void the_authors_has_not_set_a(String arg1) throws Throwable {
    }

    @When("^the componente is rendered$")
    public void the_componente_is_rendered() throws Throwable {

    }

    @Then("^no blue button is shown$")
    public void no_blue_button_is_shown() throws Throwable {

        String colorBackGround = "rgba(28, 75, 124, 1)";

        String colore = LifeWay.AEM.getCssValueXpath("/html/body/div[3]/div[1]/div/div/div[1]/p[4]/a", "background-color");
        assertEquals(colorBackGround, colore);

    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 2336 sponsors brands update">
    //<editor-fold defaultstate="collapsed" desc="Scenario: Visitor should be able to see Sponsors & Brands organized by columns">
    @Given("^the Author has configured the columns as you can see in the image below$")
    public void the_Author_has_configured_the_columns_as_you_can_see_in_the_image_below() throws Throwable {
    }

    @When("^the visitor navigates to publish$")
    public void the_visitor_navigates_to_publish() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_HOME_PAGE);
    }

    @Then("^the visitor should be able to see images width according to columns configured by the author$")
    public void the_visitor_should_be_able_to_see_images_width_according_to_columns_configured_by_the_author() throws Throwable {
        String image = LifeWay.AEM.getCssValueXpath("/html/body/div[3]/div[7]/div/div/div[1]/div", "background-image");
        image = image.replace("url(\"", "").replace("\")", "");
        assertTrue(isImage(image));
    }

    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="US 2359 Login Header Rendering Login Update">
    //<editor-fold defaultstate="collapsed" desc="Scenario: Anonymous user clicks Login/Register Link">
    @Given("^an anonymous visitor clicks on Login/Register link on the header$")
    public void an_anonymous_visitor_clicks_on_Login_Register_link_on_the_header() throws Throwable {
        LifeWay.AEM.openSite(LifeWay.URL_HOME_PAGE_QA_XMS);

        LifeWay.AEM.clickByXpath("//*[@id=\"head-nav\"]/div/ul/li[1]/a[1]");
    }

    @Given("^the visitor is redirected to the login form outside Lifeway com$")
    public void the_visitor_is_redirected_to_the_login_form_outside_Lifeway_com() throws Throwable {
        boolean log = LifeWay.AEM.isVisibleXpath("//*[@id=\"form-signin\"]/ul/li[1]/a");
        assertTrue(log);
    }

    @When("^the visitor completes the login$")
    public void the_visitor_completes_the_login() throws Throwable {
        LifeWay.AEM.writeByXpath("//*[@id=\"form-signin\"]/div/label[1]/input", "ptoc@xumak.com");

        LifeWay.AEM.writeByXpath("//*[@id=\"form-signin\"]/div/label[2]/input", "holamundo12");

        LifeWay.AEM.clickByXpath("//*[@id=\"form-signin\"]/div/input[1]");
    }

    @Then("^the visitor is redirected to the homepage of Lifeway com on the$")
    public void the_visitor_is_redirected_to_the_homepage_of_Lifeway_com_on_the() throws Throwable {
        Thread.sleep(5000);
        String url = "http://qa.xms.lifeway.com:4503/content/lifeway/HomePageQA.html";
        String urlValue = LifeWay.AEM.getUrl();

        assertEquals(url, urlValue);

        LifeWay.AEM.finalizeAemConector();
    }
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Scenario: logged visitor opens a page on Lifeway.com">

    @Given("^a logged visitor navigates to a page$")
    public void a_logged_visitor_navigates_to_a_page() throws Throwable {
    }

    @When("^the header is rendered$")
    public void the_header_is_rendered() throws Throwable {
    }

    @Then("^the logged in text is shown$")
    public void the_logged_in_text_is_shown() throws Throwable {
    }
//</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Hooks">

    @Before("@startProductQA")
    public static void startProductQA() throws Exception {
        LifeWay.openUrl(LifeWay.URL_PRODUCT_QA);
    }

    @Before("@startProductQAOK")
    public static void startProductQAOK() throws Exception {
        LifeWay.openUrl(LifeWay.URL_PRODUCT_QA_OK);
    }

    @Before("@startProductQAAddToCart")
    public static void startProducQAOK() throws Exception {
        LifeWay.openUrl(LifeWay.URL_PRODUCT_QA_OK);
    }

    @Before("@startLogoComponent")
    public static void startLogoComponent() throws Exception {
        LifeWay.openUrl(LifeWay.URL_PAGE_TEST);
        /**
         * Thread.sleep(3000);
         * LifeWay.AEM.clickByXpath("//*[@id=\"Content\"]/div[1]/nav/div[1]/button[1]");
         */
        Thread.sleep(3000);
        LifeWay.selectComponent("//*[@id=\"OverlayWrapper\"]/div[2]/div[2]/div[3]/div[3]");

    }

    @Before("@startTopNavigationComponent")
    public static void startTopNavigationComponent() throws Exception {
        LifeWay.openUrl(LifeWay.URL_PAGE_TEST);
        Thread.sleep(3000);
        LifeWay.selectComponent("//*[@id=\"OverlayWrapper\"]/div[2]/div[2]/div[3]/div[2]");
    }

    @Before("@startUpperFooter")
    public static void startUpperFooter() throws Exception {
        LifeWay.openUrl(LifeWay.URL_PAGE_TEST);
        Thread.sleep(3000);
        LifeWay.selectComponent("//*[@id=\"OverlayWrapper\"]/div[2]/div[2]/div[1]/div[2]");
    }

    @Before("@startConfigurationTemplate")
    public static void startConfigurationTemplate() throws Exception {
        LifeWay.openUrl(LifeWay.URL_SITES);
    }

    @Before("@startSimpleButton")
    public static void startSimpleButton() throws Exception {
        LifeWay.openUrl(LifeWay.URL_SIMPLE_BUTTON);
    }

    @Before("@startImageWithCaption")
    public static void startImageWithCaption() throws Exception {
        LifeWay.openUrl(LifeWay.URL_ARTICLE_TEMPLATE);
    }

    @Before("@startRelatedArticles")
    public static void startRelatedArticles() throws Exception {
        LifeWay.openUrl(LifeWay.URL_ARTICLE_RELATED);
    }
    //</editor-fold>
}
