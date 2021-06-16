<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:genericpage>
    <jsp:attribute name="header">
    </jsp:attribute>
    <jsp:attribute name="footer">
        <c:set var="addHomeLink" value="${false}" scope="request"/>
    </jsp:attribute>

    <jsp:body>


        <c:if test="${sessionScope.role == 'employee' }">
            <h5>Admin email: ${sessionScope.email} </h5>
            <p><a href="${pageContext.request.contextPath}/fc/viewrequestspage">process requests as admin</a>
        </c:if>

        <c:if test="${sessionScope.role == 'employee' }">
            <h5> On the OrderPage you can view all orders and you can general information about the orders in the system.</h5>
            <h5> ** there should be a list of usefull information. And there should be a link to a more in depth page </h5>
            <h5> You can access information about specific orders by clicking them </h5>
            <p><a href="${pageContext.request.contextPath}/fc/vieworderspage">Go to OrderPage </a>
        </c:if>

        <c:if test="${sessionScope.role == 'employee' }">
            <h5> On the UserPage you can view all users in the system. You access the orderhistory of the a user by clicking them</h5>
            <p><a href="${pageContext.request.contextPath}/fc/viewuserpage">Go to UserPage</a>
        </c:if>


        <c:if test="${sessionScope.Selected_Carport != null}">
            <h7> Du har lige tilføjet en carport til din kurv. Gå til 'view my basket' i navigations bjælken for at se din kurv.</h7><br>
            <h7> Du kan max have 1 carport i din kurv.</h7><br>
        </c:if>

        <c:if test="${requestScope.request_customer != null}">
            <h7> Du har lige oprettet en forespørgsel. Gå til 'requests' i navigations bjælken for at se information on din request.</h7><br>
            <h7> Du kan ikke oprette en nu forespørgsel før denne request er blevet bearbejdet.</h7><br>
            <h7> Du kan altid ringe for at ændre i din request eller for general hjælp.</h7><br>
        </c:if>


        <c:if test="${sessionScope.role == 'customer' }">
            <h6> MANGLER** Carport dropdown menu </h6>
            <p><a href="${pageContext.request.contextPath}/fc/standardcarportpage">med flat tag</a><br>
            <p><a href="${pageContext.request.contextPath}/fc/standardcarportpage">med rejst tag</a><br>
            <p><a href="${pageContext.request.contextPath}/fc/sendrequestpage">CARPORT EFTER EGNE MÅL</a><br>
        </c:if>




        <c:if test="${requestScope.error != null }">
            <p style="color:red">
                    ${requestScope.error}
            </p>
        </c:if>

        <c:if test="${not empty param.msg}">
            <p style="font-size: large">${param.msg}</p>
        </c:if>



    </jsp:body>
</t:genericpage>