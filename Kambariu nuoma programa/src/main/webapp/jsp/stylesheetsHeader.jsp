<link href="/resources/css/variables.css" rel="stylesheet" media="screen"/>
<link href="/resources/css/menu.css" rel="stylesheet" media="screen"/>
<link href="/resources/css/link.css" rel="stylesheet" media="screen"/>
<link href="/resources/css/document.css" rel="stylesheet" media="screen"/>
<link href="/resources/css/form.css" rel="stylesheet" media="screen"/>
<link href="/resources/css/table.css" rel="stylesheet" media="screen"/>
<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
    $(function () {
        $('#datepicker').datepicker({ dateFormat: 'yy-mm-dd' });
        $("#datepicker").datepicker();
    });
</script>
<script>
    $(function () {
        $('#datepicker2').datepicker({ dateFormat: 'yy-mm-dd' });
        $("#datepicker2").datepicker();
    });
</script>
<%@ page language="java" import="java.util.*" %>
<%@ page import = "java.util.ResourceBundle" %>
<%@ page import="kambariu_nuoma.UTF8Control" %>
<% ResourceBundle resources = ResourceBundle.getBundle("messages", new UTF8Control()); %>