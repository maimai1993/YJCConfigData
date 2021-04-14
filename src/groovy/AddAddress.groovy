//import com.jd.fastjson.JSON
//import com.jd.fastjson.JSONArray
//import com.jd.fastjson.JSONObject
//import com.jd.forcebot.toolkit.parameterized.latest.AsciiFileAccessArbitrarily;
//import com.jd.forcebot.toolkit.parameterized.latest.AsciiFileAccessCircularly;
//import com.jd.forcebot.engine.groovy.Lifecycle;
//import com.jd.forcebot.engine.groovy.RatePolicy;
//import com.jd.forcebot.engine.groovy.TestCase;
//import com.jd.forcebot.engine.groovy.TestSuite
//import groovy.json.JsonSlurper;
//
//import java.util.concurrent.TimeUnit;
//import com.jd.forcebot.engine.TestUtils;
//import com.jd.forcebot.engine.groovy.*;
//import com.jd.forcebot.toolkit.*;
//import okhttp3.*;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * @author fuyuguang
// *
// * @TestSuite - 类注解
// *   value - 事物父名称》String
// *   Lifecycle - 生命周期》Lifecycle.PROCESS, Lifecycle.THREAD, Lifecycle.SUITE, Lifecycle.CASE
// *   ratePolicy - 比例执行策略》RatePolicy.STANDARD, RatePolicy.PROBABILITY
// *
// * @TestCase - 方法注解
// *   value - 事物详细名称》String
// *   rate - 方法运行比例权重》int
// *   timeout - 方法运行超时时间》long default 60000L
// *   record - 是否统计该方法》boolean
// *
// * 自定义事务：
// * TestUtils.transactionBegin("xxx")
// * TestUtils.transactionSuccess("xxx")
// * TestUtils.transactionFailure("xxx")
// */
//
///***
// * 增加地址
// */
//@TestSuite(value = "forcebot")
//class AddAddress {
//
//    /**
//     * 实例化顺序读取文本文件，构造方法参数：
//     * filepath:文件存储路径，可以指定classpath相对路径，也可以指定文件所在文件系统的绝对路径
//     * separator:文件分隔符，用于对行数据进行分列读取使用，默认值为NULL，不对行数据进行分列读取
//     * enCycle:启用循环读取文件模式，默认为true，当为true时，文件读取到末行数据时，下次调用读取方法时会将文件指针指向文件起始位置，当为false时，调用读取方法至文件末时，会返回NULL
//     */
//    static AsciiFileAccessCircularly accessCircularly = new AsciiFileAccessCircularly("submitpin_noPreSale.txt","",false);
//    static MediaType textPlainMT = MediaType.parse("application/x-www-form-urlencoded; charset=utf-8");
//    /**
//     * 实例化随机读取文本文件，构造方法参数：
//     * filepath:文件存储路径，可以指定classpath相对路径，也可以指定文件所在文件系统的绝对路径
//     * separator:文件分隔符，用于对行数据进行分列读取使用，默认值为NULL，不对行数据进行分列读取
//     */
//    static AsciiFileAccessArbitrarily arbitrarily = new AsciiFileAccessArbitrarily("address.txt");
//
//    public final Logger logger = TestUtils.LOGGER;
//    public final OkHttpClient client;
//    public final Request.Builder builder;
//    public static final String  host="vmborderm9.m.jd.care";
//    public static final String clientVersion = "9.1.6";
//    private static String[] telFirst="134,135,136,137,138,139,150,151,152,157,158,159,130,131,132,155,156,133,153".split(",");
//    AddAddress() {
//        client = new OkHttpClient().newBuilder()
//                .connectTimeout(6, TimeUnit.SECONDS)
//                .readTimeout(6, TimeUnit.SECONDS)
//                .writeTimeout(6, TimeUnit.SECONDS)
//                .followRedirects(false)
//                .build();
//        builder = new Request.Builder();
//
//        //builder.header("Connection", "close");
//        builder.header("Accept-Language", "zh-CN,zh;q=0.8");
////        builder.header("Host", domain);
//        builder.header("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/46.0.2490.86 Safari/537.36");
////        builder.header("Cookie", "sso=asdfsdfsadfsd; token=adfasdfasdf;")
//        builder.header("Accept-Encoding", "utf-8");
//
//
//        logger.info("TestRunner init...");
//    }
//
//    public static String getMobile(){
//
//        int index=getNum(0,telFirst.length-1);
//        String first=telFirst[index];
//        String second=String.valueOf(getNum(1,888)+10000).substring(1);
//        String third=String.valueOf(getNum(1,9100)+10000).substring(1);
//        System.out.println(first+second+third);
//        return first+second+third;
//    }
//
//    public static int getNum(int start,int end) {
//
//        return (int)(Math.random()*(end-start+1)+start);
//    }
//
//    public static void main(String[] args) {
//        def address = new AddAddress();
//        for(int i=0;i<10000;i++){
//            address.addAddress();
//        }
//    }
//
//
//    @TestCase(record=true)
//    void addAddress() {
//        String pin = accessCircularly.readLine(); //顺序读取一行数据，并将文件指针移向下一行行首
//        // String[] onelineSplit = accessCircularly.readToArray(); //顺序读取文件中某一行数据，并根据指定的分隔符进行拆分到数组中
//        String addressLine = arbitrarily.readLine(); //随机读取一行数据
////         String[] onelineSplit = arbitrarily.readToArray(); //随机读取文件中某一行数据，并根据指定的分隔符进行拆分到数组中
//        //set request body
//        String[] arr = addressLine.split(",");
//
//
//        String requestBody="{\"Name\":\"测试\",\"IdProvince\":"+arr[0];
//        requestBody=requestBody+",\"IdCity\":"+arr[1];
//        requestBody= requestBody+",\"IdArea\":"+arr[2]+",\"IdTown\":"+arr[3];
//        requestBody=requestBody+",\"addressDetail\":\""+arr[5]+"\",\"Mobile\":\""+getMobile()+"\",\"addressDefault\":true,\"validRegion\":true}";
//
//
//        FormBody body = new FormBody.Builder()
//                .add("", "")
//                .build();
//
//        Response response = null;
//        TestUtils.transactionBegin("addAddress_begin");
//
//
//        String url="http://"+host+"/addAddress?pin=" + pin + "&client=android&clientVersion="+clientVersion+"&osVersion=9.0.0&screen=1920*1080&area=1_2800_2850_0&networkType=wifi&forcebot=1&body="+requestBody+"&ip=127.0.0.1";
//        logger.info("url..."+url);
//        try {
//            Request request = builder.url(url).post(body).build();
//            response = client.newCall(request).execute();
//            String bodyStr = response.body().string();
//            // logger.info("bodyStr..."+bodyStr);
//            if(response.code() == 200){ //TODO 请根据业务需求添加判断！(如：bodyStr.contains("success"))
//                TestUtils.transactionSuccess("addAddress_success");
//                logger.info("----------Success!");
//            }else{
//                TestUtils.transactionFailure("addAddress_Failure");
//                logger.error("============= response code: {}, response body: {}", response.code(), bodyStr);
//            }
//        } catch (Exception e) {
//            TestUtils.transactionFailure("addAddress_Failure");
//            logger.error("error info:", e);
//        } finally {
//            if (response != null) response.close();
//        }
//    }
//
//
//    // @TestCase(record=true)
//    void getAddresByPin() {
//        String pin = accessCircularly.readLine(); //顺序读取一行数据，并将文件指针移向下一行行首
//
////        String pin="forcebot_sub5_0045";
////        String pin="forcebot_pay_a_1";
//        String requestBody="{}";
//        Response response = null;
//        TestUtils.transactionBegin("getAddressByPin_begin");
//        String url="http://"+host+"/getAddressByPin?pin=" + pin + "&client=android&clientVersion="+clientVersion+"&osVersion=9.0.0&screen=1920*1080&area=1_2800_2850_0&networkType=wifi&forcebot=1&body="+requestBody+"&ip=127.0.0.1";
//        logger.info("url..."+url);
//        try {
//            Request request = builder.url(url).post(RequestBody.create(textPlainMT, "&body=" + requestBody)).build();
//            response = client.newCall(request).execute();
//            String bodyStr = response.body().string();
//            logger.info("bodyStr..."+bodyStr);
//            if(response.code() == 200){
//                //todo 删除所有地址  当前地址有错误的 就删除对应id
//                JSONObject jsonObject = JSON.parseObject(bodyStr);
//                JSONArray addressList = jsonObject.getJSONArray("addressList");
//                if(addressList!=null){
//                    if(addressList.size()>0){
//                        int size=addressList.size();
////                        logger.info("addressList..."+addressList.size());
//                        for(int i=0;i<size;i++){
////                            logger.info("进来了..."+i);
//                            JSONObject address=addressList.get(i);
//                            long id=address.getLongValue("Id");
////                            logger.info("id..."+id);
//
//                            if(id != null){
//                                //调用删除接口
//
//                                String requestBody1="{\"addressId\":\""+id+"\"}";
//                                FormBody body1 = new FormBody.Builder().build();
//                                Response response1 = null;
//                                TestUtils.transactionBegin("delAddress_begin");
//                                String url1="http://"+host+"/delAddress?pin=" + pin + "&client=android&clientVersion="+clientVersion+"&osVersion=9.0.0&screen=1920*1080&area=1_2800_2850_0&networkType=wifi&forcebot=1&body="+requestBody1+"&ip=127.0.0.1";
////                                logger.info("url1..."+url1);
//
//                                Request request1 = builder.url(url1).post(body1).build();
//                                response1 = client.newCall(request1).execute();
//                                String bodyStr1 = response1.body().string();
////                                logger.info("bodyStr1..."+bodyStr1);
//
//                                if(response1.code() == 200){
//                                    TestUtils.transactionSuccess("delAddress_success");
//                                }else{
//                                    TestUtils.transactionFailure("delAddress_Failure");
//                                }
//
//                            }
//
//                        }
//                    }
//
//                }
//
//
//                logger.info("----------Success!");
//                //TODO 请根据业务需求添加判断！(如：bodyStr.contains("success"))
//                TestUtils.transactionSuccess("getAddressByPin_success");
//            }else{
//                TestUtils.transactionFailure("getAddressByPin_Failure");
//                logger.error("============= response code: {}, response body: {}", response.code(), bodyStr);
//            }
//        } catch (Exception e) {
//            TestUtils.transactionFailure("getAddressByPin_Failure");
//            logger.error("error info:", e);
//        } finally {
//            if (response != null) response.close();
//        }
//    }
//}