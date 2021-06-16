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
        <h6>Din ordre er gennemført.</h6>
        <h6>Ordre nummer: #${requestScope.comfirmation_object.id} blev gennemført: ${requestScope.comfirmation_object.time}</h6>
        <h6>Carport: CPN${requestScope.confirmation_request.carport.id}  Mål:${requestScope.confirmation_request.carport.length}X${requestScope.confirmation_request.carport.width}.</h6>
        <h6>${requestScope.comfirmation_object.price}kr</h6>
        <h6>Tak for din bestilling ${requestScope.confirmation_request.user.name}. En bekræftelses mail er sendt til ${requestScope.confirmation_request.user.email} </h6>

    </jsp:body>


</t:genericpage>