<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ include file="tagHeader.jsp"%>
<html>
<head>
    <%@include file="stylesheetsHeader.jsp"%>
    <title>
        <%out.println(String.format("%s | %s", resources.getString("msg.profile"), resources.getString("msg.appName")));%>
    </title>
</head>
<body>
    <%@include file="header.jsp"%>
    <br />
    <div class="form-container">
        <h3 class="form-header">
            <%out.println(resources.getString("msg.registerMessage"));%>
        </h3>
        <form:form action="/register" method="POST" commandName="newUser">
            <table class="form-container">
                <tr>
                    <td class="form-label">
                        <%out.println(resources.getString("msg.email"));%>
                    </td>
                    <td class="form-entry">
                        <form:input class="form-entry" type = "text" path = "email"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label">
                        <%out.println(resources.getString("msg.username"));%>
                    </td>
                    <td class="form-entry">
                        <form:input class="form-entry" type="text" path="login"/>
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
                <tr>
                    <td class="form-label">
                        <%out.println(resources.getString("msg.firstName"));%>
                    </td>
                    <td class="form-entry">
                        <form:input class="form-entry" type="text" path="firstName"/>
                    </td>
                </tr>
                <tr>
                    <td class="form-label">
                        <%out.println(resources.getString("msg.lastName"));%>
                    </td>
                    <td class="form-entry">
                        <form:input class="form-entry" type="text" path="lastName"/>
                    </td>
                </tr>
                 <tr>
                    <td class="form-label">
                        <%out.println(resources.getString("msg.livingLocation"));%>
                    </td>
                    <td class="form-entry">
                        <form:input class="form-entry" type="text" path="livingLocation"/>
                    </td>
                </tr>
            </table>
            <h4 class="failed">
                <c:if test="${errorMessage != null}">
                    <c:out value="${errorMessage}" />
                </c:if>
            </h4>
            <div class="form-bottom">
                <input type="submit" value=<%out.println(resources.getString("msg.submitRegister"));%>">
            </div>
        </form:form>
    </div>
    <%@include file="footer.jsp"%>
</body>
</html>

