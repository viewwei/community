package lift.community.Model;

import lombok.Data;

@Data
public class Question {
    private  Integer id;
    private  String title;
    private  String description;
    private  Long gmt_create;
    private  Long gmt_modified;
    private  Integer creator;
    private  Integer view_count;
    private  Integer comment_count;
    private  Integer link_count;
    private  String tag;
    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", gmt_create=" + gmt_create +
                ", gmt_modified=" + gmt_modified +
                ", creator=" + creator +
                ", view_count=" + view_count +
                ", comment_count=" + comment_count +
                ", link_count=" + link_count +
                ", tag='" + tag + '\'' +
                '}';
    }
}
