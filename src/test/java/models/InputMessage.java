package models;

public class InputMessage {

    public String key;
    public String value;
    public Info info;

    public static class Info {
        public String _postman_id;
        public String name;
        public String schema;
    }
}
