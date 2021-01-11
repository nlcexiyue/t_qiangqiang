<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>修改密码</title>
    <#include "common.ftl">
    <style>
        .layui-form-item .layui-input-company {width: auto;padding-right: 10px;line-height: 38px;}
    </style>
</head>
<body>
<div class="layuimini-container">
    <div class="layuimini-main">

        <div class="layui-form layuimini-form">
            <div class="layui-form-item">
                <label class="layui-form-label required">旧的密码</label>
                <div class="layui-input-block">
                    <input type="password" name="old_password" lay-verify="required" lay-reqtext="旧的密码不能为空" placeholder="请输入旧的密码"  value="" class="layui-input">
                    <tip>填写自己账号的旧的密码。</tip>
                </div>
            </div>

            <div class="layui-form-item">
                <label class="layui-form-label required">新的密码</label>
                <div class="layui-input-block">
                    <input type="password" name="new_password" lay-verify="required" lay-reqtext="新的密码不能为空" placeholder="请输入新的密码"  value="" class="layui-input">
                </div>
            </div>
            <div class="layui-form-item">
                <label class="layui-form-label required">确认密码</label>
                <div class="layui-input-block">
                    <input type="password" name="again_password" lay-verify="required" lay-reqtext="新的密码不能为空" placeholder="请输入新的密码"  value="" class="layui-input">
                </div>
            </div>

            <div class="layui-form-item">
                <div class="layui-input-block">
                    <button class="layui-btn" lay-submit lay-filter="saveBtn">确认保存</button>
                </div>
            </div>
        </div>
    </div>
</div>
<#--<script type="text/javascript" src="${ctx}/js/user/password.js"></script>-->
<script>
    layui.use(['layer','form','jquery','jquery_cookie'],function () {
        var form = layui.form,
                layer = layui.layer,
                $ = layui.jquery,
                $ = layui.jquery_cookie($);
    form.on('submit(saveBtn)',function (data) {
        var field = data.field;
        var oldPassword = field.old_password;
        var newPassword = field.new_password;
        var againPassword = field.again_password;
        if (oldPassword === newPassword ){
            layer.msg("新密码不能与旧密码一样");
            return false;
        }
        if(!newPassword === againPassword){
            layer.msg('两次输入的新密码不一致');
            return false;
        }

        $.ajax({
            type:'post',
            data:{
                oldPassword:oldPassword,
                newPassword:newPassword,
                confirmPassword:againPassword
            },
            dataType:'json',
            url:ctx + '/user/updatePassword',
            success:function (msg) {
                var code = msg.code;
                var result = msg.result;
                if (code == 200){
                    layer.msg("密码修改成功,请重新登录",{
                        time:1000
                    },function () {
                        //清除cookie,将对应属性值设置为null
                        $.cookie('userIdStr',null);
                        $.cookie('userName',null);
                        $.cookie('trueName',null);
                        window.parent.location.href = ctx +'/index';
                    });
                }else{
                    layer.msg("密码修改错误,请联系管理员");
                }
            }



        })
        



    })
    





    });
</script>
</body>
</html>