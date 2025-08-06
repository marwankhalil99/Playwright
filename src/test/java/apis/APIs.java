package apis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class APIs {
    private static String sessionId;
    public APIs(String sessionId){
        this.sessionId = sessionId;
        RestAssured.baseURI = "https://q-line-iis.alqemam.com/qCMS_Test_v14.3.18";
    }
    private Response getResFollowUpSmartService(){
        String path = "component/fol/fol_followup_smart_services.aspx/getData";
        Map<String,String> headers = new HashMap<>();
        headers.put("accept","*/*");
        headers.put("accept-encoding","gzip, deflate, br, zstd");
        headers.put("accept-language","en-US,en;q=0.9,fa;q=0.8");
        headers.put("origin","https://q-line-iis.alqemam.com");
        headers.put("priority","u=0, i");
        headers.put("referer","https://q-line-iis.alqemam.com/qCMS_Test_v14.3.18/component/fol/fol_followup_smart_services.aspx?prog_id=141&sub_prog_id=22");
        headers.put("content-type","application/json; charset=UTF-8");
        headers.put("sec-ch-ua","\"Not)A;Brand\";v=\"8\", \"Chromium\";v=\"138\", \"Microsoft Edge\";v=\"138\"");
        headers.put("sec-ch-ua-platform","\"Windows\"");
        headers.put("sec-fetch-dest","empty");
        headers.put("sec-fetch-mode","cors");
        headers.put("sec-fetch-site","same-origin");
        headers.put("user-agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/138.0.0.0 Safari/537.36 Edg/138.0.0.0");
        headers.put("Cookie", "ASP.NET_SessionId=xgzrxnadyoxr0f2scs4kkads");

        return given().headers(headers)
//                .cookies("ASP.NET_SessionId",sessionId)
                .body("{\"Params\":{\"sub_prog_id\":\"22\",\"date_fromSend\":\"\",\"date_toSend\":\"\",\"flag\":\"0\",\"sendOrRecived\":0},\"sortColumn\":\"CREATE_DATE\",\"sortOrder\":\"DESC\",\"pageIndex\":0,\"pageRowCounts\":10}")
                .when().post(path).then().log().body().extract().response();
    }

    public String getOTP(String phoneNum) throws JsonProcessingException {
        Response response = getResFollowUpSmartService();
        String responseString = response.body().jsonPath().get("d").toString();

        // Clean the response string and fix JSON format
        String cleaned = responseString
                .trim()
                .replaceAll("^\"|\"$", "")
                .replace("'", "\"");

        // Parse the cleaned JSON string
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(cleaned);
        JsonNode dataArray = root.path("Data");

        for (JsonNode sms : dataArray) {
            String toPhone = sms.path("TO_PHONE").asText();
            String content = sms.path("SMS_CONTENT").asText();

            // ✅ Match only the SMS for the provided phone number
            if (toPhone.contains(phoneNum)) {
                String otp = extractOtp(content);
                if (otp != null) {
                    System.out.println("✅ OTP found: " + otp);
                    return otp;
                }
            }
        }

        System.out.println("❌ OTP not found for number: " + phoneNum);
        return "0";
    }
    private static String extractOtp(String smsContent) {
        Pattern pattern = Pattern.compile("التحقق.*?\\((\\d{4,8})\\)");
        Matcher matcher = pattern.matcher(smsContent);
        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
