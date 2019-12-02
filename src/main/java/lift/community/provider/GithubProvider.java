package lift.community.provider;

import com.alibaba.fastjson.JSON;
import lift.community.dot.AccessTokenDTO;
import lift.community.dot.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        final MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("A")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().toString();
            String[] splite = string.split("&");
            String tokenStr = splite[0].split("=")[1];
            return tokenStr;
        } catch (Exception e) {

        }
        return null;
    }

    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("E")
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().toString();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {

        }

        return null;
    }
}
