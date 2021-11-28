package com.olgaskyba.elective.model;

import java.io.Serializable;
import java.util.Objects;

public class Topic implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long topicId;
    private String nameTopic;

    public Topic(String name) {
        this.nameTopic=name;
    }

    public Topic() {
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public String getNameTopic() {
        return nameTopic;
    }

    public void setNameTopic(String nameTopic) {
        this.nameTopic = nameTopic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Topic)) return false;
        Topic topic = (Topic) o;
        return Objects.equals(topicId, topic.topicId) && Objects.equals(nameTopic, topic.nameTopic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicId, nameTopic);
    }

    @Override
    public String toString() {
        return "Topic{" +
                "topicId=" + topicId +
                ", nameTopic='" + nameTopic + '\'' +
                '}';
    }
}
