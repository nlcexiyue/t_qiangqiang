<!DOCTYPE html>
<html>
<head>
    <title>客户信息管理</title>
	<#include "common.ftl">
</head>
<body class="childrenBody">
<button data-type="test2" class="layui-btn layui-btn-primary">右侧呼出</button>
<div class="layui-row layui-col-space1">
    <div class="layui-col-xs4">
        <!-- 填充内容 -->
        <div class="layui-card">
            <div class="layui-card-body" style="height: 246px;">1</div>
        </div>
    </div>
    <div class="layui-col-xs4">
        <div class="layui-card">
            <div class="layui-card-body" style="height: 246px;">2</div>
        </div>
    </div>
    <div class="layui-col-xs4">
        <div class="layui-card">
            <div class="layui-card-body" style="height: 246px;">3</div>
        </div>
    </div>
    <div class="layui-col-xs4">
        <div class="layui-card">
            <div class="layui-card-body" style="height: 246px;">4</div>
        </div>
    </div>
    <div class="layui-col-xs4">
        <div class="layui-card">
            <div class="layui-card-body" style="height: 246px;">5</div>
        </div>
    </div>
    <div class="layui-col-xs4">
        <div class="layui-card">
            <div class="layui-card-body" style="height: 246px;">6</div>
        </div>
    </div>
    <div class="layui-col-xs4">
        <div class="layui-card">
            <div class="layui-card-body" style="height: 246px;">7</div>
        </div>
    </div>
    <div class="layui-col-xs4">
        <div class="layui-card">
            <div class="layui-card-body" style="height: 246px;">8</div>
        </div>
    </div>
    <div class="layui-col-xs4">
        <div class="layui-card">
            <div class="layui-card-body" style="height: 246px;">9</div>
        </div>
    </div>
</div>

<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <input type="text" name="customerName"
                           class="layui-input
					searchVal" placeholder="客户名"/>
                </div>
                <div class="layui-input-inline">
                    <input type="text" name="createMan" class="layui-input
					searchVal" placeholder="创建人"/>
                </div>
                <div class="layui-input-inline">
                    <select name="state" id="state">
                        <option value="">分配状态</option>
                        <option value="0">未分配</option>
                        <option value="1">已分配</option>
                    </select>
                </div>
                <a class="layui-btn search_btn" data-type="reload"><i
                        class="layui-icon">&#xe615;</i> 搜索</a>
            </div>
        </form>
    </blockquote>
    <table id="customerList" class="layui-table" lay-filter="customer"></table>


    <script type="text/html" id="toolbarDemo">
        <div class="layui-btn-container">
            <a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
                <i class="layui-icon">&#xe608;</i>
                添加
            </a>
            <a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
                <i class="layui-icon">&#xe608;</i>
                删除
            </a>
        </div>
    </script>


    <!--操作-->
    <script id="saleChanceListBar" type="text/html">
        <a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
    </script>

</form>
<#--<script type="text/javascript" src="${ctx}/js/customer/customer.js"></script>-->
<script>

</script>
</body>
</html>