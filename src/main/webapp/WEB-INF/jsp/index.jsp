<%@include file="begin.jsp" %>
<!--Start main-content -->
<div class="main-content">

   
    <!--Start products -->
    <div class="freshHeader"><h2>Latest Models</h2></div>
    <div class="main-nested">
        <div class="products">

            <c:forEach items = "${fresh}" var = "f">
                <div class="product">
                    <div class="productImg"><img src="resources/picts/${f.getProductPicture()}" ></div>
                    <div class="productContent">
                        <h3>${f.getProductName()}</h3><span style="margin-top:0;">Price : $${f.getProductPrice()}</span><br/>

                        <form method="post" action="">
                            <input type="hidden" name="productID" value="${f.getProductId()}"/>
                            <input id="buyButton" type="submit" value="BUY NOW">
                        </form>
                    </div>
                </div>
            </c:forEach>

        </div>
        <!--ENd products -->
        <c:if test = "${!freshProducts.isEmpty()}">
            <div class="show-more">
                <form method="get" action="loadmore">
                    <input  class="show-more-form" type="submit" value="load more" />
                </form>
            </div>
        </c:if>
    </div>
</div>
<!--End main-content -->


<%@include  file="end.jsp" %>