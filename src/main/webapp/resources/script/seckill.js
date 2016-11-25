//存放主要的交互逻辑JS代码
//Javascript 模块化

var seckill = {
    //封装秒杀相关ajax的url
    URL:{
        now: function () {
            return '/seckill/time/now';
        }
    },
    //处理秒杀逻辑
    handleSeckill: function (seckillId, node) {

        node.hide()
            .html(); //按钮


    },
    //验证手机号码
    validatePhone: function (phone) {
        if (phone && phone.length==11 && !isNaN(phone)){
            return true;
        }else {
            return false;
        }
    },
    //计时逻辑处理
    countdown: function (seckillId, nowTime, startTime, endTime) {

        var seckillBox = $('#seckill-box');
        //时间判断
        if (nowTime > endTime){
            seckillBox.html('秒杀结束！');
        }else if (nowTime < startTime){
            //秒杀未开始，倒计时
            var killTime = new Date(startTime+1000);
            seckillBox.countdown(killTime, function (event) {
                //时间格式
                var format = event.strftime('秒杀倒计时: %D天 %H时 %M分 %S秒');
                seckillBox.html(format);
                //计时完成后的回调事件
            }).on('finish.countdown', function () {
                //获取秒杀地址 控制显示逻辑 执行秒杀
                seckill.handleSeckill(seckillId, seckillBox);
            });

        }else {
            //秒杀开始
            seckill.handleSeckill(seckillId, seckillBox);
        }
    },

    //详情页秒杀逻辑
    detail:{
        //详情页初始化
        init : function (params) {
            //手机验证和登录， 计时交互
            //在cookie中查找手机号码
            var killPhone = $.cookie('killPhone');
            //验证手机号码
            if (!seckill.validatePhone(killPhone)) {
                //绑定Phone
                //控制输出
                var killPhoneModel = $('#killPhoneModal');
                //显示弹出层
                killPhoneModel.modal({
                    show:true, //显示弹出层
                    backdrop:'static', //禁止位置关闭
                    keyboard:false //关闭键盘事件
                });
                $('#killPhoneBtn').click(function () {
                    var inputPhone = $('#killphoneKey').val();
                    console.log('inputPhone='+inputPhone); //TODO
                    if (seckill.validatePhone(inputPhone)){
                        //电话写入cookie
                        $.cookie('killPhone', inputPhone, {expires: 7, path: '/seckill'});
                        //刷新页面
                        window.location.reload();
                    }else {
                        $('#killphoneMessage').hide().html('<label class="label label-danger">手机号码输入错误！</label>').show(300);
                    }
                });
            };
            //已经登录了
            var startTime = params['startTime'];
            var endTime = params['endTime'];
            var seckillId = params['seckillId'];

            $.get(seckill.URL.now(), {}, function (result) {

                if (result && result['success']){
                    var nowTime = result['data'];
                    //时间判断
                    seckill.countdown(seckillId, nowTime, startTime, endTime);
                }else {
                    console.log('result='+result);
                }

            })
        }
    }
}