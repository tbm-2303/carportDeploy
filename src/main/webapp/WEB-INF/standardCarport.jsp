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
        Her kan du se alle standard carporte. Hvis du vil se mere om en carport så tryk på billedet. --->carportPage.jsp
        <c:if test="${sessionScope.user.role == 'customer'}">

            <c:if test="${not empty requestScope.carportList_standard}">
                <h4>Standard Carport </h4><br>
                <c:forEach var="carport" items="${requestScope.carportList_standard}" varStatus="status">
                    <img class="img-fluid w-25" src="<c:url value='/IMAGE/ProductPage${carport.id}.png'/>"
                         alt="carport" type="submit"/><br>
                    ID:${carport.id}<br>
                    Price:${carport.price}<br>
                    Dimensions:${carport.width}cm x ${carport.length}cm<br>
                    <c:if test="${carport.HasShed()== true}">
                        Shed Dimensions:${carport.shed_width}cm x ${carport.shed_length}cm<br>
                    </c:if>
                    Standard INFO:skal skrives her<br>
                    <form action="${pageContext.request.contextPath}/fc/sendrequest_standard" method="post">
                        <button type="submit" class=" btn btn-danger" name="getCarportID" id="getCarportID"
                                value="${carport.id}">tilføj til kurv
                        </button>
                    </form>



                </c:forEach>
            </c:if>
        </c:if>



            _______________________________________________________________________________________________________________________________________________________________________________________________
            <h6> CARPORT EFTER EGNE MÅL</h6>
            <p><a href="${pageContext.request.contextPath}/fc/sendrequestpage">order a custom carport</a>
            _______________________________________________________________________________________________________________________________________________________________________________________________



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