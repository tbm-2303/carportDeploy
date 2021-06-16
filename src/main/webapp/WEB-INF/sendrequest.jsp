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
        <div>

            <form id="form" method="post" action="${pageContext.request.contextPath}/fc/sendRequestCommand">

                <div class="row">

                    <div class="col">
                        <div class="form-group">
                            <label class="form-check-label" for="length">Length:</label>
                            <select class="form-control" name="length" id="length">
                                <c:forEach var="var" begin="2400" end="7800" step="300">
                                    <option value="${var}">${var}cm</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>


                    <div class="col">
                        <div class="form-group">
                            <label class="form-check-label" for="width"> Width</label>
                            <select class="form-control" name="width" id="width">
                                <c:forEach var="var" begin="2400" end="7500" step="300">
                                    <option value="${var}">${var}cm</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                </div>


                <div class="col">
                    <div class="form-group">
                        <label class="form-check-label" for="shed_length">Shed Length:</label>
                        <select class="form-control" name="shed_length" id="shed_length">
                            <option value="0">No Shed</option>
                            <c:forEach var="var" begin="1500" end="6900" step="300">
                                <option value="${var}">${var}cm</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>


                <div class="col">
                    <div class="form-group">
                        <label class="form-check-label" for="shed_width">Shed Width:</label>
                        <select class="form-control" name="shed_width" id="shed_width">
                            <option value="0">No Shed</option>
                            <c:forEach var="var" begin="2100" end="7200" step="300">
                                <option value="${var}">${var}cm</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>


                <div>
                    <button type="submit" class="btn btn-lg btn btn-outline-success">send forespørgsel</button>
                </div>
            </form>
        </div>


    </jsp:body>
</t:genericpage>>