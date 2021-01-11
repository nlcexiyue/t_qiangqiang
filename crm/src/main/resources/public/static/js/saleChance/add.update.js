layui.use(['form','layer','jquery'],function () {
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery;

    $('#formCancel').on('click',function (e) {
        console.log(e);
        // e.stopPropagation();
        // document.getElementById('iframe id').contentWindow.history.back();
        // window.location.href = ctx + '/sale_chance/index';
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

    form.on('submit(addOrUpdateSaleChance)',function (data) {
        var field = data.field;
        var chanceSource = field.chanceSource;
        var customerName = field.customerName;
        var cgjl = field.cgjl;
        var linkMan = field.linkMan;
        var linkPhone = field.linkPhone;
        var overview = field.overview;
        var description = field.description;
        var assignMan = field.assignMan;

        $.ajax({
            type:'post',
            url:ctx+'/sale_chance/save',
            dataType:'json',
            data:{
                chanceSource:chanceSource,
                customerName:customerName,
                cgjl:cgjl,
                linkMan:linkMan,
                linkPhone:linkPhone,
                overview:overview,
                description:description,
                assignMan:assignMan
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
       return false;
    });

})