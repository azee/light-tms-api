<%@ page contentType="text/html;charset=UTF-8" language="java" %>

    <jsp:include page="../application/header.jsp">
        <jsp:param name="title" value="Suites"/>
    </jsp:include>

    <jsp:include page="../application/includes.jsp" />

    <jsp:include page="../application/navbar.jsp">
        <jsp:param name="active" value="Suites"/>
    </jsp:include>

    <div id="content" class="container">
        <h2>Suites</h2>
    </div>

    <jsp:include page="../application/footer.jsp" />