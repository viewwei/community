package lift.community.Model;

import lombok.Data;

@Data
public class User {
    private  Integer id;
    private  String name;
    private  String accountId;
    private  String token;
    private  String avatar_url;
    private  Long gmtCreate;
    private  Long gmtModified;

}
