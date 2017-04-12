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
                        <input type="text" class="form-control" style="width: 300px;" name="name" id="name" placeholder="请输入用户名称">
                    </div>
                    <span class=""> <%--pull-right --%>
                        <a onclick="goPageByAjax(1)" class="btn btn-primary">查询</a>
                     </span>
                </div>
                <hr>
                <table id="table" class="table table-bordered">
                    <thead>
                        <th width="10%">ID</th>
                        <th width="10%">姓名</th>
                        <th width="10%">年龄</th>
                        <th width="10%">创建时间</th>
                        <th width="10%">操作</th>
                    </thead>
                    <tbody>
                    <c:choose>
                        <c:when test="${page != null && fn:length(page.list) gt 0}">
                            <c:forEach items="${page.list}" var="it">
                                <tr>
                                    <td>${it.id}</td>
                                    <td>${it.name}</td>
                                    <td>${it.age}</td>
                                    <td>${it.createTime}</td>
                                    <td></td>
                                </tr>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <tr>
                                <td class="text-center danger" colspan="5">没有找到数据</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                    </tbody>
                </table>
                <c:if test="${page != null && fn:length(page.list) gt 0}">
                    <div class="pagination pull-right">
                            ${page.siAjaxPageHtml}
                    </div>
                </c:if>
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
        function goPageByAjax(pageNo)
        {
            $.post('<%=basePath%>/mydata/list.do',
                {
                    name:$("#name").val(),
                    pageNo:pageNo,
                    pageSize:10
                },
                function(result){
                    if(result && result.list){
                        $("#table tbody").html('');
                        for(var i = 0; i < result.list.length; i++)
                        {
                            var trHtml = '<tr>';
                            trHtml += '<td>'+result.list[i].id+'</td>';
                            trHtml += '<td>'+result.list[i].name+'</td>';
                            trHtml += '<td>'+result.list[i].age+'</td>';
                            trHtml += '<td>'+result.list[i].createTime+'</td>';
                            trHtml += '<td></td>';
                            trHtml += '<tr>';
                            $("#table tbody").append(trHtml);
                        }
                        $(".pagination").html(result.siAjaxPageHtml);
                    }else{
                        layer.msg(result.message);
                    }
                },
                'json'
            );
        }
    </script>

</div>
</body>
</html>
