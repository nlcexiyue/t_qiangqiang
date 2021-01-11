layui.use(['form','table','jquery'],function () {
    var form = layui.form,
        table = layui.table,
        $ = layui.jquery;
    var render = table.render({
        elem:'#saleChanceList',
        height:690,
        id:'tableOne',
        method:'post',
        url:'/cus_dev_plan/list',
        page:true,
        limit: 10,
        where:{},
        cols:[[
            {field:'id',title:'ID',width:80,fixed:'left'},
            {field:'saleChanceId',title:'订单机会',width:200},
            {field:'planItem',title:'计划标题',width:200},
            {field:'planDate',title:'计划时间',width:200},
            {field:'exeAffect',title:'计划影响',width:200},
            {field:'createDate',title:'创建时间',width:200},
            {field:'updateDate',title:'更新时间',width:200},
            {field:'isValid',title:'有效状态',width:200,templet:function (d) {
                    if(d.isValid === 1){
                        return '有效';
                    }else{
                        return  '无效';
                    }
                }}]],
        parseData:function (data) {
            return {
                'data':data.data.list,
                'code':data.code,
                'count':data.count
            }
        },
        response:{
            statusCode:0
        }
    });

    $.ajax({
        type:'get',
        url:ctx+'/sale_chance/list',
        data:{flag:1},
        dataType:'json',
        success:function (msg) {
            var code = msg.code;
            if(code === 0){
                var data = msg.data;
                var length = msg.data.length;
                for (var i = 0; i < length ; i++) {
                    $('#devResult').append('<option value="'+data[i].id+'" >'+data[i].chanceSource+'----'+data[i].customerName+'</option>');
                }
                form.render('select');
            }else{
                layer.msg(msg);
            }
        }
    })

    $('#search').on('click',function () {
        var val = $('#devResult option:selected').val();
        console.log(val)
        table.reload('tableOne',{
            method:'get',
            where:{
                sid:val
            },
            url:'/cus_dev_plan/listBySearch',
        });
        return false;
    })








})