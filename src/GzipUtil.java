import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil {


    /**
     * 数据解压缩
     *
     * @param data
     * @return
     * @throws Exception
     */
    public static byte[] decompress(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // 解压缩

        decompress(bais, baos);

        data = baos.toByteArray();

        baos.flush();
        baos.close();

        bais.close();

        return data;
    }

    /**
     * 数据解压缩
     *
     * @param is
     * @param os
     * @throws Exception
     */
    public static void decompress(InputStream is, OutputStream os)
            throws Exception {

        GZIPInputStream gis = new GZIPInputStream(is);

        int count;
        byte data[] = new byte[BUFFER];
        while ((count = gis.read(data, 0, BUFFER)) != -1) {
            os.write(data, 0, count);
        }

        gis.close();
    }

    public static byte[] compress(byte[] data) throws Exception {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        // 压缩
        compress(bais, baos);

        byte[] output = baos.toByteArray();

        baos.flush();
        baos.close();

        bais.close();

        return output;
    }
    static final int BUFFER = 10240 ;
    /**
     * 数据压缩
     *
     * @param is
     * @param os
     * @throws Exception
     */
    public static void compress(InputStream is, OutputStream os)
            throws Exception {

        GZIPOutputStream gos = new GZIPOutputStream(os);

        int count;
        byte data[] = new byte[BUFFER];
        while ((count = is.read(data, 0, BUFFER)) != -1) {
            gos.write(data, 0, count);
        }

        gos.finish();

        gos.flush();
        gos.close();
    }



    public static void main(String[] args) throws Exception {
        StringBuilder value = new StringBuilder() ;
//        for (int i = 0; i < 100; i++) {
//            value.append("java gzip 压缩测试");
//        }
        value.append("{\n" +
                "    \"returnValue\": [\n" +
                "        {\n" +
                "            \"busiType\": \"2\",\n" +
                "            \"mainOfferType\": \"2000\",\n" +
                "            \"custIdCard\": \"320586199307220538\",\n" +
                "            \"detailList\": [\n" +
                "                {\n" +
                "                    \"prodId\": \"100000379\",\n" +
                "                    \"accNum\": \"18915598993\",\n" +
                "                    \"prodInstId\": \"123217993569\",\n" +
                "                    \"remark\": \"\\u5ba2\\u6237\\u7684\\u624b\\u673a\\u4fe1\\u606f:18915598993(\\u4e3b\\u5361),18913057422(\\u526f\\u5361),18913079380(\\u526f\\u5361),\",\n" +
                "                    \"accProdInstId\": \"123217993569\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"custId\": \"123017329834\",\n" +
                "            \"areaCode\": \"0512\",\n" +
                "            \"oldOfferList\": [\n" +
                "                {\n" +
                "                    \"offerCode\": \"300509034199\",\n" +
                "                    \"offerName\": \"\\u5929\\u7ffc\\u4e91\\u76d8\\u9ec4\\u91d1\\u4f1a\\u545829\\u5143/\\u6708\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"offerCode\": \"911007646\",\n" +
                "                    \"offerName\": \"\\u5168\\u56fd\\u6d41\\u91cf\\u7545\\u4eab399\\u5143\\u878d\\u5408\\u5957\\u9910\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"offerCode\": \"300509036538\",\n" +
                "                    \"offerName\": \"\\u5168\\u56fd\\u6d41\\u91cf\\u7545\\u4eab399\\u5143\\u4e2a\\u4eba\\u5957\\u9910\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"offerCode\": \"300509006342\",\n" +
                "                    \"offerName\": \"\\u5458\\u5de5\\u901a\\u4fe1\\u8d39\\u7efc\\u5408\\u4f18\\u60e0400\\u5143\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"offerCode\": \"300500002120\",\n" +
                "                    \"offerName\": \"\\u6807\\u51c6_\\u5929\\u7ffc\\u865a\\u62df\\u7f51\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"createTime\": \"2019-12-10 15:52:17\",\n" +
                "            \"bssChannelId\": \"14106527\",\n" +
                "            \"orderId\": \"MINI20191210155217899458320\",\n" +
                "            \"custName\": \"\\u6f58\\u5fd7\\u6770\",\n" +
                "            \"mainOfferName\": \"\\u5168\\u56fd\\u7545\\u4eab\\u4e2a\\u4eba\\u5957\\u9910199\\u6863(\\u65b0\\u88c5)\",\n" +
                "            \"mainOfferCode\": \"250000552\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"busiType\": \"2\",\n" +
                "            \"mainOfferType\": \"2000\",\n" +
                "            \"custIdCard\": \"320586199307220538\",\n" +
                "            \"detailList\": [\n" +
                "                {\n" +
                "                    \"prodId\": \"100000379\",\n" +
                "                    \"accNum\": \"18915598993\",\n" +
                "                    \"prodInstId\": \"123217993569\",\n" +
                "                    \"remark\": \"\\u5ba2\\u6237\\u7684\\u624b\\u673a\\u4fe1\\u606f:18915598993(\\u4e3b\\u5361),18913057422(\\u526f\\u5361),18913079380(\\u526f\\u5361),\",\n" +
                "                    \"accProdInstId\": \"123217993569\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"custId\": \"123017329834\",\n" +
                "            \"areaCode\": \"0512\",\n" +
                "            \"oldOfferList\": [\n" +
                "                {\n" +
                "                    \"offerCode\": \"300509034199\",\n" +
                "                    \"offerName\": \"\\u5929\\u7ffc\\u4e91\\u76d8\\u9ec4\\u91d1\\u4f1a\\u545829\\u5143/\\u6708\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"offerCode\": \"911007646\",\n" +
                "                    \"offerName\": \"\\u5168\\u56fd\\u6d41\\u91cf\\u7545\\u4eab399\\u5143\\u878d\\u5408\\u5957\\u9910\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"offerCode\": \"300509036538\",\n" +
                "                    \"offerName\": \"\\u5168\\u56fd\\u6d41\\u91cf\\u7545\\u4eab399\\u5143\\u4e2a\\u4eba\\u5957\\u9910\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"offerCode\": \"300509006342\",\n" +
                "                    \"offerName\": \"\\u5458\\u5de5\\u901a\\u4fe1\\u8d39\\u7efc\\u5408\\u4f18\\u60e0400\\u5143\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"offerCode\": \"300500002120\",\n" +
                "                    \"offerName\": \"\\u6807\\u51c6_\\u5929\\u7ffc\\u865a\\u62df\\u7f51\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"createTime\": \"2019-12-10 15:50:37\",\n" +
                "            \"bssChannelId\": \"14106527\",\n" +
                "            \"orderId\": \"MINI20191210155037559463867\",\n" +
                "            \"custName\": \"\\u6f58\\u5fd7\\u6770\",\n" +
                "            \"mainOfferName\": \"\\u5168\\u56fd\\u7545\\u4eab\\u4e2a\\u4eba\\u5957\\u9910199\\u6863(\\u65b0\\u88c5)\",\n" +
                "            \"mainOfferCode\": \"250000552\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"busiType\": \"2\",\n" +
                "            \"mainOfferType\": \"2000\",\n" +
                "            \"custIdCard\": \"320586199307220538\",\n" +
                "            \"detailList\": [\n" +
                "                {\n" +
                "                    \"prodId\": \"100000379\",\n" +
                "                    \"accNum\": \"18915598993\",\n" +
                "                    \"prodInstId\": \"123217993569\",\n" +
                "                    \"remark\": \"\\u5ba2\\u6237\\u7684\\u624b\\u673a\\u4fe1\\u606f:18915598993(\\u4e3b\\u5361),18913057422(\\u526f\\u5361),18913079380(\\u526f\\u5361),\",\n" +
                "                    \"accProdInstId\": \"123217993569\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"custId\": \"123017329834\",\n" +
                "            \"areaCode\": \"0512\",\n" +
                "            \"oldOfferList\": [\n" +
                "                {\n" +
                "                    \"offerCode\": \"300509034199\",\n" +
                "                    \"offerName\": \"\\u5929\\u7ffc\\u4e91\\u76d8\\u9ec4\\u91d1\\u4f1a\\u545829\\u5143/\\u6708\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"offerCode\": \"911007646\",\n" +
                "                    \"offerName\": \"\\u5168\\u56fd\\u6d41\\u91cf\\u7545\\u4eab399\\u5143\\u878d\\u5408\\u5957\\u9910\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"offerCode\": \"300509036538\",\n" +
                "                    \"offerName\": \"\\u5168\\u56fd\\u6d41\\u91cf\\u7545\\u4eab399\\u5143\\u4e2a\\u4eba\\u5957\\u9910\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"offerCode\": \"300509006342\",\n" +
                "                    \"offerName\": \"\\u5458\\u5de5\\u901a\\u4fe1\\u8d39\\u7efc\\u5408\\u4f18\\u60e0400\\u5143\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"offerCode\": \"300500002120\",\n" +
                "                    \"offerName\": \"\\u6807\\u51c6_\\u5929\\u7ffc\\u865a\\u62df\\u7f51\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"createTime\": \"2019-12-10 15:46:35\",\n" +
                "            \"bssChannelId\": \"14106527\",\n" +
                "            \"orderId\": \"MINI20191210154635496367363\",\n" +
                "            \"custName\": \"\\u6f58\\u5fd7\\u6770\",\n" +
                "            \"mainOfferName\": \"\\u5168\\u56fd\\u7545\\u4eab\\u4e2a\\u4eba\\u5957\\u9910199\\u6863(\\u65b0\\u88c5)\",\n" +
                "            \"mainOfferCode\": \"250000552\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"busiType\": \"2\",\n" +
                "            \"mainOfferType\": \"2000\",\n" +
                "            \"custIdCard\": \"320586199307220538\",\n" +
                "            \"detailList\": [\n" +
                "                {\n" +
                "                    \"prodId\": \"100000379\",\n" +
                "                    \"accNum\": \"18915598993\",\n" +
                "                    \"prodInstId\": \"123217993569\",\n" +
                "                    \"remark\": \"\\u5ba2\\u6237\\u7684\\u624b\\u673a\\u4fe1\\u606f:18915598993(\\u4e3b\\u5361),18913057422(\\u526f\\u5361),18913079380(\\u526f\\u5361),\",\n" +
                "                    \"accProdInstId\": \"123217993569\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"custId\": \"123017329834\",\n" +
                "            \"areaCode\": \"0512\",\n" +
                "            \"oldOfferList\": [\n" +
                "                {\n" +
                "                    \"offerCode\": \"300509034199\",\n" +
                "                    \"offerName\": \"\\u5929\\u7ffc\\u4e91\\u76d8\\u9ec4\\u91d1\\u4f1a\\u545829\\u5143/\\u6708\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"offerCode\": \"911007646\",\n" +
                "                    \"offerName\": \"\\u5168\\u56fd\\u6d41\\u91cf\\u7545\\u4eab399\\u5143\\u878d\\u5408\\u5957\\u9910\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"offerCode\": \"300509036538\",\n" +
                "                    \"offerName\": \"\\u5168\\u56fd\\u6d41\\u91cf\\u7545\\u4eab399\\u5143\\u4e2a\\u4eba\\u5957\\u9910\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"offerCode\": \"300509006342\",\n" +
                "                    \"offerName\": \"\\u5458\\u5de5\\u901a\\u4fe1\\u8d39\\u7efc\\u5408\\u4f18\\u60e0400\\u5143\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"offerCode\": \"300500002120\",\n" +
                "                    \"offerName\": \"\\u6807\\u51c6_\\u5929\\u7ffc\\u865a\\u62df\\u7f51\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"createTime\": \"2019-12-10 15:36:17\",\n" +
                "            \"bssChannelId\": \"14106527\",\n" +
                "            \"orderId\": \"MINI20191210153617217387010\",\n" +
                "            \"custName\": \"\\u6f58\\u5fd7\\u6770\",\n" +
                "            \"mainOfferName\": \"\\u5b58\\u91cf\\u5347\\u7ea7\\u878d\\u5408139\\u673a\\u8865(\\u6a59\\u5206\\u671f)\",\n" +
                "            \"mainOfferCode\": \"250000837\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"isSuccess\": \"0\"\n" +
                "}");
        //字符串压缩为byte数组
        byte[] values = value.toString().getBytes() ;
        System.out.println("解压前大小"+values.length);
        values = compress(values) ;
        System.out.println("解压后大小"+values.length);




        //把压缩后的byte数组转为字符串
        String str = new String(values,"iso8859-1") ;

        //传输字符串
        System.out.println(str);

        //将接受到的字符串转换为byte数组
        values = str.getBytes("iso8859-1") ;
        //解压缩这个byte数组
        values = decompress(values) ;
        System.out.println(new String(values, "utf-8"));

    }

}
