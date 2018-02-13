//存放交互逻辑代码
// javascript 模块化
//java  DAO/DTO
//分包JSON
//交互流程，验证用户：根据用户填写的参数，验证
var seckill = {
    //封装秒杀相关的Cookie,ajax url
    URL: {
        now:function () {
            return "/seckill/time/now"
        },
        exposer:function (seckillId) {
            return
        },
        execution:function () {
            return "/seckill/"+ seckillId +  md5 + "execution";
        }
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
        //    验证
            if(!seckill.validatePhone(killPhone)) {
                //控制输出
                var killPhoneModal = $("#killPhoneModal");
                killPhoneModal.modal({
                    show:true,//显示弹出层
                    backdrop:'static',//禁止位置关闭
                    keyboard:false//关闭键盘事件
                });
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
                var startTime = params['seckillStarttime']
                var endTime = params['seckillEndtime']
                //已经登录
                //及时交互
                //ajax请求获得当前系统时间
                $.get(seckill.URL.now(),{},function (result){
                    if(result && result['success']) {
                        var nowTime = result['data']
                        //    时间判断

                    }
                })
            }
        },
        countdown:function(seckillId,nowTime,startTime,endTime){
            var seckillBox = $('#seckill-box');
            if(nowTime > endTime) {
                seckillBox.html("秒杀结束！")
            } else if(nowTime < startTime) {
                // 秒杀未开始，开始计时
                var killTime = new Date(startTime + 1000)
                seckillBox.countDown(killTime,function(event){
                    var format = event.strftime('秒杀倒计时：%D天：%H时：%M分：%S秒')
                }).on('finish.countdown',function(){
                    //    获取秒杀地址
                    //    控制显示逻辑
                    //    用户执行秒杀
                    seckill.handlerSeckill();
                })
            } else {
                //    秒杀开始，与上面的逻辑相同
                seckill.handlerSeckill();
            }
        },
        handlerSeckill:function(seckillId, node){
            node.hide().html("<button class = 'btn btn-lg btn-primary' id = 'killBtn'>开启秒杀</button>")
            $.post(seckill.URL.exposer(seckillId),{},function (result) {
                if(result && result['success']) {
                    var exposer = result['data']
                    if(exposer['exposer']) {
                    //    开启秒杀
                        var url = seckill.URL.execution(seckillId,md5);
                        //使用one限制每次用户能发送的请求数量，从而减少服务端压力
                        $('#killBtn').one('click',function () {
                        //    执行秒杀请求的操作
                            $(this).addClass('disabled');//禁用按钮
                            $.post(url,{},function (result) {
                                if(result && result['success']) {
                                    var killResult = result['data'];
                                    var state = killResult['state'];
                                    var stateInfo = killResult['stateInfo'];
                                    node.html('<span class="label label-success">'+stateInfo+'</span>')
                                }
                            })
                        })
                    } else {
                    //    未开始
                        var now = exposer['now'];
                        var end = exposer['end'];
                        var start = exposer['start'];
                        seckill.countdown(seckillId,now,start,end);
                        //重新进入计时面板计时，PC时间偏差
                    }
                }
            })
        }
    }
}