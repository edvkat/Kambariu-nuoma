﻿<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<html>
<head>
    <%@include file="stylesheetsHeader.jsp"%>
    <title>
        <%out.println(String.format("%s | %s", resources.getString("msg.index"), resources.getString("msg.appName")));%>
    </title>
</head>
<body>
    <%@include file="header.jsp"%>
    <%@include file="footer.jsp"%>
</body>
</html>
