//存放交互逻辑代码
// javascript 模块化
//java  DAO/DTO
//分包JSON
//交互流程，验证用户：根据用户填写的参数，验证
var seckill = {
    //封装秒杀相关的Cookie,ajax url
    URL: {

    },
    validatePhone:function (phone) {
        if(phone && phone.length === 11 && !isNaN(phone)) {
            return true;
        } else {
            return false;
        }
    },
    detail: {
        init:function(params){
            // Cookie中查找手机号,js访问JSON数据
            var killPhone = $.cookie('killPhone')
            var startTime = params['seckillStarttime']
            var endTime = params['seckillEndtime']
        //    验证
            if(!seckill.validatePhone(killPhone)) {
                //控制输出
                var killPhoneModal = $("#killPhoneModal");
                killPhoneModal.modal({
                    show:true,//显示弹出层
                    backdrop:'static',//禁止位置关闭
                    keyboard:false//关闭键盘事件
                })
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killPhone').val();
                    if(seckill.validatePhone(inputPhone)) {
                        //电话写入Cookie
                        $.cookie('killPhone',inputPhone,{expires:7,path:"/seckill"});
                        window.location.reload();
                    } else {
                        //html页面先隐藏,放进去内容，再显示出来
                        $('#killPhoneMessage').hide().html('<label class="label label-danger">手机号错误</label>').show(300);
                    }

                });
            }
        }
    }
}