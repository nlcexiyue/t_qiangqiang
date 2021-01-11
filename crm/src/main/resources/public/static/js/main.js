layui.use(['element', 'layer', 'layuimini','jquery','jquery_cookie'], function () {
    var $ = layui.jquery,
        layer = layui.layer,
        $ = layui.jquery_cookie($);

    // 菜单获取
    //layuimini.init(ctx+'/static/api/init.json');
    $('#layuiminiHomeTabIframe').html('<iframe width="100%" height="100%" frameborder="0"  src="welcome"></iframe>')
    layuimini.initTab();


    $('.login-out').on("click", function () {
        layer.confirm('退出请确认',{
            btn:['确认退出','取消'],
            yes:function (index,layero) {
                $.removeCookie('userIdStr',{domain:'localhost',path:'/'});
                $.removeCookie('userName',{domain:'localhost',path:'/'});
                $.removeCookie('trueName',{domain:'localhost',path:'/'});
                layer.open({
                    type:3,
                    time:500
                })
                window.parent.location.href = ctx + '/index';
            },
            btn2:function (index,layero) {
                layer.close(index);

            },cancel: function(){

            }
        })




        // $.ajax({
        //     type:"post",
        //     url:ctx+"/user/exit",
        //     dataType:"json",
        //     success:function (data) {
        //         if(data.code==200){
        //             layer.msg('退出登录成功', function () {
        //                 $.removeCookie("userIdStr");
        //                 $.removeCookie("userName");
        //                 $.removeCookie("trueName");
        //                 window.location = ctx+"/index";
        //             });
        //         }else{
        //             layer.msg(data.msg);
        //         }
        //     }
        // });
    });

});