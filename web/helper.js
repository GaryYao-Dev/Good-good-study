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
function likeornot(p_id,id) {
    var req = getXMLHttpRequest();
    req.onreadystatechange = function ()
    {

        if(req.readyState == 4)
        {
            if(req.status == 200)
            {
                 alert(req.responseText);
                window.location.href ="postmessage"


            }
        }
    }

    var r = document.getElementById(id);
    var flag = r.lastElementChild;
    if(flag.innerHTML == "like"){
        //取消赞

        flag.innerHTML="unlike";
        req.open("get", "unlikepost?p_id=" + p_id);
        req.send(null);

    }else {
        flag.innerHTML="like";
        req.open("get", "likepost?p_id=" + p_id);
        req.send(null);
    }




}
