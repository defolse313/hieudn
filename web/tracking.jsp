<%-- 
    Document   : tracking
    Created on : Oct 28, 2020, 7:44:15 PM
    Author     : hp
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Booking History</h1>
        <form action="TrackHistory" method="POST">
            <input type="date" name="txtTrackDate" required=""/>
            <button type="submit" name="acion">Search</button>
        </form>
        <br/>
        <form action="Search" method="POST">
            <input type="submit" value="Back to Home"/>
        </form> 
        <br/>
        <c:if test="${not empty requestScope.HISTORY}" var="khoi">
            <table border="1">
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Book Id</th>
                        <th>Book Time</th>
                        <th>Total Price</th>
                        <th>Coupon</th>
                        <th>Remove Booking</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="h" items="${requestScope.HISTORY}" varStatus="counter">
                        <tr>
                            <td style="text-align:center">
                                ${counter.count}
                            </td>
                            <td style="text-align:center">
                                ${h.bookId}
                            </td>
                            <td style="text-align:center">
                                ${h.bookTime}
                            </td>
                            <td style="text-align:center">
                                ${h.totalPrice}
                            </td>
                            <td style="text-align:center">
                                ${h.couponId.discountValue}
                            </td>
                            <td>
                                <form id="del" action="RemoveBooking" method="POST">
                                    <input type="hidden" name="txtIdBookRemove" value="${h.bookId}"/>
                                    <button type="button" name="RemoveCart" onclick="checkDel()">Remove</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
        </table>
        <c:if test="${!khoi}">
            No History Found.
        </c:if>
        <script>
            function checkDel() {
                let confirmed = confirm("Do you really want to remove this from booking list?");
                if (confirmed) {
                    document.getElementById("del").submit();
                }
            }
        </script>
    </body>
</html>
