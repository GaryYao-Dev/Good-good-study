package main.control;

import javafx.geometry.Pos;
import main.JDBC.DButil;
import main.JDBC.PostMessage;
import main.JDBC.likeMessage;
import main.admin.admin_model;
import main.model.postMessageBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static main.JDBC.likeMessage.likelist;


/**
 *
 * 1,如接收到 p_id 则是 删除 post 操作
 * 2, 没有接收到 p_id 就是 单纯显示操作： 输出 用户 所有的好友的 状态 按时间顺序 返回给前端
 */

@WebServlet(name = "PostMessageServlet")
public class PostMessageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //获取 自身的 userid
        HttpSession session = request.getSession();
        int user_id = (int) session.getAttribute("userid");

        //根据是否传入 p_id的 值  判断 是 显示操作 还是 删除操作
        String p_id = request.getParameter("p_id");
        if(p_id != null){
            //删除操作
            String p_content = PostMessage.getPostContentByP_id(Integer.parseInt(p_id));
            String p_img = PostMessage.getIMGByP_id(Integer.parseInt(p_id));
            PostMessage.DeletePostMessageByP_id(Integer.parseInt(p_id));
            admin_model admin = new admin_model();
            try {
                String log_content = p_content;
                if (!p_img.equals("")) log_content += " and photo: "+ p_img;
                admin.log_delete_post(user_id, log_content);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            // 根据当前user_id 找出 他的 所有friend的 post
            List<postMessageBean> _list =  PostMessage.getPostByUserid(user_id);
            //找出自身赞过的所有post
            HashMap<Integer,Boolean> hlist =likeMessage.likelist(user_id);
            for (postMessageBean p:_list
                 ) {

                //该 post 是否被 自身点赞
                if(hlist.get(p.getP_id()) == null){
                    p.setLike("unlike");

                }else {

                    p.setLike("like");
                }
                //该 post 被那些人点过赞
                List<String> _l = likeMessage.getlikeUserList(p.getP_id());
                p.setLikeUserList(_l);
                p.setListUseramount(_l.size());
            }

            //发送到 wallpage 页面显示
            session.setAttribute("postMessageList",_list);

            request.getRequestDispatcher("/wallpage.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);


    }
}
