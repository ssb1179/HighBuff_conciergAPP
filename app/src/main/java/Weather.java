import org.json.JSONException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class Weather {
    private String nx = "60";    //위도
    private String ny = "125";  //경도
    private String baseDate = "02020520";
    private String type = "json";

    public void lookUpWeather() throws IOException, JSONException {
        String apiUrl = "Call Back URL\thttp://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";

        String serviceKey = "m1wx%2FQKRVfg3pKAZGd7vk3ukarFaJEwDpSkDFwvNJA6v%2F6RvBVt39W9Fyyjxb%2BGyMVvUBzF4SYdEutggDuSzPw%3D%3D";

        StringBuilder uriSB = new StringBuilder(apiUrl);
        uriSB.append("?" + URLEncoder.encode("ServiceKey", "UTF-8") + "=" + serviceKey);

    }

}
