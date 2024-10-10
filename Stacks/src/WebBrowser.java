import java.util.ArrayList;
import java.util.EmptyStackException;
/*
 * Name: Clayton Hammen Tan
 * PID:  A17819097
 */

/**
 * Web Browser Implementation
 * @author Clayton Hammen Tan
 * @since 04-08-24
 */
public class WebBrowser {

    private String currentPage;
    private ArrayList<String> history;
    private MyStack prev;
    private MyStack next;

    private static final String DEFAULT_PAGE = "google.com";
    private static final int MAX_CAPACITY = 100;

    /**
     * Sets current page to default page, initializes the array list and stack instance variables.
     */
    public WebBrowser() {
        this.currentPage = DEFAULT_PAGE;
        this.history = new ArrayList<>();
        this.prev = new MyStack(MAX_CAPACITY);
        this.next = new MyStack(MAX_CAPACITY);
    }

    /**
     * Returns the current page.
     * @return the current page as a string
     */
    public String getCurrentPage() {
        return this.currentPage;
    }

    /**
     * Sets the current page to the new link
     * @param newLink , new link for the current page
     * @throws IllegalArgumentException , if newLink is null
     */
    public void openNewPage(String newLink) {
        if (newLink == null) {
            throw new IllegalArgumentException();
        } else {
            this.prev.push(currentPage);
            this.currentPage = newLink;
            this.history.add(newLink);
            this.next.clear();
        }
    }

    /**
     * Sets current page to previous page.
     * @throws IllegalStateException , if there is no previous page
     */
    public void previousPage() {
        if (this.prev.isEmpty()) {
            throw new IllegalStateException();
        } else {
            this.next.push(currentPage);
            this.currentPage = prev.pop();
            this.history.add(currentPage);
        }
    }

    /**
     * Sets current page to next page.
     * @throws IllegalStateException , if there is no next page
     */
      public void nextPage() {
          if (next.isEmpty()) {
              throw new IllegalStateException();
          } else {
              this.prev.push(currentPage);
              this.currentPage = next.pop();
              this.history.add(currentPage);
          }
    }

    /**
     * Simulate opening a new tab, sets the current page to default and clears next and prev.
     */
    public void newTab() {
        this.currentPage = DEFAULT_PAGE;
        this.prev.clear();
        this.next.clear();
    }

    /**
     * Returns the history.
     * @return history of pages as an array
     */
    public ArrayList<String> getHistory() {
        return this.history;
    }

    /**
     * Clears the history.
     */
    public void clearHistory() {
        this.history.clear();
    }

    /**
     * Searches history for a specific link, and opens if link is found,
     * returning true otherwise return false.
     * @param link , link of a page as a string
     * @return boolean on if the link was found or not
     */
    public boolean findLink(String link) {
        if (this.history.contains(link)) {
            this.openNewPage(link);
            return true;
        } else {
            return false;
        }
    }

}
