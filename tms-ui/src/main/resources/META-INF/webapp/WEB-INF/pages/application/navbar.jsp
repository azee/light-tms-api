<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
	    <div class="container">
		    <a href="/" class="brand">Light Tms</a>
			<div class="nav-collapse">
			    <ul class="nav">
                    <c:choose>
                        <c:when test="${param.active == 'Suites'}">
                            <li class="active">
                        </c:when>
                        <c:otherwise>
                            <li>
                        </c:otherwise>
                    </c:choose>
                            <a href="/suites">Suites</a>
                        </li>
                    <c:choose>
                        <c:when test="${param.active == 'Projects'}">
                            <li class="active">
                        </c:when>
                        <c:otherwise>
                            <li>
                        </c:otherwise>
                    </c:choose>
                            <a href="/projects">Projects</a>
                	    </li>
                </ul>
		    </div>
	    </div>
	</div>
</div>

