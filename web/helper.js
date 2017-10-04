// for wallpage.jsp



//隐藏 post
function toggle(targetid){
    if (document.getElementById){
        var target=document.getElementById(targetid);
        if (target.style.display==""){
            target.style.display="none";
            sessionStorage.setItem(targetid,"1");
        } else {
            target.style.display="";
        }
    }
}


//删除post
function deletepost(targetid) {
    if (document.getElementById){
        var target=document.getElementById(targetid);
        if (target.style.display==""){
            target.style.display="none";
            deletep(targetid);
        }
    }
}


//ajax  --- xmlhttprequest
function getXMLHttpRequest() {
    var xmlhttp;
    if(window.XMLHttpRequest){
        xmlhttp = new XMLHttpRequest();
    }else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    return xmlhttp;

}

//ajax异步 连接servlet 发送请求
function deletep(p_id) {
        //获得 XMLHTTPREQUEST 对象
        var req = getXMLHttpRequest();
        req.onreadystatechange = function ()
        {
            if(req.readyState == 4)
            {
                if(req.status==200)
                {
                    alert("delete success");
                }
            }
        }
        req.open("get","postmessage?p_id=" + p_id);
        req.send(null);

    }

//点赞  取消赞
function likeornot(p_id,flag,id) {
    var req = getXMLHttpRequest();
    req.onreadystatechange = function ()
    {

        if(req.readyState == 4)
        {
            if(req.status == 200)
            {
                 alert(req.responseText);

            }
        }
    }

    if(flag == "like"){
        //取消赞
        var r1 = document.getElementById(id);
        r1.innerHTML="unlike";
        req.open("get", "unlikepost?p_id=" + p_id);
        req.send(null);

    }else {
        var r2 = document.getElementById(id);
        r2.innerHTML="like";
        req.open("get", "likepost?p_id=" + p_id);
        req.send(null);
    }




}
