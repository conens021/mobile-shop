<%@include file="top.jsp" %>

<!--Start products -->
<div class="freshHeader"><h2>Order ${order.getOrderId()}</h2></div>
<div class="orders"> 
    <table id="orderTable">

        <tr>
            <th style="">Customer name</th>
            <td>${order.getCustomerId().getFirstName()} ${order.getCustomerId().getLastName()}</td>
        </tr>
        <tr>
            <th style="">Customer address</th>
            <td>${order.getCustomerId().getAddress()}</td>
        </tr><tr>
            <th style="">Customer city</th>
            <td>${order.getCustomerId().getCity()}</td>
        </tr><tr>
            <th style="">Customer email</th>
            <td>${order.getCustomerId().getEmail()}</td>
        </tr><tr>
            <th style="">Customer phone number</th>
            <td>${order.getCustomerId().getPhoneNumber()}</td>
        </tr>
        <tr>
            <th style="">Customer gender</th>
            <td>${order.getCustomerId().getGender()}</td>
        </tr>
        <tr>
            <th style="">Customer note</th>
            <td>${order.getCustomerId().getOrderNote()}</td>
        </tr>
        <tr>
            <th style="">Products</th>
            <td>
                <ul style="list-style-type: none">
                    <c:forEach items = "${pol}" var = "p">
                        <li style="margin: 1em 0">${p.getProductId().getProductName()} (x${p.getProdQuant()})</li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
        <tr>
            <th style="">Order time</th>
            <td>${order.getOrderTime()}</td>
        </tr>
        <tr>
            <th style="">Order total</th>
            <td>$${order.getOrderTotalPrice()}</td>
        </tr>
        
    </table>
</div>
<%@include file="end.jsp" %>