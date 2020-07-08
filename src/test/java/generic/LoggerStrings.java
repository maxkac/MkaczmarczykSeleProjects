package generic;

public enum LoggerStrings {

    SCROLL("Scroll to element {}"),
    EDIT("Element is editable"),
    VISIBLE("Element is visible"),
    CLICK("Element is clickable, try to click...{}");

    private String msg;

    LoggerStrings(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
