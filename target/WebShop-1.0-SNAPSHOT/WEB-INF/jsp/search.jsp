<%@include file="begin.jsp" %>
<!--Start main-content -->
<div class="main-content">

    <!--Start products -->
    <div class="freshHeader"><h2>Models</h2></div>

    <c:if test = "${products.isEmpty() || products == null}">
        <div class="products">
            <h4 id='noContentHeader'>Sorry,no models from this query...</h4>
        </div>
    </c:if>
    <div class="products">
    <c:forEach items = "${products}" var = "f">
        <div class="product">
            <div class="productImg"><img src="resources/picts/${f.getProductPicture()}" ></div>
            <div class="productContent">
                <h3>${f.getProductName()}</h3><span style="margin-top:0;">Price : $${f.getProductPrice()}</span><br/>

                <form method="post" action="search">
                        <input type="hidden" name="productID" value="${f.getProductId()}"/>
                        <input id="buyButton" type="submit" value="BUY NOW">
                </form>
            </div>
        </div>
    </c:forEach>	      
</div>
<!--ENd products -->
</div>
<!--End main-content -->

<%@include  file="end.jsp" %>