<%@ include file="tag.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>商品详情页</title>
</head>
<body>
    <div class="container">
        <div class="panel panel-default">
            <div class="panel-heading text-center">
                ${secKill.name}
            </div>
            <div class="panel-body" >
                <h2 class="text-danger">
                    <span class="glyphicon glyphicon-time" ></span>
                    <span class="glyphicon" id = "seckill-box" ></span>
                </h2>
            </div>
        </div>
    </div>
    <div id="killPhoneModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title text-center">
                        <span class="glyphicon glyphicon-phone" ></span>
                    </h3>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-8 col-xs-offset-2">
                            <input type="text" name="killPhone" id="killPhone" placeholder="填手机号" class="form-control"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span id="killPhoneMessage" class="glyphicon"></span>
                    <button type="submit" class="btn btn-success" id="killPhoneBtn">
                        <span class="glyphicon glyphicon-phone"></span>
                        Submit
                    </button>
                </div>
            </div>
        </div>
    </div>
</body>
<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.js">
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-cookie/1.4.1/jquery.cookie.min.js">
</script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.countdown/2.2.0/jquery.countdown.min.js">
</script>
<script src="/resources/script/seckill.js" type="text/javascript" ></script>
<script type="text/javascript">
    $(function () {
        //使用EL表达式传入参数
        seckill.detail.init({
            seckillId:${secKill.seckillId},
            startTime:${secKill.startTime.time},//毫秒
            endTime:${secKill.endTime.time}
        });
    })
</script>
</html>
