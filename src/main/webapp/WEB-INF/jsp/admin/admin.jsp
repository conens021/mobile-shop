<%@include file="top.jsp" %>

<!--Start products -->
<div class="freshHeader"><h2>Orders</h2></div>
<div class="orders"> 
    <table id="customers">
        <tr>
            <th>Order ID</th>
            <th>Customer</th>
            <th>Customer address</th>
            <th>Customer city</th>
            <th>Customer email</th>
            <th>Order time</th>
            <th>Order total</th>

        </tr>
        <c:forEach items = "${orders}" var = "o">
            <tr> 
                <td><a href="order?orderId=${o.getOrderId()}">${o.getOrderId()} </a></td>                               

                <td><a href="order?orderId=${o.getOrderId()}">${o.getCustomerId().getFirstName()} ${o.getCustomerId().getLastName()}</a></td>
                <td><a href="order?orderId=${o.getOrderId()}">${o.getCustomerId().getAddress()}</a></td>
                <td><a href="order?orderId=${o.getOrderId()}">${o.getCustomerId().getCity()}</a></td>
                <td><a href="order?orderId=${o.getOrderId()}">${o.getCustomerId().getEmail()}</a></td>
                <td><a href="order?orderId=${o.getOrderId()}">${o.getOrderTime()}</a></td>
                <td><a href="order?orderId=${o.getOrderId()}">${o.getOrderTotalPrice()}</a></td>

            </tr>  
        </c:forEach>	 


    </table>
</div>
<%@include file="end.jsp" %>