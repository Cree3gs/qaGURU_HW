package data;

public enum SortTabs {
    POPULAR("популярные"),
    CHEAPER("подешевле"),
    EXPENSIVE("подороже"),
    RATING("высокий рейтинг");

    public final String title;

    SortTabs(String title) {
        this.title = title;
    }
}
