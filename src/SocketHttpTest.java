
import java.io.*;
import java.net.Socket;

public final class SocketHttpTest {


    public static void main(String[] args) throws Exception {
        StringBuffer reqSb = new StringBuffer("");

        StringBuffer append = reqSb.append("POST /eop HTTP/1.1\r\n" +
                "Host: 192.168.58.239\r\n" +
                "User-Agent: Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 2.0.50727; .NET CLR 3.0.04506.648; .NET CLR 3.5.21022)\r\n" +
                "Connection: Keep-Alive\r\n" +
                "Accept-Encoding: *;q=0\r\n" +
                "Content-Length: 0\r\n" +
                "\r\n");


        System.out.println(reqSb.toString());
        Socket socket = new Socket("192.168.58.239",38764);

        //获取输出流，即我们写出给服务器的数据
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        //发送请求
        bufferedWriter.write(reqSb.toString());
        bufferedWriter.flush();
        socket.shutdownOutput();
        //打印请求日志
        final BufferedReader requestReader = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(reqSb.toString().getBytes())));
        String line = null;
        while((line=requestReader.readLine())!=null){
            System.out.println("-->"+line);
        }
        //获取输入流，即从服务器获取的数据
        SegmentInputStream segmentInputStream  = new SegmentInputStream(socket.getInputStream());

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
            tt=new byte[1024]; //zhao bao
        }
        byte []   values = bodySb.toString().getBytes("iso8859-1") ;
        String  contentEncodeing = segmentInputStream.getHeaders().get("Content-Encoding");
        if(contentEncodeing!=null&&contentEncodeing.trim().toLowerCase().equals("gzip")){
            values = GzipUtil.decompress(values);
        }
        System.out.println("result="+new String(values, "utf-8"));
        segmentInputStream.close();
        bufferedWriter.close();
        socket.close();

    }


}
