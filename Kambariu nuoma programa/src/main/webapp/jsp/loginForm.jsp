﻿<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="tagHeader.jsp"%>
<head>
    <%@include file="stylesheetsHeader.jsp"%>
    <title>
        <%out.println(String.format("%s | %s", resources.getString("msg.login"), resources.getString("msg.appName")));%>
    </title>
</head>

<body>
    <%@include file="header.jsp"%>
    <br />
    <div class="form-container">
        <h3 class="form-header">
            <%out.println(resources.getString("msg.loginMessage"));%>
        </h3>
        <form:form action="/login" method="POST" commandName="loginBean">
            <table class="form-container">
                <tr>
                    <td class="form-label">
                        <%out.println(resources.getString("msg.username"));%>
                    </td>
                    <td class="form-entry">
                        <form:input class="form-entry" type="text" path="username"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label">
                        <%out.println(resources.getString("msg.password"));%>
                    </td>
                    <td class="form-entry">
                        <form:input class="form-entry" type="password" path="password"/>
                    </td>
                </tr>
            </table>
            <h4 class="failed">
                <c:if test="${errorMessage != null}">
                    <c:out value="${errorMessage}" />
                </c:if>
            </h4>
            <div class="form-bottom">
                <input type="submit" value="<%out.println(resources.getString("msg.submitLogin"));%>"/>
            </div>
        </form:form>
    </div>
    <%@include file="footer.jsp"%>
</body>
</html>

