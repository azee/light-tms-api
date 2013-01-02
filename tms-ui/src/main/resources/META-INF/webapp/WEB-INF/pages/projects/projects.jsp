<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <jsp:include page="../application/header.jsp">
        <jsp:param name="title" value="Projects"/>
    </jsp:include>

    <jsp:include page="../application/includes.jsp" />

    <jsp:include page="../application/navbar.jsp">
        <jsp:param name="active" value="Projects"/>
    </jsp:include>

    <div id="content" class="container">
        <h2>Projects</h2>
    </div>

    <jsp:include page="../application/footer.jsp" />