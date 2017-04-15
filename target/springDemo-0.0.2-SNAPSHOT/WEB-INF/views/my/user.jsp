<%--
  Created by IntelliJ IDEA.
  User: songfeng
  Date: 2017/4/12
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions"  prefix="fn"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path;
%>
<html>
<head>
    <title>Title</title>
    <meta charset="utf-8" />
    <%--不知道这里的basePath 设置没用 --%>
    <base href="<%=basePath%>"/>
    <title>我的数据</title>
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport" />
    <link   rel="icon" href="https://open.itboy.net/favicon.ico" type="image/x-icon" />
    <link   rel="shortcut icon" href="http://img.wenyifan.net/images/favicon.ico" />
    <link href="<%=basePath%>/js/common/bootstrap/3.3.5/css/bootstrap.min.css?${_v}" rel="stylesheet"/>
    <link href="<%=basePath%>/css/common/base.css?${_v}" rel="stylesheet"/>
    <script  src="http://open.itboy.net/common/jquery/jquery1.8.3.min.js"></script>
    <script  src="<%=basePath%>/js/common/layer/layer.js"></script>
    <script  src="<%=basePath%>/js/common/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
    <jsp:include page="../common/config/top.jsp"></jsp:include>
    <div class="container" style="padding-bottom: 15px;min-height: 300px; margin-top: 40px;">
        <div>
            <h2>我的数据</h2>
            <hr>
            <form method="post" action="" id="formId" class="form-inline">
                <div clss="well">
                    <div class="form-group">
                        <input type="text" class="form-control" style="width: 150px;" name="id" id="id" placeholder="请输入用户Id">
                    </div>
                    <span class=""> <%--pull-right --%>
                        <a onclick="queryUser()" class="btn btn-primary">查询</a>
                        <a onclick="addUser()" class="btn btn-primary">新增</a>
                     </span>
                </div>
            </form>
        </div>
    </div><%--/row--%>

    <%--弹框--%>
    <div class="modal fade bs-example-modal-sm"  id="selectRole" tabindex="-1" role="dialog" aria-labelledby="selectRoleLabel">
        <div class="modal-dialog modal-sm" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="selectRoleLabel">添加角色</h4>
                </div>
                <div class="modal-body">
                    <form id="boxRoleForm">
                        loading...
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" onclick="selectRole();" class="btn btn-primary">Save</button>
                </div>
            </div>
        </div>
    </div>
    <%----/弹框--%>

    <script type="text/javascript">
        function queryUser()
        {
            $.post('<%=basePath%>/mydata/queryUser.do',
                {
                    id:$("#id").val()
                },
                function(result){
                    if(result){
                        layer.msg(result.name);
                    }else{
                        layer.msg(result);
                    }
                },
                'json'
            );
        }
        function addUser()
        {
            $.post('<%=basePath%>/mydata/addUser.do',
                function(result){
                    if(result){
                        layer.msg(result.code);
                    }else{
                        layer.msg(result);
                    }
                },
                'json'
            );
        }
    </script>

</div>
</body>
</html>
