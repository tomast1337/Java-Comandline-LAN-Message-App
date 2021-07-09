package io.github.tomast13337;

import java.util.Calendar;

public class Message {
    private String name;
    private String content;
    private long time;

    public Message(String name, String content, int time) {
        this.name = name;
        this.content = content;
        this.time = time;
    }

    @Override
    public String toString() {
        Calendar time = Calendar.getInstance();
        time.setTimeInMillis(this.time);
        return name + "-" + String.valueOf(time.get(Calendar.HOUR)) + ":" + String.valueOf(time.get(Calendar.MINUTE)) + "-" + content;

    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
