<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>主页</title>
    <style type="text/css">
        td {
            text-align: center;
        }

        .td2 {
            text-align: right;
        }

        .table1 {
            border: 1px solid #ddd;
            width: 900px;
        }

        thead {
            background-color: lightblue;
        }
    </style>
</head>
<body>
<%--根据login返回的session 用户名--%>
欢迎你：${currentUser}
<br>
<%-- 当前用户：${pageScope.currentUser}<!-- (只能在同一个页面中使用) --> <br>
当前用户：${requestScope.currentUser}-${roleName} 有重定向数据就访问不到<br>
当前用户：${sessionScope.currentUser}-${roleName} 可以使用<br> --%>
<hr>
<a href="add"> 添加用户</a><br>
<%--获取PageBean<User>判断返回值是否为空--%>
<c:if test="${empty requestScope.pagemsg}">
    没有任何用户信息！
</c:if>
<%--很严谨 非空才展示列表--%>
<c:if test="${!empty requestScope.pagemsg}">
    <table border="1" cellpadding="10" cellspacing="0" class="table1">
        <thead>
            <%--上面一排字段名--%>
        <tr>
            <td>编号</td>
            <td>用户名</td>
            <td>密码</td>
            <td>邮件</td>
            <td>联系电话</td>
            <td>职位</td>
            <td>Edit</td>
            <td>Delete</td>
        </tr>
        </thead>
            <%--循环遍历 lists也就是每个users--%>
        <c:forEach items="${requestScope.pagemsg.lists}" var="u">
            <tr>
                <th>${u.id }</th>
                <th>${u.username }</th>
                <th>${u.password }</th>
                <th>${u.email }</th>
                <th>${u.phone }</th>
                <th>${u.rolename }</th>
                    <%--url链接和平常笨方法一样 用？ id是动态获取的users--%>
                <th><a href="edit?id=${u.id}">Edit</a></th>
                    <%--删除同理  弹出一个对话框--%>
                <th><a href="delete?id=${u.id}" onclick="return confirm('确定要删除吗')">Delete</a></th>
            </tr>
        </c:forEach>
    </table>
</c:if>

<table border="0" cellspacing="0" cellpadding="0" width="900px">
    <tr>
        <%--这个就是最后一行--%>
        <td class="td2">
            <%--获取当前页和/总页数--%>
            <span>第${requestScope.pagemsg.currPage }/ ${requestScope.pagemsg.totalPage}页</span>&nbsp;&nbsp;
            <span>总记录数：${requestScope.pagemsg.totalCount }&nbsp;&nbsp;每页显示:${requestScope.pagemsg.pageSize}</span>&nbsp;&nbsp;
            <span>
	   <%--获取总条目数--%>
	   <%--如果当前页不是首页才去添加首页--%>
       <c:if test="${requestScope.pagemsg.currPage != 1}">
           <a href="${pageContext.request.contextPath }/main?currentPage=1">[首页]</a>&nbsp;&nbsp;
           <a href="${pageContext.request.contextPath }/main?currentPage=${requestScope.pagemsg.currPage-1}">[上一页]</a>&nbsp;&nbsp;
       </c:if>
       <%--如果当前页不是最后一页才去展示--%>
       <c:if test="${requestScope.pagemsg.currPage != requestScope.pagemsg.totalPage}">
           <a href="${pageContext.request.contextPath }/main?currentPage=${requestScope.pagemsg.currPage+1}">[下一页]</a>&nbsp;&nbsp;
           <a href="${pageContext.request.contextPath }/main?currentPage=${requestScope.pagemsg.totalPage}">[尾页]</a>&nbsp;&nbsp;
       </c:if>
   </span>
        </td>
    </tr>
</table>
</body>
</html>