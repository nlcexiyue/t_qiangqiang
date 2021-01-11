layui.use(['form','jquery','jquery_cookie'],function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        $ = layui.jquery_cookie($);
    form.on('submit(login)',function (data) {
        var field = data.field;
        var username = field.username;
        var password = field.password;
        if(username == 'undefined' || username == '' || username.trim() == ''){
            layer.msg('用户名称不能为空');
            return false;
        }else if(password == 'undefined' ||password == '' || password.trim() == ''){
            layui.msg('密码不能为空');
            return false;
        }
        $.ajax({
            type:'post',
            data:{
                userName:field.username,
                userPwd:field.password
            },
            url:"/login",
            dataType:'json',
            success:function (msg) {
                var code = msg.code;
                var result = msg.result;
                if(code == 200){
                    layer.msg('登录成功',{
                        icon:1,
                        time:1000
                    },function () {
                        $.cookie('userIdStr',result.userIdStr);
                        $.cookie('userName',result.userName);
                        $.cookie('trueName',result.trueName);
                        if($("input[type='checkbox']").is(':checked')){
                            // 写入cookie 7天
                            $.cookie("userIdStr",result.userIdStr, { expires: 7 });
                            $.cookie("userName",result.userName, { expires: 7 });
                            $.cookie("trueName",result.trueName, { expires: 7 });
                            $.cookie("lala","这是我自己存进去的");
                        }
                        window.location.href = ctx + '/main';
                    })

                }else{
                    layer.msg(msg.msg)
                }
            }

        })
        return false;
    });





});

function isEmpty(data) {
    if(data == '' || data.trim() == ''){
        return false;
    }
}
