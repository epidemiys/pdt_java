package ru.stqa.pft.mantis.model;

/**
 * Created by aleksandr.petrov on 10.11.16.
 */
public class Issue {

    private int id;
    private String summary;
    private String discription;
    private String name;
    private Project project;

    public int getId() {
        return id;
    }

    public Issue withId(int id) {
        this.id = id;
        return this;
    }

    public String getSummary() {
        return summary;
    }

    public Issue withSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public String getDiscription() {
        return discription;
    }

    public Issue withDiscription(String discription) {
        this.discription = discription;
        return this;
    }

    public Project getProject() {
        return project;
    }

    public Issue withProject(Project project) {
        this.project = project;
        return this;
    }
}
