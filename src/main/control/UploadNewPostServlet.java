package main.control;

import main.JDBC.PostMessage;
import main.admin.admin_model;
import main.model.postMessageBean;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;


/**
 * 接受 wallpage  页面  post  发送表单
 * 将表单中  文字 图片  插入数据库
 */



@WebServlet(name = "UploadNewPostServlet")

public class UploadNewPostServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //    获取 session 中的 自身的 user_id
        HttpSession session = request.getSession();
        int user_id = (int) session.getAttribute("userid");

        //插入一条新的状态
        String p_content = null;
        String p_image = null;

        //     文字 图片 处理
        //step1 是否支持上传
        boolean ismultipartContent =  ServletFileUpload.isMultipartContent(request);
        if(!ismultipartContent){
            throw new RuntimeException("your form is not multipart/form-data");
        }

        //step2 创建 DiskFileItemFactory 工厂类
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //step 3创建 servletfileupload核心对象
        ServletFileUpload sfu = new ServletFileUpload(factory);
        //step 4解析request对象 得到表单项集合
        try {
            List<FileItem> fileItems = sfu.parseRequest(request);
            //遍历表单项数据
            for (FileItem fileitem:fileItems
                 ) {
                if(fileitem.isFormField()){
                    //普通表单项目 -- p_contentadmin
                    p_content = processFormField(fileitem);


                }else {
                    //上传表单项目 -- p_image
                    p_image = processUploadField(fileitem);
                }

            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        //将 新 post 插入数据库
        PostMessage.insertMessage(p_content,p_image,user_id);
        admin_model admin = new admin_model();
        try {
            String log_content = p_content;
            if (!p_image.equals("")){
                log_content += " and photo: "+p_image;
            }
            admin.log_post(user_id, log_content);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    private String  processFormField(FileItem fileitem) {
         return fileitem.getString();

    }

    private String processUploadField(FileItem fileitem) {
        String filename = null;

        //得到文件流
        try {
            //得到文件输入流
            InputStream is = fileitem.getInputStream();

            //创建 磁盘文件夹
            String directoryRealPath = this.getServletContext().getRealPath("/uploadImage/");
            System.out.print(directoryRealPath);
            File storeDirectory = new File(directoryRealPath);
            if(!storeDirectory.exists()){
                storeDirectory.mkdirs();//如不存在 创建一个制定的文件夹
            }


            //得到上传文件名字
            filename = fileitem.getName();
            if(filename!=null && filename != ""){
                filename = FilenameUtils.getName(filename);
                filename = UUID.randomUUID() + "_" + filename;
            }else {
                return null;
            }

            //创建一个文件 在这个目录下
            File file = new File(storeDirectory,filename);

            //通过文件输出流将上传的文件保存在磁盘
            FileOutputStream fos = new FileOutputStream(file);

            int len = 0;
            byte[] b = new byte[1024];
            while ((len=is.read(b)) != -1)
            {
                fos.write(b,0,len);
            }
            fos.close();
            is.close();


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            return filename;
        }

    }



    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);

    }
}
