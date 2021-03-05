
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.Socket;

public final class SocketHttpsTest {


    public static void main(String[] args) throws Exception {
        StringBuffer reqSb = new StringBuffer("");

        reqSb.append("POST /ExecOrderTCServlet HTTP/1.1\n" +
                "Content-length: 235\n" +
                "Accept: application/json\n" +
                "Host: 132.252.3.204:443\n" +
                "Content-type: application/xml\n" +
                "Accept-Language: zh-cn\n" +
                "User-Agent: sgw web server\n" +
                "SOAPAction:  \n" +
                "\n" +
                "{\n" +
                "    \"Request\": {\n" +
                "        \"userCode\": \"111724702559\",\n" +
                "        \"userType\": \"1\",\n" +
                "        \"busiCode\": \"OPR_PO11S_REQ\",\n" +
                "        \"hallId\": \"13188694\",\n" +
                "        \"operId\": \"ZHAO1WG\",\n" +
                "        \"oprPosCode\": \"2001\",\n" +
                "        \"cityId\": \"0511\"\n" +
                "    }\n" +
                "}\n" +
                "\n");
        System.out.println(reqSb.toString());
        X509TrustManager xtm = new EopX509TrustManager();
        TrustManager mytm[] = {xtm};
        // 得到上下文  
        SSLContext ctx = SSLContext.getInstance("SSL");
// 初始化  
        ctx.init(null, mytm, null);
// 获得工厂  
        SSLSocketFactory factory =ctx.getSocketFactory();
// 从工厂获得Socket连接  
        Socket socket = factory.createSocket("132.252.3.204",443);
        SegmentInputStream segmentInputStream = null;
        try {
            //获取输出流，即我们写出给服务器的数据
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            //发送请求
            bufferedWriter.write(reqSb.toString());
            bufferedWriter.flush();
            //socket.shutdownOutput();
            //打印请求日志
            final BufferedReader requestReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(reqSb.toString().getBytes())));
            String line = null;
            while ((line = requestReader.readLine()) != null) {
                System.out.println("-->" + line);
            }
            //获取输入流，即从服务器获取的数据
            segmentInputStream = new SegmentInputStream(socket.getInputStream());

            StringBuffer resSb = new StringBuffer();
            resSb.append(segmentInputStream.getStringHeader());
            //解析包体
            byte[] tt = new byte[1024];
            int b;
            StringBuffer bodySb = new StringBuffer();
            while ((b = segmentInputStream.read(tt)) != -1) {
                String tzt = new String(tt, "iso8859-1").trim();
                System.out.println("<--" + tzt);
                bodySb.append(tzt);

            }
            byte[] values = bodySb.toString().getBytes("iso8859-1");
            String contentEncodeing = segmentInputStream.getHeaders().get("Content-Encoding");
            if (contentEncodeing != null && contentEncodeing.trim().toLowerCase().equals("gzip")) {
                values = GzipUtil.decompress(values);
            }
            System.out.println("result=" + new String(values, "utf-8"));


            // Thread.sleep(10000);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            segmentInputStream.close();
            socket.close();
        }

    }


}
