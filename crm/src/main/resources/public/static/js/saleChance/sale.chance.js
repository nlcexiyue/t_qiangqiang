layui.use(['form', 'table','layer','jquery'], function () {
    var form = layui.form,
        table = layui.table,
        layer = layui.layer,
        $ = layui.jquery;

    table.render({
        elem: '#saleChanceList',
        url: '/sale_chance/list',
        toolbar:'#toolbarDemo',
        page: true,
        height : 690,
        where: {},
        cols: [[
            {type: 'checkbox', fixed: 'left'},
            {field: 'id', title: 'ID', width: 70, fixed: 'left'},
            {field: 'chanceSource', title: '机会来源', width: 98},
            {field: 'customerName', title: '客户名称', width: 98},
            {field: 'cgjl', title: '成功概率', width: 80},
            {field: 'overview', title: '概要', width: 98},
            {field: 'linkMan', title: '联系人', width: 98},
            {field: 'linkPhone', title: '联系电话', width: 98},
            {field: 'description', title: '备注描述', width: 98},
            {field: 'createMan', title: '创建人', width: 98},
            {field: 'assignMan', title: '分配人', width: 98},
            {field: 'assignTime', title: '分配时间', width: 98},
            {
                field: 'state', title: '分配状态', width: 98, templet: function (d) {
                    if (d.state === 0) {
                        return '<span style="color: red">未分配</span>';
                    } else if (d.state === 1) {
                        return '<span style="color: green">已分配</span>';
                    }
                }
            },
            {field: 'devResult', title: '开发结果', width: 98,templet:function (d) {
                    if(d.devResult === 0){
                        return '<span style="color: red">未开发</span>';
                    }else if(d.devResult === 1){
                        return '<span style="color: greenyellow">开发中</span>';
                    }else if(d.devResult === 2){
                        return '<span style="color: green">开发成功</span>';
                    }else if(d.devResult === 3){
                        return '<span style="color: yellow">开发失败</span>';
                    }
                }},
            {field: 'isValid', title: '有效状态', width: 98,templet:function (d) {
                    if(d.isValid === 0){
                        return '<span style="color: red">无效</span>';
                    }else if (d.isValid === 1) {
                        return '<span style="color: green">有效</span>';
                    }
                }},
            {field: 'createDate', title: '创建时间', width: 98},
            {field: 'updateDate', title: '更新时间', width: 98},
            {field: 'uname', title: '客户', width: 98},
            {field:'right',title:'操作',toolbar:'#saleChanceListBar',width:118}]],
        parseData: function (data) {
            return {
                'data': data.data,
                'code': data.code
            }
        },
        response: {
            statusCode: 0
        }


    });
    //头工具栏事件
    table.on('toolbar(saleChances)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);
        console.log(checkStatus)
        switch(obj.event){
            case 'add':
                openAdd();


                // window.location.href = ctx + '/sale_chance/addOrUpdateSaleChancePage';
                break;
            case 'del':
                var data = checkStatus.data;
                layer.msg('选中了：'+ data.length + ' 个');
                break;
            //自定义头工具栏右侧图标 - 提示
            case 'LAYTABLE_TIPS':
                layer.alert('这是工具栏右侧自定义的一个图标按钮');
                break;
        };
    });

    //监听行工具事件
    table.on('tool(saleChances)', function(obj){
        var data = obj.data;
        //console.log(obj)
        if(obj.event === 'del'){
            layer.confirm('真的删除行么', function(index){
                obj.del();
                layer.close(index);
            });
        } else if(obj.event === 'edit'){
            layer.prompt({
                formType: 2
                ,value: data.email
            }, function(value, index){
                obj.update({
                    email: value
                });
                layer.close(index);
            });
        }
    });



    function openAdd() {
        layer.open({
            type: 1,
            area:['80%','80%'],
            content: '<form class="layui-form" style="width:80%;" id="window">\n' +
                '    <input type="hidden" name="id"  value="">\n' +
                '    <input type="hidden" name="man"  value="">\n' +
                '    <div class="layui-form-item layui-row layui-col-xs12">\n' +
                '        <label class="layui-form-label">客户名称</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input type="text" class="layui-input"\n' +
                '                   lay-verify="required" name="customerName" id="customerName"  value=""  placeholder="请输入客户名称">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item layui-row layui-col-xs12">\n' +
                '        <label class="layui-form-label">机会来源</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input type="text" class="layui-input"\n' +
                '                   name="chanceSource" id="chanceSource" placeholder="请输入机会来源" value="">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item layui-row layui-col-xs12">\n' +
                '        <label class="layui-form-label">联系人</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input type="text" class="layui-input"\n' +
                '                   id="linkMan" name="linkMan"  lay-verify="required"\n' +
                '                   placeholder="请输入联系人" value="">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '\n' +
                '    <div class="layui-form-item layui-row layui-col-xs12">\n' +
                '        <label class="layui-form-label">联系电话</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input type="text" class="layui-input"\n' +
                '                   lay-verify="phone" name="linkPhone" id="phone" placeholder="请输入联系电话" value="">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item layui-row layui-col-xs12">\n' +
                '        <label class="layui-form-label">概要</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input type="text" class="layui-input"\n' +
                '                   name="overview"  id="overview" placeholder="请输入概要" value="">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item layui-row layui-col-xs12">\n' +
                '        <label class="layui-form-label">成功几率(%)</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <input type="text" class="layui-input"\n' +
                '                   id="cgjl" name="cgjl" placeholder="请输入成功几率" value="">\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item layui-row layui-col-xs12">\n' +
                '        <label class="layui-form-label">机会描述</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <textarea placeholder="请输入机会描述信息" name="description" id="description" class="layui-textarea"></textarea>\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <div class="layui-form-item layui-row layui-col-xs12">\n' +
                '        <label class="layui-form-label">指派给</label>\n' +
                '        <div class="layui-input-block">\n' +
                '            <select name="assignMan" id="assignMan">\n' +
                '                <option id="option" value="" >请选择</option>\n' +
                '            </select>\n' +
                '        </div>\n' +
                '    </div>\n' +
                '    <br/>\n' +
                '</form>',
            btn: ['确定', '取消'],
            yes: function(index, layero){
                //按钮【按钮一】的回调
                $.ajax({
                    type:'post',
                    url:ctx+'/sale_chance/save',
                    dataType:'json',
                    data:{
                        chanceSource:$('#chanceSource').val(),
                        customerName:$('#customerName').val(),
                        cgjl:$('#cgjl').val(),
                        linkMan:$('#linkMan').val(),
                        linkPhone:$('#phone').val(),
                        overview:$('#overview').val(),
                        description:$('#description').val(),
                        assignMan:$("#assignMan").find("option:selected").attr("value")
                    },
                    success:function (msg) {
                        var code = msg.code;
                        if(code === 200){
                            layer.msg('添加成功',{
                                time:1000
                            },function () {
                                window.location.href = ctx+'/sale_chance/index';
                            })
                        }else{
                            layer.msg(msg.msg)
                        }
                    }
                });
            },
            cancel: function(){

            }

        })


        $.ajax({
            type:'post',
            data:{},
            dataType:'json',
            url:ctx+'/user/list',
            success:function (msg) {
                var code = msg.code;
                if(code === 0){
                    var data = msg.data;
                    var length = data.length;
                    for (var i = 0; i < length; i++) {
                        $('#assignMan').append('<option value="'+data[i].id+'" >'+data[i].trueName+'</option>')
                    }
                    form.render('select');
                }else{
                    layer.msg(msg.msg);
                }
            }
        });
    }




})

