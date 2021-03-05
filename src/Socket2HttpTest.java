
import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public final class Socket2HttpTest {


    public static void main(String[] args) throws Exception {
        StringBuffer reqSb = new StringBuffer("");

        reqSb.append("POST /resweb/service/od HTTP/1.1\n" +
                "Accept-Encoding: gzip, deflate, br\n" +
                "Connection: Keep-Alive\n" +
                "Content-Length: 739\n" +
                "Content-Type: application/xml;charset=utf-8\n" +
                "Host: 132.228.224.9\n" +
                "User-Agent:Apache-HttpClient/4.1.1(java1.5)\n" +
                "\n" +
                "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://service.webservice.module.resmaster.ztesoft.com/\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <ser:orderUnLockNew>\n" +
                "         <arg0><![CDATA[\n" +
                "\n" +
                "\t\t<root>\n" +
                "\t\t\t<msgHead>\n" +
                "\t\t\t\t<time>2020-01-09T13:10:15</time>\n" +
                "\t\t\t\t<source>xxxx@j1s.cn</source>\n" +
                "\t\t\t\t<target>vvvv@j2s.cn</target>\n" +
                "\t\t\t</msgHead>\n" +
                "\t\t\t<msgBody>\n" +
                "\t\t\t\t<areaCode>gy.yz.js.cn</areaCode>\n" +
                "\t\t\t\t<boIds>\n" +
                "\t\t\t\t\t<boId>14000157032709_0</boId>\n" +
                "\t\t\t\t</boIds>\n" +
                "\t\t\t\t<staffName>xxx</staffName>\n" +
                "\t\t\t\t<staffNumber>GY9381</staffNumber>\n" +
                "\t\t\t\t<staffId>1000000036393</staffId>\n" +
                "\t\t\t\t<channelId>16169846</channelId>\n" +
                "\t\t\t</msgBody>\n" +
                "\t\t</root>\n" +
                "\t]]></arg0>\n" +
                "      </ser:orderUnLockNew>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>\n" +
                "\n");
        System.out.println(reqSb.toString());
        Socket socket = new Socket("132.228.224.9",80);
        SegmentInputStream segmentInputStream = null;
   try{
        //获取输出流，即我们写出给服务器的数据
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        //发送请求
        bufferedWriter.write(reqSb.toString());
        bufferedWriter.flush();
       //socket.shutdownOutput();
        //打印请求日志
        final BufferedReader requestReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(reqSb.toString().getBytes())));
        String line = null;
        while((line=requestReader.readLine())!=null){
            System.out.println("-->"+line);
        }
        //获取输入流，即从服务器获取的数据
         segmentInputStream  = new SegmentInputStream(socket.getInputStream());

        StringBuffer resSb = new StringBuffer();
        resSb.append(segmentInputStream.getStringHeader());
        //解析包体
        byte [] tt=new byte[1024];
        int b;
        StringBuffer bodySb = new StringBuffer();
        while((b=segmentInputStream.read(tt))!=-1) {
            String tzt = new String(tt, "iso8859-1").trim();
            System.out.println("<--" + tzt);
            bodySb.append(tzt);

        }
        byte []   values = bodySb.toString().getBytes("iso8859-1") ;
        String  contentEncodeing = segmentInputStream.getHeaders().get("Content-Encoding");
        if(contentEncodeing!=null&&contentEncodeing.trim().toLowerCase().equals("gzip")){
            values = GzipUtil.decompress(values);
        }
        System.out.println("result="+new String(values, "utf-8"));


       // Thread.sleep(10000);

   }catch (Exception ex){
       ex.printStackTrace();
   }finally {
       segmentInputStream.close();
       socket.close();
   }

    }


}
