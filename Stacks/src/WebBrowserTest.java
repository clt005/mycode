import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.EmptyStackException;

public class WebBrowserTest {
    private static final String DEFAULT_PAGE = "google.com";
    @Test
    public void openNewPageTest() {
        WebBrowser browser0 = new WebBrowser();
        Assertions.assertThrows(IllegalArgumentException.class, () -> browser0.openNewPage(null));
        WebBrowser browser1 = new WebBrowser();
        browser1.openNewPage("dsc30.org");
        Assertions.assertEquals("dsc30.org", browser1.getCurrentPage());
        Assertions.assertEquals(1, browser1.getHistory().size());
        Assertions.assertTrue(browser1.getHistory().contains(browser1.getCurrentPage()));
        Assertions.assertTrue(browser1.findLink("dsc30.org"));
        browser1.clearHistory();
        Assertions.assertTrue(browser1.getHistory().isEmpty());
    }
    @Test
    public void previousPageTest() {
        WebBrowser browser2 = new WebBrowser();
        browser2.openNewPage("dsc30.org");
        browser2.openNewPage("dsc20.org");
        browser2.openNewPage("dsc40A.org");
        browser2.previousPage();
        browser2.previousPage();
        browser2.previousPage();
        Assertions.assertThrows(IllegalStateException.class, browser2::previousPage);
        Assertions.assertTrue(browser2.findLink("dsc30.org"));
        browser2.clearHistory();
        Assertions.assertTrue(browser2.getHistory().isEmpty());
    }
    @Test
    public void nextPageTest() {
        WebBrowser browser3 = new WebBrowser();
        browser3.openNewPage("dsc10.org");
        browser3.openNewPage("dsc20.org");
        browser3.openNewPage("dsc30.org");
        browser3.previousPage();
        browser3.previousPage();
        browser3.nextPage();
        Assertions.assertEquals("dsc20.org", browser3.getCurrentPage());
        browser3.nextPage();
        Assertions.assertEquals("dsc30.org", browser3.getCurrentPage());
        Assertions.assertThrows(IllegalStateException.class, browser3::nextPage);
        Assertions.assertTrue(browser3.findLink("dsc30.org"));
        browser3.clearHistory();
        Assertions.assertTrue(browser3.getHistory().isEmpty());
    }
    @Test
    public void newTabTest() {
        WebBrowser browser4 = new WebBrowser();
        browser4.openNewPage("dsc30.org");
        browser4.newTab();
        Assertions.assertFalse(browser4.getHistory().isEmpty());
        Assertions.assertEquals(1, browser4.getHistory().size());
        WebBrowser browser5 = new WebBrowser();
        browser5.openNewPage("dsc20.org");
        browser5.openNewPage("dsc10.org");
        browser5.newTab();
        Assertions.assertEquals(DEFAULT_PAGE, browser5.getCurrentPage());
        WebBrowser browser6 = new WebBrowser();
        browser6.openNewPage("dsc30.org");
        browser6.newTab();
        Assertions.assertThrows(IllegalStateException.class, browser6::nextPage);
        Assertions.assertThrows(IllegalStateException.class, browser6::previousPage);

    }

}
