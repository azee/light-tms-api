<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Login page | Hello World Struts application in Eclipse</title>
    </head>
    <body>
        <h1>Login</h1>
        <s:form action="LoginAction">
		    <s:textfield name="username" label="Username" />
		    <s:password name="password" label="Password" />
		    <s:submit />
	    </s:form>
    </body>
</html>